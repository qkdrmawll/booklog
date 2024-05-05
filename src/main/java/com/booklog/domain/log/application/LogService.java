package com.booklog.domain.log.application;

import com.booklog.domain.book.application.BookService;
import com.booklog.domain.book.domain.Book;
import com.booklog.domain.log.dao.LogRepository;
import com.booklog.domain.log.domain.Log;
import com.booklog.domain.log.domain.Visibility;
import com.booklog.domain.log.dto.LogCreateDto;
import com.booklog.domain.member.domain.Member;
import com.booklog.domain.member.dao.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class LogService {
    private final LogRepository logRepository;
    private final BookService bookService;
    public long saveLog(LogCreateDto logCreateDto, Member member) {
        Book book = bookService.saveBook(logCreateDto.bookName(),
                logCreateDto.author(),
                logCreateDto.thumbnail());

        Visibility visibility;
        if (logCreateDto.visibility().equals("private")) {
            visibility = Visibility.PRIVATE;
        } else if (logCreateDto.visibility().equals("public")) {
            visibility = Visibility.PUBLIC;
        } else
            visibility = Visibility.NEIGHBORS;
        Log log = Log.builder()
                .title(logCreateDto.title())
                .member(member)
                .book(book)
                .content(logCreateDto.content())
                .visibility(visibility)
                .build();

        return logRepository.save(log).getId();
    }
}
