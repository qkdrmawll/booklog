package com.booklog.domain.log.dto;


public record LogCreateDto(String title, String bookName, String author, String thumbnail, String content, String visibility) {

}
