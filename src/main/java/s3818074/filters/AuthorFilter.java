package s3818074.filters;

import s3818074.models.Author;
import s3818074.models.Library;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorFilter {
    private List<Author> authors;

    public AuthorFilter(List<Author> authors) {
        this.authors = authors;
    }

    public AuthorFilter withName(String name) {
        if (name == null) return this;
        authors = authors.stream().filter(a -> a.getName() != null && a.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        return this;
    }

    public AuthorFilter withCredentials(String credentials) {
        if (credentials == null) return this;
        authors = authors.stream().filter(a -> a.getAcademicCredentials() != null && a.getAcademicCredentials().equalsIgnoreCase(credentials)).collect(Collectors.toList());
        return this;
    }

    public List<Author> get() {
        return authors;
    }
}