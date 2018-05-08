grammar Mx;

all : program;

program
    : (typeDefine|instantiation|function)+
    ;

typeDefine
    : CLASS Identifier '{' program? '}'
    ;

function
    : class_statement? Identifier '(' parameter? ')' noScope_block
    ;

scope_block
    : '{' statement* '}'
    ;

noScope_block
    : '{' statement* '}'
    | statement
    ;

parameter
    : class_statement Identifier ',' parameter
    | class_statement Identifier
    ;

statement
    : IF '(' expression ')' noScope_block                                   #IF_STATE
    | IF '(' expression ')' noScope_block ELSE noScope_block                #IFELSE_STATE
    | FOR '(' first = expression? ';' second = expression? ';' third =expression? ')' noScope_block
                                                                            #FOR_STATE
    | WHILE '(' expression ')' noScope_block                                #WHILE_STATE
    | RETURN expression? ';'                                                #RETURN_STATE
    | BREAK ';'                                                             #BREAK_STATE
    | CONTINUE ';'                                                          #CONTINUE_STATE
    | expression ';'                                                        #EXPR_STATE
    | instantiation                                                         #INS_STATE
    | scope_block                                                           #BLOCK_STATE
    | ';'                                                                   #NONE
    ;


expression
    : Identifier '(' expressionList? ')'                                    #FUNCTION_USE
    | NEW wrongCreator                                                      #WRONG_CREATOR
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

wrongCreator
    : wrongCreator '[' ']'
    | wrongCreator '[' expression ']'
    | subCreator ('['']')+  '[' expression ']'
    ;

creator
    : creator '[' ']'
    | subCreator
    ;

subCreator
    : subCreator '[' expression ']'                                         #SUB_CREATOR
    | class_name '(' expressionList? ')'                                    #FUNCTION_NEW
    | class_name                                                            #TYPE_NEW
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
    | class_statement '[' ']'                                               #ARRAY_VAR
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