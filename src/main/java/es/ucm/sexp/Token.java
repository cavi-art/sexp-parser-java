package es.ucm.sexp;

import java.io.StreamTokenizer;

/**
 * Base token class. We need this class to handle tokens in the
 * tokenization phase of the compiler, to pass them to the compiler.
 *
 * This class is based on work from Joel F. Klein, and authors at
 * rosettacode.org and is released under the terms of the GNU GPL and the GNU
 * GFDL.
 * <p>
 * See: https://rosettacode.org/wiki/S-Expressions#Java
 *
 * @author Joel F. Klein
 * @author Santiago Saavedra López
 */
public class Token {
    public static final int SYMBOL = StreamTokenizer.TT_WORD;
    public int type;
    public String text;
    public int line;

    public Token(StreamTokenizer tzr) {
        this.type = tzr.ttype;
        this.text = tzr.sval;
        this.line = tzr.lineno();
    }

    public String toString() {
        switch (this.type) {
            case SYMBOL:
            case '"':
                return this.text;
            default:
                return String.valueOf((char) this.type);
        }
    }
}