package net.bplaced.clayn.cfs2.impl.local;

import net.bplaced.clayn.cfs2.test.VirtualDirectoryTest;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class LocalDirectoryTest extends VirtualDirectoryTest
{

    @Override
    public void setUpConfigurator()
    {
        configurator = new LocalFileSystemTestConfigurator();
    }

}
