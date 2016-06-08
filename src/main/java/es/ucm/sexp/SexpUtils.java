package es.ucm.sexp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility static methods for convenience on handling conses.
 *
 * @author Santiago Saavedra
 */
public class SexpUtils {
    /**
     * Gets the car of the element.
     * <p>
     * The element can be null, in which case the function does not fail, but
     * it also returns null.
     *
     * @param e an Expr which is really a cons or null
     * @return the car of e or null
     */
    public static SexpParser.Expr car(SexpParser.Expr e) {
        if (e == null) return null;

        return e.getList().car;
    }

    /**
     * Gets the cdr of the element.
     * <p>
     * The element can be null, in which case the function does not fail, but
     * it also returns null..
     *
     * @param e an Expr which is really a cons or null
     * @return the cdr of e or null
     */
    public static SexpParser.Expr cdr(SexpParser.Expr e) {
        if (e == null) return null;

        return e.getList().cdr;
    }

    /**
     * Gets the second element of the list e.
     *
     * @param e an Expr which is really a cons
     * @return the second element of e
     */
    public static SexpParser.Expr cadr(SexpParser.Expr e) {
        return car(cdr(e));
    }

    /**
     * Gets the third element of the list e.
     *
     * @param e an Expr which is really a cons
     * @return the third element of e
     */
    public static SexpParser.Expr caddr(SexpParser.Expr e) {
        return car(cdr(cdr(e)));
    }

    /**
     * Gets the rest of the rest of the list e (third and following
     * elements).
     *
     * @param e an Expr which is really a cons
     * @return the rest of the rest of the cons
     */
    public static SexpParser.Expr cddr(SexpParser.Expr e) {
        return cdr(cdr(e));
    }

    /**
     * This param receives the list whose car matches the provided key.
     * <p>
     * It is to be used in association-lists (alists) and the like, so that it
     * is easy to get elements from a map-like structure encoded in a sexp.
     * <p>
     * If the element is matched, the whole list is returned, not only the
     * cdr. That means that if a list <code>((a b c) (d e f))</code> is asked
     * for key <code>a</code>, then the return value will be <code>(a b
     * c)</code>, not only the cons-list <code>(b c)</code>. Remember that the
     * former list is exactly the same as <code>((a . (b c)) (d . (e
     * f)))</code>.
     *
     * @param key   the key to search for (compared using {@link
     *              SexpParser.Expr#equals(Object)})
     * @param alist the alist in which to search for the key (must be a proper
     *              list)
     * @return an expr containing the list if the element is matched or null.
     */
    public static SexpParser.Expr assoc(SexpParser.Expr key, SexpParser.Expr alist) {
        Iterator<SexpParser.Expr> i = alist.getList().iterator();
        while (i.hasNext()) {
            SexpParser.Expr l = i.next();
            if (l.isList() && car(l).equals(key)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Converts a list to a cons list.
     *
     * @param list a Java List which is to be converted to a cons list
     * @return a cons list of stringified atoms from the original Java list
     */
    public static Cons listToCons(List<? extends Object> list) {
        Object[] objects = list.toArray();
        Cons cons = null;

        for (int i = objects.length - 1; i >= 0; --i) {
            cons = new Cons(new Atom(objects[i].toString()), cons);
        }

        return cons;
    }

    /**
     * Converts a cons list to a Java list of strings. The cons list should be
     * formed by atoms.
     *
     * @param cons a list of atoms
     * @return List of Strings
     */
    public static List<String> consToStringList(SexpParser.Expr cons) {
        ArrayList l;
        for (l = new ArrayList(); cons != null; cons = cdr(cons)) {
            l.add(car(cons).getAtom().toString());
        }

        return l;
    }

    /**
     * Converts a cons list to a Java list of Exprs. That is, converts the
     * first level of a cons list into a Java list, with the underlying
     * elements still there (be it Atoms or further Conses).
     *
     * @param cons an arbitrary cons list
     * @return a List of {@link es.ucm.sexp.SexpParser.Expr}
     */
    public static List<SexpParser.Expr> consToList(SexpParser.Expr cons) {
        ArrayList l;
        for (l = new ArrayList(); cons != null; cons = cdr(cons)) {
            l.add(car(cons));
        }

        return l;
    }
}
