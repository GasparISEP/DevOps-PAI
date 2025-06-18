package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.assembler.programme.IProgrammeHATEOASAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.assembler.programmeEnrolment.IUS34ProgrammeEnrolmentAssembler;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.assembler.studyPlan.IStudyPlanHATEOASAssembler;
import PAI.assembler.studyPlan.StudyPlanHATEOASAssemblerImpl;
import PAI.domain.programme.Programme;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.Programme.*;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentListIDDTO;
import PAI.dto.programmeEnrolment.US34ListOfProgrammesDTO;
import PAI.dto.student.StudentIDDTO;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import PAI.dto.teacher.TeacherIdDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.ErrorResponse;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.programmeEnrolment.ProgrammeEnrolmentServiceImpl;
import PAI.service.studyPlan.IStudyPlanService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ProgrammeRestControllerTest {

    private IProgrammeService _programmeServiceDouble;
    private IProgrammeAssembler _programmeAssemblerDouble;
    private IProgrammeEnrolmentService _programmeEnrolmentService;
    private IStudyPlanService _studyPlanServiceDouble;
    private IStudyPlanAssembler _studyPlanAssemblerDouble;
    private ProgrammeDTO _programmeDTODouble;
    private ProgrammeID _programmeIDDouble;
    private ProgrammeIDDTO _programmeIDDTODouble;
    private ProgrammeVOsDTO _programmeVOsDTODouble;
    private RegisterStudyPlanCommand _studyPlanCommandDouble;
    private StudyPlanDTO _studyPlanDTODouble;
    private StudyPlanResponseDTO _studyPlanResponseDTODouble;
    private ProgrammeEnrolmentListIDDTO _programmeEnrolmentIDDTO;
    private IProgrammeDirectorAssembler _programmeDirectorAssemblerDouble;
    private Programme _programmeDouble;
    private IProgrammeHATEOASAssembler _programmeHATEOASAssembler;
    private EntityModel _programmeEntityModelDouble;
    private IProgrammeEnrolmentAssembler _programmeEnrolmentAssembler;
    private IStudentDTOAssembler _studentAssembler;
    private IUS34ProgrammeEnrolmentAssembler _us34Assembler;
    private IStudyPlanHATEOASAssembler _studyPlanHateoasAssembler;
    private ProgrammeRestController _programmeRestController;


    @BeforeEach
    void setup() {
        _programmeServiceDouble = mock(IProgrammeService.class);
        _programmeAssemblerDouble = mock(ProgrammeAssembler.class);
        _programmeEnrolmentService = mock(ProgrammeEnrolmentServiceImpl.class);
        _studyPlanServiceDouble = mock(IStudyPlanService.class);
        _studyPlanAssemblerDouble = mock(IStudyPlanAssembler.class);
        _programmeDirectorAssemblerDouble = mock(IProgrammeDirectorAssembler.class);
        _programmeHATEOASAssembler = mock(IProgrammeHATEOASAssembler.class);
        _programmeEnrolmentAssembler = mock(IProgrammeEnrolmentAssembler.class);
        _studentAssembler = mock(IStudentDTOAssembler.class);
        _us34Assembler = mock(IUS34ProgrammeEnrolmentAssembler.class);
        _studyPlanHateoasAssembler = mock(StudyPlanHATEOASAssemblerImpl.class);

        createProgrammeRestController();
    }

    private void createProgrammeDoubles() {
        _programmeDTODouble = mock(ProgrammeDTO.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _programmeIDDTODouble = mock(ProgrammeIDDTO.class);
        _programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
        _programmeDouble = mock(Programme.class);
        _programmeEntityModelDouble = mock(EntityModel.class);
    }

    private void createStudyPlanDoubles() {
        _studyPlanCommandDouble = mock(RegisterStudyPlanCommand.class);
        _studyPlanDTODouble = mock(StudyPlanDTO.class);
        _studyPlanResponseDTODouble = mock(StudyPlanResponseDTO.class);
    }

    private void createProgrammeEnrolmentDoubles() {
        _programmeEnrolmentIDDTO = mock(ProgrammeEnrolmentListIDDTO.class);
    }

    private void createProgrammeRestController() {

        this._programmeRestController = new ProgrammeRestController(
                _programmeServiceDouble,
                _programmeAssemblerDouble,
                _programmeEnrolmentService,
                _studyPlanServiceDouble,
                _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble,
                _programmeHATEOASAssembler,
                _programmeEnrolmentAssembler,
                _studentAssembler,
                _us34Assembler,
                _studyPlanHateoasAssembler);
    }

    @Test
    void shouldCreateController() {
        //Arrange

        //Act
        new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler, _programmeEnrolmentAssembler, _studentAssembler, _us34Assembler, _studyPlanHateoasAssembler);

        //Assert
    }

    @Test
    void shouldSendEntityModel() throws Exception {
        //Arrange
        createProgrammeDoubles();

        when(_programmeAssemblerDouble.fromDTOToDomain(_programmeDTODouble)).thenReturn(_programmeVOsDTODouble);
        when(_programmeServiceDouble.registerProgramme(_programmeVOsDTODouble)).thenReturn(_programmeDouble);
        when(_programmeDouble.identity()).thenReturn(_programmeIDDouble);
        when(_programmeAssemblerDouble.toDTO(_programmeIDDouble)).thenReturn(_programmeIDDTODouble);
        when(_programmeHATEOASAssembler.toModel(_programmeIDDTODouble)).thenReturn(_programmeEntityModelDouble);

        //Act
        ResponseEntity<?> result = _programmeRestController.registerProgramme(_programmeDTODouble);

        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(_programmeEntityModelDouble, result.getBody());
    }

    @Test
    void shouldReturnCreatedResponseAndStudyPlanResponseDTOWhenStudyPlanIsRegisteredSuccessfully() throws Exception {
        // Arrange
        createStudyPlanDoubles();

        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);
        EntityModel<StudyPlanResponseDTO> _studyPlanEntityModel = mock(EntityModel.class);

        when(_studyPlanAssemblerDouble.toCommand(programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenReturn(_studyPlanDTODouble);
        when(_studyPlanAssemblerDouble.toResponseDTO(_studyPlanDTODouble)).thenReturn(_studyPlanResponseDTODouble);
        when(_studyPlanHateoasAssembler.toModel(_studyPlanResponseDTODouble)).thenReturn(_studyPlanEntityModel);

        // Act
        EntityModel<StudyPlanResponseDTO> studyPlanEntityModel = _studyPlanEntityModel;

        ResponseEntity<EntityModel<StudyPlanResponseDTO>> response =
                (ResponseEntity<EntityModel<StudyPlanResponseDTO>>) _programmeRestController.registerStudyPlan(programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(studyPlanEntityModel, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenStudyPlanIsRegisteredInAProgrammeThatDoesNotExist() throws Exception {
        // Arrange
        createStudyPlanDoubles();
        String programmeName = "Biology";
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new EntityNotFoundException(
                                        "Programme with name " + programmeName + " and acronym " + programmeAcronym));

        // Act
        ResponseEntity<?> response = _programmeRestController.registerStudyPlan(programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenStudyPlanIsRegisteredInAProgrammeThatDoesNotHaveValidDegreeType() throws Exception {
        // Arrange
        createStudyPlanDoubles();
        String programmeName = "Biology";
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new EntityNotFoundException("Degree type does not exist"));

        // Act
        ResponseEntity<?> response = _programmeRestController.registerStudyPlan(programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnConflictIfRegisteredStudyPlanAlreadyExists() throws Exception {
        // Arrange
        createStudyPlanDoubles();
        String programmeName = "Biology";
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new BusinessRuleViolationException("Study plan already exists"));

        // Act
        ResponseEntity<?> response = _programmeRestController.registerStudyPlan(programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    static Stream<Arguments> NullOrEmptyNameValue(){
        return Stream.of(
               Arguments.of(""),
               Arguments.of(" "),
               Arguments.of((Object) null)
        );
    }
    @ParameterizedTest
    @MethodSource("NullOrEmptyNameValue")
    void shouldReturnBadRequestIfAParameterIsEmpty(String programmeName) throws Exception {
        // Arrange
        createStudyPlanDoubles();
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new IllegalArgumentException("Name cannot be null or empty"));

        // Act
        ResponseEntity<?> response = _programmeRestController.registerStudyPlan(programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnListOfProgrammeIDSDTOs() {
        //Arrange
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);
        ProgrammeIDDTO programmeIDDTO2 = mock(ProgrammeIDDTO.class);
        ProgrammeIDResponseDTO resultProgrammeIdResponseDTO = mock(ProgrammeIDResponseDTO.class);
        ProgrammeIDResponseDTO resultProgrammeIdResponseDTO2 = mock(ProgrammeIDResponseDTO.class);
        when(_programmeServiceDouble.getAllProgrammeIDDTOs()).thenReturn(List.of(programmeIDDTO, programmeIDDTO2));
        when(_programmeAssemblerDouble.toResponseDTO(programmeIDDTO)).thenReturn(resultProgrammeIdResponseDTO);
        when(_programmeAssemblerDouble.toResponseDTO(programmeIDDTO2)).thenReturn(resultProgrammeIdResponseDTO2);
        //Act
        ResponseEntity<List<ProgrammeIDResponseDTO>> result = _programmeRestController.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
    }

    @Test
    void shouldReturnNotFoundIfNoProgrammeIDSDTOs() {
        //Arrange
        when(_programmeServiceDouble.getAllProgrammeIDDTOs()).thenReturn(List.of());
        //Act
        ResponseEntity<List<ProgrammeIDResponseDTO>> result = _programmeRestController.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void getProgrammeDirector_ShouldReturnOkWithTeacherIDDTO_WhenDirectorExists() {
        TeacherID director = new TeacherID(new TeacherAcronym("AAA"));
        when(_programmeServiceDouble.getProgrammeDirectorByProgrammeID(any(ProgrammeID.class)))
                .thenReturn(Optional.of(director));

        ResponseEntity<?> response = _programmeRestController.getProgrammeDirector("AAA");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof TeacherIdDTO);

        TeacherIdDTO dto = (TeacherIdDTO) response.getBody();
        assertEquals("AAA", dto.acronym());

        verify(_programmeServiceDouble).getProgrammeDirectorByProgrammeID(any());
    }

    @Test
    void getProgrammeDirector_ShouldReturnNotFound_WhenDirectorDoesNotExist() {
        when(_programmeServiceDouble.getProgrammeDirectorByProgrammeID(any(ProgrammeID.class)))
                .thenReturn(Optional.empty());

        ResponseEntity<?> response = _programmeRestController.getProgrammeDirector("WWW");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Programme director not found", response.getBody());

        verify(_programmeServiceDouble).getProgrammeDirectorByProgrammeID(any());
    }

    @Test
    void getProgrammeDirector_ShouldReturnBadRequest_WhenExceptionIsThrown() {
        when(_programmeServiceDouble.getProgrammeDirectorByProgrammeID(any(ProgrammeID.class)))
                .thenThrow(new IllegalArgumentException());

        ResponseEntity<?> response = _programmeRestController.getProgrammeDirector("WWW");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid programme ID", response.getBody());

        verify(_programmeServiceDouble).getProgrammeDirectorByProgrammeID(any());
    }

    @Test
    void assignProgrammeDirector_ShouldReturnNoContent_WhenSuccessful() throws Exception {
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(new TeacherIdDTO("AAA"));

        ResponseEntity<Void> response = _programmeRestController.assignProgrammeDirector("ABB", dto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(_programmeServiceDouble).changeProgrammeDirector(any(ProgrammeID.class), any(TeacherID.class));
    }

    @Test
    void assignProgrammeDirector_ShouldReturnBadRequest_WhenExceptionIsThrown() throws Exception {
        ProgrammeDirectorRequestDTO dto = new ProgrammeDirectorRequestDTO(new TeacherIdDTO("AAA"));

        doThrow(new RuntimeException()).when(_programmeServiceDouble).changeProgrammeDirector(any(), any());

        ResponseEntity<Void> response = _programmeRestController.assignProgrammeDirector("WWW", dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(_programmeServiceDouble).changeProgrammeDirector(any(), any());
    }


    @Test
    void shouldReturnProgrammesByDegreeTypeID() {
        // Arrange
        createProgrammeDoubles();
        String degreeTypeIdStr = "123";

        ProgrammeIDDTO dto = new ProgrammeIDDTO("Acr");

        when(_programmeServiceDouble.getProgrammeIDDTOsByDegreeTypeID(any(DegreeTypeID.class)))
                .thenReturn(List.of(dto));

        // Act
        ResponseEntity<List<ProgrammeIDDTO>> response = _programmeRestController.getProgrammesByDegreeTypeID(degreeTypeIdStr);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Acr", response.getBody().get(0).acronym());
    }

    @Test
    void shouldReturnNotFoundWhenNoProgrammesFound()  {
        // Arrange
        createProgrammeDoubles();
        String degreeTypeIdStr = "123";

        when(_programmeServiceDouble.getProgrammeIDDTOsByDegreeTypeID(any(DegreeTypeID.class)))
                .thenReturn(List.of());

        // Act
        ResponseEntity<List<ProgrammeIDDTO>> response = _programmeRestController.getProgrammesByDegreeTypeID(degreeTypeIdStr);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenExceptionIsThrown()  {
        // Arrange
        createProgrammeDoubles();
        String degreeTypeIdStr = "invalid-id";

        when(_programmeServiceDouble.getProgrammeIDDTOsByDegreeTypeID(any(DegreeTypeID.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<List<ProgrammeIDDTO>> response = _programmeRestController.getProgrammesByDegreeTypeID(degreeTypeIdStr);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturn200WhenProgrammeIsFoundById() {
        // Arrange
        createProgrammeDoubles();
        String acronym = "CSD";

        ProgrammeID doubleProgrammeID = mock(ProgrammeID.class);
        when(doubleProgrammeID.getProgrammeAcronym()).thenReturn(acronym);

        ProgrammeDTO programmeDTOMock = mock(ProgrammeDTO.class);

        when(_programmeServiceDouble.getProgrammeByID(any(ProgrammeID.class)))
                .thenReturn(Optional.of(programmeDTOMock));

        // Act
        ResponseEntity<Object> result = _programmeRestController.getProgrammeByID(acronym);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(programmeDTOMock, result.getBody());
    }

    @Test
    void shouldReturn404WhenProgrammeIsNotFoundById() {
        // arrange
        String acronym = "XYZ";

        when(_programmeServiceDouble.getProgrammeByID(any(ProgrammeID.class)))
                .thenReturn(Optional.empty());

        // act
        ResponseEntity<Object> result = _programmeRestController.getProgrammeByID(acronym);

        // assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void shouldReturn400WhenNameIsInvalid() {
        // arrange
        String invalidName = "";
        String acronym = "CSD";

        // act/assert
        assertThrows(IllegalArgumentException.class, () -> {
            new NameWithNumbersAndSpecialChars(invalidName);
        });
    }

    @Test
    void shouldReturnAListOfProgrammes () {
        // Arrange
        ProgrammeDTO dto1 = mock(ProgrammeDTO.class);
        ProgrammeDTO dto2 = mock(ProgrammeDTO.class);
        ProgrammeDTO dto3 = mock(ProgrammeDTO.class);
        List<ProgrammeDTO> progDTOS = List.of(dto1, dto2, dto3);
        when(_programmeServiceDouble.getAllProgrammes()).thenReturn(progDTOS);

        // Act
        ResponseEntity<?> response = _programmeRestController.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(progDTOS, response.getBody());
    }

    @Test
    void shouldReturnAnEmptyListOfProgrammesIfThereAreNoSchoolYearsInTheSystem () {
        //Arrange
        List<ProgrammeDTO> progDTOS = List.of();
        when(_programmeServiceDouble.getAllProgrammes()).thenReturn(progDTOS);

        // Act
        ResponseEntity<?> response = _programmeRestController.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(progDTOS, response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrown() {
        // Arrange
        String errorMessage = "Invalid input data";

        when(_programmeServiceDouble.getAllProgrammes()).thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = _programmeRestController.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionIsThrown() {
        // Arrange

        when(_programmeServiceDouble.getAllProgrammes()).thenThrow(new RuntimeException("Database is down"));

        // Act
        ResponseEntity<?> response = _programmeRestController.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnProgrammesStudentIsEnrolledIn() {
        //arrange
        String studentIdStr = "1234567";
        StudentID studentID = mock(StudentID.class);
        US34ListOfProgrammes domainObject = mock(US34ListOfProgrammes.class);
        US34ListOfProgrammesDTO dto = mock(US34ListOfProgrammesDTO.class);

        when(studentID.getUniqueNumber()).thenReturn(1234567);

        when(_studentAssembler.toIdDTO(any(StudentIDDTO.class))).thenReturn(studentID);
        when(_programmeEnrolmentService.getProgrammesStudentIsEnrolled(studentID)).thenReturn(domainObject);
        when(_us34Assembler.toDto(domainObject)).thenReturn(dto);

        //act
        ResponseEntity<US34ListOfProgrammesDTO> response =
                _programmeRestController.getAllProgrammesThatTheStudentIsEnrolledIn(studentIdStr);

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenStudentIDisInvalid() {
        //arrange
        String invalidStudentIdStr = "invalid";

        when(_studentAssembler.toIdDTO(any(StudentIDDTO.class)))
                .thenThrow(new IllegalArgumentException("Invalid ID format"));

        //act
        ResponseEntity<US34ListOfProgrammesDTO> response =
                _programmeRestController.getAllProgrammesThatTheStudentIsEnrolledIn(invalidStudentIdStr);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturnOKForStudyPlanByGeneratedId() {
        //Arrange
        UUID studyPlanGeneratedId = UUID.randomUUID();
        StudyPlanGeneratedID sPGeneratedId = new StudyPlanGeneratedID(studyPlanGeneratedId);
        StudyPlan expectedStudyPlan = mock(StudyPlan.class);

        when(_studyPlanServiceDouble.findByGeneratedUUID(sPGeneratedId)).thenReturn(Optional.of(expectedStudyPlan));
        when(_studyPlanAssemblerDouble.toDTO(expectedStudyPlan)).thenReturn(_studyPlanDTODouble);
        when(_studyPlanAssemblerDouble.toResponseDTO(_studyPlanDTODouble)).thenReturn(_studyPlanResponseDTODouble);

        //Act
        ResponseEntity<?> result = _programmeRestController.getStudyPlanByGeneratedID(studyPlanGeneratedId);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenFindingStudyPlanByGeneratedId() {
        //Arrange
        UUID studyPlanGeneratedId = UUID.randomUUID();
        StudyPlanGeneratedID sPGeneratedId = new StudyPlanGeneratedID(studyPlanGeneratedId);

        when(_studyPlanServiceDouble.findByGeneratedUUID(sPGeneratedId)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<?> result = _programmeRestController.getStudyPlanByGeneratedID(studyPlanGeneratedId);

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestWhenFindingStudyPlanByGeneratedId() {

    // Arrange
        UUID invalidUuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        when(_studyPlanServiceDouble.findByGeneratedUUID(any(StudyPlanGeneratedID.class)))
                .thenThrow(new IllegalArgumentException("invalid-UUID"));

    // Act
    ResponseEntity<?> result = _programmeRestController.getStudyPlanByGeneratedID(invalidUuid);

    // Assert
    assertNotNull(result);
    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
}

@Test
void getStudyPlansForProgramme_ShouldReturnOkWithEmptyList_WhenNoStudyPlansFound() {
    // Arrange
    String programmeIdString = "EMPTYPROG";
    Acronym mockAcronym = new Acronym(programmeIdString);
    ProgrammeID mockProgrammeID = new ProgrammeID(mockAcronym);

    when(_studyPlanServiceDouble.getStudyPlansByProgrammeID(mockProgrammeID)).thenReturn(Collections.emptyList());

    // Act
    ResponseEntity<?> result = _programmeRestController.getStudyPlansForProgramme(programmeIdString);

    // Assert
    assertNotNull(result);
    assertEquals(HttpStatus.OK, result.getStatusCode());
}

@Test
void getStudyPlansForProgramme_ShouldReturnNotFound_WhenServiceThrowsEntityNotFoundException() {
    // Arrange
    String programmeIdString = "NONEXIST";
    Acronym mockAcronym = new Acronym(programmeIdString);
    ProgrammeID mockProgrammeID = new ProgrammeID(mockAcronym);

    // Stub service to throw EntityNotFoundException
    when(_studyPlanServiceDouble.getStudyPlansByProgrammeID(mockProgrammeID))
            .thenThrow(new EntityNotFoundException("Programme with ID " + programmeIdString + " not found."));

    // Act
    ResponseEntity<?> result = _programmeRestController.getStudyPlansForProgramme(programmeIdString);

    // Assert
    assertNotNull(result);
    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
}

@Test
void getStudyPlansForProgramme_ShouldReturnConflict_WhenServiceThrowsBusinessRuleViolationException() {
    // Arrange
    String programmeIdString = "CONFLICTID";
    Acronym mockAcronym = new Acronym(programmeIdString);
    ProgrammeID mockProgrammeID = new ProgrammeID(mockAcronym);

    when(_studyPlanServiceDouble.getStudyPlansByProgrammeID(mockProgrammeID))
            .thenThrow(new BusinessRuleViolationException("Business rule violated for programme: " + programmeIdString));

    // Act
    ResponseEntity<?> result = _programmeRestController.getStudyPlansForProgramme(programmeIdString);

    // Assert
    assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    assertEquals("Programme not found: Business rule violated for programme: " + programmeIdString, ((ErrorResponse) result.getBody()).getMessage());
}

@Test
void getStudyPlansForProgramme_ShouldReturnBadRequest_WhenAcronymConstructorException() {
    // Arrange
    String programmeIdString = "ACRO";

    when(_studyPlanServiceDouble.getStudyPlansByProgrammeID(any(ProgrammeID.class)))
            .thenThrow(new IllegalArgumentException("Invalid Acronym format: " + programmeIdString));

    // Act
    ResponseEntity<?> result = _programmeRestController.getStudyPlansForProgramme(programmeIdString);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    assertEquals("Error retrieving Study Plans: Invalid Acronym format: " + programmeIdString, ((ErrorResponse) result.getBody()).getMessage());
}


@Test
void getStudyPlansForProgramme_ShouldReturnBadRequest_WhenServiceThrowsGenericException() {
    // Arrange
    String programmeIdString = "PROGEXCEP";
    Acronym mockAcronym = new Acronym(programmeIdString);
    ProgrammeID mockProgrammeID = new ProgrammeID(mockAcronym);

    when(_studyPlanServiceDouble.getStudyPlansByProgrammeID(mockProgrammeID))
            .thenThrow(new RuntimeException("Database connection lost."));

    // Act
    ResponseEntity<?> result = _programmeRestController.getStudyPlansForProgramme(programmeIdString);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    assertEquals("Error retrieving Study Plans: Database connection lost.", ((ErrorResponse) result.getBody()).getMessage());

}
}