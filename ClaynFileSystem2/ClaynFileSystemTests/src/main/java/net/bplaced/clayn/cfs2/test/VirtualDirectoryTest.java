package net.bplaced.clayn.cfs2.test;

import java.io.IOException;
import java.util.List;
import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public abstract class VirtualDirectoryTest extends BaseFileSystemTest
{

    private VirtualDirectory root;

    @Before
    public void initRoot()
    {
        root = fileSystem.getRoot();
    }

    @Test
    public void testRootPath()
    {
        Assert.assertEquals("Root path is not '/'", "/", root.getPath());
    }

    @Test
    public void testRootParent()
    {
        Assert.assertNull("The root directory must not have any parent",
                root.getParent());
    }

    @Test
    public void testRootLocation()
    {
        Assert.assertEquals("Root path is not '/'", "/", root.getLocation());
    }

    @Test
    public void testRootName()
    {
        Assert.assertEquals("Root path is not '/'", "/", root.getName());
    }

    @Test
    public void testChangeDirectory()
    {
        String name = testName.getMethodName();
        VirtualDirectory dir = root.changeDirectory(name);
        Assert.assertFalse(dir.isRoot());
        Assert.assertTrue(dir.getParent().isRoot());
        Assert.assertEquals(name, dir.getName());
        Assert.assertEquals("/" + name, dir.getPath());
    }

    @Test
    public void testChangeDirectoryDeep()
    {
        String first = testName.getMethodName();
        String second = first + 2;
        VirtualDirectory dir = root.changeDirectory(first + "/" + second);
        Assert.assertFalse(dir.isRoot());
        Assert.assertFalse(dir.getParent().isRoot());
        Assert.assertEquals(second, dir.getName());
        Assert.assertEquals(
                "Paths don't match. Expected '" + ("/" + first + "/" + second) + "' got '" + dir.getPath() + "'",
                "/" + first + "/" + second, dir.getPath());
    }

    @Test
    public void testListDirectories() throws IOException
    {
        List<VirtualDirectory> directories = root.listDirectories();
        Assert.assertTrue(directories.isEmpty());
        VirtualDirectory dir = root.changeDirectory("Dir");
        directories = root.listDirectories();
        Assert.assertTrue(directories.isEmpty());
        dir.mkDirs();
        directories = root.listDirectories();
        Assert.assertFalse(directories.isEmpty());
        Assert.assertEquals(dir.getName(), directories.get(0).getName());
    }

}
