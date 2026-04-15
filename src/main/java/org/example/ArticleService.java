package org.example;

import java.util.Collections;
import java.util.List;

public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    /**
    * 게시글 쓰기
    * */
    public Article write(String title, String content) {
        return repository.save(title, content);
    }

    /**
    * 모든 게시글 조회 (최신순 정렬)
    * */
    public List<Article> findAll() {
        List<Article> articles = repository.findAll();
        Collections.reverse(articles);
        return articles;
    }

    /**
     * ID로 게시글 찾기
     * */
    public Article findById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * 게시글 수정
     * @return 수정 성공 시 true, 실패 시 false
     * */
    public boolean update(int id, String title, String content) {
        return repository.findById(id).map(article -> {
            article.setTitle(title);
            article.setContent(content);
            return true;
        }).orElse(false);
    }

    /**
     * 게시글 삭제
     * */
    public boolean delete(int id) {
        return repository.delete(id);
    }
}
