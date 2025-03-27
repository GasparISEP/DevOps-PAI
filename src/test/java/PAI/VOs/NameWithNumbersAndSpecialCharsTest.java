package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameWithNumbersAndSpecialCharsTest {

    @Test
    void shouldCreateName() {

        // Act
        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("D1an@");
        // Assert
        assertNotNull(name1);
    }
}