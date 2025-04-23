package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeMapper implements IStudentGradeMapper {

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapper studentIDMapper;
    private final IStudentGradeFactory iStudentGradeFactory;


    public StudentGradeMapper(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapper studentIDMapper, IStudentGradeFactory iStudentGradeFactory) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapper = studentIDMapper;
        this.iStudentGradeFactory = iStudentGradeFactory;
    }

    public StudentGradeDM toData(StudentGrade studentGrade) throws Exception {

        StudentIDDataModel      studentIdDM       = studentIDMapper.domainToDataModel(studentGrade.get_studentID());
        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentGrade.get_studentID());
        CourseEditionIDDataModel courseEditionDM = courseEditionIDMapper.toDataModel(studentGrade.get_courseEditionID());

        StudentGradeIDDataModel idDM = new StudentGradeIDDataModel(
                studentIdDM,
                courseEditionDM
        );

        return new StudentGradeDM( idDM,studentGrade.get_grade().knowGrade(),studentGrade.get_date().getLocalDate(), courseEditionDM,studentIDDataModel);
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.get_grade());
        Date date = new Date(studentGradeDM.get_date());

        StudentID studentID = studentIDMapper.dataModelToDomain(studentGradeDM.getStudentId());

        CourseEditionID courseEditionID = courseEditionIDMapper.toDomain(studentGradeDM.getCourseEditionID());

        return iStudentGradeFactory.newGradeStudent(grade,date,studentID,courseEditionID);
    }
}
