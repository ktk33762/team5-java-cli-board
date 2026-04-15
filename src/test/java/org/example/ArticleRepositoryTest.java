package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ArticleRepositoryTest {

    private ArticleRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ArticleRepository();
    }

    @Test
    @DisplayName("save 호출 시 id가 1부터 자동 부여된다")
    void saveAutoId() {
        Article article = repository.save("제목", "내용");

        assertThat(article.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("save를 두 번 호출하면 id는 1, 2가 된다")
    void saveAutoIdIncrement() {
        Article first = repository.save("제목1", "내용1");
        Article second = repository.save("제목2", "내용2");

        assertThat(first.getId()).isEqualTo(1);
        assertThat(second.getId()).isEqualTo(2);
    }

    @Test
    @DisplayName("findAll은 저장된 모든 게시글을 반환한다")
    void findAll() {
        repository.save("제목2", "내용2");

        assertThat(repository.findAll()).hasSize(2);
    }

    @Test
    @DisplayName("findById로 특정 id의 게시글을 조회할 수 있다")
    void findById() {
        repository.save("제목", "내용");

        assertThat(repository.findById(1)).isPresent();
        assertThat(repository.findById(1).get().getTitle()).isEqualTo("제목");
    }

    @Test
    @DisplayName("존재하지 않는 id로 findById 시 Optional.empty를 반환한다")
    void findByIdNotFound() {
        assertThat(repository.findById(999)).isEmpty();
    }

    @Test
    @DisplayName("delete로 게시글을 삭제할 수 있다")
    void delete() {
        repository.save("제목", "내용");

        boolean result = repository.delete(1);

        assertThat(result).isTrue();
        assertThat(repository.findAll()).hasSize(0);
    }

    @Test
    @DisplayName("존재하지 않는 id로 delete 시 false를 반환한다")
    void deleteNotFound() {
        boolean result = repository.delete(999);

        assertThat(result).isFalse();
    }
}
