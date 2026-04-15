package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleServiceTest {

    private ArticleService service;

    @BeforeEach
    void setUp() {
        service = new ArticleService(new ArticleRepository());
    }

    @Test
    @DisplayName("write 호출 시 새 게시글이 저장되고 id를 반환한다.")
    void write() {
        Article article = service.write("제목", "내용");

        assertThat(article.getId()).isEqualTo(1);
        assertThat(article.getTitle()).isEqualTo("제목");
        assertThat(article.getContent()).isEqualTo("내용");
    }

    @Test
    @DisplayName("write 시 regDate는 오늘 날짜로 설정된다")
    void writeRegDate() {
        Article article = service.write("제목", "내용");

        assertThat(article.getRegDate()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("findAll은 최신순(역순)으로 반환된다")
    void findAllLatestFirst() {
        service.write("첫번째 글", "내용1");
        service.write("두번째 글", "내용2");

        assertThat(service.findAll())
                .extracting(Article::getTitle)
                .containsExactly("두번째 글", "첫번째 글");
    }

    @Test
    @DisplayName("update 호출 시 제목과 내용이 변경된다")
    void update() {
        service.write("원래 제목", "원래 내용");

        boolean result = service.update(1, "바뀐 제목", "바뀐 내용");

        assertThat(result).isTrue();
        assertThat(service.findById(1).getTitle()).isEqualTo("바뀐 제목");
        assertThat(service.findById(1).getContent()).isEqualTo("바뀐 내용");
    }

    @Test
    @DisplayName("존재하지 않는 id로 update 시 false를 반환한다")
    void updateNotFound() {
        boolean result = service.update(999, "제목", "내용");

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("delete 호출 시 게시글이 삭제된다")
    void delete() {
        service.write("제목", "내용");

        boolean result = service.delete(1);

        assertThat(result).isTrue();
        assertThat(service.findAll()).hasSize(0);
    }

    @Test
    @DisplayName("존재하지 않는 id로 delete 시 false를 반환한다")
    void deleteNotFound() {
        boolean result = service.delete(999);

        assertThat(result).isFalse();
    }
}
