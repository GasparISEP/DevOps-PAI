package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentIDDataModel;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentGradeMapperTest {

    @Test
    public void testToDataWithMocks() {
        // Arrange

        StudentGradeMapper mapper = new StudentGradeMapper();

        StudentID studentID = mock(StudentID.class);
        int uniqueNumber = 1000001;
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);


        when(studentGradeID.toString()).thenReturn("mocked-uuid");
        when(studentID.getUniqueNumber()).thenReturn(uniqueNumber);
        when(grade.knowGrade()).thenReturn(17.5);
        when(date.getLocalDate()).thenReturn(LocalDate.of(2024, 6, 1));

        StudentGrade studentGrade = mock(StudentGrade.class);
        when(studentGrade.identity()).thenReturn(studentGradeID);
        when(studentGrade.get_studentID()).thenReturn(studentID);
        when(studentGrade.get_studentID()).thenReturn(studentID);
        when(studentGrade.get_grade()).thenReturn(grade);
        when(studentGrade.get_date()).thenReturn(date);

        when(studentGrade.get_courseEditionID()).thenReturn(courseEditionID);

        // Act
        StudentGradeDM dataModel = mapper.toData(studentGrade);

        // Assert
        assertEquals(1000001, dataModel.getStudentId().getUniqueNumber());
        assertEquals(17.5, dataModel.get_grade(), 0.01);
        assertEquals(LocalDate.of(2024, 6, 1), dataModel.get_date());
        assertEquals(courseEditionID, dataModel.getCourseEditionID());
    }

    @Test
    public void testToDomain_withMockedDataModel() throws Exception {
        // Arrange
        StudentGradeMapper mapper = new StudentGradeMapper();

        StudentGradeDM dataModel = mock(StudentGradeDM.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);

        when(studentIDDataModel.getUniqueNumber()).thenReturn(1000001);
        when(dataModel.get_grade()).thenReturn(18.0);
        when(dataModel.get_date()).thenReturn(LocalDate.of(2025, 4, 14));
        when(dataModel.getStudentId()).thenReturn(studentIDDataModel);
        CourseEditionID fakeCourseEditionID = mock(CourseEditionID.class);
        when(dataModel.getCourseEditionID()).thenReturn(fakeCourseEditionID);

        // Act
        StudentGrade result = mapper.toDomain(dataModel);

        // Assert
        assertEquals(18.0, result.get_grade().knowGrade(), 0.01);
        assertEquals(LocalDate.of(2025, 4, 14), result.get_date().getLocalDate());
        assertEquals((1000001), result.get_studentID().getUniqueNumber());
        assertEquals(fakeCourseEditionID, result.get_courseEditionID());
    }

}