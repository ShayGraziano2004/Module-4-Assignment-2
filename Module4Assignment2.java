import java.util.*;
import java.io.*;

public class CountKeywords {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (file.exists()) {
            System.out.println("The number of keywords in " + filename
                    + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false and null
        String[] keywordString = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
                "const", "continue", "default", "do", "double", "else", "enum", "extends", "for", "final", "finally",
                "float", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while",
                "true", "false", "null"};

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0;

        Scanner input = new Scanner(file);
        boolean insideComment = false;

        while (input.hasNext()) {
            String word = input.next();
            if (!insideComment) {
                if (word.startsWith("//")) {
                    // Line comment, skip the rest of the line
                    continue;
                } else if (word.startsWith("/*")) {
                    // Start of a block comment
                    if (!word.endsWith("*/")) {
                        insideComment = true;
                    }
                    continue;
                } else if (insideComment) {
                    // Inside a block comment, check for its end
                    if (word.endsWith("*/")) {
                        insideComment = false;
                    }
                    continue;
                }

                // Check if the word is a keyword
                if (keywordSet.contains(word)) {
                    count++;
                }
            } else if (word.endsWith("*/")) {
                // End of a block comment
                insideComment = false;
            }
        }

        return count;
    }
}
