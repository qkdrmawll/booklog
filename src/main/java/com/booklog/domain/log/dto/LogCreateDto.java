package com.booklog.domain.log.dto;


public record LogCreateDto(int memberId, String title, String bookName, String author, String content) {

}
