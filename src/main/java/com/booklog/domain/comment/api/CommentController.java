package com.booklog.domain.comment.api;

import com.booklog.domain.comment.application.CommentService;
import com.booklog.domain.member.application.MemberService;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
public class CommentController {
    private final CommentService commentService;
    private final HttpSession httpSession;
    private final MemberService memberService;

    @PostMapping("/{logId}/comment")
    public String addComment(@PathVariable Long logId, @Validated String comment) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Member member = memberService.sessionToMember(sessionUser);
        log.info(comment);
        commentService.saveComment(comment, member, logId);
        return "redirect:/log/{logId}";
    }
}
