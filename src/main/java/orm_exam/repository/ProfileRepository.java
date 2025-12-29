package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
