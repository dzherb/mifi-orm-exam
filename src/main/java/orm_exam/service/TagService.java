package orm_exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import orm_exam.dto.request.TagRequest;
import orm_exam.entity.Tag;
import orm_exam.exception.DuplicateEntityException;
import orm_exam.exception.EntityNotFoundException;
import orm_exam.repository.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with id " + id + " not found"));
    }

    public Tag createTag(Tag tag) {
        try {
            return tagRepository.save(tag);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntityException("Tag with this name already exists.");
        }
    }

    public Tag updateTag(Long id, TagRequest tagRequest) {
        Tag existingTag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with id " + id + " not found"));

        if (tagRequest.getName() != null) {
            existingTag.setName(tagRequest.getName());
        }

        return tagRepository.save(existingTag);
    }

    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag with id " + id + " not found"));
        tagRepository.delete(tag);
    }
}
