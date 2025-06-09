package PAI.mapper.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanGeneratedID;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanGeneratedIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class CourseInStudyPlanGeneratedIDMapperImpl implements ICourseInStudyPlanGeneratedIDMapper {

    public CourseInStudyPlanGeneratedIDMapperImpl() {
    }

    @Override
    public CourseInStudyPlanGeneratedIDDataModel toDataModel(CourseInStudyPlanGeneratedID domainId) {
        if (domainId == null) {
            throw new IllegalArgumentException("CourseInStudyPlanGeneratedID cannot be null");
        }

        return new CourseInStudyPlanGeneratedIDDataModel(domainId.getId());
    }

    @Override
    public CourseInStudyPlanGeneratedID toDomain(CourseInStudyPlanGeneratedIDDataModel dataModel) {
        if (dataModel == null) {
            throw new IllegalArgumentException("CourseInStudyPlanGeneratedIDDataModel cannot be null");
        }

        return new CourseInStudyPlanGeneratedID(dataModel.getId());
    }
}
