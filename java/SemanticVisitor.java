import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import static java.lang.System.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.RuleNode;
import java.util.ArrayList;
import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class SemanticVisitor extends RobotLangBaseVisitor<Boolean> {
	//Fazer tratamento com ErrorHandling e printErrors

  boolean in_breakable_loop = false;
  boolean in_return_statement_header = false;

  Symbol s = null;
  String assignable = null;

	@Override public Boolean visitMain(RobotLangParser.MainContext ctx) 
	{
		Boolean res = true;

		List<RobotLangParser.Import_statementContext> imports = ctx.import_statement();
		for(RobotLangParser.Import_statementContext i : imports){
			res = res && visit(i);
		}

                // System.out.println(RobotLangParser.symbolTable);

		List<RobotLangParser.ChunkContext> chunks = ctx.chunk();
		for(RobotLangParser.ChunkContext i : chunks){
			res = res && visit(i);
		}

		return res; 
	}
	
        private void visit_imported(String lib_name) {
          String file_name = "../src_code_ex/" + lib_name + ".rlh";
          InputStream in = null;
          try {
            in = new FileInputStream(new File(file_name));
          }
          catch (FileNotFoundException e) {
            System.out.println("Import library not found under ../src_code_ex/");
            return;
          }
          CharStream input = null;
          try {
            input = CharStreams.fromStream(in);
          }
          catch (IOException e) {
            System.out.println("Import library not found under ../src_code_ex/");
            return;
          }
          RobotLangLexer lexer = new RobotLangLexer(input);
          CommonTokenStream tokens = new CommonTokenStream(lexer);
          RobotLangParser parser = new RobotLangParser(tokens);
          ParseTree tree = parser.main();
          if (parser.getNumberOfSyntaxErrors() == 0) {
             SemanticVisitor visitor_import = new SemanticVisitor();
             // System.out.println("Visiting " + file_name);
             visitor_import.visit(tree);
          }
        }

        @Override public Boolean visitImport_statement(RobotLangParser.Import_statementContext ctx) {
          String library_name = ctx.IDENTIFIER().getText(); 
          // System.out.println("Importing " + library_name);
          visit_imported(library_name);
          return true;
        }

	@Override public Boolean visitChunk(RobotLangParser.ChunkContext ctx) 
	{
		Boolean res = true;
		List<RobotLangParser.InstructionContext> instr = ctx.instruction();
		for(RobotLangParser.InstructionContext i : instr){
			res = res && visit(i);
		}

		return res; 
	}

	@Override public Boolean visitBreak_statement(RobotLangParser.Break_statementContext ctx) { 
          if(!in_breakable_loop) {
            ErrorHandling.printError(ctx,"Break instruction can only be used inside a breakable loop");
            return false;
          }
          return true;
        }

	@Override public Boolean visitCallNormalFunc(RobotLangParser.CallNormalFuncContext ctx) 
	{
		Boolean res = true;

		//ver se functionname ja existe
		String func_name = ctx.function_name.getText();
		if(RobotLangParser.functionMap.containsKey(func_name))
		{
			ErrorHandling.printError(ctx,"The function \"" + func_name +"\" has already been declared!");
			return false;
		}
		
		RobotLangParser.symbolTable.setNewSymbolTable();		//criar nova symbol table com variaveis definidas apenas na funcao
		List<RobotLangParser.DeclarationContext> arguments = ctx.declaration();
		ArrayList<Type> args = new ArrayList<>();

		for (RobotLangParser.DeclarationContext d : arguments){
			res = res && visit(d);							//declarar variaveis nos argumentos (deve dar erro se 2 args com mesmo nome)
			

			String varID = d.IDENTIFIER().getText();		// arguments in function count as initialized
			Symbol symb = RobotLangParser.symbolTable.get(varID);
			symb.setValueDefined();
			RobotLangParser.symbolTable.put(varID,symb);
			
			args.add(d.vartypes().eType);
		}

		RobotLangParser.functionMap.put(func_name, args);

		RobotLangParser.returnTypes.put(func_name, null);

                // System.out.println(RobotLangParser.symbolTable);

		res = res && visit(ctx.chunk());	//visitar definicao da funcao

		if ( RobotLangParser.returnTypes.get(func_name) == null){
			RobotLangParser.returnTypes.put(func_name, voidType);
		}

		RobotLangParser.endfunctions += 1;
		RobotLangParser.symbolTable.setDefaultSymbolTable();	//regressar à symbolTable original
		return res; 
	}

	@Override public Boolean visitCallImportFunc(RobotLangParser.CallImportFuncContext ctx) 
	{ 
		Boolean res = true;

		//ver se functionname ja existe
		String func_name = ctx.function_name.getText();
		if(RobotLangParser.functionMap.containsKey(func_name))
		{
			ErrorHandling.printError(ctx,"The function \"" + func_name +"\" has already been declared!");
			return false;
		}
		
		RobotLangParser.symbolTable.setNewSymbolTable();		//criar nova symbol table com variaveis definidas apenas na funcao
		List<RobotLangParser.DeclarationContext> arguments = ctx.declaration();
		ArrayList<Type> args = new ArrayList<>();

		for (RobotLangParser.DeclarationContext d : arguments){
			res = res && visit(d);							//declarar variaveis nos argumentos (deve dar erro se 2 args com mesmo nome)
			
			if (!res)
				ErrorHandling.printError(ctx,"The variable \"" + d.IDENTIFIER().getText() +"\" was already declared in the arguments");

			String varID = d.IDENTIFIER().getText();		// arguments in function count as initialized
			Symbol symb = RobotLangParser.symbolTable.get(varID);
			symb.setValueDefined();
			RobotLangParser.symbolTable.put(varID,symb);
			
			args.add(d.vartypes().eType);
		}

		RobotLangParser.functionMap.put(func_name, args);

                in_return_statement_header = true;

		RobotLangParser.returnTypes.put(func_name, null);

                if(ctx.return_value != null)
                  visit(ctx.return_value);
                
                in_return_statement_header = true;

		// Type t = voidType;
		// if(RobotLangParser.returnTypes.containsKey(ret_val)){
		// 	t = RobotLangParser.returnTypes.get(ret_val);
		// }
		// else {
		// 	res = false;
		// 	ErrorHandling.printError("Your imported function " + func_name + " doesnt have a valid return type.");
		// }

		if ( RobotLangParser.returnTypes.get(func_name) == null){
			RobotLangParser.returnTypes.put(func_name, voidType);
		}

		RobotLangParser.endfunctions += 1;
		RobotLangParser.symbolTable.setDefaultSymbolTable();	//regressar à symbolTable original
		return res;  
	}


        @Override public Boolean visitFunction_header(RobotLangParser.Function_headerContext ctx) { 
		Boolean res = true;

		//ver se functionname ja existe
		String func_name = ctx.function_name.getText();
		if(RobotLangParser.functionMap.containsKey(func_name))
		{
			ErrorHandling.printError(ctx,"The function \"" + func_name +"\" has already been declared!");
			return false;
		}
		
		RobotLangParser.symbolTable.setNewSymbolTable();		//criar nova symbol table com variaveis definidas apenas na funcao
		List<RobotLangParser.DeclarationContext> arguments = ctx.declaration();
		ArrayList<Type> args = new ArrayList<>();

		for (RobotLangParser.DeclarationContext d : arguments){
			res = res && visit(d);							//declarar variaveis nos argumentos (deve dar erro se 2 args com mesmo nome)
			
			if (!res)
				ErrorHandling.printError(ctx,"The variable \"" + d.IDENTIFIER().getText() +"\" was already declared in the arguments");

			String varID = d.IDENTIFIER().getText();		// arguments in function count as initialized
			Symbol symb = RobotLangParser.symbolTable.get(varID);
			symb.setValueDefined();
			RobotLangParser.symbolTable.put(varID,symb);
			
			args.add(d.vartypes().eType);
		}

		RobotLangParser.functionMap.put(func_name, args);

		RobotLangParser.returnTypes.put(func_name, null);

		if ( RobotLangParser.returnTypes.get(func_name) == null){
			RobotLangParser.returnTypes.put(func_name, voidType);
		}

		RobotLangParser.endfunctions += 1;
		RobotLangParser.symbolTable.setDefaultSymbolTable();	//regressar à symbolTable original
		return res; 
        }
	
	@Override public Boolean visitWhile_loop(RobotLangParser.While_loopContext ctx) 
	{ 
                in_breakable_loop = true;
		//verificar se vartypes sao iguais
		//visit boolean expresson
		//visitar chunk
		//does this make sense?
		Boolean res = true;
		Boolean res1 = visit(ctx.boolean_expression());
		if (!res1){
			ErrorHandling.printError(ctx, "boolean expression is not correct!");
			res = false;
		}
		//Boolean res2 = visit(ctx.chunk());

		if (visit(ctx.chunk()) == null){
			ErrorHandling.printError(ctx, "Your while statement is incorrect!");
			res = false;
		}
		in_breakable_loop = false;
				//System.out.println("Visiting while: " + res);
		return res;
	}

	@Override public Boolean visitBoolTrue(RobotLangParser.BoolTrueContext ctx) { return true; }

	@Override public Boolean visitBoolFalse(RobotLangParser.BoolFalseContext ctx) { return true; }
	
	@Override public Boolean visitCallNormalFor(RobotLangParser.CallNormalForContext ctx) 
	{ 
                in_breakable_loop = true;
                RobotLangParser.symbolTable.setNewSymbolTable();
		//visit boolean expresson
		//checkar start stop conditions, visit chunk
		Boolean res = visit(ctx.chunk()) && visit(ctx.start_condition) && visit(ctx.stop_condition);
                in_breakable_loop = false;
                RobotLangParser.symbolTable.setDefaultSymbolTable();
		return res;
	}
	
	@Override public Boolean visitCallStepFor(RobotLangParser.CallStepForContext ctx) 
	{ 
                in_breakable_loop = true;
                RobotLangParser.symbolTable.setNewSymbolTable();
		//visit boolean expresson
		//checkar start stop conditions, visit chunk
		Boolean res = visit(ctx.start_condition) && visit(ctx.stop_condition) && visit(ctx.step);
		Boolean bool_chunk = visit(ctx.chunk());
		Type t1 = ctx.step.eType;
		if(!res || bool_chunk==null || t1.name() != "integer"){
			ErrorHandling.printError(ctx, "Your increment must be an integer!");
			res = false;
		}
                in_breakable_loop = false;
                RobotLangParser.symbolTable.setDefaultSymbolTable();
		return res;
	}
	
	@Override public Boolean visitCallForEach(RobotLangParser.CallForEachContext ctx) 
	{ 
		in_breakable_loop = true;
		RobotLangParser.symbolTable.setNewSymbolTable();
		//visit boolean expresson
		//check var type is iterable type, visit chunk
		// if(!(ctx.list instanceof Collection)){
		visit(ctx.list);
	

		RobotLangParser.symbolTable.put(ctx.element.getText(), RobotLangParser.symbolTable.getType(ctx.list.getText()));
		RobotLangParser.symbolTable.get(ctx.element.getText()).setValueDefined();
		Boolean res = visit(ctx.chunk());
		// System.out.println(RobotLangParser.symbolTable.isIterable(assignable));
		if(!RobotLangParser.symbolTable.isIterable(ctx.list.getText())) {
				ErrorHandling.printError(ctx, "You must iterate over an iterable Object. Example: Array");
				res = false;
		}
		in_breakable_loop = false;
		assignable = null;
		RobotLangParser.symbolTable.setDefaultSymbolTable();
		return res;
	}
	
	@Override public Boolean visitCallChooseOneOf(RobotLangParser.CallChooseOneOfContext ctx) 
	{ 
		//ver o numero de cases, visit chunk e visit chunk_block, verificar que tem mais que 1 case antes de else
		//visit boolean expresson
		Boolean res = true;
		RobotLangParser.ChunkContext else_block = ctx.else_block; 
		if(else_block != null){
			res = res && visit(ctx.else_block);
		}

		List<RobotLangParser.Boolean_expressionContext> bools = ctx.boolean_expression();
		List<RobotLangParser.ChunkContext> chunks = ctx.if_chunk;

		for(RobotLangParser.ChunkContext c : chunks){
			res = res && visit(c);
		}

		for(RobotLangParser.Boolean_expressionContext c : bools){
			res = res && visit(c);
		}

		//System.out.println("Visiting oneof: " + res);
		return res; 
	}
	
	@Override public Boolean visitCallChooseAnyOf(RobotLangParser.CallChooseAnyOfContext ctx) 
	{ 
		//ver o numero de cases, visit chunk e visit chunk_block, verificar que tem mais que 1 case antes de else
		//visit boolean expresson
		Boolean res = true;
		RobotLangParser.ChunkContext else_block = ctx.else_block; 
		if(else_block != null){
			res = res && visit(ctx.else_block);
		}

		List<RobotLangParser.Boolean_expressionContext> bools = ctx.boolean_expression();
		List<RobotLangParser.ChunkContext> chunks = ctx.if_chunk;

		for(RobotLangParser.ChunkContext c : chunks){
			res = res && visit(c);
		}

		for(RobotLangParser.Boolean_expressionContext c : bools){
			res = res && visit(c);
		}

		//System.out.println("Visiting anyof: " + res);
		return res; 
	}
	
	@Override public Boolean visitCallIf(RobotLangParser.CallIfContext ctx) 
	{ 
		//visit boolean expression, chunk
		Boolean res = visit(ctx.boolean_expression()) && visit(ctx.chunk());
				//System.out.println("Visiting if: " + res);
		return res; 
	}
	
	@Override public Boolean visitCallIfElse(RobotLangParser.CallIfElseContext ctx) 
	{ 
		//visit boolean expr, chunk 
		//check if else without ifs
		Boolean res = visit(ctx.boolean_expression()) && visit(ctx.if_block) && visit(ctx.else_block);
				//System.out.println("Visiting ifelse: " + res);
		return res; 
	}
	
	@Override public Boolean visitCallMove(RobotLangParser.CallMoveContext ctx) 
	{ 
		//check if two numbers
		//verificar se sao inteiros
		//ver se move 0 0 que e igual a stop?
		String speed1 = ctx.value1.getText();
		String speed2 = ctx.value2.getText();
		if(speed1==speed2 && speed1.equals("0")){
			ErrorHandling.printWarning("Instead of <move 0 0>, you should use <stop>");
		}
		return true;
	}
	
	@Override public Boolean visitCallMoveStop(RobotLangParser.CallMoveStopContext ctx) 
	{ 
		//check no other args
		//sera que devia verificar se devia estar a andar? 
		//Talvez nao e posso simplesmente parar algo ja parado
		return true; 
	}
	
	@Override public Boolean visitPrint(RobotLangParser.PrintContext ctx) 
	{ 
		//check no incompatible types or maybe contactenate, isto e feito no assignable?
		//check for prints vazios ou print newline mal usado
		Boolean res = visit(ctx.assignable());
		return res;
	}

	@Override public Boolean visitCallAssignableValue(RobotLangParser.CallAssignableValueContext ctx) { 
		Boolean res = visit(ctx.varvalue());

		if (res){
			ctx.eType = ctx.varvalue().eType;
			ctx.isArray = ctx.varvalue().isArray;
		}

		return res; 
	}
	
	@Override public Boolean visitCallAssignableExpression(RobotLangParser.CallAssignableExpressionContext ctx) { 
		Boolean res = visit(ctx.expression());

		if (res){
			ctx.eType = ctx.expression().eType;
			ctx.isArray = false;
		}

		return res; 
	}
	
	@Override public Boolean visitCallAssignableFunctionCall(RobotLangParser.CallAssignableFunctionCallContext ctx) { 
		Boolean res = visit(ctx.function_call());

		if (res){
			ctx.eType = ctx.function_call().eType;
		}

		return res; 
	}
	
	@Override public Boolean visitCallExprAddSub(RobotLangParser.CallExprAddSubContext ctx) { 
		Boolean res = visit(ctx.expr1) && visit(ctx.expr2);
		String op = ctx.op.getText();

		if (res){
			Type t1 = ctx.expr1.eType;
			Type t2 = ctx.expr2.eType;

			if (!t1.name().equals("void") && !t2.name().equals("void")){

				if (op.equals("-")){
					if (t1.isNumeric() && t2.isNumeric())		//so subtracoes entre numeros
						ctx.eType = fetchType(t1, t2);
					else{
						ErrorHandling.printError(ctx, "Cannot subtract Strings");
						res = false;
					}
				}
				else{
					ctx.eType = fetchType(t1, t2);				//soma ganha o tipo mais global
				}
			}
			else{
				res = false;
				ErrorHandling.printError(ctx, "Cannot perform operations on void type");
			}
		}

		return res;
	}
	
	@Override public Boolean visitCallExprString(RobotLangParser.CallExprStringContext ctx) { 
		ctx.eType = stringType;
		return true; 
	}
	
	@Override public Boolean visitCallExprNumber(RobotLangParser.CallExprNumberContext ctx) {
		Boolean res = visit(ctx.number());

		if (res){
			ctx.eType = ctx.number().eType;
		}

		return res; 
	}
	
	@Override public Boolean visitCallExprId(RobotLangParser.CallExprIdContext ctx) { 
		Boolean res = visit(ctx.var_identifier());

		if (res){
			Symbol s = RobotLangParser.symbolTable.get(ctx.var_identifier().IDENTIFIER().getText());
			if (!s.valueDefined()){
				ErrorHandling.printError(ctx, "variable \"" + s.name() + "\" is not initialized");
				res = false;
			}
			if (ctx.var_identifier().isArray){
				ErrorHandling.printError(ctx, "Cannot do this expression with array (variable \"" + ctx.var_identifier().IDENTIFIER().getText()+ "\")");
				res = false;
			}
			//String var = ctx.var_identifier().IDENTIFIER().getText();
			//Symbol s = RobotLangParser.symbolTable.get(var);
			ctx.eType = ctx.var_identifier().eType;
		}

		return res; 
	}	//Verificar se foi inicializado deve ser feito na regra var_identifier
	
	@Override public Boolean visitCallExprParent(RobotLangParser.CallExprParentContext ctx) { 
		Boolean res = visit(ctx.expr);

		if (res){
			ctx.eType = ctx.expr.eType;
		}
		return res; 
	}
	
	@Override public Boolean visitCallExprMultDiv(RobotLangParser.CallExprMultDivContext ctx) { 

		Boolean res = visit(ctx.expr1) && visit(ctx.expr2);
		String op = ctx.op.getText();

		if (res){

			Type t1 = ctx.expr1.eType;
			Type t2 = ctx.expr2.eType;

			if (!t1.name().equals("void") && !t2.name().equals("void")){

				if (op.equals("/")){
					if (t1.isNumeric() && t2.isNumeric())		//so divisoes entre numeros
						ctx.eType = fetchType(t1, t2);
					else{
						res = false;
						ErrorHandling.printError(ctx, "Cannot divide Strings");
					}
				}
				else{
					if (t1.isNumeric() || t2.isNumeric())		//mult pode ser entre numeros || 1 num e 1 string
						ctx.eType = fetchType(t1, t2);
					else{
						res = false;
						ErrorHandling.printError(ctx, "Cannot multiply String * String");
					}
				}
			}
			else{
				res = false;
				ErrorHandling.printError(ctx, "Cannot perform operations on void type");
			}
		}

		return res;
	}

	@Override public Boolean visitBool1comparator(RobotLangParser.Bool1comparatorContext ctx) {
		Boolean res = true;

		int cont = 0;
		Type last = null;

		List<RobotLangParser.AssignableContext> assign = ctx.assignable();
		List<RobotLangParser.Bool_comparatorContext> comparators = ctx.bool_comparator();
		res = res && visit(assign.get(0));

		for (int i = 1; i<assign.size(); i++){
			res = res && visit(assign.get(i));

			if(!res){
				break;
			}

			if (assign.get(i).eType.isNumeric() && assign.get(i-1).eType.isNumeric()){	//2 numeros
				//OK
			}
			else if (!assign.get(i).eType.isNumeric() && !assign.get(i).eType.isNumeric()){	//2 strings
				if (comparators.get(i-1).getText().equals("different from") || comparators.get(i-1).getText().equals("equals")){
					//OK
				}
				else{
					ErrorHandling.printError(ctx,"Invalid operator for type string");
					res = false;
				}
			}
			else{
				ErrorHandling.printError(ctx,"Cannot compare integer/real with string");
				res = false;
			}

		}
		
		for (RuleNode r : ctx.boolean_expression()){
			res = res && visit(r);
		}

		return res;
	}
	
	@Override public Boolean visitDeclaration(RobotLangParser.DeclarationContext ctx) { 
		if (RobotLangParser.symbolTable.exists(ctx.IDENTIFIER().getText())){	//var já foi declarada
			ErrorHandling.printError(ctx, "Variable \""+ctx.IDENTIFIER().getText()+"\" already declared!");
			return false;
		}
		else{
			Type t = ctx.vartypes().eType;
			RobotLangParser.symbolTable.put(ctx.IDENTIFIER().getText(),t);
			ctx.eType = t;
                        s = RobotLangParser.symbolTable.get(ctx.IDENTIFIER().getText());
                        // System.out.println("Declaring " + ctx.IDENTIFIER().getText());
		}

		boolean res = visit(ctx.vartypes());
                return res;
	}


	@Override public Boolean visitPlainAssignment(RobotLangParser.PlainAssignmentContext ctx) { 
		
                s = RobotLangParser.symbolTable.get(ctx.id.IDENTIFIER().getText());
		Boolean res = visit(ctx.assignable()) && visit(ctx.var_identifier());
                s = null;
		//String varID = ctx.id.getText();

		if (res){
			String varID = ctx.id.IDENTIFIER().getText();

                        // System.out.println(RobotLangParser.symbolTable);

			if (!RobotLangParser.symbolTable.exists (varID)) {
				ErrorHandling.printError(ctx,"Variable \"" + varID + "\" was not declared!");
                                res=false;
			}
			else{
				Symbol symb = RobotLangParser.symbolTable.get(varID);

				if (symb.isArray() == true && ctx.assignable().isArray==false){
					ErrorHandling.printError(ctx, "Variable \"" + varID + "\" was declared as array");
					res = false;
				}
				else if (symb.isArray() == false && ctx.assignable().isArray==true){
					ErrorHandling.printError(ctx, "you have to initialize a variable as an array in the same line that you declared it");
					res = false;
				}
				else if (!(symb.type().conformsTo(ctx.assignable().eType))){
					ErrorHandling.printError(ctx, "assignment type does not conform to variable \"" + varID + "\" type");
					res = false;
				}
				else{

					symb.setValueDefined();
					RobotLangParser.symbolTable.put(varID,symb);
				}
	
			}
		}

		return res;
		
	}
	
	@Override public Boolean visitInlineAssignment(RobotLangParser.InlineAssignmentContext ctx) { 

		Boolean res = visit(ctx.declaration()) && visit(ctx.assignable());

		if (res){
			
			Type t1 = ctx.assignable().eType;
			Type t2 = ctx.declaration().eType;

			if (!(t2.conformsTo(t1))){
				ErrorHandling.printError(ctx, "assignment type does not conform to variable \"" + ctx.declaration().IDENTIFIER().getText() + "\" type");
				res = false;
			}
			else{
				String varID = ctx.declaration().IDENTIFIER().getText();

				Symbol symb = RobotLangParser.symbolTable.get(varID);

				symb.setValueDefined();

				if (ctx.assignable().isArray == true){
					symb.setArray();
					symb.setIterable();
				}
				RobotLangParser.symbolTable.put(varID,symb);
			}
		}

				//System.out.println("Visiting inlineassignment: " + res);
                s = null;
		return res;

	}
	
	@Override public Boolean visitFunction_call(RobotLangParser.Function_callContext ctx) { 
		//just checking if it is already declared
		Boolean res = true;
		String func_name = ctx.function_name.getText();

		if(! RobotLangParser.functionMap.containsKey(func_name)){
			ErrorHandling.printError(ctx,"The function \"" + func_name + "\" isn't declared!" );
			res = false;
		}
		else{

			List<RobotLangParser.AssignableContext> args = ctx.assignable();
			ArrayList<Type> funcT = RobotLangParser.functionMap.get(func_name);

			if (args.size() != funcT.size()){	//number of args
				res = false;
				ErrorHandling.printError(ctx,"Missmatched number of arguments in function \"" + func_name + "\"" );
			}
			else{	//type of args

				for (int i = 0 ; i < funcT.size(); i++ ){
					res = res && visit(args.get(i));
					
					if (!res)
						break;

					if (!(funcT.get(i).name().equals(args.get(i).eType.name()))){
						res = false;
						ErrorHandling.printError(ctx,"Missmatched type of argument in function \"" + func_name + "\" \n Expecting: \"" + funcT.get(i).name() + "\" but found \"" + args.get(i).eType.name() + "\"" );
					}
					
				}
				
			}

			ctx.eType = RobotLangParser.returnTypes.get(func_name);
		}
				//System.out.println("Visiting functioncall: " + res);
		return res;
	}
	
	@Override public Boolean visitVarvaluevalue(RobotLangParser.VarvaluevalueContext ctx) { 
		Boolean res = visit(ctx.value());
				//System.out.println("Visiting vararray: " + res);

		if (res){
			ctx.eType = ctx.value().eType;
		}
		return res; 
	}
	
	@Override public Boolean visitVararray(RobotLangParser.VararrayContext ctx) { 

		Boolean res = visit(ctx.array());
				//System.out.println("Visiting vararray: " + res);

		if (res){
			ctx.eType = ctx.array().eType;
		}
		return res; 
	 }

	@Override public Boolean visitArray(RobotLangParser.ArrayContext ctx) { 
		Boolean res = visit(ctx.array_init());
				//System.out.println("Visiting array: " + res);

		if (res){
			ctx.eType = ctx.array_init().eType;
		}
                if(s != null)
                  s.setIterable();
		return res; 
	}
	
	@Override public Boolean visitArray_initString(RobotLangParser.Array_initStringContext ctx) { 

		ctx.eType = stringType;
		return true; 
	 }
	
	@Override public Boolean visitArray_initNumber(RobotLangParser.Array_initNumberContext ctx) { 

		List<RobotLangParser.NumberContext> numbers = ctx.number();
		
		Boolean res = true;
		ctx.eType = integerType;

		for (RobotLangParser.NumberContext r : numbers){
			res = res && visit(r);

			if (r.eType.name.equals("real"))
				ctx.eType = realType;

		}
				//System.out.println("Visiting ArrayInitNUmber: " + res);

		return res; 
	 }
	
	
	@Override public Boolean visitValueString(RobotLangParser.ValueStringContext ctx) { ctx.eType = stringType; return true; }

	@Override public Boolean visitValueNumber(RobotLangParser.ValueNumberContext ctx) { ctx.eType = ctx.number().eType; return true; }
	
	@Override public Boolean visitVartypesInteger(RobotLangParser.VartypesIntegerContext ctx) { 
          if(in_return_statement_header) {
            for (String s : RobotLangParser.returnTypes.keySet() ){
                    if (RobotLangParser.returnTypes.get(s) == null)
                            RobotLangParser.returnTypes.put(s, integerType);
            }
          }
          return true; 
        }
	
	@Override public Boolean visitVartypesReal(RobotLangParser.VartypesRealContext ctx) { 
          if(in_return_statement_header) {
            for (String s : RobotLangParser.returnTypes.keySet() ){
                    if (RobotLangParser.returnTypes.get(s) == null)
                            RobotLangParser.returnTypes.put(s, realType);
            }
          }
          return true; 
        }
	
	@Override public Boolean visitVartypesString(RobotLangParser.VartypesStringContext ctx) { 
          if(in_return_statement_header) {
            for (String s : RobotLangParser.returnTypes.keySet() ){
                    if (RobotLangParser.returnTypes.get(s) == null)
                            RobotLangParser.returnTypes.put(s, stringType);
            }
          }
          return true; 
        }
	
	@Override public Boolean visitBool_comparator(RobotLangParser.Bool_comparatorContext ctx) { 
		
		
		return true;	
	
	}
	
	@Override public Boolean visitReturn_statement(RobotLangParser.Return_statementContext ctx) { 
		
		Boolean res = visit(ctx.assignable());
				//System.out.println("Visiting Return: " + res);
		if (res){
			if (RobotLangParser.endfunctions == RobotLangParser.functionMap.size() || RobotLangParser.functionMap.size()==0){
				res = false;
				ErrorHandling.printError(ctx,"Can only return inside function");
			}
			else{

				Set<String> s = RobotLangParser.returnTypes.keySet();
				String lastfunction = "";
				for (String name : s){ lastfunction = name;	}

				if (RobotLangParser.returnTypes.get(lastfunction) == null){
					RobotLangParser.returnTypes.put(lastfunction, ctx.assignable().eType);
				}
				else if (RobotLangParser.returnTypes.get(lastfunction).name() != ctx.assignable().eType.name()){
					ErrorHandling.printError(ctx,"Cannot return 2 different types: \n 1st type was \"" + RobotLangParser.returnTypes.get(lastfunction).name() +"\", but now returning \"" + ctx.assignable().eType.name());
					res = false;
				}	
				
				
				/*for (String s : RobotLangParser.returnTypes.keySet() ){
					if (RobotLangParser.returnTypes.get(s) == null)
						RobotLangParser.returnTypes.put(s, ctx.assignable().eType);
				}*/
			}
			
		}

		return res;
		
	}

	
	@Override public Boolean visitVar_identifier(RobotLangParser.Var_identifierContext ctx) 
	{ 
		Boolean res = false;

                // System.out.println("WHY");
                // System.out.println(RobotLangParser.symbolTable);

		if (RobotLangParser.symbolTable.exists(ctx.IDENTIFIER().getText())){	//var já foi declarada (o correcto)
			Boolean varArrayelement;
			try{
				visit(ctx.number());
				varArrayelement = true;
				if (ctx.number().eType.name() != "integer"){
					ErrorHandling.printError(ctx,"Array access is done with integers");
					return false;
				}
			}
			catch(Exception e){		//no number
				try{
					visit(ctx.var_identifier());
					varArrayelement = true;
					if (ctx.var_identifier().eType.name() != "integer"){
						ErrorHandling.printError(ctx,"Array access is done with integers, your variable is not");
						return false;
					}
				}
				catch(Exception e2){
					varArrayelement = false;
				}
			}
			if (RobotLangParser.symbolTable.get(ctx.IDENTIFIER().getText()).isArray()==false && varArrayelement == true){
				ErrorHandling.printError(ctx,"Variable \"" + ctx.IDENTIFIER().getText() + "\" wasn't initialized as an array!");
			}
			else if (RobotLangParser.symbolTable.get(ctx.IDENTIFIER().getText()).isArray()==true && varArrayelement == false){
				ctx.isArray = true;
				res = true;
			}
			else{
				res = true;
				ctx.eType = RobotLangParser.symbolTable.get(ctx.IDENTIFIER().getText()).type();
				assignable = ctx.IDENTIFIER().getText();
				ctx.isArray = false;

			}
		}
		else
			ErrorHandling.printError(ctx,"Variable \"" + ctx.IDENTIFIER().getText() + "\" was not declared!");
		
		//System.out.println(ctx.isArray);
		return res; 
	}

	@Override public Boolean visitNumberInteger(RobotLangParser.NumberIntegerContext ctx) { return true; }
	
	@Override public Boolean visitNumberReal(RobotLangParser.NumberRealContext ctx) { return true; }

	
	private Type fetchType(Type t1, Type t2)
   {
      Type res = null;
      if (t1.isNumeric() && t2.isNumeric())
      {
         if ("real".equals(t1.name()))
            res = t1;
         else if ("real".equals(t2.name()))
            res = t2;
         else
            res = t1;
      }
      else if ("string".equals(t1.name()) || "string".equals(t2.name()))
         res = stringType;
      return res;
   	}

	private final RealType realType = new RealType();
      private final IntegerType integerType = new IntegerType();
	private final StringType stringType = new StringType();
	private final VoidType voidType = new VoidType();
}
