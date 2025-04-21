package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.mapper.courseInStudyPlanID.CourseInStudyPlanIDMapperImpl;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;

public class StudentGradeIDMapperImpl {

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapper studentIDMapper;

    public StudentGradeIDMapperImpl(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapper studentIDMapper) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapper = studentIDMapper;
    }



    public StudentGradeIDDataModel toDataModel (StudentGradeID studentGradeID) throws Exception{

        return new StudentGradeIDDataModel(studentIDMapper.domainToDataModel(studentGradeID.get_studentID()),courseEditionIDMapper.toDataModel(studentGradeID.get_courseEdition()));
    }

    public StudentGradeID toDomain (StudentGradeIDDataModel studentGradeIDDataModel) throws Exception{

        StudentIDMapper studentIDMapper = new StudentIDMapper();
        CourseEditionIDDataModel courseEditionIDDataModel = studentGradeIDDataModel.get_courseEditionIDDataModel();
        String name = courseEditionIDDataModel.getProgrammeEditionIDDataModel().getProgrammeName();
        String abbreviation = courseEditionIDDataModel.getProgrammeEditionIDDataModel().getProgrammeAcronym();
        ProgrammeID programmeID = new ProgrammeID(new NameWithNumbersAndSpecialChars(name),new Acronym(abbreviation));
        SchoolYearID schoolYearID = new SchoolYearID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID,schoolYearID);
        CourseInStudyPlanIDMapperImpl courseInStudyPlanIDMapper =new CourseInStudyPlanIDMapperImpl();
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = courseEditionIDDataModel.getCourseInStudyPlanIDDataModel();
        CourseInStudyPlanID courseInStudyPlanID = courseInStudyPlanIDMapper.toDomain(courseInStudyPlanIDDataModel);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        return new StudentGradeID(studentIDMapper.dataModelToDomain(studentGradeIDDataModel.get_studentIDDataModel()),courseEditionID);
    }


}
