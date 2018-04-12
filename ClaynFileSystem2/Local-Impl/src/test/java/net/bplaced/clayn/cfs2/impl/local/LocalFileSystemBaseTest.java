package net.bplaced.clayn.cfs2.impl.local;

import net.bplaced.clayn.cfs2.test.BaseFileSystemTest;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class LocalFileSystemBaseTest extends BaseFileSystemTest
{

    @Override
    public void setUpConfigurator()
    {
        configurator = new LocalFileSystemTestConfigurator();
    }

}
