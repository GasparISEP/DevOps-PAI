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

    @Test
    void shouldReturnDepartment_whenAllTheAtributesAreValid_withDirector () throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "Department1";
        Address address1 = new Address("Passeio Alegre", "4432-345", "Porto","Portugal");
        TeacherCategory teacherCategory1= new TeacherCategory ("categoria1");
        Department department1 = new Department ("DEI", "Dept1");
        Teacher teacherDirector1 = new Teacher ("AMM","Arlindo Maia" ,"AMM@isep.ipp.pt","213456789","B123",address1,teacherCategory1,department1);
        //act
        Department department = new Department(acronym, name,  teacherDirector1);
        //assert
        assertNotNull (department);
    }

}