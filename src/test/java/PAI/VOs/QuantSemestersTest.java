package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantSemestersTest {

    @Test
    void shouldCreateQuantSemesters() {
        //Arrange+Act
        QuantSemesters quantSemesters1 = new QuantSemesters(6);
        //Assert
        assertNotNull(quantSemesters1);
    }

}