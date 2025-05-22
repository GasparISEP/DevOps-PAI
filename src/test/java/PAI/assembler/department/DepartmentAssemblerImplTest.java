package PAI.assembler.department;

import PAI.VOs.*;
import PAI.domain.department.Department;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.RegisterDepartmentCommand;
import PAI.dto.department.RegisterDepartmentRequest;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class DepartmentAssemblerImplTest {

    @Test
    void shouldCreateDepartmentAssembler() {
        // Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        assertNotNull(departmentAssembler);
    }

    @Test
    void shouldConvertRegisterDepartmentRequestToRegisterDepartmentCommand() {
        // Arrange
        RegisterDepartmentRequest registerDepartmentRequestDouble = mock(RegisterDepartmentRequest.class);
        when(registerDepartmentRequestDouble.name()).thenReturn("Software Engineering Department");
        when(registerDepartmentRequestDouble.acronym()).thenReturn("DEI");

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act
        RegisterDepartmentCommand registerDepartmentCommand = departmentAssembler.toRegisterDepartmentCommand(registerDepartmentRequestDouble);

        // Assert
        assertNotNull(registerDepartmentCommand);
    }

    @Test
    void shouldThrowExceptionWhenRegisterDepartmentRequestIsNull() {
        // Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.toRegisterDepartmentCommand(null);
        });
    }

    @Test
    void shouldConvertDepartmentToDTO() {
        // Arrange
        Department department = mock(Department.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        when(department.identity()).thenReturn(departmentID);

        Name name = mock(Name.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);

        when(department.identity().getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("DEI");

        when(department.getName()).thenReturn(name);
        when(name.getName()).thenReturn("Software Engineering Department");

        when(department.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("DEI");

        DepartmentAssemblerImpl assembler = new DepartmentAssemblerImpl();

        // Act
        DepartmentDTO dto = assembler.toDTO(department);

        // Assert
        assertEquals("DEI", dto.id());
        assertEquals("Software Engineering Department", dto.name());
        assertEquals("DEI", dto.acronym());
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIsNull() {
        // Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.toDTO(null);
        });
    }
    @Test
    void shouldConvertListOfDepartmentsToDTOs() {
        // Arrange
        Department departmentDouble = mock(Department.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = mock(Name.class);

        when(departmentDouble.identity()).thenReturn(departmentIDDouble);
        when(departmentIDDouble.getAcronym()).thenReturn(acronymDouble);
        when(acronymDouble.getAcronym()).thenReturn("DEI");
        when(departmentDouble.getName()).thenReturn(nameDouble);
        when(nameDouble.getName()).thenReturn("Software Engineering Department");
        when(departmentDouble.getAcronym()).thenReturn(acronymDouble);

        Iterable<Department> listDepartment = List.of(departmentDouble);

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act
        Iterable<DepartmentDTO> departmentDTOs = departmentAssembler.toDTOs(listDepartment);

        // Assert
        assertNotNull(departmentDTOs);
    }
    @Test
    void shouldReturnEmptyListWhenInputIsNull() {
        //Arrange

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();
        //Act
        Iterable<DepartmentDTO> departmentDTOS = departmentAssembler.toDTOs(null);
        //Assert
        assertNotNull(departmentDTOS);

    }
    @Test
    void shouldReturnEmptyListWhenInputIsEmpty() {
        //Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();
        //Act
        Iterable<DepartmentDTO> departmentDTOS = departmentAssembler.toDTOs(List.of()); // Static method to create an empty list
        //Assert
        assertNotNull(departmentDTOS);
    }
}