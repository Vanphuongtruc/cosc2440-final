package s3818074.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s3818074.filters.BookFilter;
import s3818074.models.Author;
import s3818074.models.Book;
import s3818074.models.Library;
import s3818074.repositories.AuthorRepository;
import s3818074.repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService extends AbstractService<Book, Integer> {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    protected BookService(AuthorRepository authorRepository, BookRepository repo) {
        super(repo);
        this.authorRepository = authorRepository;
        this.bookRepository = repo;
    }

    public List<Book> search(String name, Date createdDate, String sortOrder) {
        List<Book> books = (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) ? bookRepository.findAllByOrderByIdDesc() : bookRepository.findAllByOrderByIdAsc();
        return new BookFilter(books).withName(name).in(createdDate).get();
    }

    @Override
    public Book add(Book book) {
        if (book.getAuthor() != null) {
            Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
            if (authorOptional.isEmpty()) throw new RuntimeException("Author not found!");
            book.setAuthor(authorOptional.get());

        }
        return super.add(book);
    }

    @Override
    public Book updateById(Book updatedBook, Integer id) {
        Optional<Book> bookOptional = repo.findById(id);
        if (bookOptional.isEmpty()) throw new RuntimeException("Book not found!");
        Book book = bookOptional.get();
        book.setName(Optional.ofNullable(updatedBook.getName()).orElse(book.getName()));
        book.setDateOfCreation(Optional.ofNullable(updatedBook.getDateOfCreation()).orElse(book.getDateOfCreation()));
        return book;
    }
}
