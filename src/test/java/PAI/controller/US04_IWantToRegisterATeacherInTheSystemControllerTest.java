package PAI.controller;
import PAI.domain.*;
import PAI.factory.AddressFactory;
import PAI.factory.TeacherCareerProgressionFactory;
import PAI.factory.TeacherCareerProgressionListFactory;
import PAI.repository.DepartmentRepository;
import PAI.repository.TeacherCategoryRepository;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class US04_IWantToRegisterATeacherInTheSystemControllerTest {

    @Test
    void shouldReturnExceptionIfTeacherRepositoryIsNull (){
        //arrange
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    null, teacherCategoryRepositoryDouble,departmentRepositoryDouble);
        });

        //assert
        assertEquals("TeacherRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfTeacherCategoryRepositoryIsNull (){
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepositoryDouble, null,departmentRepositoryDouble);
        });

        //assert
        assertEquals("TeacherCategoryRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfDepartmentRepositoryIsNull (){
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);

        //act
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new US04_IWantToRegisterATeacherInTheSystemController(
                    teacherRepositoryDouble, teacherCategoryRepositoryDouble,null);
        });

        //assert
        assertEquals("DepartmentRepository is null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueIfTeacherIsRegisteredWithSuccess () {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        TeacherCategory tc1Double = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dpt1Double = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);


        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.of(tc1Double));
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(true);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble, "15-04-2005", tc1Double, 70, dpt1Double, TCPfactoryDouble, tcpLFactoryDouble);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfInvalidDepartment () {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        TeacherCategory tc1Double = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dpt1Double = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);


        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.of(tc1Double));
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(false);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble, "15-04-2005", tc1Double, 70, dpt1Double, TCPfactoryDouble, tcpLFactoryDouble);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfInvalidTeacherCategory () {
        //arrange
        TeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        TeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepository.class);
        DepartmentRepository departmentRepositoryDouble = mock(DepartmentRepository.class);
        TeacherCategory tc1Double = mock(TeacherCategory.class);
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        Department dpt1Double = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCareerProgressionListFactory tcpLFactoryDouble = mock(TeacherCareerProgressionListFactory.class);

        US04_IWantToRegisterATeacherInTheSystemController controller = new US04_IWantToRegisterATeacherInTheSystemController(
                teacherRepositoryDouble, teacherCategoryRepositoryDouble, departmentRepositoryDouble);

        when(tc1Double.getName()).thenReturn("Professor");
        when(teacherCategoryRepositoryDouble.getTeacherCategoryByName("Professor")).thenReturn(Optional.empty());
        when(departmentRepositoryDouble.departmentExists(dpt1Double)).thenReturn(true);

        //act
        boolean result = controller.registerATeacherInTheSystem("ABC", "Jo", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble, "15-04-2005", tc1Double, 70, dpt1Double, TCPfactoryDouble, tcpLFactoryDouble);
        //assert
        assertFalse(result);
    }
}