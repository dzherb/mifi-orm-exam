package orm_exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orm_exam.dto.request.QuizRequest;
import orm_exam.dto.response.QuizResponse;
import orm_exam.dto.response.QuizSubmissionResponse;
import orm_exam.entity.Quiz;
import orm_exam.entity.QuizSubmission;
import orm_exam.mapper.QuizMapper;
import orm_exam.mapper.QuizSubmissionMapper;
import orm_exam.service.QuizService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuizMapper quizMapper;
    private final QuizSubmissionMapper quizSubmissionMapper;

    @GetMapping
    public List<QuizResponse> getAllQuizzes() {
        return quizService.getAllQuizzes().stream()
                .map(quizMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuizResponse getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        return quizMapper.toResponse(quiz);
    }

    @PostMapping
    public QuizResponse createQuiz(@RequestBody QuizRequest quizRequest) {
        Quiz quiz = quizMapper.toEntity(quizRequest);
        Quiz createdQuiz = quizService.createQuiz(quiz);
        return quizMapper.toResponse(createdQuiz);
    }

    @PutMapping("/{id}")
    public QuizResponse updateQuiz(@PathVariable Long id, @RequestBody QuizRequest quizRequest) {
        Quiz updatedQuiz = quizService.updateQuiz(id, quizRequest);
        return quizMapper.toResponse(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{quizId}/take")
    public QuizSubmissionResponse takeQuiz(
            @PathVariable Long quizId,
            @RequestParam Long studentId,
            @RequestBody Map<Long, Long> answers) {
        QuizSubmission quizSubmission = quizService.takeQuiz(studentId, quizId, answers);
        return quizSubmissionMapper.toResponse(quizSubmission);
    }
}
