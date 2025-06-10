package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionGeneratedID;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;
import org.springframework.stereotype.Service;


    @Service
    public class CourseEditionGeneratedIDMapperImpl implements ICourseEditionGeneratedIDMapper {

        @Override
        public CourseEditionGeneratedID toDomain (CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel) {
            if (courseEditionGeneratedIDDataModel == null)
                throw new IllegalArgumentException("CourseEditionGeneratedIDDataModel cannot be null");
            return new CourseEditionGeneratedID();
        }

        @Override
        public CourseEditionGeneratedIDDataModel toDataModel (CourseEditionGeneratedID courseEditionGeneratedID) {
            if (courseEditionGeneratedID == null)
                throw new IllegalArgumentException("CourseEditionGeneratedID cannot be null");
            return new CourseEditionGeneratedIDDataModel(courseEditionGeneratedID.toString());
        }
}
