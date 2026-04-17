package org.example.Article;

import java.util.ArrayList;

public class ArticleController {

    public ArticleModel articleModel;

    public ArticleController() {
        articleModel = new ArticleModel();
    }

    public int push_list(String account, String password, String momey, String name) {

        int article;
        article = articleModel.push_model(account, password, momey, name);
        if (article == -1) {
            return -1;
        }
        if (article == -2) {
            return -2;
        }

        articleModel.articles.get(article).addMoney(Integer.parseInt(momey));

        return article;
    }


    public int pull_list(String exaccount, String password, String exmomey, String name) {
        int article;
        article = articleModel.pull_model(exaccount, password, exmomey, name);
        if (article == -1) {
            return -1;
        }
        if (article == -2) {
            return -2;
        }
        if (article == -3) {
            return -3;
        }

        articleModel.articles.get(article).popMoney(Integer.parseInt(exmomey));

        return article;

    }

    public Article trans_list(String exaccount, String password, String exmomey, String account) {

        Article article2;
        article2=articleModel.list(exaccount, password);

        Article article;
        article = articleModel.trans_model(exaccount, password, exmomey, account, article2);

        if (article == null) {
                return null;
        } else {
            if (article.getAccount().equals(account)) {
                if (!article.getAccountCode().equals("C")) {
                    article2.popMoney(Integer.parseInt(exmomey));
                    int index = articleModel.articles.indexOf(article);
                    articleModel.articles.get(index).addMoney(Integer.parseInt(exmomey));
                }
                return article;
            } else {
                return article;
            }

        }
    }


    public Article add_list(String account, String password, String momey, String name, String acccode) {
        return articleModel.add_model(account, password, momey, name, acccode);
    }

    public Article list_list(String account, String password) {

        return articleModel.list_model(account, password);
    }

    public int remove_list(String account, String password) {
        return articleModel.remove_model(account, password);
    }

    public ArrayList<ArticleExIN> list_lists(String account, String password) {
        return articleModel.list_models(account, password);
    }
}
