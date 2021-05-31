package s3818074.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s3818074.models.Book;
import s3818074.models.Library;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderByIdAsc();

    List<Book> findAllByOrderByIdDesc();
}
