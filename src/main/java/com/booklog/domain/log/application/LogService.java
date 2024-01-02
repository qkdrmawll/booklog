package com.booklog.domain.log.application;

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
public class LogService {
    private final MemberRepository memberRepository;
    private final LogRepository logRepository;
    public long saveLog(LogCreateDto logCreateDto) {
        long memberId = logCreateDto.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow();

        Log log = Log.builder()
                .title(logCreateDto.getTitle())
                .member(member)
                .bookName(logCreateDto.getBookName())
                .author(logCreateDto.getAuthor())
                .content(logCreateDto.getContent())
                .build();

        return logRepository.save(log).getId();
    }
}
