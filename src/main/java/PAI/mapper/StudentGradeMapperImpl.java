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
    private final IStudentGradeIDMapper iStudentGradeIDMapper;


    public StudentGradeMapperImpl(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapperImpl studentIDMapperImpl, IStudentGradeFactory iStudentGradeFactory, IStudentGradeIDMapper iStudentGradeIDMapper) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapperImpl = studentIDMapperImpl;
        this.iStudentGradeFactory = iStudentGradeFactory;
        this.iStudentGradeIDMapper = iStudentGradeIDMapper;
    }

    public StudentGradeDM toData(StudentGrade studentGrade) throws Exception {

        StudentGradeIDDataModel studentGradeIDDataModel = iStudentGradeIDMapper.toDataModel(studentGrade.identity());

        return new StudentGradeDM( studentGradeIDDataModel, studentGrade.get_grade().knowGrade(),studentGrade.get_date().getLocalDate());
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.getGrade());
        Date date = new Date(studentGradeDM.getDate());

        StudentID studentID = studentIDMapperImpl.dataModelToDomain(studentGradeDM.getStudentId());

        CourseEditionID courseEditionID = courseEditionIDMapper.toDomain(studentGradeDM.getCourseEditionID());

        StudentGradeID studentGradeID = iStudentGradeIDMapper.toDomain(studentGradeDM.getId());

        return iStudentGradeFactory.newGradeStudentFromDataModel(grade,date,studentID,courseEditionID,studentGradeID);
    }
}
