import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import Visitor.*;
import Parser.*;

public class Main {
    private static ClassList class_list = new ClassList();
    private static FunctionList function_list = new FunctionList(class_list);

    private static String readTestFile(String filePath) {
        String ans = "";
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                ans += tempString + '\n';
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return ans;
    }

    private static ParseTree parser(String text) {
        try {
            CharStream input = CharStreams.fromString(text);
            MxLexer lexer = new MxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MxParser parser = new MxParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            return parser.all();
        } catch (Exception e) {
            System.err.println("Parser error");
            System.exit(1);
            return null;
        }
    }

    private static void semanticAnalysis(ParseTree tree){
        FirstVisitor first_visitor = new FirstVisitor(class_list);
        first_visitor.visit(tree);
        SecondVisitor second_visitor = new SecondVisitor(class_list,function_list);
        second_visitor.visit(tree);
        ThirdVisitor third_visitor = new ThirdVisitor(class_list, function_list);
        third_visitor.visit(tree);
    }

    public static void main(String[] args) {
        String text;
        if (args.length == 1)
            text = readTestFile(args[0]);
        else
            text = readTestFile("program.txt");
        ParseTree tree = parser(text);
        semanticAnalysis(tree);
    }
}
