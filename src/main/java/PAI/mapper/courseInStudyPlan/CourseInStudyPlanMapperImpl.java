package PAI.mapper.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.mapper.course.ICourseIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanGeneratedIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import org.springframework.stereotype.Component;
import static PAI.utils.ValidationUtils.*;

@Component
public class CourseInStudyPlanMapperImpl implements ICourseInStudyPlanMapper {

    private final ICourseIDMapper courseIDMapper;
    private final IStudyPlanIDMapper studyPlanIDMapper;
    private final ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper;
    private final ICourseInStudyPlanFactory courseInStudyPlanFactory;
    private final ICourseInStudyPlanGeneratedIDMapper generatedIDMapper;
    private final IProgrammeIDMapper programmeIDMapper;


    public CourseInStudyPlanMapperImpl(ICourseIDMapper courseIDMapper, IStudyPlanIDMapper studyPlanIDMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper,
                                       ICourseInStudyPlanFactory courseInStudyPlanFactory, ICourseInStudyPlanGeneratedIDMapper generatedIDMapper, IProgrammeIDMapper programmeIDMapper) throws Exception {

        this.studyPlanIDMapper = validateNotNull(studyPlanIDMapper, "StudyPlanIDMapper");
        this.courseIDMapper = validateNotNull(courseIDMapper, "CourseIDMapper");
        this.courseInStudyPlanIDMapper = validateNotNull(courseInStudyPlanIDMapper, "CourseInStudyPlanIDMapper");
        this.courseInStudyPlanFactory = validateNotNull(courseInStudyPlanFactory, "CourseInStudyPlanFactory");
        this.generatedIDMapper = validateNotNull(generatedIDMapper, "CourseInStudyPlanGeneratedIDMapper");
        this.programmeIDMapper = validateNotNull(programmeIDMapper, "ProgrammeIDMapper");

    }

    public CourseInStudyPlanDataModel toDataModel(CourseInStudyPlan courseInStudyPlan) {

        CourseInStudyPlanGeneratedIDDataModel generatedIDDataModel = generatedIDMapper.toDataModel(courseInStudyPlan.getGeneratedID());

        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = courseInStudyPlanIDMapper.toDataModel(courseInStudyPlan.identity());

        int semester = courseInStudyPlan.getSemester().toInt();

        int curricularYear = courseInStudyPlan.getCurricularYear().toInt();

        int durationOfCourse = courseInStudyPlan.getDurationOfCourse().getDuration();

        double quantityOfCreditsEcts = courseInStudyPlan.getQuantityOfCreditsEcts().getQuantity();

        return new CourseInStudyPlanDataModel(generatedIDDataModel, courseInStudyPlanIDDataModel, semester, curricularYear, durationOfCourse, quantityOfCreditsEcts);
    }

    public CourseInStudyPlan toDomain(CourseInStudyPlanDataModel courseInStudyPlanDataModel) {
        try {
            Semester semester = new Semester(courseInStudyPlanDataModel.getSemester());

            CurricularYear year = new CurricularYear(courseInStudyPlanDataModel.getCurricularYear());

            CourseID courseId = courseIDMapper.toDomain(courseInStudyPlanDataModel.getCourseIDDataModel());

            StudyPlanID studyPlanId = studyPlanIDMapper.toDomain(courseInStudyPlanDataModel.getStudyPlanIDDataModel());

            DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(courseInStudyPlanDataModel.getDurationOfCourse());

            CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(courseInStudyPlanDataModel.getQuantityOfCreditsEcts());

            CourseInStudyPlanID courseInStudyPlanId = courseInStudyPlanIDMapper.toDomain(courseInStudyPlanDataModel.getCourseInStudyPlanIDDataModel());

            CourseInStudyPlanGeneratedID generatedID = generatedIDMapper.toDomain(courseInStudyPlanDataModel.getGeneratedID());

            ProgrammeID programmeID = programmeIDMapper.toDomain(courseInStudyPlanDataModel.getStudyPlanIDDataModel().getProgrammeID());

            return courseInStudyPlanFactory.newCourseInStudyPlanFromDataModel(courseInStudyPlanId, generatedID, semester, year, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts, programmeID);

        } catch (Exception e) {
            throw new RuntimeException("Error trying to map CourseInStudyPlanDataModel back to domain", e);
        }
    }
}