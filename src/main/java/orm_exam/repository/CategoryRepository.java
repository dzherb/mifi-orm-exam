package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
