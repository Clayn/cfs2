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
package net.bplaced.clayn.cfs2.api.evt;

import net.bplaced.clayn.cfs2.api.Child;
import net.bplaced.clayn.cfs2.api.IOEntity;
import net.bplaced.clayn.cfs2.api.VirtualDirectory;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @param <E>
 * @since 0.1
 */
public class BasicIOEvent<E extends IOEntity & Child<VirtualDirectory>> implements IOEvent<E>
{

    private final E target;
    private final IOEventType type;

    public BasicIOEvent(E target, IOEventType type)
    {
        this.target = target;
        this.type = type;
    }

    @Override
    public E getTarget()
    {
        return target;
    }

    @Override
    public VirtualDirectory getSource()
    {
        return target.getParent();
    }

    @Override
    public String getPath()
    {
        return target.getPath();
    }

    @Override
    public String getName()
    {
        return target.getName();
    }

    @Override
    public IOEventType getType()
    {
        return type;
    }

}
