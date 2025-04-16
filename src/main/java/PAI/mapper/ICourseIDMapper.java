package PAI.mapper;

import PAI.VOs.CourseID;
import PAI.persistence.datamodel.CourseIDDataModel;

public interface ICourseIDMapper {

    CourseID toDomain(CourseIDDataModel courseIDDataModel);

    CourseIDDataModel toDataModel(CourseID courseID);
}
