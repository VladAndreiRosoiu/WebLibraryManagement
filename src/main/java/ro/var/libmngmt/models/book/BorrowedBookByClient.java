package ro.var.libmngmt.models.book;

import ro.var.libmngmt.models.user.Client;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowed_book_user")
public class BorrowedBookByClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id_book")
    private Book book;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id_user")
    private Client client;
    @Column(name = "borrowed_on")
    private LocalDate borrowedOn;
    @Column(name = "returned_on")
    private LocalDate returnedOn;

    public BorrowedBookByClient() {
    }

    public BorrowedBookByClient(Book book, Client client, LocalDate borrowedOn, LocalDate returnedOn) {
        this.book = book;
        this.client = client;
        this.borrowedOn = borrowedOn;
        this.returnedOn = returnedOn;
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
