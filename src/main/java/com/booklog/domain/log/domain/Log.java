package com.booklog.domain.log.domain;

import com.booklog.BaseEntity;
import com.booklog.domain.comment.Comment;
import com.booklog.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Log extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String bookName;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 5000, nullable = false)
    private String content;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @Column
    private int viewCount;

    @Builder
    public Log(Member member, String title, String bookName, String author,String content) {
        this.member = member;
        this.title = title;
        this.bookName = bookName;
        this.author = author;
        this.content = content;
    }

    public void update(String title, String bookName, String author,String content) {
        this.title = title;
        this.bookName = bookName;
        this.author = author;
        this.content = content;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
