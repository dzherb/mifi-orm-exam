package orm_exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orm_exam.dto.request.TagRequest;
import orm_exam.dto.response.TagResponse;
import orm_exam.entity.Tag;
import orm_exam.mapper.TagMapper;
import orm_exam.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public List<TagResponse> getAllTags() {
        return tagService.getAllTags().stream()
                .map(tagMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TagResponse getTagById(@PathVariable Long id) {
        Tag tag = tagService.getTagById(id);
        return tagMapper.toResponse(tag);
    }

    @PostMapping
    public TagResponse createTag(@RequestBody TagRequest tagRequest) {
        Tag tag = tagMapper.toEntity(tagRequest);
        Tag createdTag = tagService.createTag(tag);
        return tagMapper.toResponse(createdTag);
    }

    @PutMapping("/{id}")
    public TagResponse updateTag(@PathVariable Long id, @RequestBody TagRequest tagRequest) {
        Tag updatedTag = tagService.updateTag(id, tagRequest);
        return tagMapper.toResponse(updatedTag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
