package ro.var.libmngmt.models.user;

import ro.var.libmngmt.models.book.BorrowedBook;

import java.util.List;
import java.util.Objects;

public class Client extends User {
    private List<BorrowedBook> borrowedBooks;
    private BorrowedBook currentBorrowedBook;
    private boolean isActive;

    public Client(int id, String firstName, String lastName, String user, String email, List<BorrowedBook> borrowedBooks,
                  BorrowedBook currentBorrowedBook, boolean isActive) {
        super(id, firstName, lastName, user, email, UserType.CLIENT);
        this.borrowedBooks = borrowedBooks;
        this.currentBorrowedBook = currentBorrowedBook;
        this.isActive = isActive;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public BorrowedBook getCurrentBorrowedBook() {
        return currentBorrowedBook;
    }

    public void setCurrentBorrowedBook(BorrowedBook currentBorrowedBook) {
        this.currentBorrowedBook = currentBorrowedBook;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return isActive == client.isActive &&
                Objects.equals(borrowedBooks, client.borrowedBooks) &&
                Objects.equals(currentBorrowedBook, client.currentBorrowedBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), borrowedBooks, currentBorrowedBook, isActive);
    }

    @Override
    public String toString() {
        return "Client{" +
                "borrowedBooks=" + borrowedBooks +
                ", currentBorrowedBook=" + currentBorrowedBook +
                ", isActive=" + isActive +
                '}';
    }
}
