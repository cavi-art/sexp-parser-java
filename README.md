IR Sexp Parser for CAVI-ART
===========================
[![Build Status](https://travis-ci.org/cavi-art/sexp-parser-java.svg?branch=master)](https://travis-ci.org/cavi-art/sexp-parser-java)
[![Get dependency as JitPack](https://jitpack.io/v/cavi-art/sexp-parser-java.svg)](https://jitpack.io/#cavi-art/sexp-parser-java)

This is a Proof of Concept.

This is a parser, based on work from Joel F. Klein, published unde GPL
and FDL at RosettaCode.  We changed the parser so that it also
correctly accepts *improper* lists, as well as using a more similar
datatype for storing lists, as it is the cons, instead of using an
ArrayList-based strategy.


To compile you will need:

- Java >=7 (in order to use switches with strings)

Compilation
-----------

To compile using just plain javac use the following commands:

    $ cd path/to/ir-sexp-parser-java
    $ mkdir out
    $ javac -d out src/main/java/**/*.java

Then execute with:

    $ java -cp out es.ucm.sexp.SexpFileParserDemo path/to/file.clir

    $ java c-p out es.ucm.sexp.Main path/to/file.clir


Usage
-----

To use this as a library in your code, just create a new
`SexpFileParser(InputStream in)` from your preferred InputStream.

You can get all the top-level expressions in the file by means of
`public List<Expr> parseExprs()`.

Remember that an `Expr` is either an `Atom` or a `Cons`. An Atom can
be a `StringAtom` for literal strings. Numbers are (non-string) atoms.
You can test against that via the `Expr.isAtom()` and `Expr.isList()`
methods.  You can get the first element of the list with the static
`es.ucm.sexp.SexpUtils.car()` method, and the **rest** of the
list (not the second element) with the static `cdr()` counterpart. You
also have the convenience methods `cadr()` and `caddr()` for accesing
the second and third elements of the list.
