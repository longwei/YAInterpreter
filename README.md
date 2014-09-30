How to make a interpreter?
===

It accept a expression and return a value.

* parsing such as lex or yacc are just for output AST, so as long as the formal programe structure is like scheme, then we could save time by directly going to semantics level

* match, sort of like regex's pattern match, find the match patten then bind vars to symbols. (,op ,e1 ,e2) is a pattern to match the same structure like (+ 1 2).

* CBV: call-by-value; CBN: call-by-need(or Lazy evaluation)


Lamda calculus
===

* lambda calcuclus only have three primitive
  * var -- binding & evaluatie
  * function
  * invokcation
  
* multi parameter

```
  ----------
  |    --- |
  | -y | | |
-x|    --- |
  ----------
  ((
  (lambda (x) (lambda (y) (* y y)))
  2) 3)
  
  ((f 2)3)
```

* scope is for record the value

```
(define lookup
(lambda (x env)
    (let ([p (assq x env)])
      (cond
       [(not p) x]
       [else (cdr p)]))))
     
((x.1)(y.2)(z.3))
     
(define ext-env
  (lambda (x v env)
    (cons `(,x . ,v) env)))
    
insert a new part to front of the list
```
* closure: 
```(lambda (y) (lambda (x) (* y 2)))```, it can't just return (* y 2) as a function, because y is in the inner scope. That's why we need to have a closure to remeber outer function's parameter. (a.k.a lexical scoping or static scoping)

dynamic scoping is just returning the function body itself, and it is evaluate it value on invokecation. which could be both useful and buggie

```
(struct Closure (f env))

 [`(lambda (,x) ,e)
   (Closure exp env)]
 
 <exp> ::= <var>
        |  (lambda (<var>) <exp>)
        |  (<exp> <exp>)
   
```


REF:

* http://matt.might.net/articles/implementing-a-programming-language/
* http://www.yinwang.org/blog-cn/2012/08/01/interpreter/