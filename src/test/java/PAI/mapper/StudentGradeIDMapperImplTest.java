package PAI.mapper;

import PAI.VOs.*;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeIDMapperImplTest {

    @Test
    void shouldReturnStudentGradeIDDataModel() throws Exception {
        // arrange
        StudentIDMapperImpl studentIDMapperImpl = mock(StudentIDMapperImpl.class);
        CourseEditionIDMapperImpl courseEditionIDMapper = mock(CourseEditionIDMapperImpl.class);
        StudentGradeIDMapperImpl studentGradeIDMapper = new StudentGradeIDMapperImpl(courseEditionIDMapper, studentIDMapperImpl);

        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(studentGradeID.get_studentID()).thenReturn(studentID);
        when(studentGradeID.get_courseEdition()).thenReturn(courseEditionID);
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);

        // act
        StudentGradeIDDataModel result = studentGradeIDMapper.toDataModel(studentGradeID);

        // assert
        assertNotNull(result);
    }


    @Test
    void shouldReturnStudentGradeID() throws Exception {
       //arrange
        StudentIDMapperImpl studentIDMapperImpl = mock(StudentIDMapperImpl.class);
        CourseEditionIDMapperImpl courseEditionIDMapper = mock(CourseEditionIDMapperImpl.class);
        StudentGradeIDMapperImpl studentGradeIDMapper = new StudentGradeIDMapperImpl(courseEditionIDMapper, studentIDMapperImpl);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentID studentID = mock(StudentID.class);

        when(studentGradeIDDataModel.getCourseEditionIDDataModel()).thenReturn(courseEditionIDDataModel);
        when(studentGradeIDDataModel.getStudentIDDataModel()).thenReturn(studentIDDataModel);
        when(courseEditionIDMapper.toDomain(courseEditionIDDataModel)).thenReturn(courseEditionID);
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        // act
        StudentGradeID studentGradeID = studentGradeIDMapper.toDomain(studentGradeIDDataModel);
        // assert
        assertNotNull(studentGradeID);
    }
}

