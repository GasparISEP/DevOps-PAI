package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateDepartmentDirectorServiceImplTest {

    @Test
    void shouldUpdateDirectorSuccessfully() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);


        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacher)); // Mock correto
        when(teacher.isInDepartment(departmentID)).thenReturn(true); // Simula que o professor pertence ao departamento

        // Act
        Department result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldNotUpdateDirectorWhenDepartmentNotFound() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

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
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

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
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

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

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new UpdateDepartmentDirectorServiceImpl(null, teacherRepository);
        });
    }

    @Test
    void shouldThrowExceptionWhenTeacherRepositoryIsNull() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new UpdateDepartmentDirectorServiceImpl(departmentRepository, null);
        });
    }

    @Test
    void shouldThrowExceptionWhenTeacherDoesNotBelongToDepartment() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

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
    void shouldAddDirectorWhenDepartmentDoesNotHaveADirector() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        Department department = mock(Department.class);

        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));
        when(teacher.isInDepartment(departmentID)).thenReturn(true);
        when(department.getDirectorID()).thenReturn(null); // Define que nao tem director inicialmente

        // Act
        updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        verify(department).changeDirector(teacherID); // Verifica que o department foi alterado
        }

    @Test
    void shouldReplaceDirectorWhenDepartmentAlreadyHasDirector() throws Exception {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherID existingDirectorID = mock(TeacherID.class);
        Teacher teacher = mock(Teacher.class);
        Department department = mock(Department.class); // Usar uma instância real nao mockada
        department.changeDirector(existingDirectorID); // Define um diretor existente


        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));
        when(department.getDirectorID()).thenReturn(existingDirectorID);   // Simula que o departamento já tem um diretor
        when(teacher.isInDepartment(departmentID)).thenReturn(true);

        // Act
        updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        verify(department).changeDirector(teacherID); // Verify the method call
    }
}
