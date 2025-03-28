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

    @Test
    void shouldNotCreateWithNameNull()  {

        // Act + Assert
        assertThrows(Exception.class, () -> new NameWithNumbersAndSpecialChars(null));
    }

    @Test
    void shouldNotCreateWithNameInBlank()  {

        // Act + Assert
        assertThrows(Exception.class, () -> new NameWithNumbersAndSpecialChars(" "));
    }


    @Test
    void shouldBeEqualWhenValuesAreTheSame() {
        NameWithNumbersAndSpecialChars obj1 = new NameWithNumbersAndSpecialChars("Diana123");
        NameWithNumbersAndSpecialChars obj2 = new NameWithNumbersAndSpecialChars("Diana123");

        assertEquals(obj1, obj2);
        assertTrue(obj1.equals(obj2));
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {
        NameWithNumbersAndSpecialChars obj1 = new NameWithNumbersAndSpecialChars("Diana123");
        NameWithNumbersAndSpecialChars obj2 = new NameWithNumbersAndSpecialChars("DianaG_123");

        assertNotEquals(obj1, obj2);
        assertFalse(obj1.equals(obj2));
    }

    @Test
    void shouldReturnNameWithNumber() {
        //arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Hi");

        //act
        String res = name.getnameWithNumbersAndSpecialChars();

        //assert
        assertEquals(res,name.getnameWithNumbersAndSpecialChars());
    }
}