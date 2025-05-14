package PAI.persistence.mem.department;
import PAI.domain.department.Department;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartmentListFactoryImplTest {

    @Test
    void shouldCreateDepartmentList() {
        // arrange
        DepartmentListFactoryImpl factory = new DepartmentListFactoryImpl();

        // act
        Set<Department> result = factory.newDepartmentList();

        // assert
        assertEquals(HashSet.class, result.getClass());
    }
}