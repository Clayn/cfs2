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
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import net.bplaced.clayn.cfs2.api.VirtualFile;
import net.bplaced.clayn.cfs2.api.opt.CreateOption;
import net.bplaced.clayn.cfs2.api.util.PathUtil;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public class LocalDirectory implements VirtualDirectory
{

    private final LocalDirectory parent;
    private final File localFile;

    public LocalDirectory(LocalDirectory parent, File localFile)
    {
        this.parent = parent;
        this.localFile = localFile;
    }

    @Override
    public VirtualDirectory changeDirectory(String path)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<VirtualDirectory> listDirectories()
    {
        File[] content = localFile.listFiles();
        if (content == null)
        {
            return Collections.emptyList();
        }
        return Arrays.stream(content).filter(Objects::nonNull)
                .filter(File::isDirectory).filter(File::exists)
                .map((f) -> new LocalDirectory(this, f)).collect(
                Collectors.toList());
    }

    @Override
    public List<VirtualFile> listFiles()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> listContent()
    {
        String[] names = localFile.list();
        if (names == null)
        {
            return Collections.emptyList();
        }
        return Arrays.asList(names);
    }

    @Override
    public VirtualFile getFile(String name)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VirtualDirectory getParent()
    {
        return parent;
    }

    @Override
    public void create(CreateOption option, CreateOption... additonal) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create() throws IOException
    {
        create(CreateOption.SKIP_IF_EXIST);
    }

    @Override
    public boolean exists()
    {
        return localFile.exists();
    }

    @Override
    public String getName()
    {
        return localFile.getName();
    }

    @Override
    public String getLocation()
    {
        if (isRoot())
        {
            return "/";
        }
        return PathUtil.normalizePath(parent.getPath());
    }

}
