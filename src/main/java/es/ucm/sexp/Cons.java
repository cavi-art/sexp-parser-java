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

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * A cons is a pair; a primitive element in a s-expression.
 * <p/>
 * A cons has two parts: traditionally called <b>car</b> and <b>cdr</b>.
 * Our cons implementation is immutable, so the properties can be directly
 * accessed. However, a more functional-oriented approach is available as
 * convenience static methods in {@link SexpUtils}.
 * <p/>
 * A list is made up of cons cells, forming a single-linked list, by which
 * the car points to each element and the cdr should point to the rest of the
 * list. We are using the java null value to indicate the empty list and the
 * list termination marker. Thus, a proper list of one element would be:
 * <code>new Cons(element, null)</code>.
 * <p/>
 * You can iterate through the values in the list via the implemented Iterable
 * interface, which will give you the elements one by one.
 *
 * @author Santiago Saavedra
 * @see SexpParser.Expr
 * @see Atom
 * @see StringAtom
 */
public class Cons implements SexpParser.Expr, Iterable<SexpParser.Expr> {
    public final SexpParser.Expr car;
    public final SexpParser.Expr cdr;

    public Cons(SexpParser.Expr car, SexpParser.Expr cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');

        if (car == null) {
            sb.append("NIL");
        } else {
            sb.append(car.toString());
        }

        sb.append(innerToString());

        sb.append(')');
        return sb.toString();
    }

    protected String innerToString() {
        StringBuilder sb = new StringBuilder();
        SexpParser.Expr cell = cdr;

        while (cell != null) {
            if (cell.isList()) {
                sb.append(" ");
                sb.append(cell.getList().car);
                cell = cell.getList().cdr;
                continue;
            } else {
                sb.append(" . ");
                sb.append(cell.toString());
                return sb.toString();
            }
        }
        return sb.toString();
    }

    @Override
    public boolean isAtom() {
        return false;
    }

    @Override
    public Atom getAtom() {
        throw new UnsupportedOperationException("This expression is a cons, not an atom");
    }

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    public Cons getList() {
        return this;
    }

    public boolean equals(Object other) {
        if (other instanceof Cons) {
            boolean eq = true;
            Cons o = (Cons) other;

            if (car != null) {
                eq = eq && car.equals(o.car);
            } else if (o.car != null) {
                return false;
            }

            if (cdr != null) {
                eq = eq && cdr.equals(o.cdr);
            } else if (o.cdr != null) {
                return false;
            }

            return eq;
        }
        return false;
    }

    /**
     * Beware: only works on "proper" lists (i.e., null-terminated)
     *
     * @return car-iterator for this cons cell
     */
    public Iterator<SexpParser.Expr> carIterator() {
        final Cons obj = this;

        return new Iterator<SexpParser.Expr>() {
            private Cons cell = new Cons(null, obj);

            @Override
            public boolean hasNext() {
                return cell != null;
            }

            @Override
            public SexpParser.Expr next() {
                cell = cell.cdr.getList();
                return cell.car;
            }
        };
    }

    public void mapcar(Consumer<? super SexpParser.Expr> action) {
        Cons obj = this;
        while (obj != null) {
            action.accept(obj.car);
            obj = obj.cdr.getList();
        }
    }

    @Override
    public Iterator<SexpParser.Expr> iterator() {
        return carIterator();
    }

    @Override
    public void forEach(Consumer<? super SexpParser.Expr> action) {
        mapcar(action);
    }
}
