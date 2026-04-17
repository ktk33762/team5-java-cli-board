package org.example;

import org.example.article.ArticleController;
import org.example.article.ArticleRepository;
import org.example.article.ArticleService;
import org.example.system.SystemController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final ArticleController articleController;
    private final SystemController systemController;

    public App() {
        this.scanner = new Scanner(System.in);

        ArticleRepository articleRepository = new ArticleRepository();
        ArticleService articleService = new ArticleService(articleRepository);
        this.articleController = new ArticleController(articleService, scanner);
        this.systemController = new SystemController();
    }
    
    public void run() {

        while (true) {
            System.out.print("명령어: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            Rq rq = new Rq(input);

            switch (rq.getActionName()) {
                case "작성"  -> articleController.write();
                case "리스트"   -> articleController.list();
                case "세부내용" -> articleController.detail(rq);
                case "수정" -> articleController.update(rq);
                case "삭제" -> articleController.delete(rq);
                case "찾기" -> articleController.search(rq);
                case "종료"   -> { systemController.exit(); return; }
                default       -> systemController.unknown();
            }
        }
    }
}
