package ro.var.libmngmt.models.book;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "additional_info")
    private String description;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "id_author")},
            inverseJoinColumns = {@JoinColumn(name = "id_book")}
    )
    private List<Book> books;

    public Author() {

    }

    public Author(int id, String firstName, String lastName, String description, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(description, author.description) &&
                Objects.equals(birthDate, author.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, description, birthDate);
    }


}
