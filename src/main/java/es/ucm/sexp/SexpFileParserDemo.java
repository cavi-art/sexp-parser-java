package es.ucm.sexp;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * This class is based on work from Joel F. Klein, and authors at rosettacode.org
 * and is released under the terms of the GNU GPL and the GNU GFDL.
 * <p/>
 * Modified by Santiago Saavedra to better suit his needs.
 *
 * @author Joel F. Klein
 * @author Santiago Saavedra López
 * @url https://rosettacode.org/wiki/S-Expressions#Java
 */
public class SexpFileParserDemo {

    private static String testData = "((data \"quoted data\" 123 4.5)\n (data (!@# (4.5) \"(more\" \"data)\")))\n" +
            "(hello world)\n" +
            "'((test 5 . NIL) (data (beta c . 4)) . 5) 4 (75 . NIL)";
    private static InputStream data = new ByteArrayInputStream(testData.getBytes());

    public static void main(String args[]) {

        if (args.length > 0) {
            if (args[0] == "--help") {
                usage();
                return;
            } else {
                try {
                    data = new FileInputStream(args[0]);
                } catch (FileNotFoundException e) {
                    System.err.printf("File %s was not found", args[0]);
                }
            }
        }

        SexpFileParser parser = new SexpFileParser(data);

        try {
            List<SexpParser.Expr> result = parser.parseExprs();
            for (SexpParser.Expr expr : result) {
                System.out.println("----- BEGIN TOPLEVEL EXPRESSION -----");
                System.out.println(expr);
                System.out.println("----- END TOPLEVEL EXPRESSION -----");
            }
        } catch (SexpParser.ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static void usage() {
        System.out.println("Usage: sexpParserDemo [--help] [file.clir]");
    }
}