package PAI.service.programme;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.domain.programme.Programme;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeServiceImplTest {

    private IProgrammeFactory _programmeFactoryDouble;
    private IProgrammeRepository _programmeRepositoryDouble;
    private NameWithNumbersAndSpecialChars _nameDouble;
    private NameWithNumbersAndSpecialChars _name2Double;
    private Acronym _acronymDouble;
    private Acronym _acronym2Double;
    private QuantEcts _quantityOfEctsDouble;
    private QuantSemesters _quantityOfSemestersDouble;
    private DegreeTypeID _degreeTypeIDDouble;
    private DepartmentID _departmentIDDouble;
    private Teacher _teacherDouble;
    private TeacherID _programmeDirectorIDDouble;
    private Programme _programmeDouble;
    private Programme _programme2Double;
    private ProgrammeID _programmeIDDouble;
    private ProgrammeID _programme2IDDouble;

    private void createDoubles() {
        _programmeFactoryDouble = mock(IProgrammeFactory.class);
        _programmeRepositoryDouble = mock(IProgrammeRepository.class);
        _nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        _name2Double = mock(NameWithNumbersAndSpecialChars.class);
        _acronymDouble = mock(Acronym.class);
        _acronym2Double = mock(Acronym.class);
        _quantityOfEctsDouble = mock(QuantEcts.class);
        _quantityOfSemestersDouble = mock(QuantSemesters.class);
        _degreeTypeIDDouble = mock(DegreeTypeID.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _teacherDouble = mock(Teacher.class);
        _programmeDirectorIDDouble = mock(TeacherID.class);
        _programmeDouble = mock(Programme.class);
        _programme2Double = mock(Programme.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _programme2IDDouble = mock(ProgrammeID.class);
    }

    @Test
    void shouldCreateProgrammeService() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        //Assert
        assertNotNull(service);
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(Exception.class, () -> new ProgrammeServiceImpl(_programmeFactoryDouble, null));
    }

    @Test
    void shouldThrowExceptionIfProgrammeFactoryIsNull() {
        //Arrange
        createDoubles();
        
        //Act + Assert
        assertThrows(Exception.class, () -> new ProgrammeServiceImpl(null, _programmeRepositoryDouble));
    }

    @Test
    void shouldRegisterProgramme() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _quantityOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.save(_programmeDouble)).thenReturn(_programmeDouble);

        //Act
        Programme result = service.registerProgramme(_nameDouble, _acronymDouble, _quantityOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble);

        //Assert
        assertEquals(result, _programmeDouble);
    }

    static Stream<Arguments> parametersToCreateProgrammeAreInvalid() {
        return Streams.of(
                Arguments.of(null, mock(Acronym.class), mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class), "Programme name cannot be null"),
                Arguments.of(mock(NameWithNumbersAndSpecialChars.class), null, mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class), "Acronym must not be null"),
                Arguments.of(mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class), null, mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class), "Number of ECTS must not be null"),
                Arguments.of(mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class), mock(QuantEcts.class), null, mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class), "Quantity of Semesters must not be null"),
                Arguments.of(mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class), mock(QuantEcts.class), mock(QuantSemesters.class), null, mock(DepartmentID.class), mock(TeacherID.class), "DegreeTypeID must not be null"),
                Arguments.of(mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class), mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), null, mock(TeacherID.class), "DepartmentID must not be null"),
                Arguments.of(mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class), mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), null, "TeacherID must not be null"),
                Arguments.of(mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class), mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class), "ProgrammeID must not be null")
        );
    }

    @ParameterizedTest
    @MethodSource("parametersToCreateProgrammeAreInvalid")
    void shouldThrowExceptionWhenParametersToCreateProgrammeAreNotValid(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID, String expectedMessage) {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        when(_programmeFactoryDouble.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID)).thenThrow(new IllegalArgumentException(expectedMessage));

        //Act
        Exception result = assertThrows(IllegalArgumentException.class, () -> service.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID));

        //Assert
        assertEquals(result.getMessage(), expectedMessage);
    }

    @Test
    void shouldNotRegisterProgrammeWhenProgrammeAlreadyExists() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _quantityOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(true);

        //Act
        Exception result = assertThrows(Exception.class, () -> service.registerProgramme(_nameDouble, _acronymDouble, _quantityOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble));

        //Assert
        assertEquals(result.getMessage(), "Programme is already registered");
    }

    @Test
    void shouldThrowExceptionWhenProgrammeFailsToBeSavedInTheDatabase() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);
        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _quantityOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.save(_programmeDouble)).thenThrow(new RuntimeException());

        //Act + Assert
        assertThrows(Exception.class, () -> service.registerProgramme(_nameDouble, _acronymDouble, _quantityOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble));
    }

    @Test
    void shouldChangeProgrammeDirector() throws Exception {
        //Arrange
        createDoubles();
        
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        when(_programmeRepositoryDouble.ofIdentity(_programmeIDDouble)).thenReturn(Optional.of(_programmeDouble));
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_teacherDouble.identity()).thenReturn(_programmeDirectorIDDouble);

        //Act
        boolean result = service.changeProgrammeDirector(_programmeIDDouble,_programmeDirectorIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfProgrammeIsNull() throws IllegalArgumentException {
        //Arrange
        createDoubles();
        
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        //Act + Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(null, _programmeDirectorIDDouble));
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfTeacherIsNull() throws IllegalArgumentException {
        //Arrange
        createDoubles();
        
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        //Act + Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(_programmeIDDouble, null));
    }

    @Test
    void shouldFindProgrammeByDepartment() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findProgrammeByDepartment(_departmentIDDouble)).thenReturn(List.of(_programmeIDDouble,_programme2IDDouble));

        //Act
        List<ProgrammeID> result = service.findProgrammeByDepartment(_departmentIDDouble);

        //Assert
        assertEquals(2,result.size());
    }

    @Test
    void shouldNotFindProgrammeByDepartment() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        //Act
        List<ProgrammeID> result = service.findProgrammeByDepartment(null);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindProgrammeByDegreeTypeID() throws IllegalArgumentException {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble,_programme2Double));
        when(_programmeDouble.hasThisDegreeTypeID(_degreeTypeIDDouble)).thenReturn(true);
        when(_programme2Double.hasThisDegreeTypeID(_degreeTypeIDDouble)).thenReturn(true);

        //Act
        List<Programme> result = service.getProgrammesByDegreeTypeID(_degreeTypeIDDouble);

        //Assert
        assertEquals(2,result.size());
    }

    @Test
    void shouldNotFindProgrammeByDegreeTypeID() throws IllegalArgumentException {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));
        when(_programmeDouble.hasThisDegreeTypeID(_degreeTypeIDDouble)).thenReturn(false);

        //Act
        List<Programme> result = service.getProgrammesByDegreeTypeID(_degreeTypeIDDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindProgrammeByName() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));
        when(_programmeDouble.hasThisProgrammeName(_nameDouble)).thenReturn(true);

        //Act
        Optional<Programme> result = service.getProgrammeByName(_nameDouble);

        //Assert
        assertNotNull(Optional.of(result));
    }

    @Test
    void shouldNotFindProgrammeByName() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));
        when(_programmeDouble.hasThisProgrammeName(_nameDouble)).thenReturn(false);

        //Act
        Optional<Programme> result = service.getProgrammeByName(_nameDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetProgrammeByAcronym() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));
        when(_programmeDouble.getAcronym()).thenReturn(_acronymDouble);
        when(_programmeDouble.hasThisProgrammeAcronym(_acronymDouble)).thenReturn(true);

        //Act
        Optional<Programme> result = service.getProgrammeByAcronym(_acronymDouble);

        //Assert
        assertNotNull(Optional.of(result));
    }


    @Test
    void shouldNotGetProgrammeByAcronym() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));
        when(_programmeDouble.getAcronym()).thenReturn(_acronymDouble);
        when(_programmeDouble.hasThisProgrammeAcronym(_acronym2Double)).thenReturn(false);

        //Act
        Optional<Programme> result = service.getProgrammeByAcronym(_acronym2Double);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldGetAllProgrammeIDs() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(Arrays.asList(_programmeDouble, _programme2Double));
        when(_programmeDouble.getProgrammeID()).thenReturn(_programmeIDDouble);
        when(_programme2Double.getProgrammeID()).thenReturn(_programme2IDDouble);

        //Act
        List<ProgrammeID> result = service.getAllProgrammeIDs();

        //Assert
        assertEquals(2, result.size());

    }

    @Test
    void shouldNotGetAllProgrammeIDs() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        //Act
        List<ProgrammeID> result = service.getAllProgrammeIDs();

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindAll() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));

        //Act
        Iterable<Programme> all = service.findAll();

        //Assert
        assertNotNull(all);
        assertTrue(all.iterator().hasNext());
    }

    @Test
    void shouldNotFindAll() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble);

        //Act
        Iterable<Programme> all = service.findAll();

        //Assert
        assertFalse(all.iterator().hasNext());
    }

    @Test
    void getProgrammeByIDFoundShouldReturnProgramme() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        ProgrammeID id = new ProgrammeID(_nameDouble, _acronymDouble);

        when(_programmeDouble.identity()).thenReturn(id);
        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));

        // Act
        Optional<Programme> result = programmeService.getProgrammeByID(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(_programmeDouble, result.get());
    }

    @Test
    void getProgrammeByIDShouldReturnEmptyOptionalWhenProgrammeNotFound() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        ProgrammeID id = new ProgrammeID(_nameDouble, _acronymDouble);

        when(_programme2Double.identity()).thenReturn(new ProgrammeID(_name2Double, _acronym2Double));
        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programme2Double));

        // Act
        Optional<Programme> result = programmeService.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetProgrammeByID_EmptyRepository() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        ProgrammeID id = new ProgrammeID(_nameDouble, _acronymDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of());

        // Act
        Optional<Programme> result = programmeService.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeNotFound() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);

        when(_programmeRepositoryDouble.ofIdentity(_programmeIDDouble)).thenReturn(Optional.empty());

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.changeProgrammeDirector(_programmeIDDouble, _programmeDirectorIDDouble);
        });

        //Assert
        assertEquals("Programme not found", exception.getMessage());
    }

    @Test
    void shouldReturnProgrammeWhenNameMatches() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Engenharia Informática");

        when(_programmeDouble.hasThisProgrammeName(name)).thenReturn(true);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));

        //Act
        Optional<Programme> result = service.getProgrammeByName(name);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(_programmeDouble, result.get());
    }
}