package net.bplaced.clayn.cfs2.test;

import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public abstract class VirtualDirectoryTest extends BaseFileSystemTest
{

    @Test
    public void testRootPath()
    {
        VirtualDirectory root = fileSystem.getRoot();
        Assert.assertEquals("Root path is not '/'", "/", root.getPath());
    }

    @Test
    public void testRootParent()
    {
        VirtualDirectory root = fileSystem.getRoot();
        Assert.assertNull("The root directory must not have any parent",
                root.getParent());
    }

    @Test
    public void testRootLocation()
    {
        VirtualDirectory root = fileSystem.getRoot();
        Assert.assertEquals("Root path is not '/'", "/", root.getLocation());
    }

    @Test
    public void testRootName()
    {
        VirtualDirectory root = fileSystem.getRoot();
        Assert.assertEquals("Root path is not '/'", "/", root.getName());
    }
}
