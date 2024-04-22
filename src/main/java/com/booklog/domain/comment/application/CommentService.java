package com.booklog.domain.comment.application;

import com.booklog.domain.comment.dao.CommentRepository;
import com.booklog.domain.comment.domain.Comment;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class CommentService {
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final LogRepository logRepository;
    public long saveComment(String text, Member member, Long logId) {
        Log log = logRepository.findById(logId).orElseThrow();
        Comment comment = Comment.builder()
                .member(member)
                .comment(text)
                .build();

        log.addComment(comment);
        return commentRepository.save(comment).getId();
    }
}
