package com;

import java.util.Scanner;

public class App {
    public App(Scanner scanner) {
        AppContext.init(scanner);
    }

    public void run() {
        AppContext.systemController.run();
    }
}
