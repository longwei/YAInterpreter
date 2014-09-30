#lang racket

(define env0 '())

;; extend the x at front of list
(define ext-env
  (lambda (x v env)
    (cons `(,x . ,v) env)))

;; lookup x in association list
(define lookup
  (lambda (x env)
    (let ([p (assq x env)])
      (cond
       [(not p) x]
       [else (cdr p)]))))

(struct Closure (f env))

;; input: exp and env
;; 5 condition: symbol, number, function, invoke, expression
(define interp1
  (lambda (exp env)
    (match exp                                          
      [(? symbol? x) (lookup x env)]                    
      [(? number? x) x]                                 
      [`(lambda (,x) ,e)                                ; function
       (Closure exp env)]
      [`(,e1 ,e2)                                       ; invoke
       (let ([v1 (interp1 e1 env)]
             [v2 (interp1 e2 env)])
         (match v1
           [(Closure `(lambda (,x) ,e) env1)            ; using env1 as we want to evulate it by the env it invoked
            (interp1 e (ext-env x v2 env1))]))]         ; if current env instead of env1, then dynamic scoping
      [`(,op ,e1 ,e2)                                   
       (let ([v1 (interp1 e1 env)]
             [v2 (interp1 e2 env)])
         (match op
           ['+ (+ v1 v2)]
           ['- (- v1 v2)]
           ['* (* v1 v2)]
           ['/ (/ v1 v2)]))])))

;;wrapper for additional parameters
(define interp
  (lambda (exp)
    (interp1 exp env0)))


(interp '(+ 1 2))
;; => 3

(interp '(* 2 3))
;; => 6

(interp '(* 2 (+ 3 4)))
;; => 14

(interp '(* (+ 1 2) (+ 3 4)))
;; => 21

(interp '(((lambda (x) (lambda (y) (* x y))) 2) 3))
;; => 6

(interp '((lambda (x) (* 2 x)) 3))
;; => 6

(interp '((lambda (y) (((lambda (y) (lambda (x) (* y 2))) 3) 0)) 4))
;; => 6

;; (interp '(1 2))
;; => match: no matching clause for 1