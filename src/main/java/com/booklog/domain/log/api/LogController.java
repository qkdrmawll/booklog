package com.booklog.domain.log.api;

import com.booklog.domain.log.application.LogService;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.log.domain.Visibility;
import com.booklog.domain.log.dto.LogCreateDto;
import com.booklog.domain.member.dao.MemberRepository;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/log")
@Slf4j
@Controller
public class LogController {
    private final LogService logService;
    private final LogRepository logRepository;
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @GetMapping("/write")
    public String logForm(@RequestParam(name = "bookName", required = false) String bookName,
                          @RequestParam(name = "author", required = false) String author,
                          Model model) {
        model.addAttribute("bookName", bookName);
        model.addAttribute("author", author);
        return "addPost";
    }


    @PostMapping("/write")
    public String addLog(LogCreateDto logCreateDto){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        Member member = memberRepository.findByEmail(sessionUser.getEmail());
        logService.saveLog(logCreateDto, member);

        System.out.println("bookTitle = " + logCreateDto.bookName());
        System.out.println("bookAuthor = " + logCreateDto.author());
        System.out.println("logTitle = " + logCreateDto.title());
        System.out.println("logContent = " + logCreateDto.content());
        return "redirect:/";
    }

    @GetMapping("/{logId}")
    public String log(@PathVariable Long logId,Model model, Authentication authentication) {
        Log log = logRepository.findById(logId).orElseThrow();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Retrieve the logged-in user details from authentication
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            System.out.println("authentication 통과");

            // Check if the logged-in user has the necessary permissions to view the log
            if (hasPermissionToViewLog(sessionUser, log)) {
                System.out.println("hasPermissionToViewLog 통과");
                model.addAttribute("log", log);
                model.addAttribute("loggedInUser", sessionUser);
                return "log";
            } else {
                System.out.println("hasPermissionToViewLog 실패");
                // User does not have permission to view the log
                return "redirect:/";
            }
        } else {
            // User is not authenticated, redirect to login page
            System.out.println("User is not authenticated");
            return "redirect:/";
        }
    }
    private boolean hasPermissionToViewLog(SessionUser sessionUser, Log log) {
        // Implement your permission logic here
        // You may check the user's roles, authorities, or any other criteria
        // For example, if the log has a visibility setting:
         if (log.getVisibility() == Visibility.PUBLIC) {
             return true; // Everyone can view public logs
         } else if (log.getVisibility() == Visibility.PRIVATE) {
             if (sessionUser.getEmail().equals(log.getMember().getEmail()))
                 return true;
         }
//          else { // 이웃 공개인 경우
//             if ()
//             // Add more logic as needed
//             /**
//              * 세션 유저가 로그 작성자의 이웃이냐?
//              * return true
//              */
//         }
        return false;
    }
}
