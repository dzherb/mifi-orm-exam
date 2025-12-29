package orm_exam.dto.response;

import lombok.Data;
import orm_exam.dto.nested.ModuleInfo;

import java.util.List;

@Data
public class QuizResponse {
    private Long id;
    private String title;
    private Integer timeLimit;
    private ModuleInfo module;
    private List<QuestionResponse> questions;
}
