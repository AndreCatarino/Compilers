
import java.util.ArrayList;
import java.io.*;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.Token;
import java.util.List;
import org.stringtemplate.v4.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
// import org.stringtemplate.language.*;


public class STVisitor extends RobotLangBaseVisitor<String> {
  String target = "c";
  STGroup group = new STGroupFile("../grammar/StringTemplates/c_templates.stg");

  PrintWriter output_stream = null;
  // try {
  // }
  // catch (FileNotFoundException e) {
  //   System.out.println("Output file nor found!");
  //   System.exit(1);
  // }

  ST module = null;

  ST instruction = null;

  ST function_definition = null;
  ST function_header = null;
  boolean in_ret_statement = false;

  ST while_loop = null;

  ST for_loop = null;
  String for_var = null; // variable that defines the for loop

  ST if_block = null;
  ST else_if_block = null;
  ST else_block = null;

  ST motors = null;

  ST declaration = null;

  ST print = null;

  //public boolean validTarget(String target) {
  //  File f = new File("../grammar/StringTemplates/" + target + "_templates.stg");

  //  return ("c".equalsIgnoreCase(target)) && f.exists() && f.isFile() && f.canRead();
  //}

  //public void setTarget(String target) {
  //    assert validTarget(target);

  //    this.target = target;
  //}

  @Override public String visitMain(RobotLangParser.MainContext ctx) {
    // assert validTarget(target);

    // module = group.getInstanceOf("module");

    String output = "";
    try {
      output_stream = new PrintWriter("../output/o.c", "UTF-8");
    }
    catch (Exception e) {
      System.out.println("Output file not found!");
      System.exit(0);
    }
    // catch (IOException ioe) {
    //   System.out.println("Output file not found!");
    //   System.exit(0);
    // }

    List<RobotLangParser.Import_statementContext> imports = ctx.import_statement();
    for(RobotLangParser.Import_statementContext i : imports)
      output += visit(i);

    output += "\n";
    
    List<RobotLangParser.ChunkContext> chunks = ctx.chunk();
    for(RobotLangParser.ChunkContext c : chunks)
      output += visit(c);
    
    //for(ChunkContext c : listaInstr){
    //  output += c + " ";
    //}

    // module.add("main", listaInstr);

    // String output = module.render();
    // System.out.println(output);
    
    // module = null;
    
    System.out.println(output);
    // try {
    output_stream.println(output);
    output_stream.close();
    // }
    // catch (FileNotFoundException e) {
    //   System.out.println("Output file not found!");
    //   System.exit(0);
    // }
    // catch (IOException ioe) {
    //   System.out.println("Output file not found!");
    //   System.exit(0);
    // }

    return output;
  }

  @Override public String visitChunk(RobotLangParser.ChunkContext ctx) {
    List<RobotLangParser.InstructionContext> instructions = ctx.instruction();  
    String output = "";
    for(RobotLangParser.InstructionContext i : instructions)
      output += visit(i);
    return output;
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
       STVisitor visitor_import = new STVisitor();
       visitor_import.visit(tree);
    }
  }

  @Override public String visitImport_statement(RobotLangParser.Import_statementContext ctx) {
    String library_name = ctx.IDENTIFIER().getText(); 
    ST import_statement = group.getInstanceOf("import_statement");
    import_statement.add("library", library_name);
    visit_imported(library_name);
    return import_statement.render();
  }

  @Override public String visitInstruction(RobotLangParser.InstructionContext ctx) {
    String instr = visitChildren(ctx); 
    instruction = group.getInstanceOf("instruction");;

    instruction.add("instruction", instr);

    String output = instruction.render();
    // System.out.println(output);

    instruction = null;
    return output;
  }

  @Override public String visitCallNormalFunc(RobotLangParser.CallNormalFuncContext ctx) 
  {
    function_definition = group.getInstanceOf("function_definition");

    String function_name = ctx.function_name.getText();

    // String first_arg_type = visit(ctx.arg_t);
    // String first_arg = ctx.arg.getText();
    // function_definition.add("args", first_arg_type + " " + first_arg);

    // List<RobotLangParser.VartypesContext> args_type_list_nodes = ctx.arg2_t;
    // List<RuleNode> args_list_nodes = ctx.arg2;

    /*List<RobotLangParser.VartypesContext> args_type_list_nodes = ctx.declaration();
    List<Token> args_list_nodes = ctx.declaration();*/

    List<RobotLangParser.DeclarationContext> args = ctx.declaration();
    
    function_definition.add("return_type", "void");

    String func_body = visit(ctx.chunk());
    // System.out.println(func_body);

    function_definition.add("function_name", function_name);
    for(int i = 0; i < args.size(); i++) {
      String arg = visit(args.get(i).vartypes()) + " " + args.get(i).IDENTIFIER().getText();
      function_definition.add("args", arg);
    }
    function_definition.add("body", func_body);

    String output = function_definition.render();
    // System.out.println(output);

    function_definition = null;
    return output;
  }

  @Override public String visitCallImportFunc(RobotLangParser.CallImportFuncContext ctx) 
  {
    function_header = group.getInstanceOf("function_header");

    String function_name = ctx.function_name.getText();
    List<RobotLangParser.DeclarationContext> args = ctx.declaration();
    
    String ret_val;
    if(ctx.return_value != null)
      ret_val = ctx.return_value.getText();
    else
      ret_val = "void";

    function_header.add("return_type", ret_val);
    // System.out.println(func_body);

    function_header.add("function_name", function_name);
    for(int i = 0; i < args.size(); i++) {
      String arg = visit(args.get(i).vartypes()) + " " + args.get(i).IDENTIFIER().getText();
      function_header.add("args", arg);
    }

    String output = function_header.render();
    // System.out.println(output);

    function_header = null;
    return output; 
  }
	
  @Override public String visitFunction_header(RobotLangParser.Function_headerContext ctx) { 
    ST function_header = group.getInstanceOf("function_header");

    String function_name = ctx.function_name.getText();

    // String first_arg_type = visit(ctx.arg_t);
    // String first_arg = ctx.arg.getText();
    // function_definition.add("args", first_arg_type + " " + first_arg);

    // List<RobotLangParser.VartypesContext> args_type_list_nodes = ctx.arg2_t;
    // List<RuleNode> args_list_nodes = ctx.arg2;

    /*List<RobotLangParser.VartypesContext> args_type_list_nodes = ctx.declaration();
    List<Token> args_list_nodes = ctx.declaration();*/

    List<RobotLangParser.DeclarationContext> args = ctx.declaration();
    
    function_header.add("return_type", "void");

    function_header.add("function_name", function_name);
    for(int i = 0; i < args.size(); i++) {
      String arg = visit(args.get(i).vartypes()) + " " + args.get(i).IDENTIFIER().getText();
      function_header.add("args", arg);
    }

    String output = function_header.render();
    // System.out.println(output);

    function_header = null;
    return output;
  }
	
  @Override public String visitWhile_loop(RobotLangParser.While_loopContext ctx) {
    while_loop = group.getInstanceOf("while_loop");

    String boolean_expression = visit(ctx.boolean_expression());
    String while_body = visit(ctx.chunk());

    while_loop.add("condition", boolean_expression);
    while_loop.add("body", while_body);

    String output = while_loop.render();
    // System.out.println(output);

    while_loop = null;
    return output;
  }
	
  @Override public String visitCallNormalFor(RobotLangParser.CallNormalForContext ctx) {
    for_loop = group.getInstanceOf("for_loop");

    String start_condition = visit(ctx.start_condition);
    String stop_condition = visit(ctx.stop_condition);
    String body = visit(ctx.chunk());
    String step = for_var + "++";

    for_loop.add("start_condition", start_condition);
    for_loop.add("finish_condition", stop_condition);
    for_loop.add("step", step);
    for_loop.add("body", body);

    String output = for_loop.render();
    // System.out.println(output);

    for_loop = null;
    for_var = null;
    return output;
  }
	
  @Override public String visitCallStepFor(RobotLangParser.CallStepForContext ctx) {
    for_loop = group.getInstanceOf("for_loop");

    String start_condition = visit(ctx.start_condition);
    String stop_condition = visit(ctx.stop_condition);
    String body = visit(ctx.chunk());
    String step = for_var + "+=" + visit(ctx.step);

    for_loop.add("start_condition", start_condition);
    for_loop.add("finish_condition", stop_condition);
    for_loop.add("step", step);
    for_loop.add("body", body);

    String output = for_loop.render();
    // System.out.println(output);

    for_loop = null;
    for_var = null;
    return output;
  }
	
  @Override public String visitCallForEach(RobotLangParser.CallForEachContext ctx) {
    for_loop = group.getInstanceOf("for_loop");

    String element = ctx.element.getText();
    String list = visit(ctx.list);

    String start_condition = "int i = 0";
    String stop_condition = "i < sizeof(" + list + ")/sizeof(" + list + "[0])";
    String step = "i++";
    //Este metodo nao pode estar a fazer estas chamadas estaticas
    //System.out.println(element);
    String body = RobotLangParser.symbolTable.getTypeC(list) + " " + element + " = " + list + "[i];\n" + visit(ctx.chunk());

    for_loop.add("start_condition", start_condition);
    for_loop.add("finish_condition", stop_condition);
    for_loop.add("step", step);
    for_loop.add("body", body);

    String output = for_loop.render();
    // System.out.println(output);

    for_loop = null;
    for_loop = null;
    for_var = null;
    return output;
  }
	
  @Override public String visitCallChooseOneOf(RobotLangParser.CallChooseOneOfContext ctx) {
    ST if_block_local = group.getInstanceOf("if_block");
    else_if_block = group.getInstanceOf("else_if_block");
    ST else_block_local = group.getInstanceOf("else_block");

    List<RobotLangParser.Boolean_expressionContext> boolean_expressions_nodes = ctx.boolean_expression(); // should return list of bool exprs nodes

    ArrayList<String> boolean_expressions = new ArrayList<>(); 
    for (RobotLangParser.Boolean_expressionContext b : boolean_expressions_nodes) {
      boolean_expressions.add(visit(b));
    }

    List<RobotLangParser.ChunkContext> chunks_nodes = ctx.if_chunk; // should return list of bool exprs nodes
    ArrayList<String> chunks = new ArrayList<>(); 
    for(RobotLangParser.ChunkContext c : chunks_nodes)
      chunks.add(visit(c));

    String else_chunk = null;
    if (ctx.else_block != null)
      else_chunk = visit(ctx.else_block);

    String output = "";

    // add if statement
    if_block_local.add("boolean_expression", boolean_expressions.get(0));
    // boolean_expressions.remove(0);
    if_block_local.add("chunk", chunks.get(0));
    // if_chunk.remove(0);
    output = output + if_block_local.render();
    if_block_local = null;

    // add else_if_statements
    for(int i = 1; i < boolean_expressions.size(); i++) {
      else_if_block = group.getInstanceOf("else_if_block");
      else_if_block.add("boolean_expression", boolean_expressions.get(i));
      else_if_block.add("chunk", chunks.get(i));
      output = output + else_if_block.render();
      else_if_block = null;
    }

    // add else statement
    if(else_chunk != null) {
      else_block_local.add("chunk", else_chunk);
      output = output + else_block_local.render();
      else_block_local = null;
    }

    // System.out.println(output);

    return output;
  }
	
  @Override public String visitCallChooseAnyOf(RobotLangParser.CallChooseAnyOfContext ctx) {
    if_block = group.getInstanceOf("if_block");
    else_block = group.getInstanceOf("else_block");
    
    List<RobotLangParser.Boolean_expressionContext> boolean_expressions_nodes = ctx.boolean_expression(); // should return list of bool exprs nodes

    ArrayList<String> boolean_expressions = new ArrayList<>(); 
    for (RobotLangParser.Boolean_expressionContext b : boolean_expressions_nodes) {
      boolean_expressions.add(visit(b));
    }

    // String if_chunk = visit(ctx.if_chunk); // should return list of bool exprs
    String else_chunk = visit(ctx.else_block);

    String output = "";

    // add if statements
    for(int i = 0; i < boolean_expressions.size(); i++) {
      if_block = group.getInstanceOf("if_block");
      if_block.add("boolean_expression", boolean_expressions.get(i));
      if_block.add("chunk", boolean_expressions.get(i));
      output = output + if_block.render();
      if_block = null;
    }

    // add else statement
    else_block.add("chunk", else_chunk);
    output = output + else_block.render();
    else_block = null;

    // System.out.println(output);

    if_block = null;
    else_block = null;
    return output;
  }
	
	
	
  @Override public String visitCallIf(RobotLangParser.CallIfContext ctx) {
    if_block = group.getInstanceOf("if_block");

    String boolean_expression = visit(ctx.boolean_expression());
    String chunk = visit(ctx.chunk());

    if_block.add("boolean_expression", boolean_expression);
    if_block.add("chunk", chunk);

    String output = if_block.render();
    //System.output.println(output);                                                          ??????????????????

    if_block = null;
    return output;
  }
	
  @Override public String visitCallIfElse(RobotLangParser.CallIfElseContext ctx) {
    if_block = group.getInstanceOf("if_block");
    else_block = group.getInstanceOf("else_block");

    String boolean_expression = visit(ctx.boolean_expression());
    String if_chunk = visit(ctx.if_block);
    String else_chunk = visit(ctx.else_block); //why tf isnt this working

    if_block.add("boolean_expression", boolean_expression);
    if_block.add("chunk", if_chunk);

    else_block.add("chunk", else_chunk);

    String output = if_block.render() + else_block.render();
    // System.out.println(output);

    if_block = null;
    else_block = null;
    return output;
  }
	
  
	
  @Override public String visitCallMove(RobotLangParser.CallMoveContext ctx) 
  { 
    motors = group.getInstanceOf("motors");
    
    String val1 = visit(ctx.value1);
    String val2 = visit(ctx.value2);
    String apply_times = "1";

    motors.add("val1", val1);
    motors.add("val2", val2);
    motors.add("apply_times", apply_times);


    String output = motors.render();
    // System.out.println(output);

    motors = null;
    return output; 
  }
	
  @Override public String visitCallMoveStop(RobotLangParser.CallMoveStopContext ctx) 
  { 
    motors = group.getInstanceOf("motors");
    String val1 = "0";
    String val2 = "0";
    String apply_times = "5";

    motors.add("val1", val1);
    motors.add("val2", val2);
    motors.add("apply_times", apply_times);


    String output = motors.render();
    // System.out.println(output);

    motors = null;
    return output;
  }
	
  @Override public String visitPrint(RobotLangParser.PrintContext ctx) 
  { 
    print = group.getInstanceOf("print");
    String strVal = visit(ctx.assignable());
    String printType = ctx.assignable().eType.name();

    // System.out.println(printType);

    print.add("string", strVal);
    print.add("type", printType);

    String output = print.render().replace("\'","\"");
    //System.out.println("PRINTING: " + output);

    print = null;
    return output;
  }
	
  @Override public String visitCallExprAddSub(RobotLangParser.CallExprAddSubContext ctx) 
  { 
    String val1 = visit(ctx.expr1);
    String val2 = visit(ctx.expr2);
    String op = ctx.op.getText();

    return val1 + " " + op + " " + val2;
  }
	
  @Override public String visitCallExprMultDiv(RobotLangParser.CallExprMultDivContext ctx) 
  { 
    String val1 = visit(ctx.expr1);
    String val2 = visit(ctx.expr2);
    String op = ctx.op.getText();

    return val1 + " " + op + " " + val2;
  }
	
  @Override public String visitBool1comparator(RobotLangParser.Bool1comparatorContext ctx) 
  { 

    // e1=assignable comparator=bool_comparator e2=assignable  
    // (comp2=bool_comparator assignable)*  (op=(AND|OR) bool_expression)*
    
    //String e1 = visit(ctx.e1);
    //String comparator = visit(ctx.comparator);
    //String e2 = visit(ctx.e2);
    //String output = "";

    //List<RobotLangParser.Bool_comparatorContext> comp2 = ctx.comp2; 
    //List<RobotLangParser.AssignableContext> extra_a = ctx.extra_a; 

    //ArrayList<String> boolean_expressions = new ArrayList<>(); 
    //for (RobotLangParser.Boolean_expressionContext b : boolean_expressions_nodes) {
    //  boolean_expressions.add(visit(b));
    //}

    //List<RuleNode> boolean_expressions = visit(ctx.boolean_expression()); // should return list of bool exprs
    ////Why is this not working???
    //for(int i = 0; i < boolean_expressions.size() ; i++) {
    //  if_block = group.getInstanceOf("if_block");
    //  if_block.add("boolean_expression", boolean_expressions.get(i));
    //  if_block.add("chunk", boolean_expressions.get(i));
    //  output = output + if_block.render();
    //  if_block = null;
    //}
    
    List<RobotLangParser.AssignableContext> assign = ctx.assignable();
    List<RobotLangParser.Bool_comparatorContext> comparators = ctx.bool_comparator();

    List<Token> ops = ctx.op;
    List<RobotLangParser.Boolean_expressionContext> extra_expressions = ctx.boolean_expression();

    String res = visit(assign.get(0)) + " " + visit(comparators.get(0)) + " " + visit(assign.get(1));

    for (int i = 1; i<assign.size()-1; i++){
      res = res + " && " + visit(assign.get(i)) + " " + visit(comparators.get(i)) + " " + visit(assign.get(i + 1));
    }
    
    for (int i = 0; i < extra_expressions.size(); i++){
      if(ops.get(i).equals("OR"))
        res = res + " || " + visit(extra_expressions.get(i));
      else if(ops.get(i).equals("AND"))
        res = res + " && " + visit(extra_expressions.get(i));
    }

    // System.out.println(res);
    return res;
  }

  @Override public String visitBoolTrue(RobotLangParser.BoolTrueContext ctx) { return "true"; }
  @Override public String visitBoolFalse(RobotLangParser.BoolFalseContext ctx) { return "false"; }

  @Override public String visitCallExprParent(RobotLangParser.CallExprParentContext ctx) {
    String output = "(" + visit(ctx.expr) + ")";
    return output;
  }
	
  @Override public String visitPlainAssignment(RobotLangParser.PlainAssignmentContext ctx) { 
    ST assignment = group.getInstanceOf("assignment");
    assignment.add("var", visit(ctx.var_identifier()));
    assignment.add("value", visit(ctx.assignable()));

    String output = assignment.render();

    return output;
  }
	
  @Override public String visitInlineAssignment(RobotLangParser.InlineAssignmentContext ctx) { 
    String output = visit(ctx.declaration());

    String value = visit(ctx.assignable());
    if ( value.substring(0, 1).equals("{")) //declaracao de array
      output += "[]";

    ST assignment = group.getInstanceOf("assignment");
    assignment.add("var", output);
    assignment.add("value", visit(ctx.assignable()));

    output = assignment.render();
    return output;
  }
	
  @Override public String visitFunction_call(RobotLangParser.Function_callContext ctx) { 
    String output = ctx.function_name.getText() + "(";

    List<RobotLangParser.AssignableContext> args = ctx.args;

    if (args.size() > 0)
      output += visit(args.get(0));

    for (int i = 1; i<args.size(); i++){
      output += ", " + visit(args.get(i));
    }

    output += ")";

    return output;
  }
	
  @Override public String visitArray(RobotLangParser.ArrayContext ctx) { 
    String output = null;
    try{
      output = "{" + visit(ctx.array_init()) + "}";
    }
    catch(Exception e){
      output = "{}";
    }
    return output;
  }
	
  @Override public String visitArray_initString(RobotLangParser.Array_initStringContext ctx) { 
    List<TerminalNode> content = ctx.STRING();

    String output = content.get(0).getText(); //NAO SEI SE DA ERRO SE SO HOUVER 1

    for (TerminalNode t : content){
      output += ", " + t.getText();
    }
    return output;
  }

   @Override public String visitArray_initNumber(RobotLangParser.Array_initNumberContext ctx) 
   { 
    //List<RobotLangParser.NumberContext> content = visitChildren(ctx);
    //isto esta a dar erro
    //String output = content.get(0).getText(); //NAO SEI SE DA ERRO SE SO HOUVER 1
    //String output = visitChildren(ctx);

    //for (RobotLangParser.NumberContext t : content){
    //  output += ", " + t.getText();
    //}

    // System.out.println(output);
    List<RobotLangParser.NumberContext> content = ctx.number();

    String output = content.get(0).getText(); //NAO SEI SE DA ERRO SE SO HOUVER 1

    for (RobotLangParser.NumberContext t : content){
      output += ", " + t.getText();
    }
    return output;
   }
  
  @Override public String visitBreak_statement(RobotLangParser.Break_statementContext ctx) { return "break"; }
	
  @Override public String visitDeclaration(RobotLangParser.DeclarationContext ctx) 
  { 
    declaration = group.getInstanceOf("declaration");
    String identifier = ctx.IDENTIFIER().getText();
    String var_type = visit(ctx.type);

    declaration.add("var", identifier);
    declaration.add("var_type", var_type);

    if (for_loop != null)
      for_var = identifier;

    String output = declaration.render();
    // System.out.println(output);

    declaration = null;
    return output;
  }

  /*@Override public String visitValue(RobotLangParser.ValueContext ctx) {
    String value = ctx.val.getText();
    if(value.contains("'")) { // value is a string
      value.replaceAll("'", "\"");
    }
    return value;
  }*/

  @Override public String visitValueString(RobotLangParser.ValueStringContext ctx) 
  { 
    return ctx.STRING().getText().replaceAll("'", "\""); 
  }
  
  @Override public String visitCallExprString(RobotLangParser.CallExprStringContext ctx) { return ctx.STRING().getText(); }

  @Override public String visitVartypesInteger(RobotLangParser.VartypesIntegerContext ctx) { return "int"; }
	
  @Override public String visitVartypesReal(RobotLangParser.VartypesRealContext ctx) { return "double"; }
  
  @Override public String visitVartypesString(RobotLangParser.VartypesStringContext ctx) { return "char[]"; }
  
  @Override public String visitBool_comparator(RobotLangParser.Bool_comparatorContext ctx) {
    String comparator = ctx.comparator_name.getText();
    if(comparator.equals(">") || comparator.equals("<") || comparator.equals(">=") || comparator.equals("<="))
      return comparator;
    else if(comparator.equals("equals"))
      return "==";
    else if(comparator.equals("different from"))
      return "!=";
    return null;
  }
  
  @Override public String visitReturn_statement(RobotLangParser.Return_statementContext ctx) { 
    in_ret_statement = true;

    String output = "return " + visit(ctx.assignable());
    // System.out.println(ctx.assignable().eType);

    if(function_definition != null) {
      function_definition.remove("return_type");
      function_definition.add("return_type", ctx.assignable().eType.toStringC());
    }
    
    in_ret_statement = false;
  
    return output;
  }
  
  @Override public String visitVar_identifier(RobotLangParser.Var_identifierContext ctx) {
    String id = ctx.IDENTIFIER().getText();
    String num = null;
    if(ctx.number() != null) {
      num = visit(ctx.number());
      return id + "[" + num + "]";
    }
    else
      return id;
  }

  @Override public String visitNumberInteger(RobotLangParser.NumberIntegerContext ctx) 
  { 
    return ctx.INTEGER().getText(); 
  }
	
  @Override public String visitNumberReal(RobotLangParser.NumberRealContext ctx) 
  { 
    return ctx.REAL().getText(); 
  }

}
