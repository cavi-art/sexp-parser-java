/*
 * Copyright (c) 2016. Grupo de Programación Declarativa - Universidad Complutense de Madrid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.ucm.sexp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Santiago Saavedra
 */
public class ConsTest {
    @Test
    public void testEqualsOnNulls() throws Exception {
        Cons a = new Cons(null, null);
        Cons b = new Cons(null, null);

        assertEquals(a, b);
    }

    @Test
    public void testEqualsWithCarSet() throws Exception {
        Atom atom1 = new Atom("a");

        Cons a = new Cons(atom1, null);
        Cons b = new Cons(atom1, null);

        assertEquals(a, b);
    }

    @Test
    public void testEqualsWithCdrSet() throws Exception {
        Atom atom1 = new Atom("a");

        Cons a = new Cons(null, atom1);
        Cons b = new Cons(null, atom1);

        assertEquals(a, b);
    }

    @Test
    public void testEqualsWithRecursivity() throws Exception {
        Atom atom1 = new Atom("a");
        Atom atom2 = new Atom("b");

        Cons a = new Cons(new Cons(atom2, null), new Cons(atom2, null));
        Cons b = new Cons(new Cons(atom2, null), new Cons(atom2, null));

        assertEquals(a, b);
    }

}