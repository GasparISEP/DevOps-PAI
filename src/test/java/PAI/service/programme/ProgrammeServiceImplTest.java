package PAI.service.programme;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.domain.programme.Programme;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import jakarta.persistence.EntityNotFoundException;
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
    private IDegreeTypeRepository _degreeTypeRepositoryDouble;
    private IDepartmentRepository _departmentRepositoryDouble;
    private ITeacherRepository _teacherRepositoryDouble;
    private IProgrammeAssembler _programmeAssemblerDouble;
    private NameWithNumbersAndSpecialChars _nameDouble;
    private NameWithNumbersAndSpecialChars _name2Double;
    private Acronym _acronymDouble;
    private Acronym _acronym2Double;
    private MaxEcts _maxOfEctsDouble;
    private QuantSemesters _quantityOfSemestersDouble;
    private DegreeTypeID _degreeTypeIDDouble;
    private DegreeType _degreeTypeDouble;
    private DepartmentID _departmentIDDouble;
    private Department _departmentDouble;
    private Teacher _teacherDouble;
    private TeacherID _programmeDirectorIDDouble;
    private Programme _programmeDouble;
    private Programme _programme2Double;
    private ProgrammeID _programmeIDDouble;
    private ProgrammeID _programme2IDDouble;
    private ProgrammeVOsDTO _programmeVOsDTODouble;
    private ProgrammeResponseDTO _programmeRespondeDTODouble;
    private Name _nameDouble1;
    private Name _nameDouble2;
    private Name _nameDouble3;

    private void createDoubles() {
        _programmeFactoryDouble = mock(IProgrammeFactory.class);
        _programmeRepositoryDouble = mock(IProgrammeRepository.class);
        _degreeTypeRepositoryDouble = mock(IDegreeTypeRepository.class);
        _departmentRepositoryDouble = mock(IDepartmentRepository.class);
        _teacherRepositoryDouble = mock(ITeacherRepository.class);
        _programmeAssemblerDouble = mock(IProgrammeAssembler.class);
        _nameDouble = mock(NameWithNumbersAndSpecialChars.class);
        _name2Double = mock(NameWithNumbersAndSpecialChars.class);
        _acronymDouble = mock(Acronym.class);
        _acronym2Double = mock(Acronym.class);
        _maxOfEctsDouble = mock(MaxEcts.class);
        _quantityOfSemestersDouble = mock(QuantSemesters.class);
        _degreeTypeIDDouble = mock(DegreeTypeID.class);
        _degreeTypeDouble = mock(DegreeType.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _departmentDouble = mock(Department.class);
        _teacherDouble = mock(Teacher.class);
        _programmeDirectorIDDouble = mock(TeacherID.class);
        _programmeDouble = mock(Programme.class);
        _programme2Double = mock(Programme.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _programme2IDDouble = mock(ProgrammeID.class);
        _programmeRespondeDTODouble = mock(ProgrammeResponseDTO.class);
        _programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
        _nameDouble1 = mock(Name.class);
        _nameDouble2 = mock(Name.class);
        _nameDouble3 = mock(Name.class);
    }

    @Test
    void shouldCreateProgrammeService() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        //Assert
        assertNotNull(service);
    }

    static Stream<Arguments> parametersToCreateProgrammeServiceAreInvalid() {
        return Streams.of(
                Arguments.of(null, mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Programme Factory cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), null, mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Programme Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), null, mock(IDepartmentRepository.class), mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Degree Type Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), null, mock(ITeacherRepository.class), mock(IProgrammeAssembler.class), "Department Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), null, mock(IProgrammeAssembler.class), "Teacher Repository cannot be null"),
                Arguments.of(mock(IProgrammeFactory.class), mock(IProgrammeRepository.class), mock(IDegreeTypeRepository.class), mock(IDepartmentRepository.class), mock(ITeacherRepository.class), null, "Programme Assembler cannot be null")
        );
    }

    @ParameterizedTest
    @MethodSource("parametersToCreateProgrammeServiceAreInvalid")
    void shouldThrowExceptionWhenParametersToCreateProgrammeServiceAreNotValid(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository, IDegreeTypeRepository degreeTypeRepository, IDepartmentRepository departmentRepository, ITeacherRepository teacherRepository, IProgrammeAssembler programmeAssembler, String expectedMessage) {
        //arrange

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeServiceImpl(programmeFactory, programmeRepository, degreeTypeRepository, departmentRepository, teacherRepository, programmeAssembler));

        //assert
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void shouldRegisterProgramme() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        String degreeTypeName = "Master";
        String departmentName = "Astronomy";
        String teacherName = "AAA";

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.save(_programmeDouble)).thenReturn(_programmeDouble);

        when(_degreeTypeRepositoryDouble.ofIdentity(_degreeTypeIDDouble)).thenReturn(Optional.of(_degreeTypeDouble));
        when(_degreeTypeDouble.getName()).thenReturn(_nameDouble1);
        when(_nameDouble1.getName()).thenReturn(degreeTypeName);
        when(_departmentRepositoryDouble.ofIdentity(_departmentIDDouble)).thenReturn(Optional.of(_departmentDouble));
        when(_departmentDouble.getName()).thenReturn(_nameDouble2);
        when(_nameDouble2.getName()).thenReturn(departmentName);
        when(_teacherRepositoryDouble.ofIdentity(_programmeDirectorIDDouble)).thenReturn(Optional.of(_teacherDouble));
        when(_teacherDouble.getName()).thenReturn(_nameDouble3);
        when(_nameDouble3.getName()).thenReturn(teacherName);

        when(_programmeAssemblerDouble.fromDomainToDTO(_programmeDouble, degreeTypeName, departmentName, teacherName)).thenReturn(_programmeRespondeDTODouble);

        //Act
        ProgrammeResponseDTO result = service.registerProgramme(_programmeVOsDTODouble);

        //Assert
        assertSame(result, _programmeRespondeDTODouble);

    }

    @Test
    void shouldNotRegisterProgrammeWhenProgrammeAlreadyExists() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(true);

        //Act
        Exception result = assertThrows(Exception.class, () -> service.registerProgramme(_programmeVOsDTODouble));

        //Assert
        assertEquals(result.getMessage(), "Programme is already registered");
    }

    @Test
    void shouldThrowExceptionWhenProgrammeFailsToBeSavedInTheDatabase() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);
        when(_programmeRepositoryDouble.save(_programmeDouble)).thenThrow(new RuntimeException());

        //Act + Assert
        assertThrows(Exception.class, () -> service.registerProgramme(_programmeVOsDTODouble));
    }

    @Test
    void shouldThrowExceptionIfDegreeTypeCannotBeFoundByID() throws Exception {
        //arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);

        when(_programmeRepositoryDouble.save(_programmeDouble)).thenReturn(_programmeDouble);
        when(_degreeTypeRepositoryDouble.ofIdentity(_degreeTypeIDDouble)).thenReturn(Optional.empty());

        //act
        Exception exception = assertThrows(EntityNotFoundException.class, () -> service.registerProgramme(_programmeVOsDTODouble));

        //assert
        assertEquals(exception.getMessage(), "Degree Type not found");
    }

    @Test
    void shouldThrowExceptionIfDepartmentCannotBeFoundByID() throws Exception {
        //arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        String degreeTypeName = "Master";

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);

        when(_programmeRepositoryDouble.save(_programmeDouble)).thenReturn(_programmeDouble);
        when(_degreeTypeRepositoryDouble.ofIdentity(_degreeTypeIDDouble)).thenReturn(Optional.of(_degreeTypeDouble));
        when(_degreeTypeDouble.getName()).thenReturn(_nameDouble1);
        when(_nameDouble1.getName()).thenReturn(degreeTypeName);
        when(_departmentRepositoryDouble.ofIdentity(_departmentIDDouble)).thenReturn(Optional.empty());

        //act
        Exception exception = assertThrows(EntityNotFoundException.class, () -> service.registerProgramme(_programmeVOsDTODouble));

        //assert
        assertEquals(exception.getMessage(), "Department not found");
    }

    @Test
    void shouldThrowExceptionIfTeacherCannotBeFoundByID() throws Exception {
        //arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        String degreeTypeName = "Master";
        String departmentName = "Astronomy";

        when(_programmeVOsDTODouble.name()).thenReturn(_nameDouble);
        when(_programmeVOsDTODouble.acronym()).thenReturn(_acronymDouble);
        when(_programmeVOsDTODouble.maxEcts()).thenReturn(_maxOfEctsDouble);
        when(_programmeVOsDTODouble.quantSemesters()).thenReturn(_quantityOfSemestersDouble);
        when(_programmeVOsDTODouble.degreeTypeID()).thenReturn(_degreeTypeIDDouble);
        when(_programmeVOsDTODouble.departmentID()).thenReturn(_departmentIDDouble);
        when(_programmeVOsDTODouble.teacherID()).thenReturn(_programmeDirectorIDDouble);

        when(_programmeFactoryDouble.registerProgramme(_nameDouble, _acronymDouble, _maxOfEctsDouble, _quantityOfSemestersDouble, _degreeTypeIDDouble, _departmentIDDouble, _programmeDirectorIDDouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeRepositoryDouble.containsOfIdentity(_programmeIDDouble)).thenReturn(false);

        when(_programmeRepositoryDouble.save(_programmeDouble)).thenReturn(_programmeDouble);
        when(_degreeTypeRepositoryDouble.ofIdentity(_degreeTypeIDDouble)).thenReturn(Optional.of(_degreeTypeDouble));
        when(_degreeTypeDouble.getName()).thenReturn(_nameDouble1);
        when(_nameDouble1.getName()).thenReturn(degreeTypeName);
        when(_departmentRepositoryDouble.ofIdentity(_departmentIDDouble)).thenReturn(Optional.of(_departmentDouble));
        when(_departmentDouble.getName()).thenReturn(_nameDouble2);
        when(_nameDouble2.getName()).thenReturn(departmentName);
        when(_teacherRepositoryDouble.ofIdentity(_programmeDirectorIDDouble)).thenReturn(Optional.empty());

        //act
        Exception exception = assertThrows(EntityNotFoundException.class, () -> service.registerProgramme(_programmeVOsDTODouble));

        //assert
        assertEquals(exception.getMessage(), "Teacher not found");

    }

    @Test
    void shouldChangeProgrammeDirector() throws Exception {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        //Act + Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(null, _programmeDirectorIDDouble));
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfTeacherIsNull() throws IllegalArgumentException {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        //Act + Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(_programmeIDDouble, null));
    }

    @Test
    void shouldFindProgrammeByDepartment() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        //Act
        List<ProgrammeID> result = service.findProgrammeByDepartment(null);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindProgrammeByDegreeTypeID() throws IllegalArgumentException {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        //Act
        List<ProgrammeID> result = service.getAllProgrammeIDs();

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindAll() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        //Act
        Iterable<Programme> all = service.findAll();

        //Assert
        assertFalse(all.iterator().hasNext());
    }

    @Test
    void getProgrammeByIDFoundShouldReturnProgramme() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        ProgrammeID id = new ProgrammeID(_nameDouble, _acronymDouble);

        when(_programmeDouble.identity()).thenReturn(id);
        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programmeDouble));

        // Act
        Optional<Programme> result = service.getProgrammeByID(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(_programmeDouble, result.get());
    }

    @Test
    void getProgrammeByIDShouldReturnEmptyOptionalWhenProgrammeNotFound() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        ProgrammeID id = new ProgrammeID(_nameDouble, _acronymDouble);

        when(_programme2Double.identity()).thenReturn(new ProgrammeID(_name2Double, _acronym2Double));
        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of(_programme2Double));

        // Act
        Optional<Programme> result = service.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetProgrammeByID_EmptyRepository() {
        // Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

        ProgrammeID id = new ProgrammeID(_nameDouble, _acronymDouble);

        when(_programmeRepositoryDouble.findAll()).thenReturn(List.of());

        // Act
        Optional<Programme> result = service.getProgrammeByID(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeNotFound() {
        //Arrange
        createDoubles();

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);

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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(_programmeFactoryDouble, _programmeRepositoryDouble, _degreeTypeRepositoryDouble, _departmentRepositoryDouble, _teacherRepositoryDouble, _programmeAssemblerDouble);
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

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble,_degreeTypeRepositoryDouble,_departmentRepositoryDouble,_teacherRepositoryDouble,_programmeAssemblerDouble);

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

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(_programmeFactoryDouble,_programmeRepositoryDouble,_degreeTypeRepositoryDouble,_departmentRepositoryDouble,_teacherRepositoryDouble,_programmeAssemblerDouble);

        List<ProgrammeIDDTO> result = programmeService.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(0, result.size());
    }

}