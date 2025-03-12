package PAI.factory;
import PAI.domain.Department;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class DepartmentFactoryTest {

    @Test
    void shouldCreateAValidDepartmentWhenMockedConstructorIsGiven() throws Exception {
        //arrange
        String acronym = "DEI";
        String name = "Departamento Engenharia Informática";

        try (MockedConstruction<Department> departmentDouble = mockConstruction(Department.class, (mock, context) -> {
            String actualAcronym = (String) context.arguments().get(0);
            String actualName = (String) context.arguments().get(1);
            when(mock.getAcronym()).thenReturn(actualAcronym);
            when(mock.getName()).thenReturn(actualName);
        })) {

        DepartmentFactory factory = new DepartmentFactory();

        //act
        Department department = factory.newDepartment(acronym, name);

        //assert
        List<Department> departments = departmentDouble.constructed();
        assertEquals(1, departments.size());

        assertEquals(acronym, departmentDouble.constructed().get(0).getAcronym());
        assertEquals(acronym, department.getAcronym());
        assertEquals(name,department.getName());
        }
    }
}
