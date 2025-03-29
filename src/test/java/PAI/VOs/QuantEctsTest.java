package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantEctsTest {

    @Test
    void shouldCreateQuantEcts() throws Exception {

        // Act
        QuantEcts quantEcts1= new QuantEcts(30);
        // Assert
        assertNotNull(quantEcts1);
    }

    @Test
    void shouldReturnCorrectQuantEcts() throws Exception {
        // Arrange
        int expectedValue = 6;
        QuantEcts quantEcts1= new QuantEcts(expectedValue);

        // Act
        int actualQuantEcts1 = quantEcts1.getQuantEcts();

        // Assert
        assertEquals(expectedValue, actualQuantEcts1);
    }

    @Test
    void shouldNotCreateQuantEctsWithValueBelow0()  {

        // Act + Assert
        assertThrows(Exception.class, () -> new QuantEcts(0));
    }

    @Test
    void shouldNotCreateQuantEctsWithValueAbove30() {

        // Act + Assert
        assertThrows(Exception.class, () -> new QuantEcts(31));
    }
}