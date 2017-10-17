package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.lfkdsk.justel.utils.FormatUtils.beautifulPrint;
import static com.lfkdsk.justel.utils.FormatUtils.insertNewLine;
import static com.lfkdsk.justel.utils.FormatUtils.reformatAstPrint;
import static org.fusesource.jansi.Ansi.ansi;

public class JustRepl {
    static String logoStr =
            "\n" +
                    "     ██╗██╗   ██╗███████╗████████╗   ███████╗██╗     \n" +
                    "     ██║██║   ██║██╔════╝╚══██╔══╝   ██╔════╝██║     \n" +
                    "     ██║██║   ██║███████╗   ██║█████╗█████╗  ██║     \n" +
                    "██   ██║██║   ██║╚════██║   ██║╚════╝██╔══╝  ██║     \n" +
                    "╚█████╔╝╚██████╔╝███████║   ██║      ███████╗███████╗\n" +
                    " ╚════╝  ╚═════╝ ╚══════╝   ╚═╝      ╚══════╝╚══════╝\n" +
                    "                                                     \n";

    static final String help =
            "-a show ast structure of this expr \n" +
                    "-e eval this expr \n" +
                    "-g generate java source code \n" +
                    "-c compile java source code [need -g] \n" +
                    "Just-REPL support assign(=) operator to set id-token's value, this grammar \n " +
                    "won't support in JustEL";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String JUST_EL = "JustEL > ";

    /**
     * just-lexer
     */
    private static Lexer lexer = new MockLexer(new BufferedReader(new InputStreamReader(System.in)));


    private static JustParser parser = new MockParser();

    /**
     * just-compiler
     */
    private static JustCompiler compiler = new JustCompilerImpl();

    /**
     * code-generator
     */
    private static Generator generator = new JavaCodeGenerator();

    private static boolean openAst = false;
    private static boolean openMockEval = false;
    private static boolean openMockCompile = false;
    private static boolean openMockGenerate = false;

    private static String cyanPrint(String msg) {
        return ANSI_CYAN + msg + ANSI_RESET;
    }

    private static void resolveCommandFlag(String command, boolean flag) {
        if (command.contains("a")) openAst = flag;
        if (command.contains("e")) openMockEval = flag;
        if (command.contains("c")) openMockCompile = flag;
        if (command.contains("g")) openMockGenerate = flag;
    }

    private static boolean resolveCommandLine(String command) {
        String trimCommand = command.trim();
        if (trimCommand.startsWith("+")) {
            resolveCommandFlag(trimCommand, true);
        } else if (trimCommand.startsWith("-")) {
            resolveCommandFlag(trimCommand, false);
        } else {
            return false;
        }

        return true;
    }

    private static void run() throws IOException {
        ConsoleReader reader = new ConsoleReader();
        reader.setHistoryEnabled(true);

        String command;
        JustContext env = new JustMapContext();
        while ((command = reader.readLine(cyanPrint(JUST_EL))) != null) {

            if (command.equals("")) continue;
            else if (command.trim().equals("-q")) return;
            else if (resolveCommandLine(command)) continue;

            try {

                lexer.reset(command);
                lexer.hasMore();

                AstNode node = parser.parser(lexer);

                if (openAst) {
                    String reformat = reformatAstPrint(node.toString());
                    String[] args = {
                            "AST ---- Lisp Style",
                            insertNewLine(new StringBuilder(reformat), "\n", "║").toString()
                    };

                    System.out.println(cyanPrint(beautifulPrint(args)));
                }

                if (openMockEval) {
                    String reformat = node.eval(env).toString();

                    String[] args = {
                            "Value ---- Eval",
                            insertNewLine(new StringBuilder(reformat), "\n", "\r\n║").toString()
                    };

                    System.out.println(cyanPrint(beautifulPrint(args)));
                }

                if (openMockGenerate) {
                    generator.reset(env, node);

                    JavaSource javaSource = generator.generate();
                    System.out.println(cyanPrint(javaSource.toString()));

                    if (openMockCompile) {
                        long start = System.currentTimeMillis();

                        // save
                        Expression expr = compiler.compile(javaSource);
                        env.put(javaSource.className.toLowerCase(), expr);

                        AnsiConsole.out.println("Compile Time :" + (System.currentTimeMillis() - start + " ms"));
                    }
                }

            } catch (Throwable e) {
                AnsiConsole.out.println(ansi().fgRed().a(JUST_EL + e.getMessage()).reset().toString());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        AnsiConsole.systemInstall();

        // print logo & welcome ~
        logoStr = logoStr.replace("█", ansi().fg(Ansi.Color.GREEN).a("█").reset().toString());
        System.out.println(ansi().eraseScreen().render(logoStr));
        System.out.println(ANSI_PURPLE + "Welcome to JustEL Debug Tools~~" + ANSI_RESET);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("");
            System.out.println(ANSI_PURPLE + "Have a nice Day~~" + ANSI_RESET);
        }));

        if (args.length < 1) {
            System.out.println(ansi().fgBrightGreen().render(help).reset().toString());
            return;
        }

        String type = args[0];
        resolveCommandFlag(type, true);

        run();
    }

}
