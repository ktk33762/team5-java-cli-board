package com.global;

import com.AppContext;

public class Rq {
    public static String getLine() {
        return AppContext.scanner.nextLine().trim();
    }
}
