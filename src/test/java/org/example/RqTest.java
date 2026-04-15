package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RqTest {

    @Test
    @DisplayName("write 입력 시 cmd는 'write', id는 0이다.")
    void parseWrite() {
        Rq rq = new Rq("write");

        assertThat(rq.getCmd()).isEqualTo("write");
        assertThat(rq.getId()).isEqualTo(0);
    }

    @Test
    @DisplayName("'detail 1' 입력 시 cmd는 'detail', id는 1이다.")
    void parseDetail() {
        Rq rq = new Rq("detail 1");

        assertThat(rq.getCmd()).isEqualTo("detail");
        assertThat(rq.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("'delete 99' 입력 시 cmd는 'delete', id는 99이다")
    void parseDelete() {
        Rq rq = new Rq("delete 99");

        assertThat(rq.getCmd()).isEqualTo("delete");
        assertThat(rq.getId()).isEqualTo(99);
    }

    @Test
    @DisplayName("빈 문자열 입력 시 cmd는 빈 문자열이다")
    void parseEmpty() {
        Rq rq = new Rq("");

        assertThat(rq.getCmd()).isEqualTo("");
        assertThat(rq.getId()).isEqualTo(0);
    }

    @Test
    @DisplayName("'detail abc' 입력 시 id는 0이다 (숫자가 아닌 경우)")
    void parseInvalidId() {
        Rq rq = new Rq("detail abc");

        assertThat(rq.getCmd()).isEqualTo("detail");
        assertThat(rq.getId()).isEqualTo(0);
    }

    @Test
    @DisplayName("앞뒤 공백이 있어도 정상 파싱된다")
    void parseTrim() {
        Rq rq = new Rq("  detail 5  ");

        assertThat(rq.getCmd()).isEqualTo("detail");
        assertThat(rq.getId()).isEqualTo(5);
    }
}
