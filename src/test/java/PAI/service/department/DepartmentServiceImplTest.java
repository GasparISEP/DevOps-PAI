package PAI.service.department;
import PAI.factory.IDepartmentFactory;
import PAI.repository.IDepartmentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DepartmentServiceImplTest {

    @Test
    void shouldReturnValidDepartmentServiceWhenValidAttributesAreProvided(){
        //arrange
        IDepartmentFactory departmentFactory= mock(IDepartmentFactory.class);
        IDepartmentRepository departmentRepo= mock(IDepartmentRepository.class);
        //act
        DepartmentServiceImpl departmentService= new DepartmentServiceImpl(departmentFactory,departmentRepo);
        //assert
        assertNotNull(departmentService);
    }

    @Test
    void shouldReturnExceptionWhenDepartmentFactoryIsNull(){
        //arrange
        IDepartmentRepository departmentRepo= mock(IDepartmentRepository.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentServiceImpl(null, departmentRepo);
        });

        assertEquals("DepartmentFactory cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionWhenDepartmentRepoIsNull(){
        //arrange
        IDepartmentFactory departmentFactory= mock(IDepartmentFactory.class);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentServiceImpl(departmentFactory, null);
        });
        assertEquals("DepartmentRepo cannot be null", exception.getMessage());
    }
}