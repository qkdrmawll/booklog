package com.booklog.domain.comment.application;

import com.booklog.domain.comment.dao.CommentRepository;
import com.booklog.domain.comment.domain.Comment;
import com.booklog.domain.comment.dto.CommentCreateDto;
import com.booklog.domain.log.application.LogService;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.log.dto.LogCreateDto;
import com.booklog.domain.member.Member;
import com.booklog.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    LogService logService;
    @Autowired
    CommentService commentService;
    @Autowired
    LogRepository logRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("댓글 저장 테스트")
    void saveComment() {
        Member member = Member.builder()
                .name("test")
                .email("test")
                .nickname("test")
                .build();
        memberRepository.save(member);

        LogCreateDto logCreateDto = LogCreateDto.builder()
                .memberId(1)
                .title("test")
                .author("tester")
                .bookName("test")
                .content("test")
                .build();

        logService.saveLog(logCreateDto);

        CommentCreateDto commentCreateDto = CommentCreateDto.builder()
                .logId(1)
                .memberId(1)
                .comment("test comment").build();
        commentService.saveComment(commentCreateDto);
        Comment comment = commentRepository.findById(1L).orElseThrow();
        Assertions.assertThat(comment.getComment()).isEqualTo("test comment");
    }
}