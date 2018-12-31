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

    protected final List<DirectoryChangeListener> listeners = new ArrayList<>();

    public final void addListener(DirectoryChangeListener listener)
    {
        listeners.add(Objects.requireNonNull(listener));
    }

    public final void removeListener(DirectoryChangeListener listener)
    {
        listeners.remove(Objects.requireNonNull(listener));
    }

    public abstract boolean isActive();
}
