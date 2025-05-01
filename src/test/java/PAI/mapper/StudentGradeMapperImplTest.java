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
        IStudentGradeIDMapper studentGradeIDMapper = mock(StudentGradeIDMapperImpl.class);

        StudentGradeMapperImpl mapper = new StudentGradeMapperImpl(courseEditionIDMapper, studentIDMapperImpl,studentGradeFactory,studentGradeIDMapper);

        StudentID studentID = mock(StudentID.class);
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);

        StudentIDDataModel studentIDDM = mock(StudentIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        when(studentIDDM.getUniqueNumber()).thenReturn(1000001);

        // Mocks
        when(studentIDMapperImpl.domainToDataModel(studentID)).thenReturn(studentIDDM);
        when(courseEditionIDMapper.toDataModel(any())).thenReturn(courseEditionIDDataModel);
        when(studentID.getUniqueNumber()).thenReturn(1000001);
        when(grade.knowGrade()).thenReturn(17.5);
        when(date.getLocalDate()).thenReturn(LocalDate.of(2024, 6, 1));
        when(studentGradeIDMapper.toDataModel(studentGradeID)).thenReturn(studentGradeIDDataModel);
        when(studentGradeIDDataModel.getStudentIDDataModel()).thenReturn(studentIDDM);
        when(studentGradeIDDataModel.getCourseEditionIDDataModel()).thenReturn(courseEditionIDDataModel);

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
        IStudentGradeIDMapper studentGradeIDMapper = mock(StudentGradeIDMapperImpl.class);

        StudentGradeMapperImpl mapper = new StudentGradeMapperImpl(courseEditionIDMapper, studentIDMapperImpl,studentGradeFactory,studentGradeIDMapper);

        StudentGradeDM dataModel = mock(StudentGradeDM.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = mock(StudentGradeIDDataModel.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID fakeCourseEditionID = mock(CourseEditionID.class);

        LocalDate dateLM = LocalDate.of(2025, 4, 14);
        when(dataModel.getGrade()).thenReturn(18.0);
        when(dataModel.getDate()).thenReturn(dateLM);
        when(dataModel.getStudentId()).thenReturn(studentIDDataModel);
        when(dataModel.getCourseEditionID()).thenReturn(courseEditionIDDataModel);
        when(dataModel.getId()).thenReturn(studentGradeIDDataModel);
        when(studentGradeIDMapper.toDomain(studentGradeIDDataModel)).thenReturn(studentGradeID);
        when(studentGradeID.get_studentID()).thenReturn(studentID);
        when(studentGradeID.get_courseEdition()).thenReturn(fakeCourseEditionID);
        when(studentIDMapperImpl.dataModelToDomain(studentIDDataModel)).thenReturn(studentID);


        when(courseEditionIDMapper.toDomain(courseEditionIDDataModel)).thenReturn(fakeCourseEditionID);

        // Stub do factory para retornar o StudentGrade mockado
        Grade expectedGrade = new Grade(18.0);
        Date expectedDate = new Date(dateLM);
        StudentGrade expectedStudentGrade = mock(StudentGrade.class);
        when(studentGradeFactory.newGradeStudentFromDataModel(
                eq(expectedGrade),
                eq(expectedDate),
                eq(studentID),
                eq(fakeCourseEditionID),
                eq(studentGradeID))
        ).thenReturn(expectedStudentGrade);

        // Act
        StudentGrade result = mapper.toDomain(dataModel);

        // Assert
        assertSame(expectedStudentGrade, result);
    }

}