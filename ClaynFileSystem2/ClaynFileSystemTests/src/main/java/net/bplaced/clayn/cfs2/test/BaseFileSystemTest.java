/*
 * The MIT License
 *
 * Copyright 2017 Clayn <clayn_osmato@gmx.de>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.bplaced.clayn.cfs2.test;

import net.bplaced.clayn.cfs2.api.VirtualFileSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public abstract class BaseFileSystemTest
{

    protected VirtualFileSystem fileSystem;
    protected FileSystemTestConfigurator configurator;

    @Rule
    public TestName testName = new TestName();

    @Before
    public final void internalBefore() throws Exception
    {
        if (configurator == null)
        {
            setUpConfigurator();
            Assert.assertNotNull("FileSystemConfigurator not set", configurator);
        }
        fileSystem = configurator.getFileSystem();
        verifyFileSystem();
        setUp();
    }

    private void verifyFileSystem()
    {
        Assert.assertNotNull("Filesystem must be set", fileSystem);
    }

    public void setUp()
    {

    }

    public abstract void setUpConfigurator();

    @Test
    public void testDifferentTestFileSystems() throws Exception
    {
        Assert.assertNotNull(fileSystem);
        VirtualFileSystem local = configurator.getFileSystem();
        Assert.assertNotNull(local);
        Assert.assertNotSame(fileSystem, local);
    }

}
