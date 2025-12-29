package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.CourseReview;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
}
