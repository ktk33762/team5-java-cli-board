package org.example.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleExIN {

    public String account;
    public String exaccount;
    public int money;
    public int exmoney;
    public String accountName;
    public String exaccountname;
    public LocalDateTime ex_at;
    public LocalDateTime in_at;
    public String ex_at_s;
    public String in_at_s;

    public ArticleExIN() {

    }

    public ArticleExIN(String account, int money, String accountName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.account = account;
        this.money = money;
        this.accountName = accountName;
        this.exaccountname = "*****";
        this.exaccount = "*****";
        this.ex_at = LocalDateTime.parse("1111-11-11T11:11:11");
        this.ex_at_s = this.ex_at.format(formatter);
        this.exmoney = 0;
        this.in_at = LocalDateTime.now();
        this.in_at_s = this.in_at.format(formatter);
    }

    public ArticleExIN(String exaccount, String exmoney, String exaccountName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.account = "*****";
        this.money = 0;
        this.accountName = "*****";
        this.exaccountname = exaccountName;
        this.exaccount = exaccount;
        this.ex_at = LocalDateTime.now();
        this.ex_at_s = this.ex_at.format(formatter);
        this.exmoney = Integer.parseInt(exmoney);
        this.in_at = LocalDateTime.parse("1111-11-11T11:11:11");
        this.in_at_s = this.in_at.format(formatter);
    }

    public String getEx_at_s() {
        return ex_at_s;
    }

    public String getIn_at_s() {
        return in_at_s;
    }

    public LocalDateTime getEx_at() {
        return ex_at;
    }

    public LocalDateTime getIn_at() {
        return in_at;
    }

    public String getAccount() {
        return account;
    }

    public String getExaccount() {
        return exaccount;
    }


    public int getMoney() {
        return money;
    }

    public int getExmoney() {
        return exmoney;
    }


    public String getAccountName() {
        return accountName;
    }

    public String getExaccountname() {
        return exaccountname;
    }
}
