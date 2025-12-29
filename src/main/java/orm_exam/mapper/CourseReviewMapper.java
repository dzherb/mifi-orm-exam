package orm_exam.mapper;

import org.mapstruct.*;
import orm_exam.dto.nested.CourseInfo;
import orm_exam.dto.request.CourseReviewRequest;
import orm_exam.dto.response.CourseReviewResponse;
import orm_exam.entity.Course;
import orm_exam.entity.CourseReview;
import orm_exam.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CourseMapper.class, UserMapper.class})
public interface CourseReviewMapper {

    @Mapping(target = "course", source = "courseId", qualifiedByName = "courseIdToCourse")
    @Mapping(target = "student", source = "studentId", qualifiedByName = "courseReviewStudentIdToUser")
    CourseReview toEntity(CourseReviewRequest request);

    @Mapping(target = "course", source = "course", qualifiedByName = "courseToCourseInfo")
    @Mapping(target = "student", source = "student", qualifiedByName = "userToUserInfo")
    CourseReviewResponse toResponse(CourseReview courseReview);

    @Named("courseToCourseInfo")
    default CourseInfo courseToCourseInfo(Course course) {
        if (course == null) {
            return null;
        }
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setId(course.getId());
        courseInfo.setTitle(course.getTitle());
        return courseInfo;
    }

    @Named("courseIdToCourse")
    default Course courseIdToCourse(Long courseId) {
        if (courseId == null) {
            return null;
        }
        Course course = new Course();
        course.setId(courseId);
        return course;
    }

    @Named("courseReviewStudentIdToUser")
    default User courseReviewStudentIdToUser(Long studentId) {
        if (studentId == null) {
            return null;
        }
        User user = new User();
        user.setId(studentId);
        return user;
    }
}
