package com;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AppTestRunner {
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static PrintStream CURRENT_OUT;

    private static ByteArrayOutputStream setOutToByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CURRENT_OUT = new PrintStream(byteArrayOutputStream, true);
        System.setOut(CURRENT_OUT);

        return byteArrayOutputStream;
    }

    private static void clearSetOutToByteArray() {
        System.setOut(ORIGINAL_OUT);
        CURRENT_OUT.close();
        CURRENT_OUT = null;
    }

    public static String run(String input) {
        Scanner scanner = new Scanner(input);
        ByteArrayOutputStream output = setOutToByteArray();
        new App(scanner).run();
        clearSetOutToByteArray();
        return output.toString();
    }
}
