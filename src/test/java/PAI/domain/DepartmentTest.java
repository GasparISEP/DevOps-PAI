package PAI.domain;
import PAI.VOs.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentTest {

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid () throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        //act
        Department department = new Department(acronym, name);
        //assert
        assertNotNull(department);
    }

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withDirector () throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        TeacherID teacherDirectorDouble = mock(TeacherID.class);
        //act
        Department department = new Department(acronym, name, teacherDirectorDouble);
        //assert
        assertNotNull(department);
    }

    @Test
    void shouldReturnException_whenAcronymIsNull () {
        // Arrange
        DepartmentAcronym acronym=null;
        Name name = mock(Name.class);
        TeacherID director = mock(TeacherID.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Department(acronym, name, director)
        );
        assertTrue(exception.getMessage().contains("Acronym and name cannot be null."));
    }

    @Test
    void shouldReturnException_whenNameIsNull () {
        // Arrange
        Name name= null;
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        TeacherID director = mock(TeacherID.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Department(acronym, name, director)
        );
        assertTrue(exception.getMessage().contains("Acronym and name cannot be null."));
    }

    @Test
    void shouldReturnException_whenDirectorIsNull () {
        // Arrange
        Name name= mock(Name.class);
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        // Act & Assert
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Department(acronym, name, null)
        );
        assertTrue(thrown.getMessage().contains("Teacher Director cannot be null."));
    }

    @Test
    void shouldThrowException_whenAcronymAndNameAreNull() {
        // Arrange
        DepartmentAcronym acronym = null;
        Name name = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Department(acronym, name)
        );
        assertTrue(exception.getMessage().contains("Acronym and Name cannot be null."));
    }

    @Test
    void shouldReturnCorrectDepartmentID() throws Exception {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        DepartmentID expectedId = mock(DepartmentID.class);
        Department department = spy(new Department(acronym, name));
        doReturn(expectedId).when(department).identity();
        // Act
        DepartmentID actualId = department.identity();
        // Assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void testDepartmentsWithSameAcronymHaveSameDepartmentID() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name1 = mock(Name.class);
        Name name2 = mock(Name.class);
        Department department1 = new Department(acronym, name1);
        Department department2 = new Department(acronym, name2);
        //act & assert
        assertEquals(department1.identity(), department2.identity());
    }

    @Test
    void testDepartmentsWithDifferentAcronymsHaveDifferentDepartmentIDs() throws Exception {
        DepartmentAcronym acronym1 = mock(DepartmentAcronym.class);
        DepartmentAcronym acronym2 = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        Department department1 = new Department(acronym1, name);
        Department department2 = new Department(acronym2, name);

        assertNotEquals(department1.identity(), department2.identity());
    }

    //Testing the equals method
    @Test
    void testShouldReturnTrueForEqualDepartments()throws Exception {
        // Arrange
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department1 = new Department(acronym,name);
        Department department2 = new Department(acronym,name);
        // Act & Assert
        assertEquals(department1, department2);
    }

    @Test
    void testShouldReturnFalseForDifferentDepartments()throws Exception {
        // Arrange
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        DepartmentAcronym acronym1= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department1 = new Department(acronym,name);
        Department department2 = new Department(acronym1,name);
        // Act & Assert
        assertNotEquals(department1, department2);
    }

    @Test
    void testShouldReturnFalseWhenNullDepartment() throws Exception{
        // Arrange
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department1 = new Department(acronym, name);
        // Act & Assert
        assertNotEquals(null, department1);
    }

    @Test
    void shouldReturnFalseWhenComparedWithDifferentClassObject() throws Exception {
        // Arrange
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department1 = new Department(acronym,name);

        // Act & Assert
        assertNotEquals("DEI", department1);
    }

    @Test
    void shouldReturnTrueWhenSameObjectIsCompared() throws Exception {
        // arrange
        DepartmentAcronym acronym= mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department = new Department(acronym,name);
        // act & assert
        assertEquals(department, department);
    }

    @Test
    void shouldReturnSameHashCodeForEqualObjects() throws Exception {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department = new Department(acronym,name);
        Department department1 = new Department(acronym,name);
        // Act & Assert
        assertEquals(department.hashCode(), department1.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodeForDiferrentObjects() throws Exception {
        // Arrange
        DepartmentAcronym acronym1 = mock(DepartmentAcronym.class);
        Name name1= mock(Name.class);
        DepartmentAcronym acronym2= mock(DepartmentAcronym.class);
        Name name2= mock(Name.class);
        Department department = new Department(acronym1,name1);
        Department department1 = new Department(acronym2,name2);
        // Act & Assert
        assertNotEquals(department.hashCode(), department1.hashCode());
    }

    @Test
    void shouldReturnHashCodeForOneDepartment() throws Exception {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department= new Department(acronym,name);
        // Act
        int result= department.hashCode();
        // Assert
        assertNotNull(result);
    }

    @Test
    void testSameAsWithSameObject() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department = new Department(acronym,name);
        //act & assert
        assertTrue(department.sameAs(department));
    }

    @Test
    void testSameAsWithNull() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department = new Department(acronym,name);
        //act & assert
        assertFalse(department.sameAs(null));
    }

    @Test
    void testSameAsWithTwoEqualObjects() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department1 = new Department(acronym,name);
        Department department2 = new Department(acronym,name);
        //act & assert
        assertTrue(department1.sameAs(department2));
    }

    @Test
    void testSameAsWithDifferentClass() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department = new Department(acronym,name);
        //act & assert
        assertFalse(department.sameAs("DEI"));
    }

    @Test
    void testEqualsWithDifferentClassObject() throws Exception {
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Department department = new Department(acronym, name);
        String differentClassObject = "String object";
        assertFalse(department.equals(differentClassObject));
    }

    @Test
    void testSameAsWithDifferentDepartmentId() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        DepartmentAcronym acronym1 = mock(DepartmentAcronym.class);
        Department department1 = new Department(acronym,name);
        Department department2= new Department(acronym1,name);
        //act & assert
        assertFalse(department1.sameAs(department2));
    }

    @Test
    void testSameAsWithDifferentDepartmentName() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name= mock(Name.class);
        Name name2= mock(Name.class);
        Department department1 = new Department(acronym,name);
        Department department2= new Department(acronym,name2);
        //act & assert
        assertFalse(department1.sameAs(department2));
    }

    @Test
    void shouldReturnDepartmentName() throws Exception {
        // arrange
        Name name = mock(Name.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Department department = new Department(acronym,name);
        // act
        Name actualName = department.getName();
        // assert
        assertEquals(name, actualName);
    }

    @Test
    void shouldReturnDepartmentAcronym() throws Exception {
        // arrange
        Name name = mock(Name.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Department department = new Department(acronym,name);
        // act
        DepartmentAcronym actualAcronym = department.getAcronym();
        // assert
        assertEquals(acronym, actualAcronym);
    }
    @Test
    void shouldReturnDepartmentID() throws Exception {
        // arrange
        Name name = mock(Name.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        DepartmentID departmentID = new DepartmentID(acronym);
        Department department = new Department(acronym,name);
        // act
        DepartmentID actualID = department.getDepartmentID();
        // assert
        assertEquals(departmentID, actualID);
    }

    //US06
    @Test
    void shouldReturnTrueWhenTeacherIsNotNull() throws Exception{
        //arrange
        TeacherID teacherIDDouble = mock(TeacherID.class);
        DepartmentAcronym departmentAcronym = mock (DepartmentAcronym.class);
        Name name = mock(Name.class);
        Department department= new Department(departmentAcronym,name);
        //act
        boolean result = department.changeDirector(teacherIDDouble);
        //assert
        assertTrue (result);
    }

    @Test
    void shouldReturnFalseWhenTeacherIsNull() throws Exception{
        //arrange
        DepartmentAcronym departmentAcronym = mock (DepartmentAcronym.class);
        Name name = mock(Name.class);
        Department department= new Department(departmentAcronym,name);
        //act
        boolean result = department.changeDirector(null);
        //assert
        assertFalse (result);
    }
}