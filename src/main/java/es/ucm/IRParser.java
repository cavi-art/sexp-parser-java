package es.ucm;

import es.ucm.irparser.sexp.Atom;
import es.ucm.irparser.sexp.IRFileParser;
import es.ucm.irparser.sexp.SexpParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static es.ucm.irparser.sexp.SexpUtils.*;

public class IRParser {

    public static void main(String args[]) {

        InputStream data = System.in;
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                usage();
                return;
            } else {
                try {
                    data = new FileInputStream(args[0]);
                } catch (FileNotFoundException e) {
                    System.err.printf("File %s was not found", args[0]);
                    System.exit(1);
                }
            }
        }

        try {
            IRFileParser parser = new IRFileParser(data);
            List<SexpParser.Expr> result = parser.parseExprs();

            for (SexpParser.Expr expr : result) {
                Atom verb = car(expr).getAtom();
                switch (verb.toString().toUpperCase()) {
                    case "DEFINE":
                        System.out.printf("Function definition for %s with %s parameters.", cadr(expr), caddr(expr));
                        break;
                    case "DEFVAR":
                        System.out.printf("New global variable named %s", car(expr));
                        break;
                    default:
                        System.out.println("Unknown toplevel expression: ");
                        System.out.println(verb);
                }
            }
        } catch (SexpParser.ParseException e1) {
            e1.printStackTrace();
        }
    }

    public static void usage() {
        System.out.println("Usage: sexpParserDemo [--help] [file.clir]");
    }
}
