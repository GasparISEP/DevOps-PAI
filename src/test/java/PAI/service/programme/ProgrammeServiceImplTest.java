package PAI.service.programme;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.teacher.Teacher;
import PAI.domain.programme.Programme;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.NotFoundException;
import PAI.service.degreeType.DegreeTypeRegistrationServiceImpl;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import PAI.service.department.IDepartmentService;
import PAI.service.teacher.ITeacherService;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
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
    private IProgrammeAssembler _programmeAssemblerDouble;
    private IDegreeTypeRegistrationService _degreeTypeService;
    private IDepartmentService _departmentServiceDouble;
    private ITeacherService _teacherServiceDouble;
    private NameWithNumbersAndSpecialChars _nameDouble;
    private NameWithNumbersAndSpecialChars _name2Double;
    private Acronym _acronymDouble;
    private Acronym _acronym2Double;
    private MaxEcts _maxOfEctsDouble;
    private QuantSemesters _quantityOfSemestersDouble;
    private DegreeTypeID _degreeTypeIDDouble;
    private DepartmentID _departmentIDDouble;
    private Teacher _teacherDouble;
    private TeacherID _programmeDirectorIDDouble;
    private Programme _programmeDouble;
    private Programme _programme2Double;
    private ProgrammeID _programmeIDDouble;
    private ProgrammeID _programme2IDDouble;
    private ProgrammeVOsDTO _programmeVOsDTODouble;

    private void createDoubles() {
        _programmeFactoryDouble = mock(IProgrammeFactory.class);
        _programmeRepositoryDouble = mock(IProgrammeRepository.class);
        _programmeAssemblerDouble = mock(IProgrammeAssembler.class);
        _departmentServiceDouble = mock(IDepartmentService.class);
        _teacherServiceDouble = mock(ITeacherService.class);
        _degreeTypeService = mock(DegreeTypeRegistrationServiceImpl.class);
        _nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        _name2Double = mock(NameWithNumbersAndSpecialChars.class);
        _acronymDouble = mock(Acronym.class);
        _acronym2Double = mock(Acronym.class);
        _maxOfEctsDouble = mock(MaxEcts.class);
        _quantityOfSemestersDouble = mock(QuantSemesters.class);
        _degreeTypeIDDouble = mock(DegreeTypeID.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _teacherDouble = mock(Teacher.class);
        _programmeDirectorIDDouble = mock(TeacherID.class);
        _programmeDouble = mock(Programme.class);
        _programme2Double = mock(Programme.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _programme2IDDouble = mock(ProgrammeID.class);
        _programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
    }

    @Test
    void shouldCreateProgrammeService() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        //Assert
        assertNotNull(service);
    }

    static Stream<Arguments> parametersToCreateProgrammeServiceAreInvalid() {
        return Stream.of(
                Arguments.of(null, mock(IProgrammeRepository.class), mock(IProgrammeAssembler.class), mock(DegreeTypeRegistrationServiceImpl.class), mock(IDepartmentService.class), mock(ITeacherService.class), "Programme Factory cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), null, mock(IProgrammeAssembler.class), mock(DegreeTypeRegistrationServiceImpl.class), mock(IDepartmentService.class), mock(ITeacherService.class), "Programme Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), null, mock(DegreeTypeRegistrationServiceImpl.class), mock(IDepartmentService.class), mock(ITeacherService.class), "Programme Assembler cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IProgrammeAssembler.class), null, mock(IDepartmentService.class), mock(ITeacherService.class), "Degree Type Service cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IProgrammeAssembler.class), mock(DegreeTypeRegistrationServiceImpl.class), null, mock(ITeacherService.class), "Department Service cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IProgrammeAssembler.class), mock(DegreeTypeRegistrationServiceImpl.class), mock(IDepartmentService.class), null, "Teacher Service cannot be null")
        );
    }

    @ParameterizedTest
    @MethodSource("parametersToCreateProgrammeServiceAreInvalid")
    void shouldThrowExceptionWhenParametersToCreateProgrammeServiceAreNotValid(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository,IProgrammeAssembler programmeAssembler, IDegreeTypeRegistrationService degreeTypeRegistrationService, IDepartmentService departmentServiceDouble, ITeacherService teacherServiceDouble, String expectedMessage) {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentServiceDouble, teacherServiceDouble));

        //assert
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void shouldRegisterProgramme() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        DegreeType degreeTypeDouble = mock(DegreeType.class);
        int numberOfSemesters = 6;
        int numberOfEcts = 180;

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_degreeTypeService.getDegreeTypeById(_degreeTypeIDDouble)).thenReturn(Optional.ofNullable(degreeTypeDouble));
        when(degreeTypeDouble.getMaxEcts()).thenReturn(_maxOfEctsDouble);
        when(_quantityOfSemestersDouble.getQuantityOfSemesters()).thenReturn(numberOfSemesters);
        when(_maxOfEctsDouble.getMaxEcts()).thenReturn(numberOfEcts);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.save(_programmeDouble)).thenReturn(_programmeDouble);
        when(_programmeRepositoryDouble.existsByName(_nameDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.existsByAcronym(_acronymDouble)).thenReturn(false);

        //Act
        Programme result = service.registerProgramme(_programmeVOsDTODouble);

        //Assert
        assertSame(result, _programmeDouble);
    }

    @Test
    void shouldNotRegisterProgrammeWhenProgrammeAlreadyExistsName() {
        //Arrange
        createDoubles();

        DegreeType degreeTypeDouble = mock(DegreeType.class);
        int numberOfSemesters = 6;
        int numberOfEcts = 180;

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_degreeTypeService.getDegreeTypeById(_degreeTypeIDDouble)).thenReturn(Optional.ofNullable(degreeTypeDouble));
        when(degreeTypeDouble.getMaxEcts()).thenReturn(_maxOfEctsDouble);
        when(_quantityOfSemestersDouble.getQuantityOfSemesters()).thenReturn(numberOfSemesters);
        when(_maxOfEctsDouble.getMaxEcts()).thenReturn(numberOfEcts);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.existsByName(_nameDouble)).thenReturn(true);

        //Act
        Exception result = assertThrows(AlreadyRegisteredException.class, () -> service.registerProgramme(_programmeVOsDTODouble));

        //Assert
        assertEquals("Programme name is already registered.", result.getMessage());
    }

    @Test
    void shouldNotRegisterProgrammeWhenProgrammeAlreadyExistsAcronym() {
        //Arrange
        createDoubles();

        DegreeType degreeTypeDouble = mock(DegreeType.class);
        int numberOfSemesters = 6;
        int numberOfEcts = 180;

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_degreeTypeService.getDegreeTypeById(_degreeTypeIDDouble)).thenReturn(Optional.ofNullable(degreeTypeDouble));
        when(degreeTypeDouble.getMaxEcts()).thenReturn(_maxOfEctsDouble);
        when(_quantityOfSemestersDouble.getQuantityOfSemesters()).thenReturn(numberOfSemesters);
        when(_maxOfEctsDouble.getMaxEcts()).thenReturn(numberOfEcts);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.existsByName(_nameDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.existsByAcronym(_acronymDouble)).thenReturn(true);

        //Act
        Exception result = assertThrows(AlreadyRegisteredException.class, () -> service.registerProgramme(_programmeVOsDTODouble));

        //Assert
        assertEquals("Programme acronym is already registered.", result.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeFailsToBeSavedInTheDatabase() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        DegreeType degreeTypeDouble = mock(DegreeType.class);
        int numberOfSemesters = 6;
        int numberOfEcts = 180;

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_degreeTypeService.getDegreeTypeById(_degreeTypeIDDouble)).thenReturn(Optional.ofNullable(degreeTypeDouble));
        when(degreeTypeDouble.getMaxEcts()).thenReturn(_maxOfEctsDouble);
        when(_quantityOfSemestersDouble.getQuantityOfSemesters()).thenReturn(numberOfSemesters);
        when(_maxOfEctsDouble.getMaxEcts()).thenReturn(numberOfEcts);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.save(_programmeDouble)).thenThrow(new RuntimeException());

        //Act + Assert
        assertThrows(Exception.class, () -> service.registerProgramme(_programmeVOsDTODouble));
    }

    @Test
    void shouldThrowExceptionWhenQuantityOfSemesterIsLowerThanWhatIsAllowed() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        DegreeType degreeTypeDouble = mock(DegreeType.class);
        int numberOfSemesters = 5;
        int numberOfEcts = 180;

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_degreeTypeService.getDegreeTypeById(_degreeTypeIDDouble)).thenReturn(Optional.ofNullable(degreeTypeDouble));
        when(degreeTypeDouble.getMaxEcts()).thenReturn(_maxOfEctsDouble);
        when(_quantityOfSemestersDouble.getQuantityOfSemesters()).thenReturn(numberOfSemesters);
        when(_maxOfEctsDouble.getMaxEcts()).thenReturn(numberOfEcts);

        //Act + Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.registerProgramme(_programmeVOsDTODouble));
    }

    @Test
    void shouldThrowExceptionWhenQuantityOfSemesterIsHigherThanWhatIsAllowed() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        DegreeType degreeTypeDouble = mock(DegreeType.class);
        int numberOfSemesters = 11;
        int numberOfEcts = 180;

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_degreeTypeService.getDegreeTypeById(_degreeTypeIDDouble)).thenReturn(Optional.ofNullable(degreeTypeDouble));
        when(degreeTypeDouble.getMaxEcts()).thenReturn(_maxOfEctsDouble);
        when(_quantityOfSemestersDouble.getQuantityOfSemesters()).thenReturn(numberOfSemesters);
        when(_maxOfEctsDouble.getMaxEcts()).thenReturn(numberOfEcts);

        //Act + Assert
        assertThrows(BusinessRuleViolationException.class, () -> service.registerProgramme(_programmeVOsDTODouble));
    }

    @Test
    void shouldChangeProgrammeDirector() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        //Act + Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(null, _programmeDirectorIDDouble));
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfTeacherIsNull() throws IllegalArgumentException {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        //Act + Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(_programmeIDDouble, null));
    }

    @Test
    void shouldFindProgrammeByDepartment() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        //Act
        List<ProgrammeID> result = service.findProgrammeByDepartment(null);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindProgrammeByDegreeTypeID() throws IllegalArgumentException {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));
        when(_programmeDouble.getAcronym()).thenReturn(_acronymDouble);
        when(_programmeDouble.hasThisProgrammeAcronym(_acronymDouble)).thenReturn(true);

        //Act
        Optional<Programme> result = service.getProgrammeByAcronym(_acronymDouble);

        //Assert
        assertTrue(result.isPresent());
    }


    @Test
    void shouldNotGetProgrammeByAcronym() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        //Act
        List<ProgrammeID> result = service.getAllProgrammeIDs();

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindAll() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        //Act
        Iterable<Programme> all = service.findAll();

        //Assert
        assertFalse(all.iterator().hasNext());
    }

    @Test
    void getProgrammeByIDFoundShouldReturnProgramme() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        ProgrammeID id = new ProgrammeID(_acronymDouble);

        when(_programmeDouble.identity()).thenReturn(id);
        when(_programmeRepositoryDouble.ofIdentity(id)).thenReturn(Optional.of(_programmeDouble));

        ProgrammeDTO expectedDTO = mock(ProgrammeDTO.class);
        when(_programmeAssemblerDouble.fromDomainToDTO(_programmeDouble)).thenReturn(expectedDTO);

        // Act
        Optional<ProgrammeDTO> result = service.getProgrammeByID(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedDTO, result.get());
    }

    @Test
    void getProgrammeByIDShouldReturnEmptyOptionalWhenProgrammeNotFound() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        ProgrammeID id = new ProgrammeID(_acronymDouble);

        when(_programme2Double.identity()).thenReturn(new ProgrammeID(_acronym2Double));
        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programme2Double));

        // Act
        Optional<ProgrammeDTO> result = service.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetProgrammeByID_EmptyRepository() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        ProgrammeID id = new ProgrammeID(_acronymDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of());

        // Act
        Optional<ProgrammeDTO> result = service.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeNotFound() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        when(_programmeRepositoryDouble.ofIdentity(_programmeIDDouble)).thenReturn(Optional.empty());

        //Act
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.changeProgrammeDirector(_programmeIDDouble, _programmeDirectorIDDouble);
        });

        //Assert
        assertEquals("Programme not found", exception.getMessage());
    }

    @Test
    void shouldReturnProgrammeWhenNameMatches() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Engenharia Informática");

        when(_programmeDouble.hasThisProgrammeName(name)).thenReturn(true);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));

        //Act
        Optional<Programme> result = service.getProgrammeByName(name);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(_programmeDouble, result.get());
    }

    @Test
    void shouldGetAllProgrammeIDDTOs(){
        createDoubles();
        ProgrammeIDDTO programmeIDDTO1 = mock(ProgrammeIDDTO.class);
        ProgrammeIDDTO programmeIDDTO2 = mock(ProgrammeIDDTO.class);
        when(_programmeDouble.getProgrammeID()).thenReturn(_programmeIDDouble);
        when(_programme2Double.getProgrammeID()).thenReturn(_programme2IDDouble);
        when(_programmeRepositoryDouble.findAll()).thenReturn(Arrays.asList(_programmeDouble, _programme2Double));
        when(_programmeAssemblerDouble.toDTO(_programmeIDDouble)).thenReturn(programmeIDDTO1);
        when(_programmeAssemblerDouble.toDTO(_programme2IDDouble)).thenReturn(programmeIDDTO2);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble,_programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        List<ProgrammeIDDTO> result = programmeService.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyListIfNoProgrammeIDInRepository(){
        createDoubles();
        ProgrammeIDDTO programmeIDDTO1 = mock(ProgrammeIDDTO.class);
        ProgrammeIDDTO programmeIDDTO2 = mock(ProgrammeIDDTO.class);
        when(_programmeDouble.getProgrammeID()).thenReturn(_programmeIDDouble);
        when(_programme2Double.getProgrammeID()).thenReturn(_programme2IDDouble);
        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of());
        when(_programmeAssemblerDouble.toDTO(_programmeIDDouble)).thenReturn(programmeIDDTO1);
        when(_programmeAssemblerDouble.toDTO(_programme2IDDouble)).thenReturn(programmeIDDTO2);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble,_programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        List<ProgrammeIDDTO> result = programmeService.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(0, result.size());
    }

    @Test
    void getProgrammeIDDTOsByDegreeTypeID_shouldReturnCorrectDTOs() {
        // Arrange
        createDoubles();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble,_programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        DegreeTypeID degreeTypeID = new DegreeTypeID("MEST");

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        when(programme1.hasThisDegreeTypeID(degreeTypeID)).thenReturn(true);
        when(programme2.hasThisDegreeTypeID(degreeTypeID)).thenReturn(false);

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Engenharia Informática");
        Acronym acronym = new Acronym("LEI");

        ProgrammeID programmeID = new ProgrammeID(acronym);
        when(programme1.getProgrammeID()).thenReturn(programmeID);

        ProgrammeIDDTO dto1 = new ProgrammeIDDTO("LEI");
        when(_programmeAssemblerDouble.toDTO(programmeID)).thenReturn(dto1);

        List<Programme> programmes = List.of(programme1, programme2);
        when(_programmeRepositoryDouble.findAll()).thenReturn(programmes);

        // Act
        List<ProgrammeIDDTO> result = programmeService.getProgrammeIDDTOsByDegreeTypeID(degreeTypeID);

        // Assert
        assertEquals(1, result.size());
        assertEquals("LEI", result.get(0).acronym());
    }

    @Test
    void getProgrammeIDDTOsByDegreeTypeID_shouldReturnEmptyListIfNoMatches() {
        // Arrange
        createDoubles();
        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble,_programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);
        DegreeTypeID degreeTypeID = new DegreeTypeID("MEST");

        Programme programme1 = mock(Programme.class);
        when(programme1.hasThisDegreeTypeID(degreeTypeID)).thenReturn(false);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(programme1));

        // Act
        List<ProgrammeIDDTO> result = programmeService.getProgrammeIDDTOsByDegreeTypeID(degreeTypeID);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_returnProgrammeID_ifProgrammeIsFound(){
        // arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        ProgrammeID id = new ProgrammeID(_acronymDouble);
        ProgrammeDTO programmeDTODouble = mock(ProgrammeDTO.class);

        when(_programmeRepositoryDouble.ofIdentity(id)).thenReturn(Optional.of(_programmeDouble));
        when(_programmeAssemblerDouble.fromDomainToDTO(_programmeDouble)).thenReturn(programmeDTODouble);

        // act
        ProgrammeDTO result = service.getProgrammeDTOByID(id);

        // assert
        assertEquals(programmeDTODouble, result);
    }

    @Test
    void should_ThrowExceptionWhenProgrammeIDIsNull(){

        // arrange
        createDoubles();
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> service.getProgrammeDTOByID(null), "ProgrammeID cannot be null");
    }

    @Test
    void should_ThrowExceptionWhenProgrammeDoesNotExist(){

        // arrange
        createDoubles();
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        when(_programmeRepositoryDouble.ofIdentity(_programmeIDDouble)).thenReturn(Optional.empty());

        // act + assert
        assertThrows(NotFoundException.class, () -> service.getProgrammeDTOByID(_programmeIDDouble), "The Programme with ID" + _programmeIDDouble + " was not found");
    }

    @Test
    void should_ThrowExceptionWhenProgrammeNotFoundByID(){

        // arrange
        createDoubles();
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(DegreeTypeRegistrationServiceImpl.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble,degreeTypeRegistrationService, _departmentServiceDouble, _teacherServiceDouble);

        ProgrammeID id = new ProgrammeID(_acronymDouble);

        when(_programmeRepositoryDouble.ofIdentity(id)).thenReturn(Optional.empty());

        // act + assert
        assertThrows(NotFoundException.class, () -> service.getProgrammeDTOByID(id), "Programme not found");
    }

    @Test
    void should_ThrowExceptionWhenProgrammeNotFoundByIDWithMessage() {
        // arrange
        createDoubles();
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(DegreeTypeRegistrationServiceImpl.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble,degreeTypeRegistrationService, _departmentServiceDouble, _teacherServiceDouble);

        ProgrammeID id = new ProgrammeID(_acronymDouble);

        when(_programmeRepositoryDouble.ofIdentity(id)).thenReturn(Optional.empty());

        // act + assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.getProgrammeDTOByID(id));
        assertEquals("The Programme with ID " + id + " was not found", exception.getMessage());
    }

    @Test
    void should_ThrowExceptionWhenRepositoryIsEmpty() {
        // arrange
        createDoubles();
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(DegreeTypeRegistrationServiceImpl.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble,degreeTypeRegistrationService, _departmentServiceDouble, _teacherServiceDouble);

        ProgrammeID id = new ProgrammeID(_acronymDouble);

        when(_programmeRepositoryDouble.ofIdentity(id)).thenReturn(Optional.empty());

        // act + assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.getProgrammeDTOByID(id));
        assertEquals("The Programme with ID " + id + " was not found", exception.getMessage());
    }

    @Test
    void should_ThrowExceptionWhenRepositoryIsNullOnServiceCreation() {
        // arrange
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeAssembler assembler = mock(IProgrammeAssembler.class);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(DegreeTypeRegistrationServiceImpl.class);

        // act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeServiceImpl(factory, null, assembler,degreeTypeRegistrationService, _departmentServiceDouble, _teacherServiceDouble)
        );
        assertEquals("Programme Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnListOfProgrammes () {
        // Arrange
        createDoubles();
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepository repository = mock(IProgrammeRepository.class);
        IProgrammeAssembler assembler = mock(IProgrammeAssembler.class);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(DegreeTypeRegistrationServiceImpl.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(factory, repository, assembler, degreeTypeRegistrationService, _departmentServiceDouble, _teacherServiceDouble);

        Programme prog1 = mock(Programme.class);
        Programme prog2 = mock(Programme.class);
        Programme prog3 = mock(Programme.class);
        List<Programme> programmes = List.of(prog1, prog2, prog3);
        when(repository.findAll()).thenReturn(programmes);

        ProgrammeDTO progDTO1 = mock(ProgrammeDTO.class);
        ProgrammeDTO progDTO2 = mock(ProgrammeDTO.class);
        ProgrammeDTO progDTO3 = mock(ProgrammeDTO.class);

        when(assembler.fromDomainToDTO(prog1)).thenReturn(progDTO1);
        when(assembler.fromDomainToDTO(prog2)).thenReturn(progDTO2);
        when(assembler.fromDomainToDTO(prog3)).thenReturn(progDTO3);

        // Act
        Iterable<ProgrammeDTO> result = service.getAllProgrammes();

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        List<ProgrammeDTO> listResult = new ArrayList<>();
        result.forEach(listResult::add);
        assertEquals(3, listResult.size());
        assertTrue(listResult.contains(progDTO1));
        assertTrue(listResult.contains(progDTO2));
        assertTrue(listResult.contains(progDTO3));
    }

    @Test
    void shouldReturnEmptyListOfProgrammesIfThereAreNoProgrammes () {
        // Arrange
        createDoubles();
        IProgrammeFactory factory = mock(IProgrammeFactory.class);
        IProgrammeRepository repository = mock(IProgrammeRepository.class);
        IProgrammeAssembler assembler = mock(IProgrammeAssembler.class);
        IDegreeTypeRegistrationService degreeTypeRegistrationService = mock(DegreeTypeRegistrationServiceImpl.class);

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(factory, repository, assembler, degreeTypeRegistrationService, _departmentServiceDouble, _teacherServiceDouble);

        List<Programme> programmes = List.of();
        when(repository.findAll()).thenReturn(programmes);

        // Act
        Iterable<ProgrammeDTO> result = service.getAllProgrammes();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
        List<ProgrammeDTO> listResult = new ArrayList<>();
        result.forEach(listResult::add);
        assertEquals(0, listResult.size());
    }

    @Test
    public void testGetProgrammesByProgrammeIDs_ReturnsMatchingProgrammes() {
        //arrange
        createDoubles();

        when(_programmeRepositoryDouble.ofIdentity(_programmeIDDouble)).thenReturn(Optional.of(_programmeDouble));
        when(_programmeRepositoryDouble.ofIdentity(_programme2IDDouble)).thenReturn(Optional.of(_programme2Double));

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(
                _programmeFactoryDouble,
                _programmeRepositoryDouble,
                _programmeAssemblerDouble,
                _degreeTypeService,
                _departmentServiceDouble,
                _teacherServiceDouble
        );

        List<ProgrammeID> idList = Arrays.asList(_programmeIDDouble, _programme2IDDouble);

        // Act
        List<Programme> result = service.getProgrammesByProgrammeIDs(idList);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(_programmeDouble));
        assertTrue(result.contains(_programme2Double));
    }

    @Test
    void shouldReturnTrueWhenContainsIdentity(){
        //Arrange
        createDoubles();
        IProgrammeService programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(true);

        //Act
        boolean result = programmeService.containsOfIdentity(_programmeIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDoesntContainsIdentity(){
        //Arrange
        createDoubles();
        IProgrammeService programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);

        //Act
        boolean result = programmeService.containsOfIdentity(_programmeIDDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionIfNullParameterForContainsOfIdentity(){
        //Arrange
        createDoubles();
        IProgrammeService programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _programmeAssemblerDouble, _degreeTypeService, _departmentServiceDouble, _teacherServiceDouble);

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> programmeService.containsOfIdentity(null));
    }
}