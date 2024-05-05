package com.booklog.domain.comment.application;

import com.booklog.domain.comment.dao.CommentRepository;
import com.booklog.domain.comment.domain.Comment;
import com.booklog.domain.comment.dto.CommentCreateDto;
import com.booklog.domain.log.application.LogService;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.dto.LogCreateDto;
import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        LogCreateDto logCreateDto = new LogCreateDto("t","t","t","t", "content","public");

        Long logId = logService.saveLog(logCreateDto,member);

        Long commentId = commentService.saveComment("test comment",member,logId);
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        Assertions.assertThat(comment.getComment()).isEqualTo("test comment");
    }
}