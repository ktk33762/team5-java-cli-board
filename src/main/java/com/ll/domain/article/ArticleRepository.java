package com.ll.domain.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleRepository {
    private final List<Article> articles;
    private int nextId;

    public ArticleRepository() {
        this.articles = new ArrayList<>();
        this.nextId = 1;
    }

    public Article create(String title, String content, String regDate) {
        Article article = new Article(nextId++, title, content, regDate);
        articles.add(article);
        return article;
    }

    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    public Optional<Article> findById(int id) {
        return articles.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    public List<Article> findByKeyword(String keyword) {
        List<Article> result = new ArrayList<>();
        for (Article a : articles) {
            if (a.getTitle().contains(keyword)
                    || a.getContent().contains(keyword)) {
                result.add(a);
            }
        }
        return result;
    }

    public boolean deleteById(int id) {
        return articles.removeIf(a -> a.getId() == id);
    }
}
