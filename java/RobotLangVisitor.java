// Generated from RobotLang.g4 by ANTLR 4.7.2

        import java.util.Map;
        import java.util.HashMap;
        import java.util.LinkedHashMap;
        import java.util.Set;
        import java.util.HashSet;
        import java.util.ArrayList;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RobotLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RobotLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(RobotLangParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#chunk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChunk(RobotLangParser.ChunkContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#import_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_statement(RobotLangParser.Import_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(RobotLangParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallNormalFunc}
	 * labeled alternative in {@link RobotLangParser#function_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallNormalFunc(RobotLangParser.CallNormalFuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallImportFunc}
	 * labeled alternative in {@link RobotLangParser#function_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallImportFunc(RobotLangParser.CallImportFuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#function_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_header(RobotLangParser.Function_headerContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#break_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_statement(RobotLangParser.Break_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#while_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_loop(RobotLangParser.While_loopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallNormalFor}
	 * labeled alternative in {@link RobotLangParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallNormalFor(RobotLangParser.CallNormalForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallStepFor}
	 * labeled alternative in {@link RobotLangParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStepFor(RobotLangParser.CallStepForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallForEach}
	 * labeled alternative in {@link RobotLangParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallForEach(RobotLangParser.CallForEachContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallChooseOneOf}
	 * labeled alternative in {@link RobotLangParser#choose}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallChooseOneOf(RobotLangParser.CallChooseOneOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallChooseAnyOf}
	 * labeled alternative in {@link RobotLangParser#choose}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallChooseAnyOf(RobotLangParser.CallChooseAnyOfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallIf}
	 * labeled alternative in {@link RobotLangParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallIf(RobotLangParser.CallIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallIfElse}
	 * labeled alternative in {@link RobotLangParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallIfElse(RobotLangParser.CallIfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallMove}
	 * labeled alternative in {@link RobotLangParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMove(RobotLangParser.CallMoveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallMoveStop}
	 * labeled alternative in {@link RobotLangParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMoveStop(RobotLangParser.CallMoveStopContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(RobotLangParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PlainAssignment}
	 * labeled alternative in {@link RobotLangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlainAssignment(RobotLangParser.PlainAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InlineAssignment}
	 * labeled alternative in {@link RobotLangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineAssignment(RobotLangParser.InlineAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallAssignableValue}
	 * labeled alternative in {@link RobotLangParser#assignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallAssignableValue(RobotLangParser.CallAssignableValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallAssignableExpression}
	 * labeled alternative in {@link RobotLangParser#assignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallAssignableExpression(RobotLangParser.CallAssignableExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExprAddSub}
	 * labeled alternative in {@link RobotLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExprAddSub(RobotLangParser.CallExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExprString}
	 * labeled alternative in {@link RobotLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExprString(RobotLangParser.CallExprStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallAssignableFunctionCall}
	 * labeled alternative in {@link RobotLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallAssignableFunctionCall(RobotLangParser.CallAssignableFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExprNumber}
	 * labeled alternative in {@link RobotLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExprNumber(RobotLangParser.CallExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExprId}
	 * labeled alternative in {@link RobotLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExprId(RobotLangParser.CallExprIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExprParent}
	 * labeled alternative in {@link RobotLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExprParent(RobotLangParser.CallExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExprMultDiv}
	 * labeled alternative in {@link RobotLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExprMultDiv(RobotLangParser.CallExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bool1comparator}
	 * labeled alternative in {@link RobotLangParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool1comparator(RobotLangParser.Bool1comparatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolTrue}
	 * labeled alternative in {@link RobotLangParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolTrue(RobotLangParser.BoolTrueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolFalse}
	 * labeled alternative in {@link RobotLangParser#boolean_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolFalse(RobotLangParser.BoolFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(RobotLangParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(RobotLangParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varvaluevalue}
	 * labeled alternative in {@link RobotLangParser#varvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarvaluevalue(RobotLangParser.VarvaluevalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vararray}
	 * labeled alternative in {@link RobotLangParser#varvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVararray(RobotLangParser.VararrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(RobotLangParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array_initString}
	 * labeled alternative in {@link RobotLangParser#array_init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_initString(RobotLangParser.Array_initStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code array_initNumber}
	 * labeled alternative in {@link RobotLangParser#array_init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_initNumber(RobotLangParser.Array_initNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueString}
	 * labeled alternative in {@link RobotLangParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueString(RobotLangParser.ValueStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueNumber}
	 * labeled alternative in {@link RobotLangParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueNumber(RobotLangParser.ValueNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vartypesInteger}
	 * labeled alternative in {@link RobotLangParser#vartypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVartypesInteger(RobotLangParser.VartypesIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vartypesReal}
	 * labeled alternative in {@link RobotLangParser#vartypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVartypesReal(RobotLangParser.VartypesRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vartypesString}
	 * labeled alternative in {@link RobotLangParser#vartypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVartypesString(RobotLangParser.VartypesStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#bool_comparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_comparator(RobotLangParser.Bool_comparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(RobotLangParser.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotLangParser#var_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_identifier(RobotLangParser.Var_identifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberInteger}
	 * labeled alternative in {@link RobotLangParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberInteger(RobotLangParser.NumberIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberReal}
	 * labeled alternative in {@link RobotLangParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberReal(RobotLangParser.NumberRealContext ctx);
}