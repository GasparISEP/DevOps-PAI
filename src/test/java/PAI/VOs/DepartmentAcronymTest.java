package PAI.VOs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentAcronymTest {

    @Test
    public void testValidAcronymWith3Letters() throws Exception {
       //arrange
        String acronym= "DEG";
        //act
        DepartmentAcronym acronym1 = new DepartmentAcronym(acronym);
        //assert
        assertNotNull(acronym1);
    }

    @Test
    public void testValidAcronymWithMoreThan3Letters() throws Exception {
        //arrange
        String acronym= "DEGI";
        //act
        DepartmentAcronym acronym1 = new DepartmentAcronym(acronym);
        //assert
        assertNotNull(acronym1);
    }

    @Test
    public void testInvalidAcronymWithOnlyTwoLetters() {
        //arrange
        String acronym= "DE";
        //act & assert
        Exception exception = assertThrows(Exception.class, () -> {
            new DepartmentAcronym(acronym);
        });
        assertEquals("Acronym must contain at least 3 characters.", exception.getMessage());
    }

    @Test
    public void testInvalidEmptyAcronym() {
        //arrange
        String acronym= "";
        //act & assert
        Exception exception = assertThrows(Exception.class, () -> {
            new DepartmentAcronym(acronym);
        });
        assertEquals("Acronym must be a non-empty string.", exception.getMessage());
    }

    @Test
    public void testInvalidNullAcronym() {
        //arrange
        String acronym= null;
        //act & assert
        Exception exception = assertThrows(Exception.class, () -> {
            new DepartmentAcronym(acronym);
        });
        assertEquals("Acronym must be a non-empty string.", exception.getMessage());
    }

    @Test
    public void testInvalidAcronymWithNumbers() {
        //arrange
        String acronym= "DEI1";
        //act & assert
        Exception exception = assertThrows(Exception.class, () -> {
            new DepartmentAcronym(acronym);
        });
        assertEquals("Acronym must contain only capital letters.", exception.getMessage());
    }

    @Test
    public void testInvalidAcronymWithLowercase() {
        //arrange
        String acronym= "Dei";
        //act & assert
        Exception exception = assertThrows(Exception.class, () -> {
            new DepartmentAcronym(acronym);
        });
        assertEquals("Acronym must contain only capital letters.", exception.getMessage());
    }

    @Test
    public void testInvalidAcronymWithSpecialCharacters() {
        //arrange
        String acronym= "CS@";
        //act & assert
        Exception exception = assertThrows(Exception.class, () -> {
            new DepartmentAcronym(acronym);
        });
        assertEquals("Acronym must contain only capital letters.", exception.getMessage());
    }

    @Test
    void shouldReturnDepartmentAcronym() throws Exception {
        // arrange
        String acronym = "DEI";
        DepartmentAcronym acronym1 = new DepartmentAcronym(acronym);
        // act
        String actualAcronym = acronym1.getAcronym();
        // assert
        assertEquals(acronym, actualAcronym);
    }

    @Test
    void shouldReturnSameHashCodeForSameAcronym() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        DepartmentAcronym acronym2 = new DepartmentAcronym("CSE");
        // Act & Assert
        assertEquals(acronym1.hashCode(), acronym2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodeForDiferrentAcronym() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        DepartmentAcronym acronym2 = new DepartmentAcronym("DSE");
        // Act & Assert
        assertNotEquals(acronym1.hashCode(), acronym2.hashCode());
    }

    @Test
    void shouldReturnHashCodeForOneAcronym() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        // Act
        int result= acronym1.hashCode();
        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldBeEqualForSameAcronym() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        DepartmentAcronym acronym2 = new DepartmentAcronym("CSE");
        // Act & Assert
        assertEquals(acronym1, acronym2);
    }

    @Test
    void shouldNotBeEqualForDifferentAcronyms() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        DepartmentAcronym acronym2 = new DepartmentAcronym("ECE");
        // Act & Assert
        assertNotEquals(acronym1, acronym2);
    }

    @Test
    void shouldNotBeEqualToNull() throws Exception {
        // Arrange
        DepartmentAcronym acronym = new DepartmentAcronym("CSE");
        // Act & Assert
        assertNotEquals(acronym, null);
    }

    @Test
    void shouldNotBeEqualToDifferentClass() throws Exception {
        // Arrange
        DepartmentAcronym acronym = new DepartmentAcronym("CSE");
        // Act & Assert
        assertNotEquals(acronym, "CSE");
    }

    @Test
    public void testEqualsWithSameInstance() throws Exception {
        // Arrange
        DepartmentAcronym acronym = new DepartmentAcronym("CSE");
        // Act & Assert
        assertEquals(acronym, acronym);
    }
}