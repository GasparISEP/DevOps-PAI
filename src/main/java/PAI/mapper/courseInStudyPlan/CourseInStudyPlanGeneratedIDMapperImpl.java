package PAI.mapper.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanGeneratedID;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanGeneratedIDDataModel;
import org.springframework.stereotype.Component;
import static PAI.utils.ValidationUtils.*;

@Component
public class CourseInStudyPlanGeneratedIDMapperImpl implements ICourseInStudyPlanGeneratedIDMapper {

    public CourseInStudyPlanGeneratedIDMapperImpl() {
    }

    @Override
    public CourseInStudyPlanGeneratedIDDataModel toDataModel(CourseInStudyPlanGeneratedID domainId) {
        validateNotNull(domainId, "Course In Study Plan Generated ID");
        return new CourseInStudyPlanGeneratedIDDataModel(domainId.getId());
    }

    @Override
    public CourseInStudyPlanGeneratedID toDomain(CourseInStudyPlanGeneratedIDDataModel dataModel) {
        validateNotNull(dataModel, "Course In Study Plan Generated ID Data Model");
        return new CourseInStudyPlanGeneratedID(dataModel.getId());
    }
}
