package orm_exam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import orm_exam.dto.request.CategoryRequest;
import orm_exam.dto.response.CategoryResponse;
import orm_exam.entity.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);
    CategoryResponse toResponse(Category category);
}
