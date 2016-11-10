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

import static org.junit.Assert.*;

/**
 * @author Santiago Saavedra
 */
public class AtomTest {
    @Test
    public void equals() throws Exception {
        Atom a = new Atom("a");
        Atom b = new Atom("b");
        Atom aa = new Atom("A");

        assertEquals(a, a);
        assertEquals(a, aa);

        assertNotEquals(a, b);
        assertNotEquals(aa, b);
    }

}