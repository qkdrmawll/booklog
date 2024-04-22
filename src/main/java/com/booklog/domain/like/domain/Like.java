package com.booklog.domain.like.domain;

import com.booklog.domain.log.domain.Log;
import com.booklog.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "log_id")
    private Log log;

    @Builder
    public Like(Member member, Log log) {
        this.member = member;
        this.log = log;
    }
}
