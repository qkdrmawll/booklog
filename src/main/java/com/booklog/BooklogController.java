package com.booklog;

import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.member.oauth.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Controller
public class BooklogController {
    private final LogRepository logRepository;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model) {
        List<Log> logs = logRepository.findAll();
        model.addAttribute("logs",logs);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if (sessionUser != null) {
            model.addAttribute("loggedInUser",sessionUser);
            log.info("email = " + sessionUser.getEmail());
        }else {
            log.info("no user");
        }
        return "index";
    }
}
