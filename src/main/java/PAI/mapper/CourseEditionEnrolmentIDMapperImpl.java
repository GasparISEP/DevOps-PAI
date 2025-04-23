package PAI.mapper;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseEditionEnrolmentIDMapperImpl implements ICourseEditionEnrolmentIDMapper{

    private final ICourseEditionIDMapper courseEditionIDMapper;

    public CourseEditionEnrolmentIDMapperImpl(ICourseEditionIDMapper courseEditionIDMapper) {
        if (courseEditionIDMapper == null) {
            throw new IllegalArgumentException("Course Edition ID Mapper Interface cannot be null!");
        }

        this.courseEditionIDMapper = courseEditionIDMapper;
    }

    @Override
    public Optional<CourseEditionEnrolmentID> toDomain(CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel) throws Exception {

        if (courseEditionEnrolmentIDDataModel == null){
            return Optional.empty();
        }

        int uniqueNumber = Integer.parseInt(courseEditionEnrolmentIDDataModel.findStudentID());
        StudentID studentID = new StudentID(uniqueNumber);

        CourseEditionID courseEditionID = courseEditionIDMapper.toDomain(courseEditionEnrolmentIDDataModel.findCourseEditionID());

        return Optional.of(new CourseEditionEnrolmentID(studentID, courseEditionID));
    }

    @Override
    public Optional <CourseEditionEnrolmentIDDataModel> toDataModel(CourseEditionEnrolmentID courseEditionEnrolmentID) throws Exception {

        if (courseEditionEnrolmentID == null){
            return Optional.empty();
        }

        CourseEditionIDDataModel courseEditionIDDataModel = courseEditionIDMapper.toDataModel(courseEditionEnrolmentID.findCourseEditionID());

        return Optional.of(new CourseEditionEnrolmentIDDataModel(courseEditionEnrolmentID.findStudentID().toString(),
                courseEditionIDDataModel));
    }
}
