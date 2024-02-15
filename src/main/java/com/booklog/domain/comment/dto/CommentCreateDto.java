package com.booklog.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

public record CommentCreateDto(long memberId, long logId, String comment) {
}
