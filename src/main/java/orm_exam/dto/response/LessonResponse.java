package orm_exam.dto.response;

import lombok.Data;
import orm_exam.dto.nested.ModuleInfo;

@Data
public class LessonResponse {
    private Long id;
    private String title;
    private String content;
    private ModuleInfo module;
}
