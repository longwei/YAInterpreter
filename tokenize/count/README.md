```
...definition...

%%
...rules...

%%
...code...
```


definition:
======
something likes:

```
letter [a-zA-Z]
digit [0-9]
punct [,.:;!?]
nonblank [^ \t]
```

and if rule depends on context, state is needed

%x INSTRING INCOMMENT


rules
====

* the matched text is char* yytext or int yyleng
* left-state ```<STATE>(some pattern){some action; BEGIN OTHERSTATE}```
* right-state ``` abc/de {some action}```
