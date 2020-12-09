package ro.var.libmngmt.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.repository.BookRepository;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

}
