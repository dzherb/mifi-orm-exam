package orm_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orm_exam.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
