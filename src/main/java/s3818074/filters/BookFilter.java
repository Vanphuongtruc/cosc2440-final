package s3818074.filters;

import s3818074.models.Book;
import s3818074.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookFilter {
    private List<Book> books;

    public BookFilter(List<Book> books) {
        this.books = books;
    }

    public BookFilter withName(String name) {
        if (name == null) return this;
        books = books.stream().filter(b -> b.getName() != null && b.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        return this;
    }

    public BookFilter in(Date date) {
        if (date == null) return this;
        books = books.stream().filter(b -> DateUtils.isSameDay(date, b.getDateOfCreation())).collect(Collectors.toList());
        return this;
    }

    public List<Book> get() {
        return books;
    }
}
