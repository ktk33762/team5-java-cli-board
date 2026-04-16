package org.example;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class App {

    private final Scanner scanner;
    private final PrintStream out;
    private final ArticleService service;

    public App(InputStream in, PrintStream out, ArticleService service) {
        this.scanner = new Scanner(in);
        this.out = out;
        this.service = service;
    }

    public void run() {
        while (true) {
            String line = scanner.nextLine().trim();
            Rq rq = new Rq(line);

            switch (rq.getCmd()) {
                case "exit" -> {
                    out.println("종료합니다.");
                    return;
                }
                case "write" -> {
                    out.print("제목: ");
                    String title = scanner.nextLine().trim();
                    out.print("내용: ");
                    String content = scanner.nextLine().trim();
                    Article article = service.write(title, content);
                    out.println("게시글이 등록되었습니다. (id=" +
                            article.getId() + ")");
                }
                case "list" -> {
                    List<Article> articles = service.findAll();
                    if (articles.isEmpty()) {
                        out.println("게시글이 없습니다.");
                    } else {
                        for (Article a : articles) {
                            out.println("[" + a.getId() + "] " +
                                    a.getTitle());
                        }
                    }
                }
                case "detail" -> {
                    Article article = service.findById(rq.getId());
                    if (article == null) {
                        out.println("존재하지 않는 게시글입니다.");
                    } else {
                        out.println("제목: " + article.getTitle());
                        out.println("내용: " + article.getContent());
                    }
                }
                case "delete" -> {
                    boolean deleted = service.delete(rq.getId());
                    if (deleted) {
                        out.println("삭제되었습니다.");
                    } else {
                        out.println("존재하지 않는 게시글입니다.");
                    }
                }
                default -> out.println("알 수 없는 명령어입니다.");
            }
        }
    }
}
