package PAI.factory;
import PAI.domain.Department;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartmentListFactoryTest {

    @Test
    void shouldCreateDepartmentList() {
        // arrange
        DepartmentListFactory factory = new DepartmentListFactory();

        // act
        Set<Department> result = factory.newDepartmentList();

        // assert
        assertEquals(HashSet.class, result.getClass());
    }
}