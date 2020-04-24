import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class RobotLangMain {
   public static void main(String[] args) throws Exception {
      InputStream in = null;
      String file_name = null;

      if(args.length > 0) {
        file_name = args[0];
        in = new FileInputStream(new File(file_name));
        System.out.println("Using file: " + file_name);
      }
      else
        in = System.in;

      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(in);
      // create a lexer that feeds off of input CharStream:
      RobotLangLexer lexer = new RobotLangLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      RobotLangParser parser = new RobotLangParser(tokens);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at main rule:
      ParseTree tree = parser.main();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         SemanticVisitor visitor0 = new SemanticVisitor();
         STVisitor visitor1 = new STVisitor();
         System.out.println("Executing SemanticVisitor...");
         if(visitor0.visit(tree)){
         //if(visitor0.visit(tree)!=null);
            System.out.println("Your code passed the semantic check!");
            System.out.println("Executing STVisitor...");
            visitor1.visit(tree);
         }
      }
   }
}
