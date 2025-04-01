package PAI.VOs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DepartmentIDTest {

    @Test
    void shouldCreateDepartmentIDSuccessfully() {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        // Act
        DepartmentID departmentID = new DepartmentID(acronym);
        // Assert
        assertNotNull(departmentID);
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull() {
        // Arrange
        DepartmentAcronym acronym = null;
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DepartmentID(acronym));
        assertEquals("Department ID cannot be null.", exception.getMessage());
    }

    @Test
    void shouldBeEqualWhenAcronymsAreTheSame() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        //Act
        DepartmentID departmentID1 = new DepartmentID(acronym1);
        DepartmentID departmentID2 = new DepartmentID(acronym1);
        //Assert
        assertEquals(departmentID1, departmentID2);
    }


    @Test
    void shouldNotBeEqualWhenAcronymsAreDifferent() {
        // Arrange
        DepartmentAcronym acronym1 = mock(DepartmentAcronym.class);
        DepartmentAcronym acronym2 = mock(DepartmentAcronym.class);
        DepartmentID departmentID1 = new DepartmentID(acronym1);
        DepartmentID departmentID2 = new DepartmentID(acronym2);
        // Act & Assert
        assertNotEquals(departmentID1, departmentID2);
    }

    @Test
    void shouldNotBeEqualToNull() {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        DepartmentID departmentID = new DepartmentID(acronym);
        // Act & Assert
        assertNotEquals(departmentID, null);
    }

    @Test
    void shouldNotBeEqualToDifferentClass(){
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        DepartmentID departmentID = new DepartmentID(acronym);
        // Act & Assert
        assertNotEquals(departmentID, "CSE");
    }

    @Test
    void shouldBeEqualToItself(){
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        DepartmentID departmentID = new DepartmentID(acronym);
        // Act & Assert
        assertEquals(departmentID, departmentID);
    }

    @Test
    void shouldReturnCorrectAcronym() {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        DepartmentID departmentID = new DepartmentID(acronym);
        // Act
        DepartmentAcronym actualAcronym = departmentID.getAcronym();
        // Assert
        assertEquals(acronym, actualAcronym);
    }

    @Test
    void shouldReturnSameHashCodeForEqualObjects() {
        // Arrange
        DepartmentAcronym acronym1 = mock(DepartmentAcronym.class);
        DepartmentID departmentID1 = new DepartmentID(acronym1);
        DepartmentID departmentID2 = new DepartmentID(acronym1);
        // Act & Assert
        assertEquals(departmentID1.hashCode(), departmentID2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodeForDiferrentObjects() {
        // Arrange
        DepartmentAcronym acronym1 = mock(DepartmentAcronym.class);
        DepartmentAcronym acronym2 = mock(DepartmentAcronym.class);
        DepartmentID departmentID1 = new DepartmentID(acronym1);
        DepartmentID departmentID2 = new DepartmentID(acronym2);
        // Act & Assert
        assertNotEquals(departmentID1.hashCode(), departmentID2.hashCode());
    }

    @Test
    void shouldReturnHashCodeForOneAcronym() {
        // Arrange
        DepartmentAcronym acronym1 = mock(DepartmentAcronym.class);
        DepartmentID departmentID1 = new DepartmentID(acronym1);
        // Act
        int result= departmentID1.hashCode();
        // Assert
        assertNotNull(result);
    }
}