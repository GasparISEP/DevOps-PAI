package PAI.mapper.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.mapper.course.ICourseIDMapper;
import PAI.mapper.studyPlan.IStudyPlanIDMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanGeneratedIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class CourseInStudyPlanMapperImpl implements ICourseInStudyPlanMapper {

    private final ICourseIDMapper _courseIDMapper;
    private final IStudyPlanIDMapper _studyPlanIDMapper;
    private final ICourseInStudyPlanIDMapper _courseInStudyPlanIDMapper;
    private final ICourseInStudyPlanFactory _courseInStudyPlanFactory;
    private final ICourseInStudyPlanGeneratedIDMapper _generatedIDMapper;


    public CourseInStudyPlanMapperImpl(ICourseIDMapper courseIDMapper, IStudyPlanIDMapper studyPlanIDMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper, ICourseInStudyPlanFactory courseInStudyPlanFactory, ICourseInStudyPlanGeneratedIDMapper generatedIDMapper) throws Exception {
        if (studyPlanIDMapper == null)
            throw new IllegalArgumentException("StudyPlanIDMapper cannot be null");
        _studyPlanIDMapper = studyPlanIDMapper;

        if (courseIDMapper == null)
            throw new IllegalArgumentException("CourseIDMapper cannot be null");
        _courseIDMapper = courseIDMapper;

        if (courseInStudyPlanIDMapper == null)
            throw new IllegalArgumentException("CourseInStudyPlanIDMapper cannot be null");
        _courseInStudyPlanIDMapper = courseInStudyPlanIDMapper;

        if (courseInStudyPlanFactory == null)
            throw new IllegalArgumentException("CourseInStudyPlanFactory cannot be null");
        _courseInStudyPlanFactory = courseInStudyPlanFactory;

        if (generatedIDMapper == null)
            throw new IllegalArgumentException("CourseInStudyPlanGeneratedIDMapper cannot be null");
        _generatedIDMapper = generatedIDMapper;
    }

    public CourseInStudyPlanDataModel toDataModel(CourseInStudyPlan courseInStudyPlan) {

        CourseInStudyPlanGeneratedIDDataModel generatedIDDataModel = _generatedIDMapper.toDataModel(courseInStudyPlan.getGeneratedID());

        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = _courseInStudyPlanIDMapper.toDataModel(courseInStudyPlan.identity());

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

            CourseID courseId = _courseIDMapper.toDomain(courseInStudyPlanDataModel.getCourseIDDataModel());

            StudyPlanID studyPlanId = _studyPlanIDMapper.toDomain(courseInStudyPlanDataModel.getStudyPlanIDDataModel());

            DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(courseInStudyPlanDataModel.getDurationOfCourse());

            CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(courseInStudyPlanDataModel.getQuantityOfCreditsEcts());

            CourseInStudyPlanID courseInStudyPlanId = _courseInStudyPlanIDMapper.toDomain(courseInStudyPlanDataModel.getCourseInStudyPlanIDDataModel());

            CourseInStudyPlanGeneratedID generatedID = _generatedIDMapper.toDomain(courseInStudyPlanDataModel.getGeneratedID());

            return _courseInStudyPlanFactory.newCourseInStudyPlanFromDataModel(courseInStudyPlanId, generatedID, semester, year, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts);

        } catch (Exception e) {
            throw new RuntimeException("Error trying to map CourseInStudyPlanDataModel back to domain", e);
        }
    }
}