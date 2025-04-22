package PAI.mapper.Course;

import PAI.domain.course.Course;
import PAI.persistence.datamodel.course.CourseDataModel;


public interface ICourseMapper {

     Course toDomain (CourseDataModel courseDataModel) throws Exception;


    CourseDataModel toDataModel(Course course);

}
