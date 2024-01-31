package com.booklog.domain.log;

import com.booklog.domain.log.domain.Log;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.member.Member;
import com.booklog.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class LogTest {
    @Autowired
    LogRepository logRepository;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("로그_빌더_테스트")
    void Log_builder() {
        Member member = Member.builder()
                .name("test")
                .email("test")
                .nickname("test")
                .build();
        memberRepository.save(member);

        Log log = Log.builder()
                .member(member)
                .title("test")
                .author("test")
                .bookName("test")
                .content("teeest")
                .build();
        long id = logRepository.save(log).getId();
        Optional<Log> find = logRepository.findById(id);
        Assertions.assertThat(find.get().getContent()).isEqualTo("teeest");
    }

}