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
package es.ucm.sexp;

/**
 * A StringAtom is the specialization of {@link Atom}s for holding strings.
 * <p/>
 * Equality of Strings is compared in a <b>case-sensitive</b> manner. Thus,
 * the equality comparison is not the same as atoms in general.
 * <p/>
 * Other atoms, including numbers are held in the general class {@link Atom}.
 * <p/>
 *
 * @see Atom
 * @see Cons
 * @see SexpParser.Expr
 */
public class StringAtom extends Atom {
    public StringAtom(String text) {
        super(text);
    }

    public String toString() {
        // StreamTokenizer hardcodes escaping with \, and doesn't allow \n inside words
        String escaped = name.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r").replace("\"", "\\\"");
        return "\"" + escaped + "\"";
    }

    /**
     * Returns the string value of the atom.
     *
     * @return string literal
     */
    public String getValue() {
        return name;
    }

    /**
     * Equality of strings is compared as per Java semantics, i.e., it is
     * case sensitive.
     *
     * @param other the other object to compare to
     * @return boolean indicating whether the two strings are the same
     */
    public boolean equals(Object other) {
        if (other instanceof StringAtom) {
            StringAtom o = (StringAtom) other;
            return name.equals(o.name);
        }
        return false;
    }
}