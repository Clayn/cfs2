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
package net.bplaced.clayn.cfs2.api.opt;

/**
 * Some opetions for creating files and directories to specifiy what should
 * happen in some cases.
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.2.0
 */
public enum CreateOption
{
    /**
     * Fails and throws an exception if the directory or file already exists
     */
    FAIL_IF_EXIST,
    /**
     * Also creates the parent directories if they don't exist
     */
    CREATE_PARENTS,
    /**
     * Replaces the directory or file if it already exists.
     */
    REPLACE_IF_EXIST,
    /**
     * Skips the creation if the directory of file already exists.
     */
    SKIP_IF_EXIST;
}
