%{
#include <stdio.h>
#include <stdlib.h>

unsigned int code = 0 ; /* Bytes of code. */
unsigned int comm = 0 ; /* Bytes of comments. */

#define CODE {code += strlen(yytext);}
#define COMM {comm += strlen(yytext);}
%}

%option noyywrap

%x INCOMMENT INSTRING

%%

<INITIAL>"/*"      { COMM ; BEGIN(INCOMMENT) ; }
<INCOMMENT>"*/"    { COMM ; BEGIN(INITIAL) ; }
<INCOMMENT>.|\n    { COMM ; }

<INITIAL>\"      { CODE ; BEGIN(INSTRING) ; }
<INSTRING>\\\"   { CODE ; } /* Escaped quote. */
<INSTRING>\"     { CODE ; BEGIN(INITIAL) ; }
<INSTRING>.|\n   { CODE ; }

<INITIAL>['](\\)?\"['] { CODE ; } /* Character quote. */
<INITIAL>.|\n          { CODE ; }

<<EOF>>   { return 0 ; }

%%

int main()
{
  yylex();
  printf("%u %u %lf\n",
         code,
         comm,
         (double)comm/(double)(code+comm)) ;
  return 0;
}
