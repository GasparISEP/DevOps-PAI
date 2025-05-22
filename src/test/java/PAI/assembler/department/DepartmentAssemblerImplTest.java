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
        Department departmentDouble = mock(Department.class);
        when(departmentDouble.identity()).thenReturn(mock(DepartmentID.class));
        when(departmentDouble.getName()).thenReturn(mock(Name.class));
        when(departmentDouble.getAcronym()).thenReturn(mock(DepartmentAcronym.class));
        when(departmentDouble.getDirectorID()).thenReturn(mock(TeacherID.class));

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act
        DepartmentDTO departmentDTO = departmentAssembler.toDTO(departmentDouble);

        // Assert
        assertNotNull(departmentDTO);
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
        when(departmentDouble.identity()).thenReturn(mock(DepartmentID.class));
        when(departmentDouble.getName()).thenReturn(mock(Name.class));
        when(departmentDouble.getAcronym()).thenReturn(mock(DepartmentAcronym.class));
        when(departmentDouble.getDirectorID()).thenReturn(mock(TeacherID.class));

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