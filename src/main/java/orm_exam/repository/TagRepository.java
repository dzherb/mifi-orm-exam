package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
