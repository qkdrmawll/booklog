package com.booklog.domain.member.api;

import com.booklog.domain.follow.application.FollowService;
import com.booklog.domain.like.application.LikeService;
import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    private final FollowService followService;
    @GetMapping("/profile/{memberEmail}")
    public String profile(@PathVariable String memberEmail, Model model) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Member sessionMember = memberRepository.findByEmail(sessionUser.getEmail());
        Member member = memberRepository.findByEmail(memberEmail);
        boolean isFollowing = followService.isFollow(sessionMember, member);
        model.addAttribute("loggedInUser",sessionMember);
        model.addAttribute("member",member);
        model.addAttribute("isFollowing", isFollowing);
        return "profile";
    }

}
