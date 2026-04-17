package org.example.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article extends ArticleExIN {
    public int totalmoney;

    public String password;

    public String accountCode;

    public LocalDateTime create_at;

    String create_at_s;

    public Article(String account, String pawssword, int money, String accountName, String accountCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.account = account;
        this.password = pawssword;
        this.money = money;
        this.accountName = accountName;
        this.accountCode = accountCode;
        this.totalmoney = money;
        this.create_at = LocalDateTime.now();
        this.create_at_s = this.create_at.format(formatter);
        this.exaccountname = "*****";
        this.exaccount = "*****";
        this.ex_at = LocalDateTime.parse("1111-11-11T11:11:11");
        this.ex_at_s = this.ex_at.format(formatter);
        this.exmoney = 0;
        this.in_at = this.create_at;

    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public void addMoney(int money) {
        this.totalmoney += money;
    }

    public String getCreate_at_s() {
        return create_at_s;
    }

    public void popMoney(int exmoney) {
        this.totalmoney -= exmoney;
    }

    public boolean istotalMoney(int exmoney) {

        int mon = this.totalmoney - exmoney;
        return mon >= 0;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public int getTotalmoney() {
        return totalmoney;
    }

}
