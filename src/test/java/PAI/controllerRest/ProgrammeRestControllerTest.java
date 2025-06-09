package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.assembler.programme.IProgrammeHATEOASAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.*;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import PAI.exception.BusinessRuleViolationException;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    private ProgrammeVOsDTO _programmeVOsDTODouble;
    private RegisterStudyPlanCommand _studyPlanCommandDouble;
    private StudyPlanDTO _studyPlanDTODouble;
    private StudyPlanResponseDTO _studyPlanResponseDTODouble;
    private IProgrammeDirectorAssembler _programmeDirectorAssemblerDouble;
    private Programme _programmeDouble;
    private IProgrammeHATEOASAssembler _programmeHATEOASAssembler;
    private EntityModel _programmeEntityModelDouble;


    @BeforeEach
    void setup() {
        _programmeServiceDouble = mock(IProgrammeService.class);
        _programmeAssemblerDouble = mock(ProgrammeAssembler.class);
        _programmeEnrolmentService = mock(ProgrammeEnrolmentServiceImpl.class);
        _studyPlanServiceDouble = mock(IStudyPlanService.class);
        _studyPlanAssemblerDouble = mock(IStudyPlanAssembler.class);
        _programmeDirectorAssemblerDouble = mock(IProgrammeDirectorAssembler.class);
        _programmeHATEOASAssembler = mock(IProgrammeHATEOASAssembler.class);
    }

    private void createProgrammeDoubles() {
        _programmeDTODouble = mock(ProgrammeDTO.class);
        _programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
        _programmeDouble = mock(Programme.class);
        _programmeEntityModelDouble = mock(EntityModel.class);
    }

    private void createStudyPlanDoubles() {
        _studyPlanCommandDouble = mock(RegisterStudyPlanCommand.class);
        _studyPlanDTODouble = mock(StudyPlanDTO.class);
        _studyPlanResponseDTODouble = mock(StudyPlanResponseDTO.class);
    }

    @Test
    void shouldCreateController() {
        //Arrange

        //Act
        new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        //Assert
    }

    @Test
    void shouldSendEntityModel() throws Exception {
        //Arrange
        createProgrammeDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        when(_programmeAssemblerDouble.fromDTOToDomain(_programmeDTODouble)).thenReturn(_programmeVOsDTODouble);
        when(_programmeServiceDouble.registerProgramme(_programmeVOsDTODouble)).thenReturn(_programmeDouble);
        when(_programmeAssemblerDouble.fromDomainToDTO(_programmeDouble)).thenReturn(_programmeDTODouble);
        when(_programmeHATEOASAssembler.toModel(_programmeDTODouble)).thenReturn(_programmeEntityModelDouble);

        //Act
        ResponseEntity<?> result = programmeRestCtrl.registerProgramme(_programmeDTODouble);

        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(_programmeEntityModelDouble, result.getBody());
    }

    @Test
    void shouldReturnCreatedResponseAndStudyPlanResponseDTOWhenStudyPlanIsRegisteredSuccessfully() throws Exception {
        // Arrange
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);
        createStudyPlanDoubles();
        String programmeName = "Biology";
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeName, programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenReturn(_studyPlanDTODouble);
        when(_studyPlanAssemblerDouble.toResponseDTO(_studyPlanDTODouble)).thenReturn(_studyPlanResponseDTODouble);

        // Act
        ResponseEntity<?> response = programmeRestCtrl.registerStudyPlan(programmeName, programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(_studyPlanResponseDTODouble, response.getBody());
    }

    @Test
    void shouldReturnNotFoundWhenStudyPlanIsRegisteredInAProgrammeThatDoesNotExist() throws Exception {
        // Arrange
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);
        createStudyPlanDoubles();
        String programmeName = "Biology";
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeName, programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new EntityNotFoundException(
                                        "Programme with name " + programmeName + " and acronym " + programmeAcronym));

        // Act
        ResponseEntity<?> response = programmeRestCtrl.registerStudyPlan(programmeName, programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenStudyPlanIsRegisteredInAProgrammeThatDoesNotHaveValidDegreeType() throws Exception {
        // Arrange
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);
        createStudyPlanDoubles();
        String programmeName = "Biology";
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeName, programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new EntityNotFoundException("Degree type does not exist"));

        // Act
        ResponseEntity<?> response = programmeRestCtrl.registerStudyPlan(programmeName, programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnConflictIfRegisteredStudyPlanAlreadyExists() throws Exception {
        // Arrange
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);
        createStudyPlanDoubles();
        String programmeName = "Biology";
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeName, programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new BusinessRuleViolationException("Study plan already exists"));

        // Act
        ResponseEntity<?> response = programmeRestCtrl.registerStudyPlan(programmeName, programmeAcronym, startDate);

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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);
        createStudyPlanDoubles();
        String programmeAcronym = "BIO";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        when(_studyPlanAssemblerDouble.toCommand(programmeName, programmeAcronym, startDate)).thenReturn(_studyPlanCommandDouble);
        when(_studyPlanServiceDouble.createStudyPlan(_studyPlanCommandDouble)).thenThrow(new IllegalArgumentException("Name cannot be null or empty"));

        // Act
        ResponseEntity<?> response = programmeRestCtrl.registerStudyPlan(programmeName, programmeAcronym, startDate);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnListOfProgrammeIDSDTOs() {
        //Arrange
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);
        ProgrammeIDDTO programmeIDDTO2 = mock(ProgrammeIDDTO.class);
        ProgrammeIDResponseDTO resultProgrammeIdResponseDTO = mock(ProgrammeIDResponseDTO.class);
        ProgrammeIDResponseDTO resultProgrammeIdResponseDTO2 = mock(ProgrammeIDResponseDTO.class);
        when(_programmeServiceDouble.getAllProgrammeIDDTOs()).thenReturn(List.of(programmeIDDTO, programmeIDDTO2));
        when(_programmeAssemblerDouble.toResponseDTO(programmeIDDTO)).thenReturn(resultProgrammeIdResponseDTO);
        when(_programmeAssemblerDouble.toResponseDTO(programmeIDDTO2)).thenReturn(resultProgrammeIdResponseDTO2);
        //Act
        ResponseEntity<List<ProgrammeIDResponseDTO>> result = programmeRestCtrl.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
    }

    @Test
    void shouldReturnNotFoundIfNoProgrammeIDSDTOs() {
        //Arrange
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        when(_programmeServiceDouble.getAllProgrammeIDDTOs()).thenReturn(List.of());
        //Act
        ResponseEntity<List<ProgrammeIDResponseDTO>> result = programmeRestCtrl.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void shouldAssignProgrammeDirectorSuccessfully() {
        ProgrammeDirectorRequestDTO requestDto = mock(ProgrammeDirectorRequestDTO.class);
        ProgrammeDirectorVOsDTO vosDto = mock(ProgrammeDirectorVOsDTO.class);

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Data Science");
        Acronym acronym = new Acronym("DSE");
        TeacherAcronym teacherAcronym = new TeacherAcronym("TCH");

        when(_programmeDirectorAssemblerDouble.fromDTOToDomain(requestDto)).thenReturn(vosDto);
        when(vosDto.getProgrammeName()).thenReturn(name);
        when(vosDto.getProgrammeAcronym()).thenReturn(acronym);
        when(vosDto.getTeacherAcronym()).thenReturn(teacherAcronym);

        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        ResponseEntity<Void> response = controller.assignProgrammeDirector(requestDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestIfAssignProgrammeDirectorDtoIsNull() {
        // Arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        // Act
        ResponseEntity<Void> response = controller.assignProgrammeDirector(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestWhenExceptionThrownDuringAssign() {
        ProgrammeDirectorRequestDTO requestDto = mock(ProgrammeDirectorRequestDTO.class);

        when(_programmeDirectorAssemblerDouble.fromDTOToDomain(requestDto))
                .thenThrow(new RuntimeException("Some error"));

        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        ResponseEntity<Void> response = controller.assignProgrammeDirector(requestDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnProgrammesByDegreeTypeID() {
        // Arrange
        createProgrammeDoubles();
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        String degreeTypeIdStr = "123";

        ProgrammeIDDTO dto = new ProgrammeIDDTO("Acr");

        when(_programmeServiceDouble.getProgrammeIDDTOsByDegreeTypeID(any(DegreeTypeID.class)))
                .thenReturn(List.of(dto));

        // Act
        ResponseEntity<List<ProgrammeIDDTO>> response = controller.getProgrammesByDegreeTypeID(degreeTypeIdStr);

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
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        String degreeTypeIdStr = "123";

        when(_programmeServiceDouble.getProgrammeIDDTOsByDegreeTypeID(any(DegreeTypeID.class)))
                .thenReturn(List.of());

        // Act
        ResponseEntity<List<ProgrammeIDDTO>> response = controller.getProgrammesByDegreeTypeID(degreeTypeIdStr);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturnBadRequestWhenExceptionIsThrown()  {
        // Arrange
        createProgrammeDoubles();
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        String degreeTypeIdStr = "invalid-id";

        when(_programmeServiceDouble.getProgrammeIDDTOsByDegreeTypeID(any(DegreeTypeID.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<List<ProgrammeIDDTO>> response = controller.getProgrammesByDegreeTypeID(degreeTypeIdStr);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturn200WhenProgrammeIsFoundById () {
        //arrange
        createProgrammeDoubles();
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        String acronym = "CSD";

        ProgrammeID doubleProgrammeID = mock(ProgrammeID.class);
        when(doubleProgrammeID.getProgrammeAcronym()).thenReturn(acronym);


        Programme programmeMock = mock(Programme.class);
        when(_programmeServiceDouble.getProgrammeByID(any(ProgrammeID.class)))
                .thenReturn(Optional.of(programmeMock));
        //act
        ResponseEntity<Object> result = controller.getProgrammeByID(acronym);

        //assert
        assertEquals(result.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void shouldReturn404WhenProgrammeIsNotFoundById() {
        // arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        String acronym = "XYZ";

        when(_programmeServiceDouble.getProgrammeByID(any(ProgrammeID.class)))
                .thenReturn(Optional.empty());

        // act
        ResponseEntity<Object> result = controller.getProgrammeByID(acronym);

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
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        ProgrammeDTO dto1 = mock(ProgrammeDTO.class);
        ProgrammeDTO dto2 = mock(ProgrammeDTO.class);
        ProgrammeDTO dto3 = mock(ProgrammeDTO.class);
        List<ProgrammeDTO> progDTOS = List.of(dto1, dto2, dto3);
        when(_programmeServiceDouble.getAllProgrammes()).thenReturn(progDTOS);

        // Act
        ResponseEntity<?> response = controller.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(progDTOS, response.getBody());
        assertTrue(((Iterable<?>) response.getBody()).iterator().hasNext());
        verify(_programmeServiceDouble).getAllProgrammes();
        assertTrue(((Collection<?>) response.getBody()).contains(dto2));
    }

    @Test
    void shouldReturnAnEmptyListOfProgrammesIfThereAreNoSchoolYearsInTheSystem () {
        // Arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        List<ProgrammeDTO> progDTOS = List.of();
        when(_programmeServiceDouble.getAllProgrammes()).thenReturn(progDTOS);

        // Act
        ResponseEntity<?> response = controller.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(progDTOS, response.getBody());
        assertFalse(((Iterable<?>) response.getBody()).iterator().hasNext());
        verify(_programmeServiceDouble).getAllProgrammes();
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrown() {
        // Arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        String errorMessage = "Invalid input data";

        when(_programmeServiceDouble.getAllProgrammes()).thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = controller.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionIsThrown() {
        // Arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        when(_programmeServiceDouble.getAllProgrammes()).thenThrow(new RuntimeException("Database is down"));

        // Act
        ResponseEntity<?> response = controller.getAllProgrammes();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnListOfProgrammeDTOsOnSuccess() {
        //arrange
        int uniqueNumber = 1234567;
        StudentID studentID = mock(StudentID.class);
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);

        List<ProgrammeID> domainList = List.of(programmeID);
        List<ProgrammeIDDTO> dtoList = List.of(programmeIDDTO);

        when(_programmeEnrolmentService.listOfProgrammesStudentIsEnrolledIn(studentID)).thenReturn(domainList);
        when(_programmeAssemblerDouble.toListOfDTOs(domainList)).thenReturn(dtoList);

        //act
        ResponseEntity<List<ProgrammeIDDTO>> response = controller.getAllProgrammesThatTheStudentIsEnrolledIn(uniqueNumber);

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturnListOfProgrammeDTOs() {
        //arrange
        int uniqueNumber = 123;
        StudentID studentID = mock(StudentID.class);
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _programmeEnrolmentService, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _programmeHATEOASAssembler);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);

        List<ProgrammeID> domainList = List.of(programmeID);
        List<ProgrammeIDDTO> dtoList = List.of(programmeIDDTO);

        when(_programmeEnrolmentService.listOfProgrammesStudentIsEnrolledIn(studentID)).thenReturn(domainList);
        when(_programmeAssemblerDouble.toListOfDTOs(domainList)).thenReturn(dtoList);

        //act
        ResponseEntity<List<ProgrammeIDDTO>> response = controller.getAllProgrammesThatTheStudentIsEnrolledIn(uniqueNumber);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}