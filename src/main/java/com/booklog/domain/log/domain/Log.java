package com.booklog.domain.log.domain;

import com.booklog.BaseEntity;
import com.booklog.domain.book.domain.Book;
import com.booklog.domain.comment.domain.Comment;
import com.booklog.domain.member.domain.Member;
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

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @Column(length = 100, nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(length = 5000, nullable = false)
    private String content;


    @Column(name = "likes_count")
    private long likesCount;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @Column
    private int viewCount;

    @Builder
    public Log(Member member, String title, Book book, String content) {
        this.member = member;
        this.title = title;
        this.book = book;
        this.content = content;
    }

    public void updateLog(String title, Book book, String content) {
        this.title = title;
        this.book = book;
        this.content = content;
    }

    public String getWriter() {
        return this.member.getName();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    public long addLike() {
        this.likesCount += 1;
        return this.likesCount;
    }
    public long subtractLike() {
        this.likesCount -= 1;
        return this.likesCount;
    }
}
