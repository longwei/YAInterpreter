How to make a interpreter?

* parsing such as lex or yacc are just for output AST, so as long as the formal programe structure is like scheme, then we could save time by directly going to semantics level

* match, sort of like regex's pattern match, find the match patten then bind vars to symbols

* lambda calcuclus only have three primitive
  * var
  * function
  * invokcation 