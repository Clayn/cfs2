package net.bplaced.clayn.cfs2.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public abstract class VirtualWatchService
{

    protected final List<FileChangeListener> fileListeners = new ArrayList<>();
    protected final List<DirectoryChangeListener> directoryListeners = new ArrayList<>();

    public final void addFileChangeListener(FileChangeListener listener)
    {
        fileListeners.add(Objects.requireNonNull(listener));
    }

    public final void removeFileChangeListener(FileChangeListener listener)
    {
        fileListeners.remove(Objects.requireNonNull(listener));
    }

    public final void addDirectoryChangeListener(
            DirectoryChangeListener listener)
    {
        directoryListeners.add(Objects.requireNonNull(listener));
    }

    public final void removeDirectoryChangeListener(
            DirectoryChangeListener listener)
    {
        directoryListeners.remove(Objects.requireNonNull(listener));
    }

    public abstract boolean isActive();
}
