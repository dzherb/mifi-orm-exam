package orm_exam.mapper;

import org.mapstruct.*;
import orm_exam.dto.nested.CourseInfo;
import orm_exam.dto.nested.UserInfo;
import orm_exam.dto.response.EnrollmentResponse;
import orm_exam.entity.Course;
import orm_exam.entity.Enrollment;
import orm_exam.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, CourseMapper.class})
public interface EnrollmentMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "userToStudentInfo")
    @Mapping(target = "course", source = "course", qualifiedByName = "courseToCourseInfo")
    EnrollmentResponse toResponse(Enrollment enrollment);

    @Named("userToStudentInfo")
    default UserInfo userToStudentInfo(User user) {
        if (user == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        return userInfo;
    }

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
}
