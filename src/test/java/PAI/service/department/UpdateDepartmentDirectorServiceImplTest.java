package PAI.service.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;
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

        // Simula retorno dos repositórios
        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));

        // Simula que o teacher pertence ao departamento
        when(teacher.isInDepartment(departmentID)).thenReturn(true);
        when(department.changeDirector(teacherID)).thenReturn(true);
        when(departmentRepository.save(department)).thenReturn(department);

        // Simula os VOs do departamento
        when(department.identity()).thenReturn(departmentID);
        when(departmentID.toString()).thenReturn("ABC");

        Name nameVO = mock(Name.class);
        when(nameVO.toString()).thenReturn("Department of Engineering and Informatics");
        when(department.getName()).thenReturn(nameVO);
        String name = nameVO.toString();

        when(department.getAcronym()).thenReturn(mock(DepartmentAcronym.class));
        when(department.getAcronym().toString()).thenReturn("DEI");
        String acronym = department.getAcronym().toString();

        TeacherID directorID = mock(TeacherID.class);
        when(department.getDirectorID()).thenReturn(directorID);
        when(directorID.toString()).thenReturn("MAF");
        String director = directorID.toString();

        // Act
        DepartmentWithDirectorDTO result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
        assertEquals("ABC", result.id());
        assertEquals(name, result.name());
        assertEquals(acronym, result.acronym());
        assertEquals(director, result.teacherID());
        verify(department).changeDirector(teacherID);
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

        // Simula retorno dos repositórios
        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));

        // Simula pertença e estado do departamento
        when(teacher.isInDepartment(departmentID)).thenReturn(true);
        when(department.getDirectorID()).thenReturn(null); // Não há diretor
        when(department.changeDirector(teacherID)).thenReturn(true);
        when(departmentRepository.save(department)).thenReturn(department);

        // Simula info usada no DTO
        when(department.identity()).thenReturn(departmentID);
        when(departmentID.toString()).thenReturn("DEF");

        Name nameVO = mock(Name.class);
        when(nameVO.toString()).thenReturn("Department of Mathematics");
        when(department.getName()).thenReturn(nameVO);

        DepartmentAcronym acronymVO = mock(DepartmentAcronym.class);
        when(acronymVO.toString()).thenReturn("DM");
        when(department.getAcronym()).thenReturn(acronymVO);

        when(department.getDirectorID()).thenReturn(teacherID);
        when(teacherID.toString()).thenReturn("JSS");

        // Act
        DepartmentWithDirectorDTO result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
        assertEquals("DEF", result.id());
        assertEquals("Department of Mathematics", result.name());
        assertEquals("DM", result.acronym());
        assertEquals("JSS", result.teacherID());
        verify(department).changeDirector(teacherID);
        verify(departmentRepository).save(department);
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
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);

        // Simula repositórios
        when(departmentRepository.findDepartmentByID(departmentID)).thenReturn(Optional.of(department));
        when(teacherRepository.ofIdentity(teacherID)).thenReturn(Optional.of(teacher));

        // Simula estado do departamento e professor
        when(department.identity()).thenReturn(departmentID);
        when(department.getDirectorID()).thenReturn(existingDirectorID);
        when(teacher.isInDepartment(departmentID)).thenReturn(true);
        when(department.changeDirector(teacherID)).thenReturn(true);
        when(departmentRepository.save(department)).thenReturn(department);

        // Simula o que é usado na criação do DTO
        when(departmentID.toString()).thenReturn("ABC");

        Name nameVO = mock(Name.class);
        when(nameVO.toString()).thenReturn("Department of Engineering and Informatics");
        when(department.getName()).thenReturn(nameVO);

        DepartmentAcronym acronymVO = mock(DepartmentAcronym.class);
        when(acronymVO.toString()).thenReturn("DEI");
        when(department.getAcronym()).thenReturn(acronymVO);

        when(department.getDirectorID()).thenReturn(teacherID);
        when(teacherID.toString()).thenReturn("MAF");

        // Act
        DepartmentWithDirectorDTO result = updateDepartmentDirectorService.updateDirector(departmentID, teacherID);

        // Assert
        assertNotNull(result);
        assertEquals("ABC", result.id());
        assertEquals("Department of Engineering and Informatics", result.name());
        assertEquals("DEI", result.acronym());
        assertEquals("MAF", result.teacherID());

        verify(department).changeDirector(teacherID);
        verify(departmentRepository).save(department);
    }
}

