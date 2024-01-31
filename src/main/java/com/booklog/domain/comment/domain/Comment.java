package com.booklog.domain.comment.domain;

import com.booklog.BaseEntity;
import com.booklog.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100, nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Comment(String comment, Member member) {
        this.comment = comment;
        this.member = member;
    }
}
