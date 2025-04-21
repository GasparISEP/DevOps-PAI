package PAI.mapper;

import PAI.VOs.*;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;


public class StudentGradeIDMapperImpl {

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapper studentIDMapper;

    public StudentGradeIDMapperImpl(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapper studentIDMapper) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapper = studentIDMapper;
    }

    public StudentGradeIDDataModel toDataModel (StudentGradeID studentGradeID) throws Exception{

        StudentID studentID = studentGradeID.get_studentID();
        CourseEditionID courseEditionID = studentGradeID.get_courseEdition();
        return new StudentGradeIDDataModel(studentIDMapper.domainToDataModel(studentID),courseEditionIDMapper.toDataModel(courseEditionID));
    }

    public StudentGradeID toDomain (StudentGradeIDDataModel studentGradeIDDataModel) throws Exception{

        CourseEditionIDDataModel courseEditionIDDataModel = studentGradeIDDataModel.get_courseEditionIDDataModel();
        StudentIDDataModel studentIDDataModel = studentGradeIDDataModel.get_studentIDDataModel();
        return new StudentGradeID(studentIDMapper.dataModelToDomain(studentIDDataModel),courseEditionIDMapper.toDomain(courseEditionIDDataModel));
    }
}
