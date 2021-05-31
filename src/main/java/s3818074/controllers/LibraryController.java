package s3818074.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import s3818074.models.Library;
import s3818074.services.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController extends AbstractController<Library, Integer> {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService service) {
        super(service);
        this.libraryService = service;
    }

    // Without search param
    @Override
    @GetMapping("/all")
    public List<Library> getAll(Integer page) {
        return super.getAll(page);
    }

    // With search param
    @GetMapping
    public List<Library> search(@RequestParam(name = "subject", required = false) String subject,
                                @RequestParam(name = "order", required = false) String sortOrder) {
        return libraryService.search(subject, sortOrder);
    }


}
