package s3818074.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import s3818074.models.Author;
import s3818074.models.Library;
import s3818074.services.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController extends AbstractController<Author, Integer> {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService service) {
        super(service);
        this.authorService = service;
    }

    // Without search param
    @Override
    @GetMapping("/all")
    public List<Author> getAll(Integer page) {
        return super.getAll(page);
    }

    // With search param
    @GetMapping
    public List<Author> search(@RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "credentials", required = false) String credentials,
                               @RequestParam(name = "order", required = false) String sortOrder) {
        return authorService.search(name, credentials, sortOrder);
    }
}