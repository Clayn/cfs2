package net.bplaced.clayn.cfs2.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import net.bplaced.clayn.cfs2.api.VirtualFile;

/**
 * Utility methods regarding input and output operations.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2
 */
public final class IOUtil
{

    private IOUtil() throws IllegalAccessException
    {
        throw new IllegalAccessException();
    }

    /**
     * Copies the data read from the inputstream to the outputstream using the
     * given buffersize. The streams will remain open but the outputstream will
     * be flushed. The buffersize should not be to small to reduce the amount of
     * read/write operations.
     *
     * @param in the inputstream to read data from
     * @param out the outputstream to write the data to
     * @param bufferSize the size for the buffer to use
     * @throws IOException if an I/O Exception occurs
     * @throws IllegalArgumentException if {@code buffersize <= 0}
     * @throws NullPointerException if the inputstream or outputstream is
     * {@code null}
     */
    public static void copy(InputStream in, OutputStream out, int bufferSize) throws IOException, NullPointerException, IllegalArgumentException
    {
        Objects.requireNonNull(in);
        Objects.requireNonNull(out);
        if (bufferSize <= 0)
        {
            throw new IllegalArgumentException();
        }
        byte buffer[] = new byte[bufferSize];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
        out.flush();
    }

    public static void copy(InputStream in, OutputStream out) throws IOException, NullPointerException, IllegalArgumentException
    {
        copy(in, out, 1024);
    }

    public static void copy(VirtualFile src, VirtualFile dest) throws IOException, NullPointerException, IllegalArgumentException
    {
        try (InputStream in = src.openRead(); OutputStream out = dest.openWrite())
        {
            copy(in, out);
        }
    }
}
