package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleRepository {

    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    /**
    * 게시글 저장
    * @param title 제목
    * @param content 내용
    * @return 생성된 게시글 객체
    * */
    public Article save(String title, String content) {
        Article article =  new Article(++lastId, title, content, LocalDate.now());
        articles.add(article);
        return article;
    }

    /**
    * 모든 게시글 조회
    * @return 전체 게시글 리스트의 복사본
    * */
    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    /**
     * 특정 번호(ID)로 게시글 찾기
     * @param id 찾고 싶은 게시글 번호
     * @return 검색 결과 (있을 수도 있고 없을 수도 있음)
     */
    public Optional<Article> findById(int id) {
        return articles.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    /**
    * 특정 번호(ID)로 게시글 삭제
    * @param id 삭제할 게시글 번호
    * @return 삭제 성공 여부 (true/false)
    * */
    public boolean delete(int id) {
        return articles.removeIf(a -> a.getId() == id);
    }
}
