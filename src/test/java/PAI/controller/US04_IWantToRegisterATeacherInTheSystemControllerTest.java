package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US04_IWantToRegisterATeacherInTheSystemControllerTest {

    @Test
    void shouldReturnExceptionIfTeacherRepositoryIsNull (){
        //arrange
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, teacherCategoryRepository,departmentRepository);
        });

        //assert
        assertEquals("TeacherRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfTeacherCategoryRepositoryIsNull (){
        //arrange
        TeacherRepository teacherRepository = new TeacherRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepository, null,departmentRepository);
        });

        //assert
        assertEquals("TeacherCategoryRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull (){
        //arrange
        TeacherRepository teacherRepository = new TeacherRepository();
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository();

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepository, teacherCategoryRepository,null);
        });

        //assert
        assertEquals("DepartmentRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfTeacherIsRegisteredWithSuccess () throws Exception {
        //arrange
        TeacherRepository teacherRepository = new TeacherRepository();
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();
        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepository, teacherCategoryRepository, departmentRepository);
        TeacherCategory tc1 = new TeacherCategory("Math");
        teacherCategoryRepository.registerTeacherCategory("Math");
        Department dpt1 = new Department("MAT", "Mathematics");
        departmentRepository.registerDepartment("MAT", "Mathematics");
        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfInvalidDepartment () throws Exception {
        //arrange
        TeacherRepository teacherRepository = new TeacherRepository();
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();
        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepository, teacherCategoryRepository, departmentRepository);
        TeacherCategory tc1 = new TeacherCategory("Math");
        teacherCategoryRepository.registerTeacherCategory("Math");
        Department dpt1 = new Department("MAT", "Mathematics");

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfInvalidTeacherCategory () throws Exception {
        //arrange
        TeacherRepository teacherRepository = new TeacherRepository();
        TeacherCategoryRepository teacherCategoryRepository = new TeacherCategoryRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();
        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepository, teacherCategoryRepository, departmentRepository);
        TeacherCategory tc1 = new TeacherCategory("Math");

        Department dpt1 = new Department("MAT", "Mathematics");
        departmentRepository.registerDepartment("MAT", "Mathematics");
        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        //assert
        assertFalse(result);
    }

}