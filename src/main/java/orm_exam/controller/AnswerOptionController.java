package orm_exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orm_exam.dto.request.AnswerOptionRequest;
import orm_exam.dto.response.AnswerOptionResponse;
import orm_exam.entity.AnswerOption;
import orm_exam.mapper.AnswerOptionMapper;
import orm_exam.service.AnswerOptionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/answer-options")
@RequiredArgsConstructor
public class AnswerOptionController {
    private final AnswerOptionService answerOptionService;
    private final AnswerOptionMapper answerOptionMapper;

    @GetMapping
    public List<AnswerOptionResponse> getAllAnswerOptions() {
        return answerOptionService.getAllAnswerOptions().stream()
                .map(answerOptionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AnswerOptionResponse getAnswerOptionById(@PathVariable Long id) {
        AnswerOption answerOption = answerOptionService.getAnswerOptionById(id);
        return answerOptionMapper.toResponse(answerOption);
    }

    @PostMapping
    public AnswerOptionResponse createAnswerOption(@RequestBody AnswerOptionRequest answerOptionRequest) {
        AnswerOption answerOption = answerOptionMapper.toEntity(answerOptionRequest);
        AnswerOption createdAnswerOption = answerOptionService.createAnswerOption(answerOption);
        return answerOptionMapper.toResponse(createdAnswerOption);
    }

    @PutMapping("/{id}")
    public AnswerOptionResponse updateAnswerOption(@PathVariable Long id, @RequestBody AnswerOptionRequest answerOptionRequest) {
        AnswerOption updatedAnswerOption = answerOptionService.updateAnswerOption(id, answerOptionRequest);
        return answerOptionMapper.toResponse(updatedAnswerOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerOption(@PathVariable Long id) {
        answerOptionService.deleteAnswerOption(id);
        return ResponseEntity.noContent().build();
    }

}
