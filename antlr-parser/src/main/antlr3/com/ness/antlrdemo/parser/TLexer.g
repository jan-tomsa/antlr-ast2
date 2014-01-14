// This is a sample lexer generated by the ANTLR3 Maven Archetype
// generator. It shows how to use a superclass to implement
// support methods and variables you may use in the lexer actions
// rather than create a messy lexer that has the Java code embedded
// in it.
//

lexer grammar TLexer;

options {

   language=Java;  // Default

   // Tell ANTLR to make the generated lexer class extend the
   // the named class, which is where any supporting code and 
   // variables will be placed.
   //
   superClass = AbstractTLexer;
}

// What package should the generated source exist in?
//
@header {

    package com.ness.antlrdemo.parser;
}

// This is just a simple lexer that matches the usual suspects
//

KEYSER : 'Keyser' ;
SOZE   : 'Soze' ;

ADD : '+' ;
SEMI: ';' ;

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {skip();}
    |   '/*' ( options {greedy=false;} : . )* '*/' {skip();}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {skip();}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

