package PAI.mapper.CourseID;

import PAI.VOs.CourseID;
import PAI.persistence.datamodel.course.CourseIDDataModel;

public interface ICourseIDMapper {

    CourseID toDomain(CourseIDDataModel courseIDDataModel);

    CourseIDDataModel toDataModel(CourseID courseID);
}
