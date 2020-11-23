package ro.var.libmngmt.models.book;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowed_book_user")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id_book")
    private Book book;
    @Column(name = "borrowed_on")
    private LocalDate borrowedOn;
    @Column(name = "returned_on")
    private LocalDate returnedOn;

    public BorrowedBook() {
    }

    public BorrowedBook(Book book, LocalDate borrowedOn, LocalDate returnedOn) {
        this.book = book;
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
