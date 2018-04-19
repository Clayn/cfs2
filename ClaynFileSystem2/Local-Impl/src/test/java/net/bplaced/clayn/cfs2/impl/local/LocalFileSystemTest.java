package net.bplaced.clayn.cfs2.impl.local;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.bplaced.clayn.cfs2.api.VirtualFileSystem;
import net.bplaced.clayn.cfs2.api.util.FileSystemProvider;
import net.bplaced.clayn.cfs2.test.FileSystemTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class LocalFileSystemTest extends FileSystemTest
{

    private TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void prepareClass()
    {
        LocalFileSystem.installProvider();
    }

    @Override
    public void setUpConfigurator()
    {
        configurator = new LocalFileSystemTestConfigurator();
    }

    @Before
    public void prepare() throws IOException
    {
        folder.create();
    }

    @Test
    public void testProviderMethodString() throws IOException
    {
        String tmp = folder.newFolder().getAbsolutePath();
        Map<String, Object> param = new HashMap<>();
        param.put("root", tmp);
        VirtualFileSystem vfs = FileSystemProvider.getInstance().getProvider(
                "cfs2-local").apply(param);
        Assert.assertNotNull(vfs);
    }

    @Test
    public void testProviderMethodFile() throws IOException
    {
        File tmp = folder.newFolder();
        Map<String, Object> param = new HashMap<>();
        param.put("root", tmp);
        VirtualFileSystem vfs = FileSystemProvider.getInstance().getProvider(
                "cfs2-local").apply(param);
        Assert.assertNotNull(vfs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProviderMethodNoValueSet()
    {
        Map<String, Object> param = new HashMap<>();
        VirtualFileSystem vfs = FileSystemProvider.getInstance().getProvider(
                "cfs2-local").apply(param);
        Assert.assertNotNull(vfs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProviderMethodNullValueSet()
    {
        Map<String, Object> param = new HashMap<>();
        param.put("root", null);
        VirtualFileSystem vfs = FileSystemProvider.getInstance().getProvider(
                "cfs2-local").apply(param);
        Assert.assertNotNull(vfs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProviderMethodWrongRootType()
    {
        Map<String, Object> param = new HashMap<>();
        param.put("root", new Object());
        VirtualFileSystem vfs = FileSystemProvider.getInstance().getProvider(
                "cfs2-local").apply(param);
        Assert.assertNotNull(vfs);
    }
}
