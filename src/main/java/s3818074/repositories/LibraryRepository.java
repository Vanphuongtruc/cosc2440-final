package s3818074.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s3818074.models.Library;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    List<Library> findAllByOrderByIdAsc();

    List<Library> findAllByOrderByIdDesc();
}
