package PAI.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DepartmentFactoryTest {

    @Test
    void shouldCreateAValidDepartment()throws Exception{
        //arrange
        DepartmentFactory factory = new DepartmentFactory();
        String acronym= "DEI";
        String name= "Departamento Engenharia Informática";

        //act & assert
        Department department = factory.newDepartment(acronym,name);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull(){
        //arrange
        DepartmentFactory factory = new DepartmentFactory();
        String name = "Departamento Engenharia Informática";

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.newDepartment(null, name);
        });
        assertEquals("Department´s acronym must be a 3 letter non-empty String.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull(){
        //arrange
        DepartmentFactory factory = new DepartmentFactory();
        String acronym= "DEI";

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.newDepartment(acronym,null);
        });
        assertEquals("Department´s name must be a non-empty String.", exception.getMessage());
    }
}
