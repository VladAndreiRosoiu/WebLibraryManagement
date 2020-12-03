package ro.var.libmngmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.var.libmngmt.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

}
