/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Ant", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 * Wraps a String as an InputStream. Note that data will be lost for
 * characters not in ISO Latin 1, as a simple char->byte mapping is assumed.
 *
 * @author <a href="mailto:umagesh@apache.org">Magesh Umasankar</a>
 */
public class StringInputStream
    extends InputStream {
    /** Source string, stored as a StringReader */
    private StringReader in;

    /**
     * Composes a stream from a String
     * 
     * @param source The string to read from. Must not be <code>null</code>.
     */
    public StringInputStream(String source) {
        in = new StringReader(source);
    }

    /**
     * Reads from the Stringreader, returning the same value. Note that
     * data will be lost for characters not in ISO Latin 1. Clients
     * assuming a return value in the range -1 to 255 may even fail on
     * such input.
     * 
     * @return the value of the next character in the StringReader
     * 
     * @exception IOException if the original StringReader fails to be read
     */
    public int read() throws IOException {
        return in.read();
    }

    /**
     * Closes the Stringreader.
     * 
     * @exception IOException if the original StringReader fails to be closed
     */
    public void close() throws IOException {
        in.close();
    }

    /**
     * Marks the read limit of the StringReader.
     * 
     * @param limit the maximum limit of bytes that can be read before the 
     *              mark position becomes invalid
     */
    public synchronized void mark(final int limit) {
        try {
            in.mark(limit);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    /**
     * Resets the StringReader.
     * 
     * @exception IOException if the StringReader fails to be reset
     */
    public synchronized void reset() throws IOException {
        in.reset();
    }

    /**
     * @see InputStream#markSupported
     */
    public boolean markSupported() {
        return in.markSupported();
    }
}

