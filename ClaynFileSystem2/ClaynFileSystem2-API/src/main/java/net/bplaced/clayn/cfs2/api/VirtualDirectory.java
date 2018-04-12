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
package net.bplaced.clayn.cfs2.api;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.bplaced.clayn.cfs2.api.evt.IOEvent;
import net.bplaced.clayn.cfs2.api.opt.CreateOption;
import net.bplaced.clayn.cfs2.api.util.Copyable;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public interface VirtualDirectory extends Child<VirtualDirectory>, IOEntity, Copyable<VirtualDirectory>
{

    /**
     * Interface for some syntactic sugar for reduced code to write.
     */
    public static interface VDir extends VirtualDirectory
    {

    }

    /**
     * Creates the directory while skipping the creation if the directory
     * already exists.
     *
     * @throws IOException if an I/O Error occurs.
     */
    public default void mkDir() throws IOException
    {
        create(CreateOption.SKIP_IF_EXIST);
    }

    /**
     * Creates the directory while skipping the creation if the directory
     * already exists. All parent directories will be created as well.
     *
     * @throws IOException if an I/O Error occurs.
     */
    public default void mkDirs() throws IOException
    {
        create(CreateOption.SKIP_IF_EXIST, CreateOption.CREATE_PARENTS);
    }

    public VirtualDirectory changeDirectory(String path);

    /**
     * Returns a list of all directories in this directory. May be empty but
     * never {@code null}.
     *
     * @return the non null list of the directories in this directory.
     */
    public List<VirtualDirectory> listDirectories();

    /**
     * Returns a list of all files in this directory. May be empty but never
     * {@code null}.
     *
     * @return the non null list of the files in this directory.
     */
    public List<VirtualFile> listFiles();

    /**
     * Returns the names of all files and directories in this directory. The
     * list will only contain the names but not if it is a file or directory.
     * May be empty but never {@code null}.
     *
     * @return the non null list with the name of all files and directories in
     * this directory.
     */
    public default List<String> listContent()
    {
        return Stream.concat(listDirectories().stream(), listFiles().stream()).map(
                IOEntity::getName).collect(Collectors.toList());
    }

    /**
     * Returns wether or not this directory is the root of its file system.
     *
     * @return {@code true} if the directory is the root, {@code false}
     * otherwise.
     */
    public default boolean isRoot()
    {
        return getParent() == null;
    }

    public VirtualFile getFile(String name);

    @Override
    public default String getPath()
    {
        if (isRoot())
        {
            return "/";
        } else
        {
            return IOEntity.super.getPath();
        }
    }

    @Override
    public default void copy(VirtualDirectory dest) throws IOException
    {
        for (VirtualDirectory dir : listDirectories())
        {
            dir.copy(dest.changeDirectory(dir.getName()));
        }
    }

    public default void setOnFileCreated(Consumer<IOEvent<VirtualFile>> handler)
    {

    }

    public default void setOnFileModified(Consumer<IOEvent<VirtualFile>> handler)
    {

    }

    public default void setOnFileDeleted(Consumer<IOEvent<VirtualFile>> handler)
    {

    }

    public default void removeListeners()
    {
        setOnFileCreated(null);
        setOnFileDeleted(null);
        setOnFileModified(null);
    }
}
