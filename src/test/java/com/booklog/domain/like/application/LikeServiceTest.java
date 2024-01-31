package com.booklog.domain.like.application;

import com.booklog.domain.comment.application.CommentService;
import com.booklog.domain.comment.domain.Comment;
import com.booklog.domain.comment.dto.CommentCreateDto;
import com.booklog.domain.like.dao.LikeRepository;
import com.booklog.domain.like.domain.Like;
import com.booklog.domain.like.dto.LikeDto;
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
class LikeServiceTest {

    @Autowired
    LikeService likeService;
    @Autowired
    LogService logService;
    @Autowired
    LogRepository logRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LikeRepository likeRepository;
    @Test
    @DisplayName("좋아요 생성 테스트")
    void like() {
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

        likeService.like(member,logRepository.findById(1L).get());
        Like like = likeRepository.findById(1L).orElseThrow();
        Assertions.assertThat(like.getMember().getName()).isEqualTo("test");
    }
    @Test
    @DisplayName("좋아요 카운팅 테스트")
    void like_count() {
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

        long logId = logService.saveLog(logCreateDto);
        Log log = logRepository.findById(logId).orElseThrow();

        long  count = likeService.like(member,log);
        Like like = likeRepository.findById(1L).orElseThrow();
        Assertions.assertThat(log.getLikesCount()).isEqualTo(1);
    }
    @Test
    @DisplayName("좋아요 취소 카운팅 테스트")
    void like_twice_count() {
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

        long logId = logService.saveLog(logCreateDto);
        Log log = logRepository.findById(logId).orElseThrow();

        likeService.like(member,log);
        long  count = likeService.like(member,log);
        Assertions.assertThat(log.getLikesCount()).isEqualTo(0);
    }
}