package ro.var.libmngmt.services;

import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.book.BorrowedBook;
import ro.var.libmngmt.models.user.Client;

import java.util.List;

public interface BorrowReturnService {
    void borrowBook(Book book, Client client);

    void returnBook(Book book, Client client);

    BorrowedBook getCurrentBorrowedBook(Client client);

    List<BorrowedBook> getBorrowHistory(Client client);
}
