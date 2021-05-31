package s3818074.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import s3818074.models.Book;
import s3818074.models.Library;
import s3818074.services.AbstractService;
import s3818074.services.BookService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController extends AbstractController<Book, Integer> {
    private final BookService bookService;

    @Autowired
    public BookController(BookService service) {
        super(service);
        this.bookService = service;
    }

    // Without search param
    @Override
    @GetMapping("/all")
    public List<Book> getAll(Integer page) {
        return super.getAll(page);
    }

    // With search param
    @GetMapping
    public List<Book> search(@RequestParam(name = "name", required = false) String subject,
                             @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                             @RequestParam(name = "order", required = false) String sortOrder) {
        return bookService.search(subject, date, sortOrder);
    }
}
