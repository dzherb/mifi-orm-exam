package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
