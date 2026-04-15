package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTest {

    @Test
    @DisplayName("Article 객체 생성 시 id, title, content, regDate가 설정된다.")
    void create() {
        Article article = new Article(1, "AT마드리드 바르셀로나에게 승리", "팀 전체가 그리즈만 마지막 시즌 어쩌고", LocalDate.of(2026,4, 15));

        assertThat(article.getId()).isEqualTo(1);
        assertThat(article.getTitle()).isEqualTo("AT마드리드 바르셀로나에게 승리");
        assertThat(article.getContent()).isEqualTo("팀 전체가 그리즈만 마지막 시즌 어쩌고");
        assertThat(article.getRegDate()).isEqualTo(LocalDate.of(2026,4, 15));
    }

    @Test
    @DisplayName("setTitle로 제목을 변경할 수 있다.")
    void setTitle() {
        Article article = new Article(1, "원래제목", "내용", LocalDate.now());

        article.setTitle("바뀐제목");

        assertThat(article.getTitle()).isEqualTo("바뀐제목");
    }

    @Test
    @DisplayName("setContent로 내용을 변경할 수 있다.")
    void setContent() {
        Article article = new Article(1, "제목", "원래내용", LocalDate.now());

        article.setContent("바뀐내용");

        assertThat(article.getContent()).isEqualTo("바뀐내용");
    }
}