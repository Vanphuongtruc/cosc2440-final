package s3818074.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "author")
public class Author extends AbstractEntity {
    @Column
    private String name;
    @Column
    private String academicCredentials;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @JsonIgnoreProperties(value = "author", allowSetters = true)
    private List<Book> books;

    @ManyToOne
    @JsonIgnoreProperties(value = "authors")
    private Library library;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademicCredentials() {
        return academicCredentials;
    }

    public void setAcademicCrendentials(String academicCredentials) {
        this.academicCredentials = academicCredentials;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
