package PAI.mapper.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.mapper.courseID.ICourseIDMapper;
import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;

public class CourseInStudyPlanMapperImpl implements ICourseInStudyPlanMapper {

    private final ICourseIDMapper _courseIDMapper;
    private final IStudyPlanIDMapper _studyPlanIDMapper;
    private final ICourseInStudyPlanIDMapper _courseInStudyPlanIDMapper;
    private final ICourseInStudyPlanFactory _courseInStudyPlanFactory;

    public CourseInStudyPlanMapperImpl(ICourseIDMapper courseIDMapper, IStudyPlanIDMapper studyPlanIDMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper, ICourseInStudyPlanFactory courseInStudyPlanFactory) throws Exception {
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
    }

    public CourseInStudyPlanDataModel toDataModel(CourseInStudyPlan courseInStudyPlan) {

        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = _courseInStudyPlanIDMapper.toDataModel(courseInStudyPlan.identity());

        int semester = courseInStudyPlan.getSemester().toInt();

        int curricularYear = courseInStudyPlan.getCurricularYear().toInt();

        int durationOfCourse = courseInStudyPlan.getDurationOfCourse().getDuration();

        double quantityOfCreditsEcts = courseInStudyPlan.getQuantityOfCreditsEcts().getQuantity();

        StudyPlanIDDataModel studyPlanDM = _studyPlanIDMapper.toDataModel(courseInStudyPlan.getStudyplanID());

        CourseIDDataModel courseDM = _courseIDMapper.toDataModel(courseInStudyPlan.getCourseID());

        return new CourseInStudyPlanDataModel(courseInStudyPlanIDDataModel, studyPlanDM, courseDM, semester, curricularYear, durationOfCourse, quantityOfCreditsEcts);
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

        return _courseInStudyPlanFactory.newCourseInStudyPlanFromDataModel(courseInStudyPlanId, semester, year, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts);

        } catch (Exception e) {
            throw new RuntimeException("Error trying to map CourseInStudyPlanDataModel back to domain", e);
        }
    }
}