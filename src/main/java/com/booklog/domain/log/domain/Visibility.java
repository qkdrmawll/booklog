package com.booklog.domain.log.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Visibility {
    PRIVATE("PRIVATE","비공개"),
    NEIGHBORS("NEIGHBORS", "이웃 공개"),
    PUBLIC("PUBLIC","전체 공개");

    private final String key;
    private final String title;
}
