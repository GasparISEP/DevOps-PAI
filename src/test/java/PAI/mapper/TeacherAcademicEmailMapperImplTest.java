package PAI.mapper;

import PAI.VOs.TeacherAcademicEmail;
import PAI.VOs.TeacherAcronym;
import PAI.persistence.datamodel.TeacherAcademicEmailDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherAcademicEmailMapperImplTest {

    @Test
    void shouldReturnNullForNullTeacherAcademicEmail() {
        //Arrange
        TeacherAcademicEmailMapperImpl mapper = new TeacherAcademicEmailMapperImpl();

        //Act
        TeacherAcademicEmailDataModel result = mapper.toDataModel(null);

        //Assert
        assertNull(result);
    }

    @Test
    void toDataModelShouldReturnTeacherAcademicEmailDataModel() {
        //Arrange
        TeacherAcademicEmail teacherAcademicEmail = mock(TeacherAcademicEmail.class);


        when(teacherAcademicEmail.getTeacherAcademicEmail()).thenReturn("abc@isep.ipp.pt");

        //Act
        TeacherAcademicEmailDataModel result = new TeacherAcademicEmailMapperImpl().toDataModel(teacherAcademicEmail);

        //Assert
        assertEquals("abc@isep.ipp.pt", result.getTeacherAcademicEmail());
    }

    @Test
    void shouldReturnNullForNullTeacherAcademicEmailDataModel() {
        //Arrange
        TeacherAcademicEmailMapperImpl mapper = new TeacherAcademicEmailMapperImpl();

        //Act
        TeacherAcademicEmail result = mapper.toDomain(null);

        //Assert
        assertNull(result);
    }

    @Test
    void shouldReturnTeacherAcademicEmail() {
        //Arrange
        TeacherAcademicEmailMapperImpl mapper = new TeacherAcademicEmailMapperImpl();
        TeacherAcademicEmailDataModel teacherAcademicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);

        when(teacherAcademicEmailDataModelDouble.getTeacherAcademicEmail()).thenReturn("ABC@isep.ipp.pt");

        TeacherAcronym teacherAcronymDouble = mock(TeacherAcronym.class);
        when(teacherAcronymDouble.getAcronym()).thenReturn("ABC");

        //Act
        TeacherAcademicEmail result = mapper.toDomain(teacherAcademicEmailDataModelDouble);

        //Assert
        assertEquals("ABC@isep.ipp.pt", result.getTeacherAcademicEmail());
    }

    @Test
    void shouldReturnExceptionForNullAcademicEmail() throws IllegalArgumentException {
        //Arrange
        TeacherAcademicEmailMapperImpl mapper = new TeacherAcademicEmailMapperImpl();
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
        TeacherAcademicEmailMapperImpl mapper = new TeacherAcademicEmailMapperImpl();
        TeacherAcademicEmailDataModel wrongEmail = mock(TeacherAcademicEmailDataModel.class);

        when(wrongEmail.getTeacherAcademicEmail()).thenReturn("no-at-sign-here");

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            mapper.toDomain(wrongEmail);
        });
    }
}