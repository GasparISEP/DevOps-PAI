package PAI.mapper.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.StudyPlanID;
import PAI.mapper.course.ICourseIDMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.springframework.stereotype.Component;

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class CourseInStudyPlanIDMapperImpl implements ICourseInStudyPlanIDMapper {

    private final ICourseIDMapper _courseIDMapper;
    private final IStudyPlanIDMapper _studyPlanIDMapper;

    public CourseInStudyPlanIDMapperImpl(IStudyPlanIDMapper studyPlanIDMapper, ICourseIDMapper courseIDMapper) {
        _studyPlanIDMapper = validateNotNull(studyPlanIDMapper, "StudyPlanIDMapper");
        _courseIDMapper = validateNotNull(courseIDMapper, "CourseIDMapper");
    }

    public CourseInStudyPlanIDDataModel toDataModel(CourseInStudyPlanID courseInStudyPlanID) {

        StudyPlanID studyPlanID = courseInStudyPlanID.getStudyPlanID();
        StudyPlanIDDataModel studyPlanIDDataModel = _studyPlanIDMapper.toDataModel(studyPlanID);

        CourseID courseID = courseInStudyPlanID.getCourseID();
        CourseIDDataModel courseIDDataModel = _courseIDMapper.toDataModel(courseID);

        return new CourseInStudyPlanIDDataModel(studyPlanIDDataModel,
                courseIDDataModel);
    }

    public CourseInStudyPlanID toDomain(CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel) {

        StudyPlanIDDataModel studyPlanIDDataModel = courseInStudyPlanIDDataModel.getStudyPlanIDDataModel();
        StudyPlanID studyPlanID = _studyPlanIDMapper.toDomain(studyPlanIDDataModel);

        CourseIDDataModel courseIDDataModel = courseInStudyPlanIDDataModel.getCourseID();
        CourseID courseID = _courseIDMapper.toDomain(courseIDDataModel);

        return new CourseInStudyPlanID(courseID, studyPlanID);
    }
}