package com.booklog.domain.log.dto;

import com.booklog.domain.log.domain.Log;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LogCreateDto {
    private long memberId;
    private String title;
    private String bookName;
    private String author;
    private String content;

    @Builder
    public LogCreateDto(int memberId, String title, String bookName, String author, String content) {
        this.memberId = memberId;
        this.title = title;
        this.bookName = bookName;
        this.author = author;
        this.content = content;
    }

}
