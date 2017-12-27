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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.bplaced.clayn.cfs2.api.VirtualFileSystem;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public class FileSystemProvider
{

    private static final FileSystemProvider INSTANCE = new FileSystemProvider();
    private boolean overrideAllowed;
    private final Map<String, Function<Map<String, Object>, VirtualFileSystem>> registeredProviders = new HashMap<>();

    public static FileSystemProvider getInstance()
    {
        return INSTANCE;
    }

    public void setOverrideAllowed(boolean overrideAllowed)
    {
        this.overrideAllowed = overrideAllowed;
    }

    public boolean isOverrideAllowed()
    {
        return overrideAllowed;
    }

    public void register(String ident,
            Function<Map<String, Object>, VirtualFileSystem> provider)
    {
        if (registeredProviders.containsKey(ident))
        {
            if (!isOverrideAllowed())
            {
                throw new RuntimeException();
            }
        }
        registeredProviders.put(ident, provider);
    }

    public Function<Map<String, Object>, VirtualFileSystem> getProvider(
            String ident)
    {
        return registeredProviders.get(ident);
    }
}
