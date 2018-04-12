package net.bplaced.clayn.cfs2.impl.local;

import net.bplaced.clayn.cfs2.test.FileSystemTest;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class LocalFileSystemTest extends FileSystemTest
{

    @Override
    public void setUpConfigurator()
    {
        configurator = new LocalFileSystemTestConfigurator();
    }

}
