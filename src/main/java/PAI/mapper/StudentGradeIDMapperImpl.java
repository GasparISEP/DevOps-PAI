package PAI.mapper;

import PAI.VOs.StudentGradeID;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;

public class StudentGradeIDMapperImpl implements IStudentGradeIDMapper{

    public StudentGradeIDDataModel toDataModel (StudentGradeID studentGradeID){

        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel();
        StudentIDMapper studentIDMapper = new StudentIDMapper();

        return new StudentGradeIDDataModel(studentIDMapper.domainToDataModel(studentGradeID.get_studentID()),courseEditionIDDataModel);
    }


}
