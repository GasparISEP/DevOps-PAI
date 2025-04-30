package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.StudentGrade;
import PAI.factory.IStudentGradeFactory;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.StudentGradeDM;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;


import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class StudentGradeMapperImplTest {

    @Test
    public void testToDataWithMocks() throws Exception {
        // Arrange
        CourseEditionIDMapperImpl courseEditionIDMapper = mock(CourseEditionIDMapperImpl.class);
        StudentIDMapperImpl studentIDMapperImpl = mock(StudentIDMapperImpl.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);

        StudentGradeMapperImpl mapper = new StudentGradeMapperImpl(courseEditionIDMapper, studentIDMapperImpl,studentGradeFactory);

        StudentID studentID = mock(StudentID.class);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);

        StudentIDDataModel studentIDDM = mock(StudentIDDataModel.class);
        when(studentIDDM.getUniqueNumber()).thenReturn(1000001);

        // Mocks
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDM);
        when(courseEditionIDMapper.toDataModel(any())).thenReturn(courseEditionIDDataModel);
        when(studentID.getUniqueNumber()).thenReturn(1000001);
        when(grade.knowGrade()).thenReturn(17.5);
        when(date.getLocalDate()).thenReturn(LocalDate.of(2024, 6, 1));

        StudentGrade studentGrade = mock(StudentGrade.class);
        when(studentGrade.identity()).thenReturn(studentGradeID);
        when(studentGrade.get_studentID()).thenReturn(studentID);
        when(studentGrade.get_courseEditionID()).thenReturn(mock(CourseEditionID.class));
        when(studentGrade.get_grade()).thenReturn(grade);
        when(studentGrade.get_date()).thenReturn(date);

        // Act
        StudentGradeDM dataModel = mapper.toData(studentGrade);
        StudentGradeIDDataModel idDM = dataModel.getId();

        // Assert
        assertEquals(1000001, dataModel.getStudentId().getUniqueNumber());
        assertEquals(studentIDDM,       idDM.getStudentIDDataModel());
        assertEquals(17.5, dataModel.getGrade(), 0.01);
        assertEquals(LocalDate.of(2024, 6, 1), dataModel.getDate());
        assertEquals(courseEditionIDDataModel, dataModel.getCourseEditionID());
        assertNotNull(idDM);

    }

    @Test
    public void testToDomain_withMockedDataModel() throws Exception {
        // Arrange
        CourseEditionIDMapperImpl courseEditionIDMapper = mock(CourseEditionIDMapperImpl.class);
        StudentIDMapperImpl studentIDMapperImpl = mock(StudentIDMapperImpl.class);
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        StudentGradeMapperImpl mapper = new StudentGradeMapperImpl(courseEditionIDMapper, studentIDMapperImpl, studentGradeFactory);

        StudentGradeDM dataModel = mock(StudentGradeDM.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        LocalDate dateLM = LocalDate.of(2025, 4, 14);
        when(dataModel.getGrade()).thenReturn(18.0);
        when(dataModel.getDate()).thenReturn(dateLM);
        when(dataModel.getStudentId()).thenReturn(studentIDDataModel);
        when(dataModel.getCourseEditionID()).thenReturn(courseEditionIDDataModel);

        StudentID studentID = mock(StudentID.class);
        when(studentIDMapperImpl.dataModelToDomain(studentIDDataModel)).thenReturn(studentID);

        CourseEditionID fakeCourseEditionID = mock(CourseEditionID.class);
        when(courseEditionIDMapper.toDomain(courseEditionIDDataModel)).thenReturn(fakeCourseEditionID);

        // Stub do factory para retornar o StudentGrade mockado
        Grade expectedGrade = new Grade(18.0);
        Date expectedDate = new Date(dateLM);
        StudentGrade expectedStudentGrade = mock(StudentGrade.class);
        when(studentGradeFactory.newGradeStudent(
                eq(expectedGrade),
                eq(expectedDate),
                eq(studentID),
                eq(fakeCourseEditionID))
        ).thenReturn(expectedStudentGrade);

        // Act
        StudentGrade result = mapper.toDomain(dataModel);

        // Assert
        assertSame(expectedStudentGrade, result);
        verify(studentGradeFactory).newGradeStudent(expectedGrade, expectedDate, studentID, fakeCourseEditionID);

    }

}