package com.booklog.domain.member.application;

import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.oauth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public Member sessionToMember(SessionUser sessionUser) {
        Member member = memberRepository.findByEmail(sessionUser.getEmail());
        if (member == null) {
            // Handle the case where member is not found
            // For example, you can throw a custom exception or return an error message
            throw new IllegalArgumentException("Member not found for email: " + sessionUser.getEmail());
        }
        return member;
    }
}
