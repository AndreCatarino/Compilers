grammar RobotLang;

@header{
        import java.util.Map;
        import java.util.HashMap;
        import java.util.LinkedHashMap;
        import java.util.Set;
        import java.util.HashSet;
        import java.util.ArrayList;

}

@parser::members{
        public static SymbolTable symbolTable = new SymbolTable();

        public static Map<String,ArrayList<Type>> functionMap = new HashMap<>();

        public static Map<String,Type> returnTypes = new LinkedHashMap<>();

        public static int endfunctions;
}

main: import_statement* chunk* EOF;

/* chunk: (instruction NEWLINE | instruction | NEWLINE)+; */
chunk: instruction+;

import_statement: IMPORT_WORD IDENTIFIER;

instruction: move
           | function_def 
           | function_header 
           | while_loop 
           | for_loop 
           | assignment
           | function_call
           | choose 
           | if_statement 
           | print
           | return_statement // need to verify if it is inside function definition
           | declaration
           | break_statement // need to see if this is inside brakable loop
           ;

//function_def: FUNC function_name=IDENTIFIER '(' (arg_t+=vartypes arg+=IDENTIFIER)? (COMMA arg_t+=vartypes arg+=IDENTIFIER)* ')' COLON chunk (END|END_FUNCTION)
//            ;

function_def: FUNC function_name=IDENTIFIER '(' (declaration)? (COMMA declaration)* ')' COLON chunk (END|END_FUNCTION)               #CallNormalFunc
            | FUNC function_name=IDENTIFIER '(' (declaration)? (COMMA declaration)* ')' (FUNC_TYPE_ASSIGN return_value=vartypes)?       #CallImportFunc
            ;

function_header: FUNC function_name=IDENTIFIER '(' (declaration)? (COMMA declaration)* ')'
               ;

break_statement: BREAK;

while_loop: WHILE '(' boolean_expression ')' COLON chunk (END|END_WHILE)
          ;

for_loop: FOR '(' start_condition=assignment COMMA stop_condition=boolean_expression ')' COLON chunk (END|END_FOR)                       #CallNormalFor
        | FOR '(' start_condition=assignment COMMA stop_condition=boolean_expression COMMA step=expression ')' COLON chunk (END|END_FOR) #CallStepFor
        | FOR '('? element=IDENTIFIER IN list=var_identifier ')'? COLON chunk (END|END_FOR)                                              #CallForEach // need to verifiy if assignable is list
        ;

choose: ONE_OF COLON (CASE '(' boolean_expression ')' COLON if_chunk+=chunk)+ (ELSE COLON else_block=chunk)? (END|END_CHOOSE) #CallChooseOneOf
      | ANY_OF COLON (CASE '(' boolean_expression ')' COLON if_chunk+=chunk)+ (ELSE COLON else_block=chunk)? (END|END_CHOOSE) #CallChooseAnyOf
      ;

if_statement: IF '(' boolean_expression ')' COLON chunk (END|END_IF)                                                    #CallIf
            | IF '(' boolean_expression ')' COLON if_block=chunk ELSE COLON else_block=chunk (END|END_IF)       #CallIfElse
            ;

move: MOVE_WORD value1=number value2=number #CallMove
    | STOP_WORD                             #CallMoveStop
    ;

print: PRINT_WORD assignable;

assignment: id =var_identifier EQUALS_SYMBOL assignable #PlainAssignment
          | declaration EQUALS_SYMBOL assignable    #InlineAssignment
          ;

// elements that we can assign to variables
assignable returns[Type eType, boolean isArray]:
            varvalue               #CallAssignableValue
          | expression          #CallAssignableExpression 
          /* | boolean_expression  #CallAssignableBoolExpression */
          ;

expression returns[Type eType]: 
          number                                          #CallExprNumber
        | STRING                                            #CallExprString
        | function_call                                         #CallAssignableFunctionCall 
        | var_identifier                                        #CallExprId
        | '(' expr=expression ')'                           #CallExprParent
        | expr1=expression op=( MULT | DIV ) expr2=expression   #CallExprMultDiv
        | expr1=expression op=( PLUS | MINUS ) expr2=expression   #CallExprAddSub
        ;

boolean_expression: assignable bool_comparator assignable (bool_comparator assignable)* (op+=(AND|OR) boolean_expression)*     #Bool1comparator
                  | TRUE #BoolTrue
                  | FALSE #BoolFalse
                  ;


declaration returns[Type eType] : type=vartypes IDENTIFIER;


function_call returns[Type eType]: function_name=IDENTIFIER '(' args+=assignable? (COMMA args+=assignable)* ')';

varvalue returns[Type eType, boolean isArray] : 
        value     {$isArray = false;}     #varvaluevalue
        | array   {$isArray = true;}      #vararray
        ;

array returns[Type eType] : '[' array_init ']';

//Only allows for arrays of var types
array_init returns[Type eType]: 
          STRING (COMMA STRING)*      #array_initString
          | number (COMMA number)*      #array_initNumber
          ;

value returns[Type eType]: 
        STRING          #valueString
        | number        #valueNumber
        ;

vartypes returns[Type eType]: 
          INTWORD {$eType = new IntegerType();}         #vartypesInteger
        | REALWORD {$eType = new RealType();}           #vartypesReal
        | STRINGWORD{$eType = new StringType();}        #vartypesString
        ;

bool_comparator: comparator_name=(GREATER_THAN | SMALLER_THAN | EQUALS | DIFF_FROM | EQUAL_OR_GREATER | EQUAL_OR_SMALLER) 
                ;

return_statement: RETURN_WORD assignable
                ;


var_identifier returns[Type eType, Boolean isArray] : IDENTIFIER ('[' (number|var_identifier) ']')?;      //String for dict

number returns[Type eType]: 
          INTEGER  {$eType = new IntegerType();}      #numberInteger 
        | REAL {$eType = new RealType();}        #numberReal
        ;

//Extras
LINE_COMMENT: '//' .*? '\n' -> skip; //Java-Like comments
COMMENT: '/*' .*? '*/' -> skip;
WHITESPACE: [ \r]+ -> skip;
TAB: [\t]->skip;
NEWLINE: '\n' -> skip;

SKIPS: ( COMMENT | LINE_COMMENT | WHITESPACE) -> skip;

/* RESERVED_WORDS: ( FUNC | WHILE | FOR | BREAK | ONE_OF | ANY_OF | CASE | IF | ELSE | MOVE_WORD | STOP_WORD | PRINT_WORD | OR | AND | EQUALS_SYMBOL | GREATER_THAN | SMALLER_THAN | EQUALS | DIFF_FROM | DIV | MULT | PLUS | MINUS | COMMA ); */

//Tokens definition
FUNC:'function';
WHILE:'while';
FOR:'for';
BREAK:'break';
ONE_OF:'one of';
ANY_OF:'any of';
CASE:'case';
IF:'if';
ELSE:'else';
MOVE_WORD:'move';
STOP_WORD:'stop';
PRINT_WORD:'print';
OR:'or';
AND:'and';
EQUAL_OR_GREATER:'>=';
EQUAL_OR_SMALLER:'<=';
FUNC_TYPE_ASSIGN: '->';
EQUALS_SYMBOL:'=';
GREATER_THAN:'>';
SMALLER_THAN:'<';
EQUALS:'equals';
DIFF_FROM:'different from';
DIV:'/';
MULT:'*';
PLUS:'+';
MINUS:'-';
COMMA:',';
COLON:':';
END:'end';
END_FUNCTION:'endfunction';
END_FOR:'endfor';
END_WHILE:'endwhile';
END_CHOOSE:'endchoose';
END_IF:'endif';
RETURN_WORD:'return';
IN:'in';
INTWORD:'integer';
STRINGWORD:'string';
REALWORD:'real';
IMPORT_WORD:'import';
TRUE:'true';
FALSE:'false';

//var identifiers
IDENTIFIER: [a-zA-Z_]+[a-zA-Z0-9_]*;

//Var types
STRING: '\'' .*? '\''; //strings com plicas

REAL: INTEGER '.' [0-9]+; //aqui nao pode ser integer senao podiamos ter -5.-5
INTEGER: '-'? [0-9]+;   //com fragment da warning em var_identifier

ERROR: .;
