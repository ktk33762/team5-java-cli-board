package org.example;

public class Main {
    static void main() {
        App app = new App(
                System.in,
                System.out,
                new ArticleService(new ArticleRepository())
        );
        app.run();
    }
}

