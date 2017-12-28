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

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public class FileInformation
{

    private final long size;
    private final long createdOn;
    private final long lastModifiedOn;

    public FileInformation(long size, long createdOn, long lastModifiedOn)
    {
        this.size = size;
        this.createdOn = createdOn;
        this.lastModifiedOn = lastModifiedOn;
    }

    /**
     * Returns the timestamp of the date when the file was created.
     *
     * @return the timestamp of the date when the file was created.
     */
    public long getCreatedOn()
    {
        return createdOn;
    }

    /**
     * Returns the timestamp of the date when the file was last modified.
     *
     * @return the timestamp of the date when the file was last modified.
     */
    public long getLastModifiedOn()
    {
        return lastModifiedOn;
    }

    /**
     * Returns the size of the file in bytes.
     *
     * @return the size of the file in bytes.
     */
    public long getSize()
    {
        return size;
    }

}
