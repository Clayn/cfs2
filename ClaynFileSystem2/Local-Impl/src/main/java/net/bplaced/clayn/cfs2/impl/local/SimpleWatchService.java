package net.bplaced.clayn.cfs2.impl.local;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import net.bplaced.clayn.cfs2.api.VirtualFile;
import net.bplaced.clayn.cfs2.api.VirtualWatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class SimpleWatchService extends VirtualWatchService
{

    private static final Logger LOG = LoggerFactory.getLogger(
            SimpleWatchService.class);
    private static final int CREATE = 0;
    private static final int DELETE = 1;
    private static final int MODIFIE = 2;

    private final Set<String> cachedDirectories = new HashSet<>();
    private boolean active = false;
    private Thread watchThread;
    private final LocalDirectory watchedDirectory;

    SimpleWatchService(LocalDirectory watchedDirectory)
    {
        this.watchedDirectory = watchedDirectory;
        this.cachedDirectories.addAll(
                watchedDirectory.listDirectories().stream().map(
                        VirtualDirectory::getName).collect(Collectors.toList()));
    }

    @Override
    public synchronized boolean isActive()
    {
        return active;
    }

    private VirtualFile translateFile(Path p)
    {
        String name = p.getFileName().toString();
        return watchedDirectory.getFile(name);
    }

    private VirtualDirectory translateDirectory(Path p)
    {
        String name = p.getFileName().toString();
        return watchedDirectory.changeDirectory(name);
    }

    private boolean isDirectory(Path p, int type)
    {
        String name = p.getFileName().toString();
        boolean dir = false;
        if (type != DELETE)
        {
            dir = watchedDirectory.listDirectories().stream().map(
                    VirtualDirectory::getName)
                    .anyMatch(name::equals);
            if (type == CREATE && dir)
            {
                cachedDirectories.add(name);
            }
        } else
        {
            dir = cachedDirectories.contains(name);
            cachedDirectories.remove(name);
        }
        return dir;
    }

    private void process(Path p, int type)
    {
        if (isDirectory(p, type))
        {
            processDirectory(p, type);
        } else
        {
            processFile(p, type);
        }
    }

    private void processDirectory(Path p, int type)
    {
        switch (type)
        {
            case CREATE:
                directoryListeners.forEach((lis) -> lis.onDirectoryCreated(
                        watchedDirectory,
                        translateDirectory(p)));
                break;
            case DELETE:
                directoryListeners.forEach((lis) -> lis.onDirectoryDeleted(
                        watchedDirectory,
                        translateDirectory(p)));
                break;
            case MODIFIE:
                directoryListeners.forEach((lis) -> lis.onDirectoryModified(
                        watchedDirectory,
                        translateDirectory(p)));
                break;
            default:
        }
    }

    private void processFile(Path p, int type)
    {
        switch (type)
        {
            case CREATE:
                fileListeners.forEach((lis) -> lis.onFileCreated(
                        watchedDirectory,
                        translateFile(p)));
                break;
            case DELETE:
                fileListeners.forEach((lis) -> lis.onFileDeleted(
                        watchedDirectory,
                        translateFile(p)));
                break;
            case MODIFIE:
                fileListeners.forEach((lis) -> lis.onFileModified(
                        watchedDirectory,
                        translateFile(p)));
                break;
            default:
        }
    }

    public void stop()
    {
        if (!isActive())
        {
            return;
        }
        active = false;
        if (watchThread != null)
        {
            LOG.debug("Interrupting the watch thread");
            watchThread.interrupt();
            watchThread = null;
        }

    }

    public void start() throws IOException
    {
        if (isActive())
        {
            return;
        }

        Path dir = watchedDirectory.getFile().getAbsoluteFile().toPath();
        WatchService service = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(service,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        active = true;
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    do
                    {
                        WatchKey key = service.take();
                        for (WatchEvent<?> event : key.pollEvents())
                        {
                            WatchEvent.Kind<?> kind = event.kind();
                            WatchEvent<Path> ev = (WatchEvent<Path>) event;
                            Path fileName = ev.context();
                            int type = -1;
                            if (kind == StandardWatchEventKinds.ENTRY_CREATE)
                            {
                                type = CREATE;
                            } else if (kind == StandardWatchEventKinds.ENTRY_DELETE)
                            {
                                type = DELETE;
                            } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY)
                            {
                                type = MODIFIE;
                            }
                            process(fileName, type);
                        }
                        boolean valid = key.reset();
                        if (!valid)
                        {
                            break;
                        }
                    } while (isActive());
                    LOG.debug("Stopping the watch thread");
                } catch (InterruptedException ex)
                {
                    if (isActive())
                    {
                        LOG.info("Stopped the watch thread for {}",
                                watchedDirectory.getName());
                    } else
                    {
                        LOG.error("Error occured while waiting for file events",
                                ex);
                    }
                }
            }
        }
        );
        t.setName("Directory watcher thread - " + watchedDirectory.getPath());
        t.setDaemon(
                true);
        t.start();
        watchThread = t;
    }

}
