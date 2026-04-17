package org.example.Article;

import java.util.ArrayList;

public class ArticleModel {


    public ArrayList<Article> articles = new ArrayList<>();
    public ArrayList<ArticleExIN> articlesEI = new ArrayList<>();

    public int push_model(String account, String password, String momey, String name) {


        int article;
        article = push_model_list(account, password, momey, name);

        if (article == -1) {
            return -1;
        }
        if (articles.get(article).getAccountCode().equals("C")) {
            return -2;
        }

        ArticleExIN articlesExI = new ArticleExIN(account, Integer.parseInt(momey), name);
        articlesEI.add(articlesExI);
        return article;


    }

    public int push_model_list(String account, String password, String momey, String name) {
        int i = 0;

        while (true) {
            if (articles.get(i).getAccount().equals(account) && articles.get(i).getPassword().equals(password)) {
                return i;
            }
            i++;
            if (i == articles.size()) {
               return -1;
            }
        }
    }

    public int pull_model(String exaccount, String password, String exmomey, String name) {

        int article;
        article = pull_model_list(exaccount, password, exmomey, name);

        if (article == -1) {
            return -1;
        }
        if (articles.get(article).getAccountCode().equals("B") && !articles.get(article).getAccountCode().equals("C"))
            return -3;
        if (articles.get(article).istotalMoney(Integer.parseInt(exmomey))) {
            ArticleExIN articlesExI = new ArticleExIN(exaccount, exmomey, name);
            articlesEI.add(articlesExI);
            return article;
        } else {
            return -2;
        }
    }

    public int pull_model_list(String exaccount, String password, String exmomey, String name) {
        int i = 0;
        int index = 0;
        while (true) {
            if (articles.get(i).getAccount().equals(exaccount) && articles.get(i).getPassword().equals(password)) {
                index = i;
                break;
            }
            i++;
            if (i == articles.size()) {
                i = 0;
                return -1;
            }
        }
        return index;
    }

    public Article trans_model(String exaccount, String password, String exmomey, String account, Article article) {
        Article article_in;
        article_in = trans_model_list(account);
        if (article_in == null) {
            return null;
        }
        if (article == null) {
            return null;
        }

        if (article_in.getAccountCode().equals("C")) {
            return article_in;
        }

        if (article.getAccountCode().equals("C") || article.getAccountCode().equals("B")) {
            return article;
        }

        if (article.istotalMoney(Integer.parseInt(exmomey))) {
            ArticleExIN articlesExI = new ArticleExIN(account, Integer.parseInt(exmomey), article_in.getAccountName());
            ArticleExIN articlesExI2 = new ArticleExIN(exaccount, exmomey, article.getAccountName());
            articlesEI.add(articlesExI);
            articlesEI.add(articlesExI2);
            return article_in;
        }else{
            return article;
        }
    }


    public Article trans_model_list(String account) {
        int i = 0;
        int index = 0;
        while (true) {
            if (articles.get(i).getAccount().equals(account)) {
                index = i;
                break;
            }
            i++;
            if (i == articles.size()) {
                i = 0;
                return null;
            }

        }
        return articles.get(index);
    }

    public Article add_model(String account, String password, String momey, String name, String acccode) {

        Article eqarticle;
        eqarticle = is_article(account);
        if (eqarticle != null) {
            return eqarticle;
        }
        Article article = new Article(account, password, Integer.parseInt(momey), name, acccode);
        ArticleExIN articlesExI = new ArticleExIN(account, Integer.parseInt(momey), name);
        articles.add(article);
        articlesEI.add(articlesExI);
        return null;
    }


    public Article is_article(String account) {

        if (!articles.isEmpty()) {
            int i = 0;

            while (true) {
                if (articles.get(i).getAccount().equals(account)) {

                    return articles.get(i);
                }
                i++;
                if (i == articles.size()) {

                    return null;
                }
            }
        }
        return null;
    }

    public Article list_model(String account, String password) {

        Article article_copy;
        article_copy = account_list(account, password);
        if (article_copy == null) {
            return null;
        }
        return article_copy;
    }

    public ArrayList<ArticleExIN> eoaccount_list(String account) {
        ArrayList<ArticleExIN> articleE = new ArrayList<>();
        int i = 0;
        while (true) {
            if (articlesEI.get(i).getAccount().equals(account) || articlesEI.get(i).getExaccount().equals(account)) {
                articleE.add(articlesEI.get(i));
            }
            i++;
            if (i == articlesEI.size()) {
                i = 0;
                break;
            }
        }
        return articleE;
    }

    public Article account_list(String account, String password) {
        int i = 0;
        if (articles.isEmpty()) {
            return null;
        }
        Article article_copy = null;
        while (true) {
            if (articles.get(i).getAccount().equals(account) && articles.get(i).getPassword().equals(password)) {
                article_copy = (articles.get(i));
                break;
            }
            i++;
            if (i == articles.size()) {
                i = 0;
                break;
            }
        }
        if (article_copy == null) {
            return null;
        }
        return article_copy;
    }

    public int remove_model(String account, String password) {
        int value = 0;
        value = remove_account(account, password);
        value = remove_ieaccount(account, password);
        return value;
    }

    public int remove_ieaccount(String account, String password) {
        if (articlesEI.isEmpty()) {
            return 0;
        }

        int i = 0;
        while (true) {
            if (articlesEI.get(i).getAccount().equals(account)) {
                articlesEI.remove(i);
                int m=articlesEI.size();

                if (i>=m) {
                    return 0;
                }

            }else{
                i++;
                int m=articlesEI.size();
                if (i>=m) {
                    return 0;
                }
            }
        }
    }

    public int remove_account(String account, String password) {
        if (articles.isEmpty()) {

            return 1;
        }

        int i = 0;
        while (true) {
            if (articles.get(i).getAccount().equals(account) && articles.get(i).getPassword().equals(password)) {
                articles.remove(i);
                return 0;
            }
            i++;
            if (i == articles.size()) {

                return 2;
            }
        }
    }

    public ArrayList<ArticleExIN> list_models(String account, String password) {
        ArrayList<ArticleExIN> articleE;
        articleE = eoaccount_list(account);
        return articleE;

    }

    public Article list(String exaccount, String password) {
        int i = 0;
        int index2 = 0;
        while (true) {
            if (articles.get(i).getAccount().equals(exaccount)) {
                if (articles.get(i).getPassword().equals(password)) {
                    index2 = i;
                    break;
                } else {
                    return articles.get(i);
                }
            }
            i++;
            if (i == articles.size()) {
                i = 0;
                return null;
            }
        }
        return  articles.get(index2);
    }
}
