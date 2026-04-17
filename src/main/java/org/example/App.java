package org.example;

import org.example.Article.Article;
import org.example.Article.ArticleController;
import org.example.Article.ArticleExIN;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ArticleController articleController = new ArticleController();
        while (true) {
            System.out.print("명령)");
            String cmd = scanner.next().trim();
            Rq rq = new Rq(cmd);
            if (rq.cmdLIST.equals("push")) {

                System.out.println("계좌를 입력하세요");
                String account = scanner.next().trim();
                System.out.println("패스워드를 입력하세요");
                String password = scanner.next().trim();
                System.out.println("금액을 입력하세요");
                String momey = scanner.next().trim();
                System.out.println("이름을 입력하세요");
                String name = scanner.next().trim();

                if (account.isEmpty()) {
                    System.out.println("계좌가 없습니다");
                    continue;
                }
                if (password.isEmpty() || password.length() != 4) {
                    System.out.println("비밀번호가 잘못되었습니다.");
                    continue;
                }
                if (momey.isEmpty()) {
                    System.out.println("금액이 잘못되었습니다.");
                    continue;
                }
                if (name.isEmpty()) {
                    System.out.println("이름이 잘못되었습니다.");
                    continue;
                }
                int article;
                article = articleController.push_list(account, password, momey, name);

                if (article == -1) {
                    System.out.println("계좌나 비밀번호가 잘못되었습니다.");
                } else if (article == -2) {
                    System.out.println("예금통장입니다.");
                }

            } else if (cmd.equals("pull")) {

                System.out.println("출금계좌를 입력하세요");
                String exaccount = scanner.next().trim();
                System.out.println("패스워드를 입력하세요");
                String password = scanner.next().trim();
                System.out.println("출금금액을 입력하세요");
                String exmomey = scanner.next().trim();
                System.out.println("이름을 입력하세요");
                String name = scanner.next().trim();

                if (exaccount.isEmpty()) {
                    System.out.println("계좌가 없습니다");
                    continue;
                }
                if (password.isEmpty() || password.length() != 4) {
                    System.out.println("비밀번호가 잘못되었습니다.");
                    continue;
                }
                if (exmomey.isEmpty()) {
                    System.out.println("금액이 잘못되었습니다.");
                    continue;
                }
                if (name.isEmpty()) {
                    System.out.println("이름이 잘못되었습니다.");
                    continue;
                }

                int article;
                article = articleController.pull_list(exaccount, password, exmomey, name);

                if (article == -1) {
                    System.out.println("계좌나 비밀번호가 잘못되었습니다.");
                } else if (article == -2) {
                    System.out.println("금액이잘못되었습니다.");
                } else if (article == -3) {
                    System.out.println("보통계좌가아닙니다.");
                }
            } else if (rq.cmdLIST.equals("trans")) {
                System.out.println("출금계좌를 입력하세요");
                String exaccount = scanner.next().trim();
                System.out.println("패스워드를 입력하세요");
                String password = scanner.next().trim();
                System.out.println("출금금액을 입력하세요");
                String exmomey = scanner.next().trim();

                System.out.println("입금계좌를 입력하세요");
                String account = scanner.next().trim();

                if (account.isEmpty()) {
                    System.out.println("계좌가 없습니다");
                    continue;
                }
                if (password.isEmpty() || password.length() != 4) {
                    System.out.println("비밀번호가 잘못되었습니다.");
                    continue;
                }
                if (exmomey.isEmpty()) {
                    System.out.println("금액이 잘못되었습니다.");
                    continue;
                }
                if (account.equals(exaccount)) {
                    System.out.println("입금게좌와 출금계좌가 같습니다.");
                    continue;
                }

                Article article;
                article = articleController.trans_list(exaccount, password, exmomey, account);
                if (article == null) {
                    System.out.println("입금통장이 없거나 출금통장이 없거나 비밀번호가 틀렸습니다..");
                    continue;
                }
                if (article.getAccount().equals(exaccount)) {
                    if (!article.getPassword().equals(password)) {
                        System.out.println("패스워드가 틀립니다.");
                        continue;
                    }
                }
                if (article.getAccountCode().equals("B")) {
                    if (article.getAccount().equals(account)) {
                        System.out.println("거래가 성사되었습니다.");
                        continue;
                    }
                    if (article.getAccount().equals(exaccount)) {
                        System.out.println("출금통장이 보통계좌가아닙니다.");
                    }
                } else if (article.getAccountCode().equals("C")) {
                    if (article.getAccount().equals(exaccount)) {
                        System.out.println("출금통장이 보통계좌가아닙니다.");

                    } else if (article.getAccount().equals(account)) {
                        System.out.println("입금통장이 보통계좌가아닙니다.");
                    }
                } else if (article.getAccountCode().equals("A")) {
                    if (article.getAccount().equals(account)) {
                        System.out.println("거래가 성사되었습니다.");
                        continue;
                    }
                    if (article.getAccount().equals(exaccount)) {
                        System.out.println("금액이 틀립니다");
                    }
                }

            } else if (rq.cmdLIST.equals("add")) {
                System.out.println("계좌를 입력하세요");
                String account = scanner.next().trim();
                System.out.println("패스워드를 입력하세요");
                String password = scanner.next().trim();
                System.out.println("금액을 입력하세요");
                String momey = scanner.next().trim();
                System.out.println("이름을 입력하세요");
                String name = scanner.next().trim();
                System.out.println("계좌코드를 입력하세요 일반통장 : A, 적금통장 : B, 예금통장 : C");
                String acccode = scanner.next().trim();
                if (is_account(account, password, momey, name, acccode)) {
                    continue;
                }
                Article article;
                article = articleController.add_list(account, password, momey, name, acccode);
                if (article != null) {
                    System.out.println("중복된 계좌가 있습니다.");
                }
            } else if (rq.cmdLIST.equals("list")) {
                System.out.println("계좌를 입력하세요");
                String account = scanner.next().trim();
                System.out.println("패스워드를 입력하세요");
                String password = scanner.next().trim();
                if (account.isEmpty()) {
                    System.out.println("계좌가 없습니다");
                    continue;
                }
                if (password.isEmpty() || password.length() != 4) {
                    System.out.println("비밀번호가 잘못되었습니다.");
                    continue;
                }
                Article article_copy;
                article_copy = articleController.list_list(account, password);

                //이름, 총금액, 계좌, 개설날짜, 입금날짜, 출금날짜 출금자, 입금자
                //입금내역, 입금액, 입금날짜, 입금자, 출금날짜, 출금액 출금자(출금시 입금내용 *****, 입금시 출금내용*****)
                if (article_copy == null) {
                    System.out.println("게좌가 없습니다.");
                    continue;
                }
                System.out.println("이름, 총금액, 계좌, 개설날짜 계좌코드");
                System.out.println(article_copy.getAccountName() + " " + article_copy.getTotalmoney() + " "
                        + article_copy.getAccount() + " " + article_copy.getCreate_at_s() + " " + article_copy.getAccountCode());
                System.out.println("입금액, 입금날짜, 입금자, 출금날짜, 출금액 출금자, 출금계좌");
                ArrayList<ArticleExIN> articleE;
                articleE = articleController.list_lists(account, password);
                if (articleE.isEmpty()) {
                    continue;
                }
                int i = 0;
                while (true) {
                    System.out.println(articleE.get(i).getMoney() + " " + articleE.get(i).getIn_at_s() + " " + articleE.get(i).getAccountName() + " " + articleE.get(i).getEx_at_s() + " " + articleE.get(i).getExmoney()
                            + " " + articleE.get(i).getExaccountname() + " " + articleE.get(i).getExaccount());
                    i++;
                    if (i == articleE.size()) {
                        i = 0;
                        break;
                    }
                }
            } else if (rq.cmdLIST.equals("remove")) {
                System.out.println("계좌를 입력하세요");
                String account = scanner.next().trim();
                System.out.println("패스워드를 입력하세요");
                String password = scanner.next().trim();
                if (account.isEmpty()) {
                    System.out.println("계좌가 없습니다");
                    continue;
                }
                if (password.isEmpty() || password.length() != 4) {
                    System.out.println("비밀번호가 잘못되었습니다.");
                    continue;
                }
                int value = 0;
                value = articleController.remove_list(account, password);
                if (value == 0) {
                    System.out.println("삭제가 완료되었습니다.");
                } else if (value == 1) {
                    System.out.println("계좌나 비밀번호가 잘못되었습니다.");
                } else if (value == 2) {
                    System.out.println("패스워드나 게좌가 잘못되었습니다.");
                }

            } else if (rq.cmdLIST.equals("exit")) {
                System.out.println("프로그램 종료");
                return;
            }
        }
    }

    public boolean is_account(String account, String password, String momey, String name, String acccode) {

        if (account.isEmpty()) {
            System.out.println("계좌가 없습니다");
            return true;
        }
        if (password.isEmpty() || password.length() != 4) {
            System.out.println("비밀번호가 잘못되었습니다.");
            return true;
        }
        if (momey.isEmpty()) {
            System.out.println("금액이 잘못되었습니다.");
            return true;
        }
        if (name.isEmpty()) {
            System.out.println("이름이 잘못되었습니다.");
            return true;
        }
        if (acccode.isEmpty() || (!acccode.equals("A") && !acccode.equals("B") && !acccode.equals("C"))) {
            System.out.println("계좌입력코드가 잘못되었습니다.");
            return true;
        }
        return false;
    }
}
