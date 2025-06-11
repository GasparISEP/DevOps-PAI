package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionGeneratedID;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;

public interface ICourseEditionGeneratedIDMapper {
    CourseEditionGeneratedID toDomain (CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel);

    CourseEditionGeneratedIDDataModel toDataModel (CourseEditionGeneratedID courseEditionGeneratedID);

}
