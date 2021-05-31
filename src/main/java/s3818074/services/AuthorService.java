package s3818074.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s3818074.filters.AuthorFilter;
import s3818074.models.Author;
import s3818074.models.Library;
import s3818074.repositories.AuthorRepository;
import s3818074.repositories.LibraryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService extends AbstractService<Author, Integer> {
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    protected AuthorService(AuthorRepository repo, LibraryRepository libraryRepository) {
        super(repo);
        this.authorRepository = repo;
        this.libraryRepository = libraryRepository;
    }

    public List<Author> search(String name, String credentials, String sortOrder) {
        List<Author> authors = (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) ? authorRepository.findAllByOrderByIdDesc() : authorRepository.findAllByOrderByIdAsc();
        return new AuthorFilter(authors).withName(name).withCredentials(credentials).get();
    }

    @Override
    public Author add(Author author) {
        if (author.getLibrary() != null) {
            Optional<Library> libraryOptional = libraryRepository.findById(author.getLibrary().getId());
            if (libraryOptional.isEmpty()) throw new RuntimeException("Library not found!");
            libraryOptional.get().getAuthors().add(author);
            author.setLibrary(libraryOptional.get());
        }
        return super.add(author);
    }

    @Override
    public Author updateById(Author updatedAuthor, Integer id) {
        Optional<Author> authorOptional = repo.findById(id);
        if (authorOptional.isEmpty()) throw new RuntimeException("Author not found!");
        Author author = authorOptional.get();
        author.setName(Optional.ofNullable(updatedAuthor.getName()).orElse(author.getName()));
        author.setAcademicCrendentials(Optional.ofNullable(updatedAuthor.getAcademicCredentials()).orElse(author.getAcademicCredentials()));

        return author;
    }
}
