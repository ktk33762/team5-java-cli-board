package org.example.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Article {
    private final int id;
    private String title;
    private String content;
    private final String regDate;
}
