package net.bplaced.clayn.cfs2.api;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public interface DirectoryChangeListener
{

    public void onDirectoryCreated(VirtualDirectory parent,
            VirtualDirectory directory);

    public void onDirectoryDeleted(VirtualDirectory parent,
            VirtualDirectory directory);

    public void onDirectoryModified(VirtualDirectory parent,
            VirtualDirectory directory);
}
