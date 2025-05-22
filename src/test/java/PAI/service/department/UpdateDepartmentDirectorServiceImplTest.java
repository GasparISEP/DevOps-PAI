package PAI.service.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import org.junit.jupiter.api.Test;

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

        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));

        // Act
        boolean result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertTrue(result);
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
        boolean result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertFalse(result);
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

        Department department1 = mock(Department.class);
        Department department2 = mock(Department.class);
        List<Department> departments = List.of(department1, department2);

        when(departmentRepository.findAll()).thenReturn(departments);

        // Act
        Iterable<Department> result = updateDepartmentDirectorService.listDepartments();

        // Assert
        assertEquals(departments, result);
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


}