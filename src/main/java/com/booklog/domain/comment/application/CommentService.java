package com.booklog.domain.comment.application;

import com.booklog.domain.comment.dao.CommentRepository;
import com.booklog.domain.comment.domain.Comment;
import com.booklog.domain.comment.dto.CommentCreateDto;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.log.dto.LogCreateDto;
import com.booklog.domain.member.Member;
import com.booklog.domain.member.MemberRepository;
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
    public long saveComment(CommentCreateDto commentCreateDto) {
        long memberId = commentCreateDto.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow();
        Log log = logRepository.findById(commentCreateDto.getLogId()).orElseThrow();
        Comment comment = Comment.builder()
                .member(member)
                .comment(commentCreateDto.getComment())
                .build();

        log.addComment(comment);
        return commentRepository.save(comment).getId();
    }
}
