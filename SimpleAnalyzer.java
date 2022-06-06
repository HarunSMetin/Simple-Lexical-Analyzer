import java.io.*;
import java.util.Scanner;

public class SimpleAnalyzer {
    static String input = "";

    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        if (args.length > 0) {
            inputFile = args[0];
            outputFile = args[1];
        }
        Scanner inFile = new Scanner(new FileReader(inputFile));
        String line;
        String number = "";
        StringBuilder out = new StringBuilder();
        boolean flag = false;
        while (inFile.hasNext()) {
            line = inFile.nextLine();
            input += line;
        }

        while (input.length() != 0) {
            if (input.charAt(0) == ' ')
                delete(1);
            else if (input.indexOf("for") == 0) {
                delete(3);
                out.append("Next token is FOR_STATEMENT\tNext lexeme is for\n");
            } else if (input.indexOf("int") == 0) {
                delete(3);
                out.append("Next token is INT_TYPE\t\tNext lexeme is int\n");
            } else if (input.indexOf("char") == 0) {
                delete(4);
                out.append("Next token is CHAR_TYPE\t\tNext lexeme is char\n");
            } else if (input.indexOf("return") == 0) {
                delete(6);
                out.append("Next token is RETURN_STMT\tNext lexeme is return\n");
            } else if (Character.isAlphabetic(input.charAt(0))) {
                char a = input.charAt(0);
                delete(1);
                out.append("Next token is identifier\tNext lexeme is " + a + "\n");
            } else {
                while (Character.isDigit(input.charAt(0))) {
                    number += input.charAt(0) + "";
                    delete(1);
                    flag = true;
                }
                if (flag) {
                    out.append("Next token is INT_LIT\t\tNext lexeme is " + number + "\n");
                    flag = false;
                    number = "";
                }
                switch (input.charAt(0)) {
                    case ' ':
                        delete(1);
                        break;
                    case '\t':
                        delete(1);
                        break;
                    case '=': {
                        delete(1);
                        out.append("Next token is ASSIGNM\t\tNext lexeme is =\n");
                        break;
                    }
                    case ';': {
                        delete(1);
                        out.append("Next token is SEMICOLON\t\tNext lexeme is ;\n");
                        break;
                    }
                    case '<': {
                        delete(1);
                        if (input.charAt(0) == '=') {
                            delete(1);
                            out.append("Next token is LESS_EQ\t\tNext lexeme is <=\n");
                        } else
                            out.append("Next token is LESS\t\tNext lexeme is <\n");
                        break;
                    }
                    case '>': {
                        delete(1);
                        if (input.charAt(0) == '=') {
                            delete(1);
                            out.append("Next token is GRE_EQ\t\tNext lexeme is >=\n");
                        } else
                            out.append("Next token is GREATER\t\tNext lexeme is >\n");
                        break;
                    }
                    case ')': {
                        delete(1);
                        out.append("Next token is RPARANT\t\tNext lexeme is )\n");
                        break;
                    }
                    case '(': {
                        delete(1);
                        out.append("Next token is LPARANT\t\tNext lexeme is (\n");
                        break;
                    }
                    case '{': {
                        delete(1);
                        out.append("Next token is LCURLYB\t\tNext lexeme is {\n");
                        break;
                    }
                    case '}': {
                        delete(1);
                        out.append("Next token is RCURLYB\t\tNext lexeme is }\n");
                        break;
                    }
                    case '-': {
                        delete(1);
                        out.append("Next token is SUBT\t\t  Next lexeme is -\n");
                        break;
                    }
                    case '+': {
                        delete(1);
                        out.append("Next token is ADD\t\tNext lexeme is +\n");
                        break;
                    }
                    case '/': {
                        delete(1);
                        out.append("Next token is DIV\t\t\tNext lexeme is /\n");
                        break;
                    }
                    case '*': {
                        delete(1);
                        out.append("Next token is MULT\t\t  Next lexeme is *\n");
                        break;
                    }
                    default: {
                        delete(1);
                        out.append("UNKNOWN OPERATOR");
                        break;
                    }
                }
            }

        }
        out.append("------------------------------------------------------------------\n");
        System.out.println(out);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
            writer.append(out);
            writer.close();
        } catch (IOException e) {
            System.out.println("Hata!");
        }
    }

    public static void delete(int a) {
        input = input.substring(a, input.length());
    }
}