package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.assembler.department.IDepartmentAssembler;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import PAI.VOs.*;
import PAI.dto.department.DepartmentWithDirectorDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateDepartmentDirectorServiceImplTest {

    @Test
    void constructor_shouldThrowException_whenDepartmentRepositoryIsNull() {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssemblerDouble = mock(IDepartmentAssembler.class);

        // 1. departmentRepository é null
        Exception ex1 = assertThrows(IllegalArgumentException.class, () ->
                new UpdateDepartmentDirectorServiceImpl(null, teacherRepositoryDouble, departmentAssemblerDouble)
        );
        assertEquals("Dependencies cannot be null.", ex1.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenTeacherRepositoryIsNull() {

        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);
        IDepartmentAssembler departmentAssemblerDouble = mock(IDepartmentAssembler.class);

        // 2. teacherRepository é null
        Exception ex2 = assertThrows(IllegalArgumentException.class, () ->
                new UpdateDepartmentDirectorServiceImpl(departmentRepositoryDouble, null, departmentAssemblerDouble)
        );
        assertEquals("Dependencies cannot be null.", ex2.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenDepartmentAssemblerIsNull() {
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);

        // 3. departmentAssembler é null
        Exception ex3 = assertThrows(IllegalArgumentException.class, () ->
                new UpdateDepartmentDirectorServiceImpl(departmentRepositoryDouble, teacherRepositoryDouble, null)
        );
        assertEquals("Dependencies cannot be null.", ex3.getMessage());
    }
    @Test
    void shouldConstruct() {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);
        IDepartmentAssembler departmentAssemblerDouble = mock(IDepartmentAssembler.class);

        // Act
        UpdateDepartmentDirectorServiceImpl result  = new UpdateDepartmentDirectorServiceImpl(departmentRepositoryDouble, teacherRepositoryDouble, departmentAssemblerDouble);
         // Assert

        assertNotNull(result);
    }

    @Test
    void shouldUpdateDirectorSuccessfully() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, departmentAssembler);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);

        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);

        // Simula retorno dos repositórios
        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));

        // Simula que o teacher pertence ao departamento
        when(teacher.isInDepartment(departmentID)).thenReturn(true);

        // Simula o DTO criado pelo assembler
        DepartmentWithDirectorDTO expectedDTO = new DepartmentWithDirectorDTO("ABC", "Astronomy", "AAA", "MAF");
        when(departmentAssembler.toDWDDTO(department)).thenReturn(expectedDTO);

        // Act
        DepartmentWithDirectorDTO result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result); // usa o próprio DTO esperado

        verify(department).changeDirector(teacherID);
        verify(departmentRepository).save(department);
        verify(departmentAssembler).toDWDDTO(department);
    }

    @Test
    void shouldUpdateDirectorSuccessfullyWhenAlreadyExistsADirector() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, departmentAssembler);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID existingDirectorID = mock(TeacherID.class); // Diretor atual
        TeacherID newDirectorID = mock(TeacherID.class);      // Novo diretor

        Department department = mock(Department.class);
        Teacher newDirector = mock(Teacher.class);

        // Simula retorno dos repositórios
        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(newDirectorID)).thenReturn(Optional.of(newDirector));

        // Simula que o teacher pertence ao departamento
        when(newDirector.isInDepartment(departmentID)).thenReturn(true);

        // Simula que o departamento já tinha um diretor
        when(department.getDirectorID()).thenReturn(existingDirectorID);

        // Simula o DTO criado pelo assembler
        DepartmentWithDirectorDTO expectedDTO = new DepartmentWithDirectorDTO("ABC", "Astronomy", "AAA", "MAF");
        when(departmentAssembler.toDWDDTO(department)).thenReturn(expectedDTO);

        // Act
        DepartmentWithDirectorDTO result = updateDepartmentDirectorService.updateDirector(departmentID, newDirectorID);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDTO, result);

    }


    @Test
    void shouldNotUpdateDirectorWhenDepartmentNotFound() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, departmentAssembler);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.empty());

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            updateDepartmentDirectorService.updateDirector(departmentID, teacherID);
        });

        // Assert
        assertEquals("Department not found for the given ID.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTeacherIDIsNull() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, departmentAssembler);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            updateDepartmentDirectorService.updateDirector(departmentID, teacherID);
        });
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIDIsNull() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, departmentAssembler);

        DepartmentID departmentID = null;
        TeacherID teacherID = mock(TeacherID.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            updateDepartmentDirectorService.updateDirector(departmentID, teacherID);
        });
    }

    @Test
    void shouldThrowExceptionWhenDepartmentRepositoryIsNull() {
        // Arrange
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new UpdateDepartmentDirectorServiceImpl(null, teacherRepository, departmentAssembler);
        });
    }

    @Test
    void shouldThrowExceptionWhenTeacherRepositoryIsNull() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new UpdateDepartmentDirectorServiceImpl(departmentRepository, null, departmentAssembler);
        });
    }

    @Test
    void shouldThrowExceptionWhenDepartmentAssemblerIsNull() {
        // Arrange
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);


        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, null);
        });
    }

    @Test
    void shouldThrowExceptionWhenTeacherDoesNotBelongToDepartment() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, departmentAssembler);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Department department = mock(Department.class);

        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.empty());
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            updateDepartmentDirectorService.updateDirector(departmentID, teacherID);
        });

        assertEquals("Teacher not found for the given ID.", exception.getMessage());
    }

    @Test
    void updateDirector_shouldThrowException_whenTeacherNotInDepartment() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssembler = mock(IDepartmentAssembler.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository, departmentAssembler);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(departmentDouble));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacherDouble));
        when(teacherDouble.isInDepartment(departmentID)).thenReturn(false); // <- chave do teste

        // Act + Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> updateDepartmentDirectorService.updateDirector(departmentID, teacherID)
        );

        assertEquals("Teacher does not belong to Department.", exception.getMessage());
    }

    @Test
    void updateDirector_shouldThrowRuntimeException_whenSavingDepartmentFails() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssemblerDouble = mock(IDepartmentAssembler.class);

        UpdateDepartmentDirectorServiceImpl service =
                new UpdateDepartmentDirectorServiceImpl(departmentRepositoryDouble, teacherRepositoryDouble, departmentAssemblerDouble);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);

        when(departmentRepositoryDouble.findDepartmentByID(departmentID)).thenReturn(Optional.of(departmentDouble));
        when(teacherRepositoryDouble.ofIdentity(teacherID)).thenReturn(Optional.of(teacherDouble));
        when(teacherDouble.isInDepartment(departmentID)).thenReturn(true);

        // Simular falha ao guardar
        when(departmentRepositoryDouble.save(departmentDouble)).thenThrow(new RuntimeException("Database error"));

        // Act + Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.updateDirector(departmentID, teacherID)
        );

        assertEquals("Department was not save.", exception.getMessage());
        assertEquals("Database error", exception.getCause().getMessage()); // opcional: validação da causa
    }

    @Test
    void updateDirector_shouldReturnDTO_whenUpdateSuccessful() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepositoryDouble = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepositoryDouble = mock(ITeacherRepository.class);
        IDepartmentAssembler departmentAssemblerDouble = mock(IDepartmentAssembler.class);

        UpdateDepartmentDirectorServiceImpl service =
                new UpdateDepartmentDirectorServiceImpl(departmentRepositoryDouble, teacherRepositoryDouble, departmentAssemblerDouble);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Department departmentDouble = mock(Department.class);
        Teacher teacherDouble = mock(Teacher.class);
        DepartmentWithDirectorDTO dtoDouble = mock(DepartmentWithDirectorDTO.class);

        when(departmentRepositoryDouble.findDepartmentByID(departmentID)).thenReturn(Optional.of(departmentDouble));
        when(teacherRepositoryDouble.ofIdentity(teacherID)).thenReturn(Optional.of(teacherDouble));
        when(teacherDouble.isInDepartment(departmentID)).thenReturn(true);
        when(departmentRepositoryDouble.save(departmentDouble)).thenReturn(departmentDouble);
        when(departmentAssemblerDouble.toDWDDTO(departmentDouble)).thenReturn(dtoDouble);

        // Act
        DepartmentWithDirectorDTO result = service.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
        assertEquals(dtoDouble, result);
        verify(departmentAssemblerDouble, times(1)).toDWDDTO(departmentDouble);
    }
}

