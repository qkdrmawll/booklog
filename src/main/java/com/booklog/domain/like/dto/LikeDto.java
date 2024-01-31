package com.booklog.domain.like.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeDto {
    private Long MemberId;
    private Long LogId;

    @Builder
    public LikeDto(Long memberId, Long logId) {
        MemberId = memberId;
        LogId = logId;
    }
}
