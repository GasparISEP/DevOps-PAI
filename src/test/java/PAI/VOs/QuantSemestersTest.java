package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantSemestersTest {

    @Test
    void shouldCreateQuantSemesters() throws Exception {
        //Arrange+Act
        QuantSemesters quantSemesters1 = new QuantSemesters(6);
        //Assert
        assertNotNull(quantSemesters1);
    }

    @Test
    void shouldReturnCorrectQuantityOfSemesters() throws Exception {
        //Arrange
        int quantityOfSemesters = 6;
        QuantSemesters quantSemesters1 = new QuantSemesters(quantityOfSemesters);

        //Act
        int actualQuantSemesters1 = quantSemesters1.getQuantityOfSemesters();

        //Assert
        assertEquals(quantityOfSemesters, actualQuantSemesters1);
    }

    @Test
    void shouldNotCreateQuantSemestersIfValueIsZero() throws Exception {
        //Act+Assert
        assertThrows(Exception.class, () -> new QuantSemesters(0));
    }

    @Test
    void shouldNotCreateQuantSemestersIfValueIsNegative() throws Exception {
        //Act+Assert
        assertThrows(Exception.class, () -> new QuantSemesters(-1));
    }

}