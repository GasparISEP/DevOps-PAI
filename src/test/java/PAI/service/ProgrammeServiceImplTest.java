package PAI.service;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.domain.Teacher;
import PAI.domain.programme.Programme;
import PAI.factory.IProgrammeFactory;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.programme.ProgrammeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeServiceImplTest {

    @Test
    void shouldCreateProgrammeService() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);

        //Act
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        //Assert
        assertNotNull(service);
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = null;

        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeServiceImpl(programmeFactory, programmeRepository));
    }

    @Test
    void shouldThrowExceptionIfProgrammeFactoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = null;
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);


        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeServiceImpl(programmeFactory, programmeRepository));
    }

    @Test
    void shouldRegisterProgramme() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme programme =mock(Programme.class);

        when(programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID)).thenReturn(programme);
        when(programmeRepository.save(programme)).thenReturn(programme);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        //Act
        boolean result = service.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);

        //Assert
        assertNotNull(result);
    }

    @Test
    void returnsFalseWhenProgrammeAlreadyExists() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        boolean res = service.doesProgrammeExist(name, acronym);

        //Act + Assert
        assertFalse(res);

    }

    @Test
    void returnsTrueWhenProgrammeAlreadyExists() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        Programme prog = mock(Programme.class);
        ProgrammeID progID = mock(ProgrammeID.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        when(programmeRepository.findAll()).thenReturn(List.of(prog));
        when(prog.identity()).thenReturn(progID);
        when(progID.getAcronym()).thenReturn(acronym);
        when(progID.getName()).thenReturn(name);

        boolean res = service.doesProgrammeExist(name, acronym);

        //Act + Assert
        assertTrue(res);

    }

    @Test
    void returnsFalseWhenNameIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        Acronym acronym = mock(Acronym.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        boolean res = service.doesProgrammeExist(null, acronym);

        //Act + Assert
        assertFalse(res);

    }

    @Test
    void returnsFalseWhenAcronymIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        boolean res = service.doesProgrammeExist(name, null);

        //Act + Assert
        assertFalse(res);

    }

    @Test
    void shouldNotRegisterProgrammeWhenProgrammeAlreadyExists() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme prog = mock(Programme.class);
        ProgrammeID progID = mock(ProgrammeID.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        when(programmeRepository.findAll()).thenReturn(List.of(prog));
        when(prog.identity()).thenReturn(progID);
        when(progID.getAcronym()).thenReturn(acronym);
        when(progID.getName()).thenReturn(name);

        //act + assert
        assertThrows(Exception.class, () -> service.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID));

    }

    @Test
    void shouldNotRegisterProgrammeWhenItsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        //Act
        //Assert
        assertThrows(Exception.class, () -> service.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID));

    }

    @Test
    void shouldChangeProgrammeDirector() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        Programme programme = mock(Programme.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(programmeRepository.ofIdentity(programmeID)).thenReturn(Optional.of(programme));
        when(programme.identity()).thenReturn(programmeID);
        when(teacher.identity()).thenReturn(teacherID);

        //Act
        boolean result = service.changeProgrammeDirector(programmeID,teacherID);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfProgrammeIsNull() throws IllegalArgumentException {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ProgrammeID programmeID = null;
        TeacherID teacherID  = mock(TeacherID.class);

        //Act+Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(programmeID, teacherID));
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfTeacherIsNull() throws IllegalArgumentException {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ProgrammeID programmeID  = mock(ProgrammeID.class);
        TeacherID teacherID = null;

        //Act+Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(programmeID, teacherID));

    }

    @Test
    void shouldFindProgrammeByDepartment() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        DepartmentID departmentID = mock(DepartmentID.class);
        ProgrammeID programme1 = mock(ProgrammeID.class);
        ProgrammeID programme2 = mock(ProgrammeID.class);

        when(doubleRepo.findProgrammeByDepartment(departmentID)).thenReturn(List.of(programme1,programme2));

        //Act
        List<ProgrammeID> result = service.findProgrammeByDepartment(departmentID);

        //Assert
        assertEquals(2,result.size());

    }

    @Test
    void shouldNotFindProgrammeByDepartment() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        DepartmentID departmentID = mock(DepartmentID.class);

        //Act
        List<ProgrammeID> result = service.findProgrammeByDepartment(departmentID);

        //Assert
        assertTrue(result.isEmpty());

    }

    @Test
    void shouldFindProgrammeByDegreeTypeID() throws IllegalArgumentException {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        DegreeTypeID id1 = mock(DegreeTypeID.class);
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        when(doubleRepo.findAll()).thenReturn(List.of(programme1,programme2));
        when(programme1.getDegreeTypeID()).thenReturn(id1);
        when(programme2.getDegreeTypeID()).thenReturn(id1);

        //Act
        List<Programme> result = service.getProgrammesByDegreeTypeID(id1);

        //Assert
        assertEquals(2,result.size());

    }

    @Test
    void shouldNotFindProgrammeByDegreeTypeID() throws IllegalArgumentException {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);

        //Act
        List<Programme> result = service.getProgrammesByDegreeTypeID(degreeTypeID);

        //Assert
        assertTrue(result.isEmpty());

    }


    @Test
    void shouldFindProgrammeByName() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Programme programme1 = mock(Programme.class);

        when(doubleRepo.findAll()).thenReturn(List.of(programme1));
        when(programme1.hasThisProgrammeName(name)).thenReturn(true);

        //Act
        Optional<Programme> result = service.getProgrammeByName(name);

        //Assert
        assertNotNull(Optional.of(result));

    }

    @Test
    void shouldNotFindProgrammeByName() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(ProgrammeFactoryImpl.class);
        IProgrammeRepository doubleRepo = mock(ProgrammeRepositoryImpl.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        Programme prog1 = mock(Programme.class);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        when(doubleRepo.findAll()).thenReturn(List.of(prog1));
        when(prog1.hasThisProgrammeName(name)).thenReturn(false);

        //Act
        Optional<Programme> result = service.getProgrammeByName(name);

        //Assert
        assertTrue(result.isEmpty());

    }

    @Test
    void shouldGetProgrammeByAcronym() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        Acronym acronym = mock(Acronym.class);
        Programme programme1 = mock(Programme.class);

        when(doubleRepo.findAll()).thenReturn(List.of(programme1));
        when(programme1.getAcronym()).thenReturn(acronym);

        //Act
        Programme result = service.getProgrammeByAcronym(acronym);

        //Assert
        assertNotNull(result);

    }

    @Test
    void shouldNotGetProgrammeByAcronym() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        //Act
        Programme result = service.getProgrammeByAcronym(null);

        //Assert
        assertNull(result);

    }

    @Test
    void shouldGetAllProgrammeIDs() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        ProgrammeID id1 = mock(ProgrammeID.class);
        ProgrammeID id2 = mock(ProgrammeID.class);

        when(doubleRepo.findAll()).thenReturn(Arrays.asList(programme1, programme2));
        when(programme1.getProgrammeID()).thenReturn(id1);
        when(programme2.getProgrammeID()).thenReturn(id2);

        //Act
        List<ProgrammeID> result = service.getAllProgrammeIDs();

        //Assert
        assertEquals(2, result.size());

    }

    @Test
    void shouldNotGetAllProgrammeIDs() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        //Act
        List<ProgrammeID> result = service.getAllProgrammeIDs();

        //Assert
        assertTrue(result.isEmpty());

    }

    @Test
    void shouldFindAll() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);
        Programme programme1 = mock(Programme.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        when(doubleRepo.findAll()).thenReturn(List.of(programme1));

        //act
        Iterable<Programme> all = service.findAll();

        //assert
        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void shouldNotFindAll() {
        //Arrange
        IProgrammeFactory doubleFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository doubleRepo = mock(IProgrammeRepository.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(doubleFactory,doubleRepo);

        //act
        Iterable<Programme> all = service.findAll();

        //assert
        assertFalse(all.iterator().hasNext());
    }

    @Test
    void getProgrammeByIDFoundShouldReturnProgramme() {
        // Arrange
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);

        ProgrammeID id = new ProgrammeID(name, acronym);
        Programme programme = mock(Programme.class);
        when(programme.identity()).thenReturn(id);

        when(programmeRepository.findAll()).thenReturn(List.of(programme));

        // Act
        Optional<Programme> result = programmeService.getProgrammeByID(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(programme, result.get());
    }

    @Test
    void getProgrammeByIDShouldReturnEmptyOptionalWhenProgrammeNotFound() {
        // Arrange
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID id = new ProgrammeID(name, acronym);

        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);

        Programme differentProgramme = mock(Programme.class);
        when(differentProgramme.identity()).thenReturn(new ProgrammeID(name1, acronym1));
        when(programmeRepository.findAll()).thenReturn(List.of(differentProgramme));

        // Act
        Optional<Programme> result = programmeService.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetProgrammeByID_EmptyRepository() {
        // Arrange
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID id = new ProgrammeID(name, acronym);

        when(programmeRepository.findAll()).thenReturn(List.of());

        // Act
        Optional<Programme> result = programmeService.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeNotFound() {
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        TeacherID directorID = mock(TeacherID.class);

        when(programmeRepository.ofIdentity(programmeID)).thenReturn(Optional.empty());

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.changeProgrammeDirector(programmeID, directorID);
        });

        assertEquals("Programme not found", exception.getMessage());
    }

    @Test
    void shouldReturnProgrammeWhenNameMatches() {
        //arrange
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Engenharia Informática");

        Programme programme = mock(Programme.class);
        when(programme.hasThisProgrammeName(name)).thenReturn(true);

        when(programmeRepository.findAll()).thenReturn(List.of(programme));

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);
        //act
        Optional<Programme> result = service.getProgrammeByName(name);
        //assert
        assertTrue(result.isPresent());
        assertEquals(programme, result.get());
    }
}