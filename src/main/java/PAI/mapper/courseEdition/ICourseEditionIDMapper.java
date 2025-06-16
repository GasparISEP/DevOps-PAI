package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;

public interface ICourseEditionIDMapper {

    CourseEditionID toDomain (CourseEditionIDDataModel courseEditionIDDataModel);

    CourseEditionIDDataModel toDataModel (CourseEditionID courseEditionID);
}
