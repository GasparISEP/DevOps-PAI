package PAI.factory;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.department.DepartmentFactoryImpl;
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

    @Test
    void shouldCreateAValidDepartmentWithDirectorWhenMockedConstructorIsGiven() throws Exception {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        Name name = mock(Name.class);
        TeacherID director= mock(TeacherID.class);

        try (MockedConstruction<Department> departmentDouble = mockConstruction(Department.class, (mock, context) -> {
            DepartmentAcronym actualAcronym = (DepartmentAcronym) context.arguments().get(0);
            Name actualName = (Name) context.arguments().get(1);
            TeacherID actualTeacherID= (TeacherID) context.arguments().get(2);
            when(mock.getAcronym()).thenReturn(actualAcronym);
            when(mock.getName()).thenReturn(actualName);
            when(mock.getDirectorID()).thenReturn(actualTeacherID);

        })) {

            DepartmentFactoryImpl factory = new DepartmentFactoryImpl();

            //act
            Department department = factory.newDepartment(acronym, name, director);

            //assert
            List<Department> departments = departmentDouble.constructed();
            assertEquals(1, departments.size());

            assertEquals(acronym, departmentDouble.constructed().get(0).getAcronym());
            assertEquals(acronym, department.getAcronym());
            assertEquals(name,department.getName());
            assertEquals(director,department.getDirectorID());
        }
    }
}
