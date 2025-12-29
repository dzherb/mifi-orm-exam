package orm_exam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import orm_exam.dto.request.TagRequest;
import orm_exam.dto.response.TagResponse;
import orm_exam.entity.Tag;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    Tag toEntity(TagRequest request);
    TagResponse toResponse(Tag tag);
}
