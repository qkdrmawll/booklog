package com.booklog.domain.member;

import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;
    @Test
    void builder() {
        Member member = Member.builder()
                .name("test")
                .email("test")
                .nickname("test")
                .build();
        memberRepository.save(member);
        Optional<Member> find = memberRepository.findById(member.getId());
        Assertions.assertThat(member.getName()).isEqualTo(find.get().getName());
    }
}