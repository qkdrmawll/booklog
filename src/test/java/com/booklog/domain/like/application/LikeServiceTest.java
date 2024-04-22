package com.booklog.domain.like.application;

import com.booklog.domain.like.dao.LikeRepository;
import com.booklog.domain.like.domain.Like;
import com.booklog.domain.log.application.LogService;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.log.dto.LogCreateDto;
import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

        LogCreateDto logCreateDto = new LogCreateDto("t","t","t","t", "public");

        long logId = logService.saveLog(logCreateDto,member);
        Log log = logRepository.findById(logId).orElseThrow();
        likeService.like(member,logRepository.findById(logId).get());
        Like like = likeRepository.findByMemberAndLog(member,log);
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
        long memberId = memberRepository.save(member).getId();

        LogCreateDto logCreateDto = new LogCreateDto("t","t","t","t", "public");

        long logId = logService.saveLog(logCreateDto,member);
        Log log = logRepository.findById(logId).orElseThrow();

        long  count = likeService.like(member,log);
        Like like = likeRepository.findByMemberAndLog(member,log);
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

        LogCreateDto logCreateDto = new LogCreateDto("t","t","t","t", "public");

        long logId = logService.saveLog(logCreateDto,member);
        Log log = logRepository.findById(logId).orElseThrow();

        likeService.like(member,log);
        long  count = likeService.like(member,log);
        Assertions.assertThat(log.getLikesCount()).isEqualTo(count);
    }
}