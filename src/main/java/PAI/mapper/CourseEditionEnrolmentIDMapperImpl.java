package PAI.mapper;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseEditionEnrolmentIDMapperImpl implements ICourseEditionEnrolmentIDMapper{

    private final IStudentIDMapper studentIDMapper;
    private final ICourseEditionIDMapper courseEditionIDMapper;

    public CourseEditionEnrolmentIDMapperImpl(IStudentIDMapper studentIDMapper, ICourseEditionIDMapper courseEditionIDMapper) {
        validateStudentIDMapper (studentIDMapper);
        validateCourseEditionIDMapper (courseEditionIDMapper);

        this.studentIDMapper = studentIDMapper;
        this.courseEditionIDMapper = courseEditionIDMapper;
    }

    @Override
    public Optional<CourseEditionEnrolmentID> toDomain(CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel) throws Exception {

        if (courseEditionEnrolmentIDDataModel == null){
            return Optional.empty();
        }

        StudentID studentID = studentIDMapper.dataModelToDomain(courseEditionEnrolmentIDDataModel.findStudentID());

        CourseEditionID courseEditionID = courseEditionIDMapper.toDomain(courseEditionEnrolmentIDDataModel.findCourseEditionID());

        return Optional.of(new CourseEditionEnrolmentID(studentID, courseEditionID));
    }

    @Override
    public Optional <CourseEditionEnrolmentIDDataModel> toDataModel(CourseEditionEnrolmentID courseEditionEnrolmentID) throws Exception {

        if (courseEditionEnrolmentID == null){
            return Optional.empty();
        }

        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(courseEditionEnrolmentID.findStudentID());

        CourseEditionIDDataModel courseEditionIDDataModel = courseEditionIDMapper.toDataModel(courseEditionEnrolmentID.findCourseEditionID());

        return Optional.of(new CourseEditionEnrolmentIDDataModel(studentIDDataModel, courseEditionIDDataModel));
    }

    private void validateStudentIDMapper(IStudentIDMapper studentIDMapper) {
        if (studentIDMapper == null){
            throw new IllegalArgumentException("Student ID Mapper Interface cannot be null!");
        }
    }

    private void validateCourseEditionIDMapper(ICourseEditionIDMapper courseEditionIDMapper) {
        if (courseEditionIDMapper == null){
            throw new IllegalArgumentException("Course Edition ID Mapper Interface cannot be null!");
        }
    }
}
