package PAI.factory;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.domain.Department;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentFactoryImplTest {

    @Test
    void shouldCreateAValidDepartmentWhenMockedConstructorIsGiven() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);

        try (MockedConstruction<Department> departmentDouble = mockConstruction(Department.class, (mock, context) -> {
            DepartmentAcronym actualAcronym = (DepartmentAcronym) context.arguments().get(0);
            Name actualName = (Name) context.arguments().get(1);
            when(mock.getAcronym()).thenReturn(actualAcronym);
            when(mock.getName()).thenReturn(actualName);
        })) {

        DepartmentFactoryImpl factory = new DepartmentFactoryImpl();

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
