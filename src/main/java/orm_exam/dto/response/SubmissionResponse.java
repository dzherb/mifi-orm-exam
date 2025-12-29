package orm_exam.dto.response;

import lombok.Data;
import orm_exam.dto.nested.AssignmentInfo;
import orm_exam.dto.nested.UserInfo;

import java.time.LocalDateTime;

@Data
public class SubmissionResponse {
    private Long id;
    private String content;
    private LocalDateTime submittedAt;
    private Integer score;
    private String feedback;
    private AssignmentInfo assignment;
    private UserInfo student;
}
