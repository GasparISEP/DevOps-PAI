package PAI.assembler.department;

import PAI.VOs.*;
import PAI.domain.department.Department;
import PAI.dto.department.*;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.department.DepartmentWithDirectorDTO;
import PAI.dto.department.RegisterDepartmentRequestVOs;
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
    void shouldConvertRegisterDepartmentRequestToRegisterDepartmentRequestVOs() {
        // Arrange
        RegisterDepartmentRequest registerDepartmentRequestDouble = mock(RegisterDepartmentRequest.class);
        when(registerDepartmentRequestDouble.name()).thenReturn("Software Engineering Department");
        when(registerDepartmentRequestDouble.acronym()).thenReturn("DEI");

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act
        RegisterDepartmentRequestVOs registerDepartmentRequestVOs = departmentAssembler.toRegisterDepartmentRequestVOs(registerDepartmentRequestDouble);

        // Assert
        assertNotNull(registerDepartmentRequestVOs);
    }

    @Test
    void shouldThrowExceptionWhenRegisterDepartmentRequestIsNull() {
        // Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.toRegisterDepartmentRequestVOs(null);
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

    //toDWDDTO Tests


    @Test
    void shouldConvertDepartmenWithDirectortToDWDDTO() {
        // Arrange
        Department department = mock(Department.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);

        when(department.identity()).thenReturn(departmentID);

        Name name = mock(Name.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);

        when(department.identity().getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("DEI");

        when(department.getName()).thenReturn(name);
        when(name.getName()).thenReturn("Software Engineering Department");

        when(department.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("DEI");

        when(department.getDirectorID()).thenReturn(teacherID);
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn("MAJ");

        DepartmentAssemblerImpl assembler = new DepartmentAssemblerImpl();

        // Act
        DepartmentWithDirectorDTO dto = assembler.toDWDDTO(department);

        // Assert
        assertEquals("DEI", dto.id());
        assertEquals("Software Engineering Department", dto.name());
        assertEquals("DEI", dto.acronym());
        assertEquals("MAJ", dto.teacherID());
    }

    @Test
    void shouldConvertDepartmenWithDirectortToDWDDTOWithStringWhenDirectorIsMissing() {
        // Arrange
        Department department = mock(Department.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);

        when(department.identity()).thenReturn(departmentID);

        Name name = mock(Name.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);

        when(department.identity().getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("DEI");

        when(department.getName()).thenReturn(name);
        when(name.getName()).thenReturn("Software Engineering Department");

        when(department.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("DEI");

        when(department.getDirectorID()).thenReturn(null);

        DepartmentAssemblerImpl assembler = new DepartmentAssemblerImpl();

        // Act
        DepartmentWithDirectorDTO dto = assembler.toDWDDTO(department);

        // Assert
        assertEquals("DEI", dto.id());
        assertEquals("Software Engineering Department", dto.name());
        assertEquals("DEI", dto.acronym());
        assertEquals("No Director Assigned", dto.teacherID());
    }

    @Test
    void shouldThrowExceptionIfDepartmentIsNull() {
        // Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.toDWDDTO(null);
        });
    }
    @Test
    void shouldConvertListOfDepartmentstoDWDDTOs() {
        // Arrange
        Department departmentDouble = mock(Department.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);
        DepartmentAcronym acronymDouble = mock(DepartmentAcronym.class);
        Name nameDouble = mock(Name.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);
        TeacherAcronym teacherAcronymDouble = mock(TeacherAcronym.class);


        when(departmentDouble.identity()).thenReturn(departmentIDDouble);
        when(departmentIDDouble.getAcronym()).thenReturn(acronymDouble);
        when(acronymDouble.getAcronym()).thenReturn("DEI");

        when(departmentDouble.getName()).thenReturn(nameDouble);
        when(nameDouble.getName()).thenReturn("Software Engineering Department");

        when(departmentDouble.getAcronym()).thenReturn(acronymDouble);

        when(departmentDouble.getDirectorID()).thenReturn(teacherIDDouble);
        when(teacherIDDouble.getTeacherAcronym()).thenReturn(teacherAcronymDouble);
        when(teacherAcronymDouble.getAcronym()).thenReturn("MAJ");

        Iterable<Department> listDepartment = List.of(departmentDouble);

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act
        Iterable<DepartmentWithDirectorDTO> departmentDWDDTO = departmentAssembler.toDWDDTOs(listDepartment);

        // Assert
        assertNotNull(departmentDWDDTO);
    }
    @Test
    void shouldReturnEmptyListIfInputIsNull() {
        //Arrange

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();
        //Act
        Iterable<DepartmentWithDirectorDTO> departmentDWDDTO = departmentAssembler.toDWDDTOs(null);
        //Assert
        assertNotNull(departmentDWDDTO);

    }
    @Test
    void shouldReturnEmptyListIfInputIsEmpty() {
        //Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();
        //Act
        Iterable<DepartmentWithDirectorDTO> departmentDWDDTO = departmentAssembler.toDWDDTOs(List.of()); // Static method to create an empty list
        //Assert
        assertNotNull(departmentDWDDTO);
    }
    @Test
    void shouldConvertRequestToDepartmentWithDirectorCommand() {
        // Arrange
        String departmentID = "DEI";
        DepartmentWithDirectorRequest requestDouble = mock(DepartmentWithDirectorRequest.class);
        when(requestDouble.teacherID()).thenReturn("MAJ");

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act
        DepartmentWithDirectorCommand command = departmentAssembler.fromRequestToCommand(departmentID, requestDouble);

        // Assert
        assertNotNull(command);
    }

    @Test
    void shouldThrowExceptionWhenRequestIsNull() {
        // Arrange
        String departmentID = "DEI";
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.fromRequestToCommand(departmentID, null);
        });

        assertEquals("Teacher ID cannot be null", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIDIsNull() {
        // Arrange
        DepartmentWithDirectorRequest requestDouble = mock(DepartmentWithDirectorRequest.class);
        when(requestDouble.teacherID()).thenReturn("MAJ");

        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.fromRequestToCommand(null, requestDouble);
        });

        assertEquals("DepartmentID cannot be null", thrown.getMessage());
    }


    @Test
    void shouldConvertStringToDepartmentID() {
        // Arrange
        String id = "DEI";
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act
        DepartmentID departmentID = departmentAssembler.fromStringToDepartmentID(id);

        // Assert
        assertNotNull(departmentID);
        assertEquals("DEI", departmentID.getAcronym().getAcronym());
    }

    @Test
    void shouldThrowExceptionWhenStringIsNull() {
        // Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.fromStringToDepartmentID(null);
        });
    }

    @Test
    void shouldThrowExceptionWhenStringIsBlank() {
        // Arrange
        DepartmentAssemblerImpl departmentAssembler = new DepartmentAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            departmentAssembler.fromStringToDepartmentID("");
        });
    }
}