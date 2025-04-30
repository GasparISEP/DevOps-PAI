package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class StudentGradeMapperImpl implements IStudentGradeMapper {

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapperImpl studentIDMapperImpl;
    private final IStudentGradeFactory iStudentGradeFactory;


    public StudentGradeMapperImpl(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapperImpl studentIDMapperImpl, IStudentGradeFactory iStudentGradeFactory) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapperImpl = studentIDMapperImpl;
        this.iStudentGradeFactory = iStudentGradeFactory;
    }

    public StudentGradeDM toData(StudentGrade studentGrade) throws Exception {

        StudentIDDataModel      studentIdDM       = studentIDMapperImpl.domainToDataModel(studentGrade.get_studentID());
        StudentIDDataModel studentIDDataModel = studentIDMapperImpl.domainToDataModel(studentGrade.get_studentID());
        CourseEditionIDDataModel courseEditionDM = courseEditionIDMapper.toDataModel(studentGrade.get_courseEditionID());

        StudentGradeIDDataModel idDM = new StudentGradeIDDataModel(
                studentIdDM,
                courseEditionDM
        );

        return new StudentGradeDM( idDM,studentGrade.get_grade().knowGrade(),studentGrade.get_date().getLocalDate(), courseEditionDM,studentIDDataModel);
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.getGrade());
        Date date = new Date(studentGradeDM.getDate());

        StudentID studentID = studentIDMapperImpl.dataModelToDomain(studentGradeDM.getStudentId());

        CourseEditionID courseEditionID = courseEditionIDMapper.toDomain(studentGradeDM.getCourseEditionID());

        return iStudentGradeFactory.newGradeStudent(grade,date,studentID,courseEditionID);
    }
}
