package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
