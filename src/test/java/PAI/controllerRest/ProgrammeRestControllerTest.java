package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.domain.programme.Programme;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.*;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.programme.IProgrammeService;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.teacher.ITeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeRestControllerTest {

    private IProgrammeService _programmeServiceDouble;
    private IProgrammeAssembler _programmeAssemblerDouble;
    private IStudyPlanService _studyPlanServiceDouble;
    private IStudyPlanAssembler _studyPlanAssemblerDouble;
    private ProgrammeDTO _programmeDTODouble;
    private ProgrammeVOsDTO _programmeVOsDTODouble;
    private RegisterStudyPlanCommand _studyPlanCommandDouble;
    private StudyPlanDTO _studyPlanDTODouble;
    private StudyPlanResponseDTO _studyPlanResponseDTODouble;
    private IProgrammeDirectorAssembler _programmeDirectorAssemblerDouble;
    private ITeacherService _teacherServiceDouble;
    private Programme _programmeDouble;


    @BeforeEach
    void setup() {
        _programmeServiceDouble = mock(IProgrammeService.class);
        _programmeAssemblerDouble = mock(ProgrammeAssembler.class);
        _studyPlanServiceDouble = mock(IStudyPlanService.class);
        _studyPlanAssemblerDouble = mock(IStudyPlanAssembler.class);
        _programmeDirectorAssemblerDouble = mock(IProgrammeDirectorAssembler.class);
        _teacherServiceDouble = mock(ITeacherService.class);
    }

    private void createProgrammeDoubles() {
        _programmeDTODouble = mock(ProgrammeDTO.class);
        _programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
        _programmeDouble = mock(Programme.class);
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
        new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        //Assert
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfProgrammeServiceNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(null, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble));
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfProgrammeAssemblerNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(_programmeServiceDouble, null, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble));
    }

    @Test
    void shouldThrowExceptionWhenTryingToCreateControllerWithNullStudyPlanService() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, null, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble));
    }

    @Test
    void shouldThrowExceptionWhenTryingToCreateControllerWithNullStudyPlanAssembler() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, null, _programmeDirectorAssemblerDouble, _teacherServiceDouble));
    }

    @Test
    void shouldThrowExceptionWhenTryingToCreateControllerWithNullProgrammeDirectorAssembler() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, null, _teacherServiceDouble));
    }

    @Test
    void shouldThrowExceptionWhenTryingToCreateControllerWithNullTeacherService() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, null ));
    }

    @Test
    void shouldSendProgrammeDTO() throws Exception {
        //Arrange
        createProgrammeDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        when(_programmeAssemblerDouble.fromDTOToDomain(_programmeDTODouble)).thenReturn(_programmeVOsDTODouble);
        when(_programmeServiceDouble.registerProgramme(_programmeVOsDTODouble)).thenReturn(_programmeDouble);
        when(_programmeAssemblerDouble.fromDomainToDTO(_programmeDouble)).thenReturn(_programmeDTODouble);

        //Act
        ResponseEntity<?> result = programmeRestCtrl.registerProgramme(_programmeDTODouble);

        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(_programmeDTODouble, result.getBody());
    }

    @Test
    void shouldReturnCreatedResponseAndStudyPlanResponseDTOWhenStudyPlanIsRegisteredSuccessfully() throws Exception {
        // Arrange
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);
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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);
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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);
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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);
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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);
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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);
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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        when(_programmeServiceDouble.getAllProgrammeIDDTOs()).thenReturn(List.of());
        //Act
        ResponseEntity<List<ProgrammeIDResponseDTO>> result = programmeRestCtrl.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void shouldReturnProgrammeDirectorResponseDTO() {
        // Arrange
        createProgrammeDoubles();
        Programme programmeMock = mock(Programme.class);
        Teacher teacherMock = mock(Teacher.class);
        ProgrammeDirectorResponseDTO responseMock = mock(ProgrammeDirectorResponseDTO.class);

        when(_programmeServiceDouble.findAll()).thenReturn(List.of(programmeMock));
        when(_teacherServiceDouble.getAllTeachers()).thenReturn(List.of(teacherMock));
        when(_programmeDirectorAssemblerDouble.fromDomainToDTO(List.of(programmeMock), List.of(teacherMock)))
                .thenReturn(responseMock);

        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        // Act
        ResponseEntity<ProgrammeDirectorResponseDTO> response = controller.getProgrammeDirectorInfo();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenExceptionIsThrown() {
        // Arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        when(_programmeServiceDouble.findAll()).thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<ProgrammeDirectorResponseDTO> response = controller.getProgrammeDirectorInfo();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
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
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        ResponseEntity<Void> response = controller.assignProgrammeDirector(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestIfAssignProgrammeDirectorDtoIsNull() {
        // Arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

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
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        ResponseEntity<Void> response = controller.assignProgrammeDirector(requestDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnProgrammesByDegreeTypeID() {
        // Arrange
        createProgrammeDoubles();
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        String degreeTypeIdStr = "123";

        ProgrammeIDDTO dto = new ProgrammeIDDTO("Name", "Acr");

        when(_programmeServiceDouble.getProgrammeIDDTOsByDegreeTypeID(any(DegreeTypeID.class)))
                .thenReturn(List.of(dto));

        // Act
        ResponseEntity<List<ProgrammeIDDTO>> response = controller.getProgrammesByDegreeTypeID(degreeTypeIdStr);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Name", response.getBody().get(0).name());
        assertEquals("Acr", response.getBody().get(0).acronym());
    }

    @Test
    void shouldReturnNotFoundWhenNoProgrammesFound()  {
        // Arrange
        createProgrammeDoubles();
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

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
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

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
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        String name = "Computer Sci";
        String acronym = "CSD";

        ProgrammeID doubleProgrammeID = mock(ProgrammeID.class);
        when(doubleProgrammeID.getProgrammeAcronym()).thenReturn(acronym);

        when(doubleProgrammeID.getProgrammeName()).thenReturn(name);

        Programme programmeMock = mock(Programme.class);
        when(_programmeServiceDouble.getProgrammeByID(any(ProgrammeID.class)))
                .thenReturn(Optional.of(programmeMock));
        //act
        ResponseEntity<Object> result = controller.getProgrammeByID(name,acronym);

        //assert
        assertEquals(result.getStatusCode(),HttpStatus.OK);
    }

    @Test
    void shouldReturn404WhenProgrammeIsNotFoundById() {
        // arrange
        ProgrammeRestController controller = new ProgrammeRestController(_programmeServiceDouble,
                _programmeAssemblerDouble, _studyPlanServiceDouble, _studyPlanAssemblerDouble,
                _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        String name = "Nonexistent Programme";
        String acronym = "XYZ";

        when(_programmeServiceDouble.getProgrammeByID(any(ProgrammeID.class)))
                .thenReturn(Optional.empty());

        // act
        ResponseEntity<Object> result = controller.getProgrammeByID(name, acronym);

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
}