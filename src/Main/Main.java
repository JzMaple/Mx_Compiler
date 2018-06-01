package Main;

import Parser.MxLexer;
import Parser.MxParser;
import Type.ClassList;
import Type.FunctionList;
import Visitor.*;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

import java.util.*;

public class Main {
    private static ClassList class_list = new ClassList();
    private static FunctionList global_function_list = new FunctionList(null, null);
    private static String[] program = new String[10000];

    public static ClassList getClassList() {return class_list;}

    private static String readTestFile(String filePath) {
        String ans = "";
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int cnt = 0;
            while ((tempString = reader.readLine()) != null) {
                ans += tempString + '\n';
                program[cnt] = tempString;
                ++cnt;
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
        global_function_list.setGlobalInsideFunctionList();
        FirstVisitor first_visitor = new FirstVisitor(class_list, program);
        first_visitor.visit(tree);
//        System.out.println("first semantic analysis finish");
        SecondVisitor second_visitor = new SecondVisitor(class_list, global_function_list, program);
        second_visitor.visit(tree);
//        System.out.println("second semantic analysis finish");
        ThirdVisitor third_visitor = new ThirdVisitor(class_list, global_function_list, program);
        third_visitor.visit(tree);
//        System.out.println("third semantic analysis finish");
    }

    public static void main(String[] args) {
        class_list.setClassList();
        String text;
        if (args.length == 1)
            text = readTestFile(args[0]);
        else
            text = readTestFile("program.txt");

        ParseTree tree = parser(text);
        System.out.println("parse finish");

        semanticAnalysis(tree);
//        System.out.println("semantic analysis finish");

        IRBuilder IR_builder = new IRBuilder(class_list, global_function_list);
        IR_builder.visit(tree);
//        System.out.println("IR build finish");

        Translator translator = new Translator(IR_builder);
        List<String> code = translator.translate();
//        System.out.println("translate finish");

        if (args.length == 1) {
            try {
                FileWriter out = new FileWriter("test/code.asm");
                for (String s : code) {
                    out.write(s + "\n");
                }
                out.flush();
                out.close();
            } catch (IOException e) {
                System.out.println("code print error");
            }
        } else {
            for (String s : code) {
                System.out.println(s);
            }
        }
    }
}
