/**
 * This file is based on work from Joel F. Klein, and authors at rosettacode.org
 * and is released under the terms of the GNU GPL and the GNU GFDL.
 * <p/>
 * Modified by Santiago Saavedra to better suit his needs.
 *
 * @author Joel F. Klein
 * @author Santiago Saavedra López
 * @url https://rosettacode.org/wiki/S-Expressions#Java
 */
package es.ucm.irparser.sexp;

import es.ucm.irparser.sexp.SexpParser.Expr;

/**
 * An atom is the most primitive element in a s-expression.
 * <p/>
 * It is atomic. Its representation can be written via toString and it has no
 * other accessor methods.
 * <p/>
 * Equality of atoms is compared always in a <b>case-insensitive</b> manner
 * for compatibility with various lisps.
 * <p/>
 * There is a specialization of an Atom called {@link StringAtom} for holding
 * string literals.
 *
 * @see StringAtom
 * @see Cons
 * @see Expr
 */
public class Atom implements Expr {
    String name;

    public Atom(String text) {
        name = text;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object other) {
        if (other instanceof Atom) {
            return name.equalsIgnoreCase(((Atom) other).name);
        }
        return false;
    }

    public boolean isAtom() {
        return true;
    }

    public Atom getAtom() {
        return this;
    }

    public boolean isList() {
        return false;
    }

    public Cons getList() {
        throw new UnsupportedOperationException("This expression is not a list");
    }
}