package PAI.mapper;

import PAI.VOs.TeacherAcademicEmail;
import PAI.VOs.TeacherAcronym;
import PAI.persistence.datamodel.TeacherAcademicEmailDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherAcademicEmailMapperTest {

    @Test
    void shouldReturnNullForNullTeacherAcademicEmail() {
        //Arrange
        TeacherAcademicEmailMapper mapper = new TeacherAcademicEmailMapper();

        //Act
        TeacherAcademicEmailDataModel result = mapper.toDataModel(null);

        //Assert
        assertNull(result);
    }

    @Test
    void toDataModelShouldReturnTeacherAcademicEmailDataModel() {
        //Arrange
        TeacherAcademicEmail teacherAcademicEmail = mock(TeacherAcademicEmail.class);

        when(teacherAcademicEmail.getEmailDomain()).thenReturn("isep.ipp.pt");
        when(teacherAcademicEmail.getTeacherAcademicEmail()).thenReturn("abc@isep.ipp.pt");

        //Act
        TeacherAcademicEmailDataModel result = new TeacherAcademicEmailMapper().toDataModel(teacherAcademicEmail);

        //Assert
        assertEquals("isep.ipp.pt", result.getEmailDomain());
        assertEquals("abc@isep.ipp.pt", result.getTeacherAcademicEmail());
    }

    @Test
    void shouldReturnNullForNullTeacherAcademicEmailDataModel() {
        //Arrange
        TeacherAcademicEmailMapper mapper = new TeacherAcademicEmailMapper();

        //Act
        TeacherAcademicEmail result = mapper.toDomain(null);

        //Assert
        assertNull(result);
    }

    @Test
    void shouldReturnTeacherAcademicEmail() {
        //Arrange
        TeacherAcademicEmailMapper mapper = new TeacherAcademicEmailMapper();
        TeacherAcademicEmailDataModel teacherAcademicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);

        when(teacherAcademicEmailDataModelDouble.getEmailDomain()).thenReturn("isep.ipp.pt");
        when(teacherAcademicEmailDataModelDouble.getTeacherAcademicEmail()).thenReturn("ABC@isep.ipp.pt");

        TeacherAcronym teacherAcronymDouble = mock(TeacherAcronym.class);
        when(teacherAcronymDouble.getAcronym()).thenReturn("ABC");

        //Act
        TeacherAcademicEmail result = mapper.toDomain(teacherAcademicEmailDataModelDouble);

        //Assert
        assertEquals("isep.ipp.pt", result.getEmailDomain());
        assertEquals("ABC@isep.ipp.pt", result.getTeacherAcademicEmail());
    }

    @Test
    void shouldReturnExceptionForNullAcademicEmail() throws IllegalArgumentException {
        //Arrange
        TeacherAcademicEmailMapper mapper = new TeacherAcademicEmailMapper();
        TeacherAcademicEmailDataModel wrongEmail = mock(TeacherAcademicEmailDataModel.class);

        when(wrongEmail.getTeacherAcademicEmail()).thenReturn(null);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            mapper.toDomain(wrongEmail);
        });
    }
    @Test
    void shouldReturnExceptionForInvalidEmailFormat() throws IllegalArgumentException {
        //Arrange
        TeacherAcademicEmailMapper mapper = new TeacherAcademicEmailMapper();
        TeacherAcademicEmailDataModel wrongEmail = mock(TeacherAcademicEmailDataModel.class);

        when(wrongEmail.getTeacherAcademicEmail()).thenReturn("no-at-sign-here");

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            mapper.toDomain(wrongEmail);
        });
    }
}