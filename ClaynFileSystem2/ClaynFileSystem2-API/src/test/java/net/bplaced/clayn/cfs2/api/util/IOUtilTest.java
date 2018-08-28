/*
 * The MIT License
 *
 * Copyright 2018 Clayn <clayn_osmato@gmx.de>.
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
package net.bplaced.clayn.cfs2.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import net.bplaced.clayn.cfs2.api.VirtualFile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class IOUtilTest
{

    public IOUtilTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testClassAvailable()
    {
        Class<?> clazz = IOUtil.class;
    }

    @Test
    public void testPreventInstance()
    {
        try
        {
            IOUtil.class.newInstance();
            Assert.fail();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException ex)
        {
            LoggerFactory.getLogger(getClass()).info("", ex);
            System.out.println("" + ex);
        }
    }

    /**
     * Test of copy method, of class IOUtil.
     */
    @Test
    public void testCopy_3args() throws Exception
    {
        byte[] data = "Hello World".getBytes();
        int size = data.length / 2;
        ByteArrayInputStream in = Mockito.spy(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtil.copy(in, out, size);
        byte[] res = out.toByteArray();
        Assert.assertArrayEquals(data, res);
        Mockito.verify(in, Mockito.atLeast(2)).read(Mockito.any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCopy_3argsNegativeBuffer() throws Exception
    {
        byte[] data = "Hello World".getBytes();
        int size = data.length / 2;
        ByteArrayInputStream in = Mockito.spy(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtil.copy(in, out, -1024);
        byte[] res = out.toByteArray();
        Assert.assertArrayEquals(data, res);
        Mockito.verify(in, Mockito.atLeast(2)).read(Mockito.any());
    }

    @Test(expected = NullPointerException.class)
    public void testCopy_InputNull() throws Exception
    {
        byte[] data = "Hello World".getBytes();
        int size = data.length / 2;
        ByteArrayInputStream in = Mockito.spy(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtil.copy(null, out);
    }

    @Test(expected = NullPointerException.class)
    public void testCopy_OutputNull() throws Exception
    {
        byte[] data = "Hello World".getBytes();
        int size = data.length / 2;
        ByteArrayInputStream in = Mockito.spy(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtil.copy(in, null);
    }

    /**
     * Test of copy method, of class IOUtil.
     */
    @Test
    public void testCopy_InputStream_OutputStream() throws Exception
    {
        byte[] data = "Hello World".getBytes();
        ByteArrayInputStream in = Mockito.spy(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtil.copy(in, out);
        byte[] res = out.toByteArray();
        Assert.assertArrayEquals(data, res);
        Mockito.verify(in, Mockito.atLeast(1)).read(Mockito.any());
    }

    @Test
    public void testCopyVirtualFiles() throws IOException
    {
        byte[] data = "Hello World".getBytes();
        VirtualFile src = Mockito.mock(VirtualFile.class);
        VirtualFile dst = Mockito.mock(VirtualFile.class);
        ByteArrayInputStream in = Mockito.spy(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Mockito.when(src.openRead()).thenReturn(in);
        Mockito.when(dst.openWrite()).thenReturn(out);
        IOUtil.copy(src, dst);
        byte[] res = out.toByteArray();
        Assert.assertArrayEquals(data, res);
        Mockito.verify(src, Mockito.times(1)).openRead();
        Mockito.verify(dst, Mockito.times(1)).openWrite();
    }

}
