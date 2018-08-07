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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import net.bplaced.clayn.cfs2.api.VirtualFile;

/**
 * This interface describes read and write access to an object. This provides an
 * option for other classes that read or write data to ignore the source/sink
 * and allow easy usage of the {@link VirtualFile} class while providing support
 * for other sources as well.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public interface IOWrapper
{

    /**
     * Returns the {@link InputStream input} to read from the object.
     *
     * @return the inputstream to read from the object
     * @throws IOException if an I/O Exception occurs;
     */
    public InputStream getInputStream() throws IOException;

    /**
     * Returns the {@link OutputStream output} to write to the object.
     *
     * @return the outputstream to write to the object
     * @throws IOException if an I/O Exception occurs;
     */
    public OutputStream getOutputstream() throws IOException;

    /**
     * Wraps the given file into a new {@link IOWrapper} instance. The interface
     * methods will create a new FileIn/Ouputstream on each call but not before
     * any call was made.
     *
     * @param f the file to wrap
     * @return a new IOWrapper giving access to the file.
     */
    public static IOWrapper wrap(File f)
    {
        return new IOWrapper()
        {
            @Override
            public InputStream getInputStream() throws IOException
            {
                return new FileInputStream(f);
            }

            @Override
            public OutputStream getOutputstream() throws IOException
            {
                return new FileOutputStream(f);
            }
        };
    }

    /**
     * Wraps the given socket into a new {@link IOWrapper} instance. The new
     * IOWrapper will use the sockets {@link Socket#getInputStream() } and {@link Socket#getOutputStream()
     * }
     * methods.
     *
     * @param sock the socket to wrap
     * @return a new IOWrapper giving access to the socket.
     */
    public static IOWrapper wrap(Socket sock)
    {
        return new IOWrapper()
        {
            @Override
            public InputStream getInputStream() throws IOException
            {
                return sock.getInputStream();
            }

            @Override
            public OutputStream getOutputstream() throws IOException
            {
                return sock.getOutputStream();
            }
        };
    }
}
