package com.booklog.domain.follow.api;

import com.booklog.domain.follow.application.FollowService;
import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class FollowController {
    private final FollowService followService;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    @PostMapping("follow/{memberId}")
    public String follow(@PathVariable Long memberId) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Member follower = memberRepository.findByEmail(sessionUser.getEmail());
        Member followed = memberRepository.findById(memberId).orElseThrow();
        String profileEmail = followed.getEmail();
        log.info(profileEmail);
        followService.follow(follower,followed);
        return "redirect:/profile/"+profileEmail;
    }
    @PostMapping("unfollow/{memberId}")
    public String unfollow(@PathVariable Long memberId) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Member follower = memberRepository.findByEmail(sessionUser.getEmail());
        Member followed = memberRepository.findById(memberId).orElseThrow();
        String profileEmail = followed.getEmail();
        followService.unfollow(follower,followed);
        return "redirect:/profile/"+profileEmail;
    }

}
