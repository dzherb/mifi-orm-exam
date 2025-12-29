package orm_exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orm_exam.dto.request.AnswerOptionRequest;
import orm_exam.dto.request.QuestionRequest;
import orm_exam.dto.response.QuestionResponse;
import orm_exam.entity.AnswerOption;
import orm_exam.entity.Question;
import orm_exam.mapper.QuestionMapper;
import orm_exam.service.AnswerOptionService;
import orm_exam.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final AnswerOptionService answerOptionService;

    @GetMapping
    public List<QuestionResponse> getAllQuestions() {
        return questionService.getAllQuestions().stream()
                .map(questionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return questionMapper.toResponse(question);
    }

    @PostMapping
    public QuestionResponse createQuestion(@RequestBody QuestionRequest questionRequest) {
        Question question = questionMapper.toEntity(questionRequest);
        Question createdQuestion = questionService.createQuestion(question);

        // Создание вариантов ответов
        if (questionRequest.getOptions() != null) {
            for (AnswerOptionRequest optionRequest : questionRequest.getOptions()) {
                AnswerOption option = new AnswerOption();
                option.setText(optionRequest.getText());
                option.setIsCorrect(optionRequest.getIsCorrect());
                option.setQuestion(createdQuestion);
                answerOptionService.createAnswerOption(option);
            }
        }

        return questionMapper.toResponse(createdQuestion);
    }

    @PutMapping("/{id}")
    public QuestionResponse updateQuestion(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        Question updatedQuestion = questionService.updateQuestion(id, questionRequest);
        return questionMapper.toResponse(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
