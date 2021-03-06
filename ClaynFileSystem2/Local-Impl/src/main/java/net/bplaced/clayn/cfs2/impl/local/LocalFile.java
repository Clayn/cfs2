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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import net.bplaced.clayn.cfs2.api.FileInformation;
import net.bplaced.clayn.cfs2.api.VirtualDirectory;
import net.bplaced.clayn.cfs2.api.VirtualFile;
import net.bplaced.clayn.cfs2.api.opt.CreateOption;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class LocalFile implements VirtualFile
{

    private final VirtualDirectory parent;
    private final File localFile;
    private boolean openWrite = false;
    private final AtomicInteger openRead = new AtomicInteger(0);

    public LocalFile(VirtualDirectory parent, File localFile)
    {
        this.parent = parent;
        this.localFile = localFile;
    }

    @Override
    public OutputStream openWrite() throws IOException, IllegalStateException
    {
        if (!exists())
        {
            throw new IOException("File '" + getPath() + "' does not exist");
        }
        synchronized (openRead)
        {
            if (openWrite)
            {
                throw new IllegalStateException(
                        "File is already open for writing");
            }
            if (openRead.get() != 0)
            {
                throw new IllegalStateException(
                        "File is already open for reading");
            }
            openWrite = true;
            return new FileOutputStream(localFile)
            {
                @Override
                public void close() throws IOException
                {
                    synchronized (openRead)
                    {
                        openWrite = false;
                    }
                    super.close();

                }
            };
        }
    }

    @Override
    public InputStream openRead() throws IOException, IllegalStateException
    {
        if (!exists())
        {
            throw new IOException("File '" + getPath() + "' does not exist");
        }
        synchronized (openRead)
        {
            if (openWrite)
            {
                throw new IllegalStateException(
                        "File is already open for writing");
            }
            openRead.incrementAndGet();
            return new FileInputStream(localFile)
            {
                @Override
                public void close() throws IOException
                {
                    synchronized (openRead)
                    {
                        openRead.decrementAndGet();
                    }
                    super.close();
                }

            };
        }
    }

    @Override
    public FileInformation getInformation()
    {
        if (!exists())
        {
            return new FileInformation(-1, -1, -1);
        }
        try
        {
            BasicFileAttributes attr = Files.readAttributes(localFile.toPath(),
                    BasicFileAttributes.class);
            return new FileInformation(attr.size(),
                    attr.creationTime().toMillis(),
                    attr.lastModifiedTime().toMillis());
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public VirtualDirectory getParent()
    {
        return parent;
    }

    @Override
    public void create(CreateOption option, CreateOption... additonal) throws IOException
    {
        Set<CreateOption> options = new HashSet<>(
                1 + (additonal == null ? 0 : additonal.length));
        if(option!=null) {
            options.add(option);
        }
        if(additonal!=null) {
            options.addAll(Arrays.asList(additonal));
        }
        if(localFile.exists()) {
            if(options.contains(CreateOption.FAIL_IF_EXIST)) {
                throw new IOException("File '"+getPath()+"' already exists");
            }
            if(options.contains(CreateOption.SKIP_IF_EXIST))
            {
                return;
            }
            if(options.contains(CreateOption.REPLACE_IF_EXIST)) {
                if(!localFile.delete()) {
                    throw new IOException("Failed to delete '"+getPath()+"'");
                }
                if(!localFile.createNewFile()) {
                    throw new IOException("Failed to recreate '"+getPath()+"'");
                }
            }
        }
        else {
            if(!parent.exists()) {
                if(!options.contains(CreateOption.CREATE_PARENTS)) {
                    throw new IOException("Parent file '"+parent.getPath()+"' does not exist");
                }
                parent.createIfNotExists(CreateOption.CREATE_PARENTS,CreateOption.SKIP_IF_EXIST);
            }
            if(!localFile.createNewFile()) {
                throw new IOException("Failed to create '"+getPath()+"'");
            }
        }
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
        return parent.getPath();
    }

    @Override
    public void copy(VirtualFile dest) throws IOException
    {
        dest.createIfNotExists(CreateOption.SKIP_IF_EXIST, CreateOption.CREATE_PARENTS,CreateOption.REPLACE_IF_EXIST);
        
    }

}
