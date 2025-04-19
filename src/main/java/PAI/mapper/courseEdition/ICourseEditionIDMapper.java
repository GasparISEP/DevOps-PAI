package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;

public interface ICourseEditionIDMapper {

    CourseEditionID toDomain (CourseEditionIDDataModel courseEditionIDDataModel) throws Exception;

    CourseEditionIDDataModel toDataModel (CourseEditionID courseEditionID) throws Exception;
}
