package com.booklog.domain.book.application;

import com.booklog.domain.book.dao.BookRepository;
import com.booklog.domain.book.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public Book saveBook(String title, String author, String thumbnail) {
        Book book = Book.builder()
                .title(title)
                .authors(author)
                .thumbnail(thumbnail)
                .build();
        return bookRepository.save(book);
    }

}
