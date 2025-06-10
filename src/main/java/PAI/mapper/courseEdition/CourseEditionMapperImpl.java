package PAI.mapper.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.teacher.ITeacherIDMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class CourseEditionMapperImpl implements ICourseEditionMapper {

    private final ICourseEditionIDMapper _courseEditionIDMapper;
    private final IProgrammeEditionIdMapper _programmeEditionIdMapper;
    private final ICourseInStudyPlanIDMapper _courseInStudyPlanIDMapper;
    private final ICourseEditionFactory _courseEditionFactory;
    private final ICourseEditionGeneratedIDMapper _courseEditionGeneratedIDMapper;
    private final ITeacherIDMapper _teacherIDMapper;

    public CourseEditionMapperImpl(ICourseEditionIDMapper courseEditionIDMapper, IProgrammeEditionIdMapper programmeEditionIdMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper, ICourseEditionFactory courseEditionFactory,ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper, ITeacherIDMapper teacherIDMapper) {

        if (courseEditionIDMapper == null)
            throw new IllegalArgumentException("courseEditionIDMapper cannot be null");
        if (programmeEditionIdMapper == null)
            throw new IllegalArgumentException("programmeEditionIdMapper cannot be null");
        if (courseInStudyPlanIDMapper == null)
            throw new IllegalArgumentException("courseInStudyPlanIDMapper cannot be null");
        if (courseEditionFactory == null)
            throw new IllegalArgumentException("courseEditionFactory cannot be null");
        if (courseEditionGeneratedIDMapper == null)
            throw new IllegalArgumentException("courseEditionGeneratedIDMapper cannot be null");
        if (teacherIDMapper == null)
            throw new IllegalArgumentException("teacherIDMapper cannot be null");

        _courseEditionIDMapper = courseEditionIDMapper;
        _programmeEditionIdMapper = programmeEditionIdMapper;
        _courseInStudyPlanIDMapper = courseInStudyPlanIDMapper;
        _courseEditionFactory = courseEditionFactory;
        _courseEditionGeneratedIDMapper = courseEditionGeneratedIDMapper;
        _teacherIDMapper = teacherIDMapper;
    }

    @Override
    public CourseEdition toDomain(CourseEditionDataModel courseEditionDataModel) throws Exception {

        if (courseEditionDataModel == null)
            throw new IllegalArgumentException("courseEditionDataModel cannot be null");

        CourseEditionID courseEditionID = _courseEditionIDMapper.toDomain(courseEditionDataModel.getCourseEditionIDDataModel());
        ProgrammeEditionID programmeEditionID = _programmeEditionIdMapper.toDomain(courseEditionDataModel.getProgrammeEditionIDDataModel());
        CourseInStudyPlanID courseInStudyPlanID = _courseInStudyPlanIDMapper.toDomain(courseEditionDataModel.getCourseInStudyPlanIDDataModel());

        TeacherIDDataModel teacherIDDataModel = courseEditionDataModel.getTeacherIDDataModel();
        TeacherID teacherID = teacherIDDataModel != null ? _teacherIDMapper.toDomain(teacherIDDataModel) : null;

        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = courseEditionDataModel.getCourseEditionGeneratedIDDataModel();
        CourseEditionGeneratedID courseEditionGeneratedID = _courseEditionGeneratedIDMapper.toDomain(courseEditionGeneratedIDDataModel);

        CourseEdition result = _courseEditionFactory.createCourseEditionFromDataModel(courseEditionID, courseInStudyPlanID, programmeEditionID, courseEditionGeneratedID, teacherID);

        return result;
    }

    @Override
    public CourseEditionDataModel toDataModel(CourseEdition courseEdition) throws Exception{

        if (courseEdition == null)
            throw new IllegalArgumentException("courseEdition cannot be null");

        CourseEditionIDDataModel courseEditionIDDataModel = _courseEditionIDMapper.toDataModel(courseEdition.identity());
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = _courseEditionGeneratedIDMapper.toDataModel(courseEdition.getCourseEditionGeneratedID());
        TeacherID ruc = courseEdition.getRuc();

        if (ruc == null) {
            return new CourseEditionDataModel(courseEditionIDDataModel, courseEditionGeneratedIDDataModel);
        }
        else {
            TeacherIDDataModel teacherIDDataModel = _teacherIDMapper.toDataModel(ruc);
            return new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);
        }
    }
}
