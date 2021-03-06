package net.bplaced.clayn.cfs2.man.samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import net.bplaced.clayn.cfs2.api.DirectoryChangeListener;
import net.bplaced.clayn.cfs2.api.FileChangeListener;
import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import net.bplaced.clayn.cfs2.api.VirtualFile;
import net.bplaced.clayn.cfs2.api.VirtualFileSystem;
import net.bplaced.clayn.cfs2.api.VirtualWatchService;
import net.bplaced.clayn.cfs2.impl.local.LocalFileSystem;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class WatchServiceSample extends CFSSample
{

    @Override
    public void executeSample() throws Exception
    {
        log.info("Preparing watch service sample");
        File f = new File(System.getProperty("user.dir"), "samples");
        f = new File(f, "WatchService");
        VirtualFileSystem vfs = new LocalFileSystem(f);
        VirtualDirectory root = vfs.getRoot();
        root.mkDirs();
        log.info("Filesystem created");
        log.info("Root is located at: {}", f);
        VirtualWatchService ws = root.enableWatchService();
        ws.addFileChangeListener(new FileChangeListener()
        {
            @Override
            public void onFileCreated(VirtualDirectory parent, VirtualFile file)
            {
                log.info("Created file: {}", file.getPath());
            }

            @Override
            public void onFileDeleted(VirtualDirectory parent, VirtualFile file)
            {
                log.info("Deleted file: {}", file.getPath());
            }

            @Override
            public void onFileModified(VirtualDirectory parent, VirtualFile file)
            {
                log.info("Modified file: {}", file.getPath());
            }
        });
        ws.addDirectoryChangeListener(new DirectoryChangeListener()
        {
            @Override
            public void onDirectoryCreated(VirtualDirectory parent,
                    VirtualDirectory directory)
            {
                log.info("Created directory: {}", directory.getPath());
            }

            @Override
            public void onDirectoryDeleted(VirtualDirectory parent,
                    VirtualDirectory directory)
            {
                log.info("Deleted directory: {}", directory.getPath());
            }

            @Override
            public void onDirectoryModified(VirtualDirectory parent,
                    VirtualDirectory directory)
            {
                log.info("Modified directory: {}", directory.getPath());
            }
        });
        log.info("Waiting for user input to exit the sample application");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        reader.readLine();
        log.info("Stopping the watch service");
        root.disableWatchService();
        log.info("Exiting the sample application");
    }

}
