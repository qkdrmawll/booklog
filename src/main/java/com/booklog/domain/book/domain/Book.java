package com.booklog.domain.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class Book {
    private List<String> authors;
    private String contents;
    private String title;
    private String publisher;
    private int price;
    private String thumbnail;
}
