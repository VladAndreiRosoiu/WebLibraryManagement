package ro.var.libmngmt.models;

import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.user.Client;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow_info")
public class BorrowHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book")
    private Book book;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "id_user")
    private Client client;
    @Column(name = "borrowed_on")
    private LocalDate borrowedOn;
    @Column(name = "returned_on")
    private LocalDate returnedOn;

    public BorrowHistory() {
    }

    public BorrowHistory(Book book, Client client, LocalDate borrowedOn) {
        this.book = book;
        this.client = client;
        this.borrowedOn=borrowedOn;

    }
    public BorrowHistory(Book book, Client client, LocalDate borrowedOn, LocalDate returnedOn) {
        this.book = book;
        this.client = client;
        this.borrowedOn=borrowedOn;
        this.returnedOn=returnedOn;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getBorrowedOn() {
        return borrowedOn;
    }

    public void setBorrowedOn(LocalDate borrowedOn) {
        this.borrowedOn = borrowedOn;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDate returnedOn) {
        this.returnedOn = returnedOn;
    }
}
