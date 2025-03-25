package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantEctsTest {

    @Test
    void shouldCreateQuantEcts() {

        // Act
        QuantEcts quantEcts1= new QuantEcts(18);
        // Assert
        assertNotNull(quantEcts1);
    }

}