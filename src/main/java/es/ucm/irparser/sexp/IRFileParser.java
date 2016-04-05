package es.ucm.irparser.sexp;

import es.ucm.irparser.sexp.SexpParser.Expr;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * This class the main entry point for our IR File Parser.
 * <p/>
 * Usage: create a new instance with an InputStream or a Reader, and call
 * `parseExpr` as many times as you want an expression, or use, either the
 * iterator interface or the parseExprs method to get them all at once.
 *
 * @author Santiago Saavedra López
 */
public class IRFileParser implements Iterator<Expr> {
    private final SexpParser parser;
    private final SexpTokenizer tokenizer;

    public IRFileParser(InputStream r) {
        this(new InputStreamReader(r));
    }

    public IRFileParser(Reader r) {
        this(new SexpTokenizer(r));
    }

    public IRFileParser(SexpTokenizer input) {
        tokenizer = input;
        parser = new SexpParser(tokenizer);
    }

    /**
     * Parses an expression, leaving the internal state ready for getting the
     * next expression (unless there is none).
     *
     * @return Expr
     * @throws SexpParser.ParseException on malformed input
     */
    public Expr parseExpr() throws SexpParser.ParseException {
        return parser.parseExpr();
    }

    /**
     * Parses all the expressions that there may be in the input stream,
     * returning them all as a java.util.List.
     *
     * @return {@link List<Expr>} with the parsed expressions
     * @throws SexpParser.ParseException on malformed input
     */
    public List<Expr> parseExprs() throws SexpParser.ParseException {
        List<Expr> exprs = new ArrayList<Expr>();
        while (hasNext()) {
            exprs.add(parseExpr());
        }

        return exprs;
    }

    /**
     * Returns whether the file has already been completely consumed.
     * Part of the iterator contract.
     *
     * @return {@link Boolean} indicating whether we have reached the end.
     */
    public boolean hasNext() {
        return tokenizer.hasNext();
    }

    /**
     * Parses an expression. Part of the iterator contract.
     *
     * @see IRFileParser#parseExpr()
     */
    public Expr next() {
        try {
            return parseExpr();
        } catch (SexpParser.ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
