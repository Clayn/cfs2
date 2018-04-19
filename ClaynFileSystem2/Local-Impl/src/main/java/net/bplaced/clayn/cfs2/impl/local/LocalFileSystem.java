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
package net.bplaced.clayn.cfs2.impl.local;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import net.bplaced.clayn.cfs2.api.VirtualFileSystem;
import net.bplaced.clayn.cfs2.api.util.FileSystemProvider;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class LocalFileSystem implements VirtualFileSystem
{

    private final File rootFile;

    public LocalFileSystem(File rootFile)
    {
        this.rootFile = rootFile;
    }

    public LocalFileSystem(String rootFile)
    {
        this(new File(rootFile));
    }

    public LocalFileSystem()
    {
        this(new File(System.getProperty("user.dir")));
    }

    private static LocalFileSystem createByMap(Map<String, Object> parameter)
    {
        if (!parameter.containsKey("root"))
        {
            throw new IllegalArgumentException(
                    "Missing 'root' argument in the parameter map");
        }
        Object val = parameter.get("root");
        if (val == null)
        {
            throw new IllegalArgumentException(
                    "Missing 'root' argument in the parameter map");
        }
        if (val instanceof File)
        {
            return new LocalFileSystem((File) val);
        } else if (val instanceof String)
        {
            return new LocalFileSystem(val.toString());
        }
        throw new IllegalArgumentException(
                "'root' argument is neither file nor string");
    }

    @Override
    public VirtualDirectory getRoot()
    {
        return new LocalDirectory(null, rootFile);
    }

    @Override
    public Map<String, Class<?>> getProviderInformation()
    {
        Map<String, Class<?>> inf = new HashMap<>();
        inf.put("root", File.class);
        return inf;
    }

    public static void installProvider()
    {
        FileSystemProvider.getInstance().register("cfs2-local",
                LocalFileSystem::createByMap);
    }

}
