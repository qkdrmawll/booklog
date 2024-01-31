package com.booklog.domain.log.application;

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
class LogServiceTest {
    @Autowired
    LogService logService;
    @Autowired
    LogRepository logRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    @DisplayName("로그 저장 테스트")
    void saveLog() {
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
        Log log = logRepository.findById(logId).get();
        Assertions.assertThat(log.getAuthor()).isEqualTo("tester");
    }
}