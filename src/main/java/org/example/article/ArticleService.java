package org.example.article;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article write(String title, String content) {
        return articleRepository.create(title, content, getCurrentDate());
    }

    public List<Article> getList() {
        List<Article> list = articleRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    public Optional<Article> getById(int id) {
        return articleRepository.findById(id);
    }

    public boolean update(int id, String newTitle, String newContent) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (optArticle.isEmpty()) return false;

        Article article = optArticle.get();
        if (!newTitle.isEmpty()) article.setTitle(newTitle);
        if (!newContent.isEmpty()) article.setContent(newContent);
        return true;
    }

    public boolean delete(int id) {
        return articleRepository.deleteById(id);
    }

    public List<Article> search(String keyword) {
        List<Article> result = articleRepository.findByKeyword(keyword);
        Collections.reverse(result);
        return result;
    }
    
    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
