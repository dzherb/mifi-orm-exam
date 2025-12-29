package orm_exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import orm_exam.dto.request.ModuleRequest;
import orm_exam.entity.Course;
import orm_exam.entity.Module;
import orm_exam.exception.EntityNotFoundException;
import orm_exam.repository.CourseRepository;
import orm_exam.repository.ModuleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module getModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module with id " + id + " not found"));
    }

    public Module createModule(Module module) {
        Long courseId = module.getCourse().getId();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        module.setCourse(course);

        return moduleRepository.save(module);
    }

    public Module updateModule(Long id, ModuleRequest moduleRequest) {
        Module existingModule = moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module with id " + id + " not found"));

        if (moduleRequest.getTitle() != null) {
            existingModule.setTitle(moduleRequest.getTitle());
        }

        if (moduleRequest.getOrderIndex() != null) {
            existingModule.setOrderIndex(moduleRequest.getOrderIndex());
        }

        if (moduleRequest.getCourseId() != null) {
            Course course = courseRepository.findById(moduleRequest.getCourseId())
                    .orElseThrow(() -> new EntityNotFoundException("Course with id " + moduleRequest.getCourseId() + " not found"));
            existingModule.setCourse(course);
        }

        return moduleRepository.save(existingModule);
    }

    public void deleteModule(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module with id " + id + " not found"));
        moduleRepository.delete(module);
    }
}
