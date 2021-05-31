package s3818074.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import s3818074.filters.LibraryFilter;
import s3818074.models.Library;
import s3818074.repositories.LibraryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibraryService extends AbstractService<Library, Integer> {
    private final LibraryRepository libraryRepository;

    @Autowired
    protected LibraryService(LibraryRepository repo) {
        super(repo);
        this.libraryRepository = repo;
    }

    @Override
    public Library updateById(Library updatedLibrary, Integer id) {
        Optional<Library> libraryOptional = repo.findById(id);
        if (libraryOptional.isEmpty()) throw new RuntimeException("Library not found!");
        Library library = libraryOptional.get();
        library.setSubject(Optional.ofNullable(updatedLibrary.getSubject()).orElse(library.getSubject()));
        return library;
    }

    public List<Library> search(String subject, String sortOrder) {
        List<Library> libraries = (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) ? libraryRepository.findAllByOrderByIdDesc() : libraryRepository.findAllByOrderByIdAsc();
        return new LibraryFilter(libraries).withSubject(subject).get();
    }
}
