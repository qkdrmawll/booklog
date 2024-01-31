package com.booklog.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentCreateDto {
    private long memberId;
    private long logId;
    private String comment;

    @Builder
    public CommentCreateDto(long memberId, long logId, String comment) {
        this.memberId = memberId;
        this.logId = logId;
        this.comment = comment;
    }
}
