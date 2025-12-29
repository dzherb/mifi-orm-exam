package orm_exam.dto.response;

import lombok.Data;
import orm_exam.dto.nested.CourseInfo;

@Data
public class ModuleResponse {
    private Long id;
    private String title;
    private Integer orderIndex;
    private CourseInfo course;
}
