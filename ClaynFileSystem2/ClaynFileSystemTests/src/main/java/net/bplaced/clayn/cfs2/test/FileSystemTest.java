package net.bplaced.clayn.cfs2.test;

import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import net.bplaced.clayn.cfs2.api.VirtualFileSystem;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public abstract class FileSystemTest extends BaseFileSystemTest
{

    @Test
    public void testGetRoot() throws Exception
    {
        VirtualDirectory root1 = fileSystem.getRoot();
        VirtualDirectory root2 = fileSystem.getRoot();
        Assert.assertEquals(
                "Filesystems returned root directory must be equals with multiple calls",
                root1, root2);
        Assert.assertEquals(
                "Filesystems returned root directory must be equals with multiple calls",
                root2, root1);
        VirtualFileSystem local = configurator.getFileSystem();
        VirtualDirectory localRoot = local.getRoot();
        Assert.assertNotEquals(
                "Different filesystems should have different root directories",
                root1, localRoot);
    }
}
