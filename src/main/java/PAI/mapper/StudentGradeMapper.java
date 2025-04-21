package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.mapper.courseInStudyPlanID.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.stereotype.Service;

@Service
public class StudentGradeMapper implements IStudentGradeMapper {

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapper studentIDMapper;

    public StudentGradeMapper(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapper studentIDMapper) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapper = studentIDMapper;
    }

    public StudentGradeDM toData(StudentGrade studentGrade) throws Exception {

        StudentIDDataModel studentIDDataModel = studentIDMapper.domainToDataModel(studentGrade.get_studentID());
        CourseEditionIDDataModel courseEditionDM = courseEditionIDMapper.toDataModel(studentGrade.get_courseEditionID());

        return new StudentGradeDM( studentGrade.identity().toString().hashCode(),studentGrade.get_grade().knowGrade(),studentGrade.get_date().getLocalDate(), courseEditionDM,studentIDDataModel);
    }

    public StudentGrade toDomain( StudentGradeDM studentGradeDM ) throws Exception {

        Grade grade = new Grade(studentGradeDM.get_grade());
        Date date = new Date(studentGradeDM.get_date());

        StudentID studentID = studentIDMapper.dataModelToDomain(studentGradeDM.getStudentId());

        CourseEditionID courseEditionID = courseEditionIDMapper.toDomain(studentGradeDM.getCourseEditionID());

        return new StudentGrade(grade,date,studentID,courseEditionID);
    }
}
