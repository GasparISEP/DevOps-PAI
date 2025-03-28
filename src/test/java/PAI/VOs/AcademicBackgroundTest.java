package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcademicBackgroundTest {

    @Test
    void shouldCreateAcademicBackground() throws Exception {
        //Arrange
        AcademicBackground ab1 = new AcademicBackground("Dr. João Silva possui Bacharelado em Física pela Universidade de Coimbra, Mestrado em Física Teórica");

        //Act + Assert
        assertNotNull(ab1);
    }

    @Test
    void shouldFailIfAcademicBackgroundIsNull() {

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new AcademicBackground(null));
    }

    @Test
    void shouldFailIfAcademicBackgroundIsEmpty() {
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new AcademicBackground(""));
    }

    @Test
    void shouldGetAcademicBackground() {
        //Arrange
        AcademicBackground ab1 = new AcademicBackground("Dr. João Silva possui Bacharelado em Física pela Universidade de Coimbra, Mestrado em Física Teórica");

        //Act
        String result = ab1.getAcademicBackground();

        //Assert
        assertEquals("Dr. João Silva possui Bacharelado em Física pela Universidade de Coimbra, Mestrado em Física Teórica", result);
    }
}