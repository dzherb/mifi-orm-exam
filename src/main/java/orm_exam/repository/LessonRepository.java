package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
