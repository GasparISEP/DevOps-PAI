package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionGeneratedID;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
    public class CourseEditionGeneratedIDMapperImpl implements ICourseEditionGeneratedIDMapper {

        @Override
        public CourseEditionGeneratedID toDomain(CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel) {
            if (courseEditionGeneratedIDDataModel == null)
                throw new IllegalArgumentException("CourseEditionGeneratedIDDataModel cannot be null");

            UUID generatedId = UUID.fromString(courseEditionGeneratedIDDataModel.getId());

            return new CourseEditionGeneratedID(generatedId);
        }

        @Override
        public CourseEditionGeneratedIDDataModel toDataModel (CourseEditionGeneratedID courseEditionGeneratedID) {
            if (courseEditionGeneratedID == null)
                throw new IllegalArgumentException("CourseEditionGeneratedID cannot be null");
            return new CourseEditionGeneratedIDDataModel(courseEditionGeneratedID.toString());
        }
}
