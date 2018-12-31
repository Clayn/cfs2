package net.bplaced.clayn.cfs2.api;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public interface DirectoryChangeListener
{

    public void onFileCreated(VirtualDirectory parent, VirtualFile file);

    public void onFileDeleted(VirtualDirectory parent, VirtualFile file);

    public void onFileModified(VirtualDirectory parent, VirtualFile file);
}
