package PAI.mapper.studentGrade;

import PAI.VOs.*;
import PAI.domain.studentGrade.StudentGrade;
import PAI.domain.studentGrade.IStudentGradeFactory;
import PAI.mapper.student.StudentIDMapperImpl;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.studentGrade.StudentGradeDM;
import PAI.persistence.datamodel.studentGrade.StudentGradeIDDataModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class StudentGradeMapperImpl implements IStudentGradeMapper {

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapperImpl studentIDMapperImpl;
    private final IStudentGradeFactory iStudentGradeFactory;
    private final IStudentGradeIDMapper iStudentGradeIDMapper;


    public StudentGradeMapperImpl(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapperImpl studentIDMapperImpl, IStudentGradeFactory iStudentGradeFactory, IStudentGradeIDMapper iStudentGradeIDMapper) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapperImpl = studentIDMapperImpl;
        this.iStudentGradeFactory = iStudentGradeFactory;
        this.iStudentGradeIDMapper = iStudentGradeIDMapper;
    }

    public StudentGradeDM toData(StudentGrade studentGrade) throws Exception {

        StudentGradeIDDataModel studentGradeIDDataModel = iStudentGradeIDMapper.toDataModel(studentGrade.identity());
        StudentGradeGeneratedID studentGradeGeneratedID = studentGrade.getStudentGradeGeneratedID();
        UUID studentGradeUUID = studentGradeGeneratedID.getStudentGradeGeneratedID();
        Double grade = studentGrade.getGrade().knowGrade();
        LocalDate localDate = studentGrade.getDate().getLocalDate();


        return new StudentGradeDM( studentGradeIDDataModel, studentGradeUUID, grade, localDate);
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.getGrade());
        Date date = new Date(studentGradeDM.getDate());

        StudentID studentID = studentIDMapperImpl.dataModelToDomain(studentGradeDM.getStudentId());

        CourseEditionID courseEditionID = courseEditionIDMapper.toDomain(studentGradeDM.getCourseEditionID());

        StudentGradeID studentGradeID = iStudentGradeIDMapper.toDomain(studentGradeDM.getId());

        UUID studentGradeUUID = studentGradeDM.getStudentGradeGeneratedID();
        StudentGradeGeneratedID studentGradeGeneratedID = new StudentGradeGeneratedID(studentGradeUUID);

        return iStudentGradeFactory.newGradeStudentFromDataModel(grade,date,studentID,courseEditionID,studentGradeID, studentGradeGeneratedID);
    }
}
