package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.List;
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
        when(teacher.identity()).thenReturn(teacherID);
        when(teacherRepository.findAllByDepartmentId(departmentID)).thenReturn(List.of(teacher));

        // Act
        Department result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
        verify(department).setDirectorID(teacherID);
        verify(departmentRepository).save(department);
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
    void shouldListDepartments() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

        DepartmentID departmentID1 = mock(DepartmentID.class);
        DepartmentID departmentID2 = mock(DepartmentID.class);
        Set<DepartmentID> departmentIDs = Set.of(departmentID1, departmentID2);

        when(departmentRepository.getDepartmentIDs()).thenReturn(departmentIDs);

        // Act
        Set<DepartmentID> result = updateDepartmentDirectorService.getDepartmentIDs();

        // Assert
        assertEquals(departmentIDs, result);
    }

    @Test
    void shouldListTeachersByDepartment() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

        DepartmentID departmentID = mock(DepartmentID.class);
        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        List<Teacher> teachers = List.of(teacher1, teacher2);

        when(teacherRepository.findAllByDepartmentId(departmentID)).thenReturn(teachers);

        // Act
        Iterable<Teacher> result = updateDepartmentDirectorService.listTeachersByDepartment(departmentID);

        // Assert
        assertEquals(teachers, result);
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIDIsNullInListTeachers() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

        DepartmentID departmentID = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            updateDepartmentDirectorService.listTeachersByDepartment(departmentID);
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
        when(teacherRepository.findAllByDepartmentId(departmentID)).thenReturn(List.of()); // No teachers in department

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            updateDepartmentDirectorService.updateDirector(departmentID, teacherID);
        });

        assertEquals("The specified teacher does not belong to the given department.", exception.getMessage());
    }

    @Test
    void shouldUpdateDirectorWhenDepartmentAlreadyHasDirector() throws Exception {
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
        when(teacher.identity()).thenReturn(teacherID);
        when(teacherRepository.findAllByDepartmentId(departmentID)).thenReturn(List.of(teacher));

        // Act
        Department result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
        verify(department).setDirectorID(teacherID); // Ensure director is updated
        verify(departmentRepository).save(department);
    }

    @Test
    void shouldThrowExceptionWhenNoTeachersInDepartment() {
        // Arrange
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        UpdateDepartmentDirectorServiceImpl updateDepartmentDirectorService =
                new UpdateDepartmentDirectorServiceImpl(departmentRepository, teacherRepository);

        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID teacherID = mock(TeacherID.class);
        Department department = mock(Department.class);

        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.findAllByDepartmentId(departmentID)).thenReturn(List.of()); // Empty teacher list

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            updateDepartmentDirectorService.updateDirector(departmentID, teacherID);
        });

        assertEquals("The specified teacher does not belong to the given department.", exception.getMessage());

    }
}