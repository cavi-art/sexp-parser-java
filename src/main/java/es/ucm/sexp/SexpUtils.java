package es.ucm.sexp;

import java.util.Iterator;

/**
 * Utility static methods for convenience on handling conses.
 *
 * @author Santiago Saavedra
 */
public class SexpUtils {
    /**
     * Gets the car of the element.
     * <p/>
     * The element can be null, in which case the function does not fail, but
     * it also returns null.
     *
     * @param e an Expr which is really a cons or null
     */
    public static SexpParser.Expr car(SexpParser.Expr e) {
        if (e == null) return null;

        return e.getList().car;
    }

    /**
     * Gets the cdr of the element.
     * <p/>
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
     * @return
     */
    public static SexpParser.Expr caddr(SexpParser.Expr e) {
        return car(cdr(cdr(e)));
    }

    /**
     * This param receives the list whose car matches the provided key.
     * <p/>
     * It is to be used in association-lists (alists) and the like, so that
     * it is easy to get elements from a map-like structure encoded in a sexp.
     * <p/>
     * If the element is matched, the whole list is returned, not only the cdr.
     * That means that if a list <code>((a b c) (d e f))</code> is asked for
     * key <code>a</code>, then the return value will be <code>(a b c)</code>, not
     * only the cons-list <code>(b c)</code>. Remember that the former list is
     * exactly the same as <code>((a . (b c)) (d . (e f)))</code>.
     *
     * @param key   the key to search for (compared using {@link SexpParser.Expr#equals(Object)})
     * @param alist the alist in which to search for the key (must be a proper list)
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
}
