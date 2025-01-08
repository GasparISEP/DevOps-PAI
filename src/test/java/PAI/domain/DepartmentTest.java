package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "Department1";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto","Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        //act
        Department department = new Department(acronym, name);
        //assert
        assertNotNull (department);
    }

}