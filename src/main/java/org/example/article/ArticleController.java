package org.example.article;

import org.example.Rq;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ArticleController {
    private final ArticleService articleService;
    private final Scanner scanner;

    public ArticleController(ArticleService articleService, Scanner scanner) {
        this.articleService = articleService;
        this.scanner = scanner;
    }

    public void write() {
        System.out.print("제목: ");
        String title = scanner.nextLine().trim();

        System.out.print("내용: ");
        String content = scanner.nextLine().trim();

        if (title.isEmpty() || content.isEmpty()) {
            System.out.println("제목과 내용을 모두 입력해주세요.");
            return;
        }

        articleService.write(title, content);
        System.out.println("게시글이 등록되었습니다.");
    }

    public void list() {
        List<Article> list = articleService.getList();

        if (list.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.");
            return;
        }

        System.out.println("번호 / 제목 / 등록일");
        System.out.println("-".repeat(40));
        for (Article article : list) {
            System.out.printf("%d / %s / %s%n",
                    article.getId(), article.getTitle(), article.getRegDate());
        }
    }

    public void detail(Rq rq) {
        int id = Integer.parseInt(rq.getParam("id", "0"));
        Optional<Article> optArticle = articleService.getById(id);

        if (optArticle.isEmpty()) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }

        Article article = optArticle.get();
        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getRegDate());
    }

    public void update(Rq rq) {
        int id = Integer.parseInt(rq.getParam("id", "0"));
        Optional<Article> optArticle = articleService.getById(id);

        if (optArticle.isEmpty()) {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
            return;
        }

        Article article = optArticle.get();

        System.out.printf("(현재 제목 : %s) -> ", article.getTitle());
        String newTitle = scanner.nextLine().trim();

        System.out.printf("(현재 내용: %s) -> ", article.getContent());
        String newContent = scanner.nextLine().trim();

        articleService.update(id, newTitle, newContent);
        System.out.println("게시글이 수정되었습니다.");
    }

    public void delete(Rq rq) {
        int id = Integer.parseInt(rq.getParam("id", "0"));
        boolean deleted = articleService.delete(id);

        if (deleted) {
            System.out.println("게시글이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
        }
    }

    public void search(Rq rq) {
        String keyword = rq.getParam("keyword", "");

        if (keyword.isEmpty()) {
            System.out.println("사용법: search?keyword=키워드");
            return;
        }

        List<Article> results = articleService.search(keyword);

        if (results.isEmpty()) {
            System.out.println("검색 결과가 없습니다: '" + keyword + "'");
            return;
        }

        System.out.println("검색 결과 (" + results.size() + "건): '" + keyword + "'");
        System.out.println("번호 / 제목 / 등록일");
        System.out.println("-".repeat(40));
        for (Article article : results) {
            System.out.printf("%d / %s / %s%n",
                    article.getId(), article.getTitle(), article.getRegDate());
        }
    }
}
