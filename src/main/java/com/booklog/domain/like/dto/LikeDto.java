package com.booklog.domain.like.dto;

import lombok.Builder;
import lombok.Getter;

public record LikeDto(Long memberId, Long logId) {
}
