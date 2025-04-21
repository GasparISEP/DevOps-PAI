package PAI.mapper;

import PAI.VOs.*;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.CourseIDDataModel;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeIDMapperImplTest {

    @Test
    void shouldReturnStudentGradeIDDataModel() throws Exception {
        // arrange
        StudentIDMapper studentIDMapper = mock(StudentIDMapper.class);
        CourseEditionIDMapperImpl courseEditionIDMapper = mock(CourseEditionIDMapperImpl.class);
        StudentGradeIDMapperImpl studentGradeIDMapper = new StudentGradeIDMapperImpl(courseEditionIDMapper, studentIDMapper);

        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(studentGradeID.get_studentID()).thenReturn(studentID);
        when(studentGradeID.get_courseEdition()).thenReturn(courseEditionID);
        when(studentIDMapper.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);

        // act
        StudentGradeIDDataModel result = studentGradeIDMapper.toDataModel(studentGradeID);

        // assert
        assertNotNull(result);
    }


    @Test
    void shouldReturnStudentGradeID() throws Exception {
       //arrange
        StudentIDMapper studentIDMapper = mock(StudentIDMapper.class);
        CourseEditionIDMapperImpl courseEditionIDMapper = mock(CourseEditionIDMapperImpl.class);
        StudentGradeIDMapperImpl studentGradeIDMapper = new StudentGradeIDMapperImpl(courseEditionIDMapper, studentIDMapper);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        StudyPlanIDDataModel studyPlanIDDataModel = mock(StudyPlanIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        when(courseInStudyPlanIDDataModel.getStudyPlanIDDataModel()).thenReturn(studyPlanIDDataModel);
        when(studyPlanIDDataModel.getProgrammeID()).thenReturn(programmeIDDataModel);
        when(programmeIDDataModel.getAcronym()).thenReturn("ABC");
        when(programmeIDDataModel.getName()).thenReturn("Abc");
        when(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel()).thenReturn(courseInStudyPlanIDDataModel);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        when(courseInStudyPlanIDDataModel.getCourseID()).thenReturn(courseIDDataModel);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        when(studentGradeIDDataModel.get_courseEditionIDDataModel()).thenReturn(courseEditionIDDataModel);
        when(studentGradeIDDataModel.get_studentIDDataModel()).thenReturn(studentIDDataModel);
        String name = "Abc";
        String acronym = "ABC";
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(courseEditionIDDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
        when(programmeEditionIdDataModel.getProgrammeName()).thenReturn(name);
        when(programmeEditionIdDataModel.getProgrammeAcronym()).thenReturn(acronym);
        when(courseIDDataModel.getName()).thenReturn("Abc");
        when(courseIDDataModel.getAcronym()).thenReturn("ABC");
        LocalDate StartDate = LocalDate.of(2020, 9, 1);
        when(studyPlanIDDataModel.getImplementationDate()).thenReturn(StartDate);
        when(studentIDDataModel.getUniqueNumber()).thenReturn(1234567);
        // act
        StudentGradeID studentGradeID = studentGradeIDMapper.toDomain(studentGradeIDDataModel);
        // assert
        assertNotNull(studentGradeID);
    }
}

