http://matt.might.net/articles/compiling-to-java/

;; The input language contains integers, variables,
;; a few primitives, lambda terms, let terms, explicit
;; recursion (letrec), conditionals and function
;; applications, sequencing and mutable variables.

;; <exp> ::= <const>
;;        |  <prim>
;;        |  <var>
;;        |  (lambda (<var> ...) <exp>)
;;        |  (if <exp> <exp> <exp>)
;;        |  (set! <var> <exp>)
;;        |  (let ((<var> <exp>) ...) <exp>)
;;        |  (letrec ((<var> (lambda (<var>...) <exp>))) <exp>)
;;        |  (begin <exp> ...)
;;        |  (<exp> <exp> ...)

;; <const> ::= <int>

;; To run this compiler, run an R5RS-compatible interpreter
;; on this file and pipe a Scheme expression into stdin:

;;  $ interpret thisfile.scm < input.scm > BOut.java
;;  $ javac Value.java BOut.java 
;;  $ java BOut
