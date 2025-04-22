package PAI.mapper.Course;

import PAI.domain.course.Course;
import PAI.persistence.datamodel.course.CourseDataModel;


public interface ICourseMapper {

    Course toDomain (CourseDataModel courseDataModel) throws Exception;

    Iterable<Course> toDomain (Iterable<CourseDataModel> listCourseDataModel);

    CourseDataModel toDataModel(Course course);

}
