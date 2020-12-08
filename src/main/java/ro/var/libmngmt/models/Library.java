package ro.var.libmngmt.models;

import java.util.*;
import ro.var.libmngmt.models.book.Book;
import ro.var.libmngmt.models.user.Client;

import java.util.Map;

public class Library {

    private Map<Book,Integer> bookStock;

    private List<Client> clients;

    public Library() {

    }

    public Map<Book, Integer> getBookStock() {
        return bookStock;
    }

    public void setBookStock(Map<Book, Integer> bookStock) {
        this.bookStock = bookStock;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
