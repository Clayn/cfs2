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
package net.bplaced.clayn.cfs2.api.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class PathUtilTest
{

    public PathUtilTest()
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

    /**
     * Test of cleanPath method, of class PathUtil.
     */
    @Test
    public void testCleanPath()
    {
        String path, expected;
        path = "/some/path";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "/some//path";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "/some///path";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "/some/path  ";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "/some/path  /";
        expected = "/some/path  /";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "///some//path";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "/some/path";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "  /some/path";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
        path = "  /some/path   ";
        expected = "/some/path";
        Assert.assertEquals(expected, PathUtil.cleanPath(path));
    }

    @Test(expected = NullPointerException.class)
    public void testCleanPathNull()
    {
        PathUtil.cleanPath(null);
    }

    /**
     * Test of normalizePath method, of class PathUtil.
     */
    @Test
    public void testNormalizePath()
    {
        String path, expected;
        path = "/some//path    ";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "/some//path  /";
        expected = "/some/path  /";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
    }

    @Test(expected = NullPointerException.class)
    public void testNormalizePathNull()
    {
        PathUtil.normalizePath(null);
    }

    @Test
    public void testNormalizePathClean()
    {
        String path, expected;
        path = "/some//path";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "/some///path";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "/some/path  ";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "/some/path  /";
        expected = "/some/path  /";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "///some//path";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "/some/path";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "  /some/path/";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
        path = "  /some/path   ";
        expected = "/some/path/";
        Assert.assertEquals(expected, PathUtil.normalizePath(path));
    }

}
