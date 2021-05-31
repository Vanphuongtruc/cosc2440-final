package s3818074.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s3818074.models.Author;
import s3818074.models.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAllByOrderByIdAsc();

    List<Author> findAllByOrderByIdDesc();
}
