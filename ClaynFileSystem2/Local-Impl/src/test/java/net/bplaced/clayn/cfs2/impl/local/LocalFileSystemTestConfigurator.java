package net.bplaced.clayn.cfs2.impl.local;

import java.io.File;
import java.io.IOException;
import net.bplaced.clayn.cfs2.api.VirtualFileSystem;
import net.bplaced.clayn.cfs2.test.FileSystemTestConfigurator;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class LocalFileSystemTestConfigurator implements FileSystemTestConfigurator
{

    @Override
    public VirtualFileSystem getFileSystem() throws IOException
    {
        TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        File rootFile = folder.newFolder();
        VirtualFileSystem fs = new LocalFileSystem(rootFile);
        return fs;
    }

}
