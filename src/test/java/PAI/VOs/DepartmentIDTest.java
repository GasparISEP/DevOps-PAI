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
        assertEquals(acronym, departmentID.getAcronym());
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
        DepartmentAcronym acronym2 = new DepartmentAcronym("CSE");

        DepartmentID departmentID1 = new DepartmentID(acronym1);
        DepartmentID departmentID2 = new DepartmentID(acronym2);

        // Act & Assert
        assertEquals(departmentID1, departmentID2);
        assertEquals(departmentID1.hashCode(), departmentID2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenAcronymsAreDifferent() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        DepartmentAcronym acronym2 = new DepartmentAcronym("ECE");

        DepartmentID departmentID1 = new DepartmentID(acronym1);
        DepartmentID departmentID2 = new DepartmentID(acronym2);

        // Act & Assert
        assertNotEquals(departmentID1, departmentID2);
    }

    @Test
    void shouldNotBeEqualToNull() throws Exception {
        // Arrange
        DepartmentAcronym acronym = new DepartmentAcronym("CSE");
        DepartmentID departmentID = new DepartmentID(acronym);

        // Act & Assert
        assertNotEquals(departmentID, null);
    }

    @Test
    void shouldNotBeEqualToDifferentClass() throws Exception {
        // Arrange
        DepartmentAcronym acronym = new DepartmentAcronym("CSE");
        DepartmentID departmentID = new DepartmentID(acronym);

        // Act & Assert
        assertNotEquals(departmentID, "CSE");
    }

    @Test
    void shouldReturnCorrectAcronym() throws Exception {
        // Arrange
        DepartmentAcronym acronym = new DepartmentAcronym("CSE");
        DepartmentID departmentID = new DepartmentID(acronym);

        // Act
        DepartmentAcronym actualAcronym = departmentID.getAcronym();

        // Assert
        assertEquals(acronym, actualAcronym);
    }

    @Test
    void shouldHaveSameHashCodeForEqualObjects() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = new DepartmentAcronym("CSE");
        DepartmentAcronym acronym2 = new DepartmentAcronym("CSE");

        DepartmentID departmentID1 = new DepartmentID(acronym1);
        DepartmentID departmentID2 = new DepartmentID(acronym2);

        // Act & Assert
        assertEquals(departmentID1.hashCode(), departmentID2.hashCode());
    }
}