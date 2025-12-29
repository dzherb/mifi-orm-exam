package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.AnswerOption;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
}
