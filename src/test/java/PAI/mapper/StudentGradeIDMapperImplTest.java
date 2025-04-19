package PAI.mapper;

import PAI.VOs.StudentGradeID;
import PAI.VOs.StudentID;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentGradeIDMapperImplTest {
    @Test
    void shouldReturnStudentGradeIDDataModel(){

        //arrange
        StudentGradeIDMapperImpl studentGradeIDMapper = new StudentGradeIDMapperImpl();
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentID studentID = mock(StudentID.class);
        when(studentGradeID.get_studentID()).thenReturn(studentID);
        //act
        StudentGradeIDDataModel result = studentGradeIDMapper.toDataModel(studentGradeID);
        //assert
        assertNotNull(result);
    }




}