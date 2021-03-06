%{
#include <stdio.h>
#include <stdlib.h>
%}

%option yylineno
%option noyywrap

ID     [A-Za-z_][A-Za-z_0-9]*
INT    -?[1-9][0-9]*
OP     [-+*/^=]

%%

 /* Print delimiters. */
[(]         {printf("(left-parenthesis %u)\n", yylineno);}
[)]         {printf("(right-parenthesis %u)\n", yylineno);}
[;]         {printf("(semicolon %u)\n", yylineno);}

 /* Print identifiers, integers and operators. */
{INT}       {printf("(int %s %u)\n",yytext, yylineno);}
{ID}        {printf("(id \"%s\" %u)\n", yytext, yylineno);}
{OP}        {printf("(op \"%s\" %u)\n", yytext, yylineno);}

 /* Ignore comments and whitespace. */
#[^\n]*     {}
[ \t\r\n]   {}

<<EOF>>     {printf("(eof %u)\n", yylineno); return 0;}

%%

int main(int argc, char* argv[]) {
  yylex() ;
  return EXIT_SUCCESS ;
}
