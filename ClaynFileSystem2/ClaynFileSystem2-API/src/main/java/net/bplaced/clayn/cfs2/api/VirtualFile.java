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
import java.io.InputStream;
import java.io.OutputStream;
import net.bplaced.clayn.cfs2.api.opt.CreateOption;
import net.bplaced.clayn.cfs2.api.util.Copyable;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public interface VirtualFile extends Child<VirtualDirectory>, IOEntity, Copyable<VirtualFile>
{

    /**
     * Interface for some syntactic sugar for reduced code to write.
     */
    public static interface VFile extends VirtualFile
    {

    }

    /**
     * Creates the file using the {@link CreateOption#FAIL_IF_EXIST} option.
     *
     * @throws IOException if an I/O Error occurs.
     */
    @Override
    public default void create() throws IOException
    {
        create(CreateOption.FAIL_IF_EXIST);
    }

    /**
     * Opens the file to write into it. If the file is already opened to be read
     * this method may fail.
     *
     * @return the outputstreaym to write into the file
     * @throws IOException if an I/O Error occurs.
     * @throws IllegalStateException if the file is already opened for reading.
     */
    public OutputStream openWrite() throws IOException, IllegalStateException;

    /**
     * Opens the file to be read from. If the file is already opened to be
     * written to this method may fail.
     *
     * @return an inputstream to read from the file
     * @throws IOException if an I/O Error occurs.
     * @throws IllegalStateException if the file is already opened for writing.
     */
    public InputStream openRead() throws IOException, IllegalStateException;

    /**
     * Returns the files current information. The returned information may not
     * be updated if the file is changed.
     *
     * @return the files current information.
     */
    public FileInformation getInformation();

    /**
     * Creates a read only variant of this file. A read only file can't be
     * created or written to.
     *
     * @return a read only variant of this file.
     */
    public default VirtualFile readOnly()
    {
        VirtualFile self = this;
        return new VirtualFile()
        {
            @Override
            public OutputStream openWrite() throws IOException, IllegalStateException
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public InputStream openRead() throws IOException, IllegalStateException
            {
                return self.openRead();
            }

            @Override
            public FileInformation getInformation()
            {
                return self.getInformation();
            }

            @Override
            public VirtualDirectory getParent()
            {
                return self.getParent();
            }

            @Override
            public void create(CreateOption option, CreateOption... additonal) throws IOException
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean exists()
            {
                return self.exists();
            }

            @Override
            public String getName()
            {
                return self.getName();
            }

            @Override
            public String getPath()
            {
                return self.getPath();
            }

            @Override
            public String getLocation()
            {
                return self.getLocation();
            }

            @Override
            public void copy(VirtualFile dest) throws IOException
            {
                self.copy(dest);
            }

        };
    }
}
