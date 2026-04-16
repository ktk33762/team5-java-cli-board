package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class AppIntegrationTest {

    // 헬퍼 메서드: 입력 문자열로 App 실행 후 출력 반환
    private String runApp(String input) {
        InputStream in = new
                ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);

        App app = new App(in, out, new ArticleService(new ArticleRepository()));
        app.run();

        return baos.toString();
    }

    @Test
    void exit_입력시_프로그램이_종료된다() {
        String output = runApp("exit\n");
        assertThat(output).contains("종료");
    }

    @Test
    void write_후_list_명령시_등록한_글이_보인다() {
        String output = runApp("write\n자바 공부\n오늘도 열심히\nlist\nexit\n");
                assertThat(output).contains("자바 공부");
    }

    @Test
    void write_후_detail_명령시_상세내용이_보인다() {
        String output = runApp("write\n자바 공부\n오늘도 열심히\ndetail 1\nexit\n");
                assertThat(output).contains("자바 공부");
        assertThat(output).contains("오늘도 열심히");
    }

    @Test
    void 존재하지않는_id로_detail_시_에러메시지가_출력된다() {
        String output = runApp("detail 999\nexit\n");
        assertThat(output).contains("존재하지 않");
    }

    @Test
    void write_후_delete_하면_list에서_사라진다() {
        String output = runApp("write\n삭제할 글\n내용\ndelete 1\nlist\nexit\n");
                assertThat(output).doesNotContain("삭제할 글");
    }
}
