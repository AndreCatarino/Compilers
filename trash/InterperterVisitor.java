import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class InterperterVisitor extends RobotLangBaseVisitor<ST>{
	
    @Override public ST visitMain(RobotLangParser.MainContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitChunk(RobotLangParser.ChunkContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitInstruction(RobotLangParser.InstructionContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitFunction_def(RobotLangParser.Function_defContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitWhile_loop(RobotLangParser.While_loopContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallNormalFor(RobotLangParser.CallNormalForContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallStepFor(RobotLangParser.CallStepForContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallForEach(RobotLangParser.CallForEachContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallChooseOneOf(RobotLangParser.CallChooseOneOfContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallChooseAnyOf(RobotLangParser.CallChooseAnyOfContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallIf(RobotLangParser.CallIfContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallIfElse(RobotLangParser.CallIfElseContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallMove(RobotLangParser.CallMoveContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallMoveStop(RobotLangParser.CallMoveStopContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitPrint(RobotLangParser.PrintContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallAssignableValue(RobotLangParser.CallAssignableValueContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallAssignableExpression(RobotLangParser.CallAssignableExpressionContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallAssignableAssignment(RobotLangParser.CallAssignableAssignmentContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallAssignableFunctionCall(RobotLangParser.CallAssignableFunctionCallContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallExprAddSub(RobotLangParser.CallExprAddSubContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallExprString(RobotLangParser.CallExprStringContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallExprNumber(RobotLangParser.CallExprNumberContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallExprId(RobotLangParser.CallExprIdContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallExprParent(RobotLangParser.CallExprParentContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitCallExprMultDiv(RobotLangParser.CallExprMultDivContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitBoolean_expression(RobotLangParser.Boolean_expressionContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitDeclaration(RobotLangParser.DeclarationContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitPlainAssignment(RobotLangParser.PlainAssignmentContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitInlineAssignment(RobotLangParser.InlineAssignmentContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitFunction_call(RobotLangParser.Function_callContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitValue(RobotLangParser.ValueContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitArray(RobotLangParser.ArrayContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitArray_init(RobotLangParser.Array_initContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitDict(RobotLangParser.DictContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitDict_content(RobotLangParser.Dict_contentContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitKeyvalue(RobotLangParser.KeyvalueContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitVartypes(RobotLangParser.VartypesContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitBool_comparator(RobotLangParser.Bool_comparatorContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitReturn_statement(RobotLangParser.Return_statementContext ctx) 
    { 
        return visitChildren(ctx); 
    }
	
    @Override public ST visitVar_identifier(RobotLangParser.Var_identifierContext ctx) 
    { 
        return visitChildren(ctx); 
    }
}