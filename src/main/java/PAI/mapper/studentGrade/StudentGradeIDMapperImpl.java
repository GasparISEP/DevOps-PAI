package PAI.mapper.studentGrade;

import PAI.VOs.*;
import PAI.mapper.student.StudentIDMapperImpl;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.studentGrade.StudentGradeIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class StudentGradeIDMapperImpl implements IStudentGradeIDMapper {

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapperImpl studentIDMapperImpl;

    public StudentGradeIDMapperImpl(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapperImpl studentIDMapperImpl) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapperImpl = studentIDMapperImpl;
    }

    public StudentGradeIDDataModel toDataModel (StudentGradeID studentGradeID) throws Exception{

        StudentID studentID = studentGradeID.get_studentID();
        CourseEditionID courseEditionID = studentGradeID.get_courseEdition();
        return new StudentGradeIDDataModel(studentIDMapperImpl.domainToDataModel(studentID),courseEditionIDMapper.toDataModel(courseEditionID));
    }

    public StudentGradeID toDomain (StudentGradeIDDataModel studentGradeIDDataModel) throws Exception{

        CourseEditionIDDataModel courseEditionIDDataModel = studentGradeIDDataModel.getCourseEditionIDDataModel();
        StudentIDDataModel studentIDDataModel = studentGradeIDDataModel.getStudentIDDataModel();
        return new StudentGradeID(studentIDMapperImpl.dataModelToDomain(studentIDDataModel),courseEditionIDMapper.toDomain(courseEditionIDDataModel));
    }
}
