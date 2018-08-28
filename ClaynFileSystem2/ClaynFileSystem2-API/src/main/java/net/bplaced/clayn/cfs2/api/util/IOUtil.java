package net.bplaced.clayn.cfs2.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import net.bplaced.clayn.cfs2.api.VirtualFile;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2
 */
public class IOUtil
{

    private IOUtil() throws IllegalAccessException
    {
        throw new IllegalAccessException();
    }

    public static void copy(InputStream in, OutputStream out, int bufferSize) throws IOException
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

    public static void copy(InputStream in, OutputStream out) throws IOException
    {
        copy(in, out, 1024);
    }

    public static void copy(VirtualFile src, VirtualFile dest) throws IOException
    {
        try (InputStream in = src.openRead(); OutputStream out = dest.openWrite())
        {
            copy(in, out);
        }
    }
}
