package PAI.utils;

import PAI.VOs.Country;
import PAI.VOs.NIF;
import PAI.VOs.TeacherAcronym;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherAlreadyExistsExceptionTest {

    @Test
    void testTeacherAlreadyExistsException () {
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("MSA");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);

        // Act
        TeacherAlreadyExistsException teacherException = new TeacherAlreadyExistsException(teacherAcronym, nif);

        // Assert
        assertNotNull(teacherException);
        assertEquals("Teacher already exists with the provided Acronym: MSA or NIF: 123456789", teacherException.getMessage());
    }
}