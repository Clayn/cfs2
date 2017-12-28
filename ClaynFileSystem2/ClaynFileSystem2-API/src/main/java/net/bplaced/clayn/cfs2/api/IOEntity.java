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
import net.bplaced.clayn.cfs2.api.opt.CreateOption;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public interface IOEntity
{

    /**
     * Creates the entity with options for the creation process.
     *
     * @param option the option to use for creation.
     * @param additonal additional options to use.
     * @throws IOException if an I/O Error occurs.
     */
    public void create(CreateOption option, CreateOption... additonal) throws IOException;

    /**
     * Creates the entity with possible default implementation options.
     *
     * @throws IOException IOException if an I/O Error occurs.
     */
    public void create() throws IOException;

    /**
     * Attempts to create the entity using
     * {@link #create(net.bplaced.clayn.cfs2.api.opt.CreateOption, net.bplaced.clayn.cfs2.api.opt.CreateOption...)}
     * if and only the entity does not exist. This operation should be used to
     * be sure a entity is created but you won't do the check if it exists to
     * skip the creation.
     *
     * @param option the option to use for creation.
     * @param additonal additional options to use.
     * @throws IOException if an I/O Error occurs.
     */
    public default void createIfNotExists(CreateOption option,
            CreateOption... additonal) throws IOException
    {
        if (exists())
        {
            return;
        }
        create(option, additonal);
    }

    /**
     * Attempts to create the entity using {@link #create() } if and only if the
     * entity does not exists. This operation should be used to be sure a entity
     * is created but you won't do the check if it exists to skip the creation.
     *
     * @throws IOException if an I/O Error occurs.
     */
    public default void createIfNotExists() throws IOException
    {
        if (exists())
        {
            return;
        }
        create();
    }

    /**
     * Checks wether or not this entity exists or not.
     *
     * @return {@code true} if this entity exists, {@code false} otherwise.
     */
    public boolean exists();

    /**
     * Returns the name of the entity. The name is only the last part of the
     * entity. For a file i.e. the name with the extension without the parent
     * path.
     *
     * @return the name of the entity.
     */
    public String getName();

}
