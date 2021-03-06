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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.bplaced.clayn.cfs2.api.opt.CreateOption;
import net.bplaced.clayn.cfs2.api.util.Copyable;

/**
 * A VirtualDirectory provides the general functionality a directory should
 * have. It also allows you to listen for changes to the content inside the
 * directory. A directory only mangages it's direct content.
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
     * @return a non null list of the directories in this directory.
     */
    public List<VirtualDirectory> listDirectories();

    /**
     * Returns a list of all files in this directory. May be empty but never
     * {@code null}.
     *
     * @return a non null list of the files in this directory.
     */
    public List<VirtualFile> listFiles();

    /**
     * Returns the names of all files and directories in this directory. The
     * list will only contain the names but not if it is a file or directory.
     * May be empty but never {@code null}.
     *
     * @return a non null list with the name of all files and directories in
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

    /**
     * Returns the file with the given name inside this directory. If the path
     * contains any {@code .} or {@code ..} the path will be traversed
     * accordingly.
     *
     * @param name the name for the file
     * @return the file with the given name/path from within this directory.
     */
    public VirtualFile getFile(String name);

    /**
     * Returns the path for this directory. The path for the root directory is
     * {@code /}
     *
     * @return the path for this directory.
     */
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

    public VirtualWatchService enableWatchService() throws IOException;

    /**
     * Disables the watchservice for this directory. If it was not enabled this
     * method does nothing. When disabeling the watchservice no further changes
     * will be send to the listeners. However pending changes may be processed
     * (no guarantee though).
     *
     * @throws IOException if an I/O exception occurs
     */
    public void disableWatchService() throws IOException;

}
