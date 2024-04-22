package com.booklog.domain.like.api;

import com.booklog.domain.like.application.LikeService;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LikeController {
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;
    private final LogRepository logRepository;
    private final LikeService likeService;
    @PostMapping("/like/{logId}")
    public String like(@PathVariable Long logId) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Member member = memberRepository.findByEmail(sessionUser.getEmail());
        Log log = logRepository.findById(logId).orElseThrow();
        likeService.like(member,log);
        return "redirect:/log/" + logId;
    }
}
