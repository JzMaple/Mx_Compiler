grammar Mx;

all : program;

program
    : (typeDefine|instantiation|function)+
    ;

typeDefine
    : CLASS Identifier '{' program? '}'
    ;

function
    : class_statement? Identifier '(' parameter? ')' block
    ;

block
    : '{' statement* '}'
    ;

parameter
    : class_statement Identifier ',' parameter
    | class_statement Identifier
    ;

statement
    : IF '(' expression ')' statement                                       #IF_STATE
    | IF '(' expression ')' statement ELSE statement                        #IFELSE_STATE
    | FOR '(' first = expression? ';' second = expression? ';' third =expression? ')' statement
                                                                            #FOR_STATE
    | WHILE '(' expression ')' statement                                    #WHILE_STATE
    | RETURN expression ';'                                                 #RETURN_STATE
    | BREAK ';'                                                             #BREAK_STATE
    | CONTINUE ';'                                                          #CONTINUE_STATE
    | expression ';'                                                        #EXPR_STATE
    | instantiation                                                         #INS_STATE
    | block                                                                 #BLOCK_STATE
    ;


expression
    : Identifier '(' expressionList? ')'                                    #FUNCTION_USE
    | expression '[' expression ']'                                         #ARRAY
    | expression '.' Identifier                                             #MEMBER_VARIABLE
    | expression '.' Identifier '(' expressionList? ')'                     #MEMBER_FUNCTION
    | Identifier                                                            #IDENTIFIER
    | Number                                                                #CONST_INT
    | TRUE                                                                  #CONST_BOOL
    | FALSE                                                                 #CONST_BOOL
    | NULL                                                                  #Null
    | ConstString                                                           #CONST_STRING
    | THIS                                                                  #SELF_POINTER
    | expression op=('++'|'--')                                             #POSTFIX
    | op=('++'|'--') expression                                             #PREFIX
    | op=('+'|'-') expression                                               #UNARY
    | op=('!'|'~') expression                                               #NOT
    | NEW creator                                                           #NEW_CREATOR
    | expression op=('*'|'/'|'%') expression                                #ARITHMETIC
    | expression op=('+'|'-') expression                                    #ARITHMETIC
    | expression op=('<<'|'>>') expression                                  #ARITHMETIC
    | expression op=('<' | '<=' | '>' | '>=') expression                    #RELATION
    | expression op=('==' | '!=') expression                                #RELATION
    | expression op='&' expression                                          #LOGIC_ARI
    | expression op='^' expression                                          #LOGIC_ARI
    | expression op='|' expression                                          #LOGIC_ARI
    | expression op='&&' expression                                         #LOGIC_RELATION
    | expression op='||' expression                                         #LOGIC_RELATION
    | <assoc=right> expression '=' expression                               #ASSIGN
    | '(' expression ')'                                                    #BRACKET
    ;

creator
    : creator '[]'
    | subCreator
    ;

subCreator
    : subCreator '[' expression ']'
    | class_name
    ;

expressionList
    : expression ',' expressionList
    | expression
    ;

instantiation
    : class_statement Identifier ';'                                        #NORMAL_INS
    | class_statement Identifier '=' expression ';'                         #ASSIGN_INS
    ;

class_statement
    : class_name                                                            #SINGLE_VAR
    | class_statement '[]'                                                  #ARRAY_VAR
    ;

class_name
    : BOOL
    | INT
    | STRING
    | VOID
    | Identifier
    ;

Number
    : [1-9][0-9]*
    | '0'
    ;

ConstString
    : '"'  (ESC|.)*? '"'
    ;

ESC : '\\"'
    | '\\\\'
    ;

BOOL    :   'bool';
INT     :   'int';
STRING  :   'string';
NULL    :   'null';
VOID    :   'void';
TRUE    :   'true';
FALSE   :   'false';
IF      :   'if';
ELSE    :   'else';
FOR     :   'for';
WHILE   :   'while';
BREAK   :   'break';
CONTINUE:   'continue';
RETURN  :   'return';
NEW     :   'new';
CLASS   :   'class';
THIS    :   'this';

Identifier : [a-zA-Z][a-zA-Z0-9_]* ;

WS
    : [ \t\r\n]+ -> skip
    ;

LineNote
    : '//'~[\r\n]* -> skip
    ;