package PAI.controllerRest;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.TeacherAcronym;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.domain.programme.Programme;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.*;
import PAI.service.programme.IProgrammeService;
import PAI.service.teacher.ITeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeRestControllerTest {

    private IProgrammeService _programmeServiceDouble;
    private IProgrammeAssembler _programmeAssemblerDouble;
    private ProgrammeRequestDTO _programmeRequestDTODouble;
    private ProgrammeVOsDTO _programmeVOsDTODouble;
    private ProgrammeResponseDTO _programmeResponseDTODouble;
    private IProgrammeDirectorAssembler _programmeDirectorAssemblerDouble;
    private ITeacherService _teacherServiceDouble;


    private void createDoubles() {
        _programmeServiceDouble = mock(IProgrammeService.class);
        _programmeAssemblerDouble = mock(ProgrammeAssembler.class);
        _programmeRequestDTODouble = mock(ProgrammeRequestDTO.class);
        _programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
        _programmeResponseDTODouble = mock(ProgrammeResponseDTO.class);
        _programmeDirectorAssemblerDouble = mock(IProgrammeDirectorAssembler.class);
        _teacherServiceDouble = mock(ITeacherService.class);
    }

    @Test
    void shouldCreateController() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        //Assert
        assertNotNull(programmeRestCtrl);
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfProgrammeServiceNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(null, _programmeAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble));
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfProgrammeAssemblerNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(_programmeServiceDouble, null, _programmeDirectorAssemblerDouble, _teacherServiceDouble));
    }

    @Test
    void shouldSendProgrammeResponseDTO() throws Exception {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble,_programmeDirectorAssemblerDouble, _teacherServiceDouble);

        when(_programmeAssemblerDouble.fromDTOToDomain(_programmeRequestDTODouble)).thenReturn(_programmeVOsDTODouble);
        when(_programmeServiceDouble.registerProgramme(_programmeVOsDTODouble)).thenReturn(_programmeResponseDTODouble);

        //Act
        ResponseEntity<ProgrammeResponseDTO> result = programmeRestCtrl.registerProgramme(_programmeRequestDTODouble);

        //Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(_programmeResponseDTODouble, result.getBody());
    }

    @Test
    void shouldSendBadResponseIfProgrammeRequestDTOIsNull() {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        //Act
        ResponseEntity<ProgrammeResponseDTO> result = programmeRestCtrl.registerProgramme(null);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void shouldSendBadResponseIfProgrammeResponseDTOIsNull() throws Exception {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        when(_programmeAssemblerDouble.fromDTOToDomain(_programmeRequestDTODouble)).thenReturn(_programmeVOsDTODouble);
        when(_programmeServiceDouble.registerProgramme(_programmeVOsDTODouble)).thenReturn(null);

        //Act
        ResponseEntity<ProgrammeResponseDTO> result = programmeRestCtrl.registerProgramme(_programmeRequestDTODouble);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void shouldSendBadResponseIfAnyExceptionIsThrown() throws Exception {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        when(_programmeAssemblerDouble.fromDTOToDomain(_programmeRequestDTODouble)).thenReturn(_programmeVOsDTODouble);
        when(_programmeServiceDouble.registerProgramme(_programmeVOsDTODouble)).thenThrow(new Exception("Programme is already registered."));

        //Act
        ResponseEntity<ProgrammeResponseDTO> result = programmeRestCtrl.registerProgramme(_programmeRequestDTODouble);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void shouldReturnListOfProgrammeIDSDTOs() {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);
        ProgrammeIDDTO programmeIDDTO = mock(ProgrammeIDDTO.class);
        ProgrammeIDDTO programmeIDDTO2 = mock(ProgrammeIDDTO.class);
        when(_programmeServiceDouble.getAllProgrammeIDDTOs()).thenReturn(List.of(programmeIDDTO, programmeIDDTO2));
        //Act
        ResponseEntity<List<ProgrammeIDDTO>> result = programmeRestCtrl.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
    }

    @Test
    void shouldReturnNotFoundIfNoProgrammeIDSDTOs() {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble, _programmeDirectorAssemblerDouble, _teacherServiceDouble);

        when(_programmeServiceDouble.getAllProgrammeIDDTOs()).thenReturn(List.of());
        //Act
        ResponseEntity<List<ProgrammeIDDTO>> result = programmeRestCtrl.getAllProgrammeIDDTOs();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void shouldReturnProgrammeDirectorResponseDTO() {
        // Arrange
        IProgrammeService programmeServiceDouble = mock(IProgrammeService.class);
        IProgrammeAssembler programmeAssemblerDouble = mock(IProgrammeAssembler.class);
        IProgrammeDirectorAssembler programmeDirectorAssemblerDouble = mock(IProgrammeDirectorAssembler.class);
        ITeacherService teacherServiceDouble = mock(ITeacherService.class);

        Programme programmeMock = mock(Programme.class);
        Teacher teacherMock = mock(Teacher.class);
        ProgrammeDirectorResponseDTO responseMock = mock(ProgrammeDirectorResponseDTO.class);

        when(programmeServiceDouble.findAll()).thenReturn(List.of(programmeMock));
        when(teacherServiceDouble.getAllTeachers()).thenReturn(List.of(teacherMock));
        when(programmeDirectorAssemblerDouble.fromDomainToDTO(List.of(programmeMock), List.of(teacherMock)))
                .thenReturn(responseMock);

        ProgrammeRestController controller = new ProgrammeRestController(
                programmeServiceDouble,
                programmeAssemblerDouble,
                programmeDirectorAssemblerDouble,
                teacherServiceDouble
        );

        // Act
        ResponseEntity<ProgrammeDirectorResponseDTO> response = controller.getProgrammeDirectorInfo();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void shouldAssignProgrammeDirectorSuccessfully() {
        IProgrammeService programmeServiceDouble = mock(IProgrammeService.class);
        IProgrammeAssembler programmeAssemblerDouble = mock(IProgrammeAssembler.class);
        IProgrammeDirectorAssembler programmeDirectorAssemblerDouble = mock(IProgrammeDirectorAssembler.class);
        ITeacherService teacherServiceDouble = mock(ITeacherService.class);

        ProgrammeDirectorRequestDTO requestDto = mock(ProgrammeDirectorRequestDTO.class);
        ProgrammeDirectorVOsDTO vosDto = mock(ProgrammeDirectorVOsDTO.class);

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Data Science");
        Acronym acronym = new Acronym("DSE");
        TeacherAcronym teacherAcronym = new TeacherAcronym("TCH");

        when(programmeDirectorAssemblerDouble.fromDTOToDomain(requestDto)).thenReturn(vosDto);
        when(vosDto.getProgrammeName()).thenReturn(name);
        when(vosDto.getProgrammeAcronym()).thenReturn(acronym);
        when(vosDto.getTeacherAcronym()).thenReturn(teacherAcronym);

        ProgrammeRestController controller = new ProgrammeRestController(
                programmeServiceDouble,
                programmeAssemblerDouble,
                programmeDirectorAssemblerDouble,
                teacherServiceDouble
        );

        ResponseEntity<Void> response = controller.assignProgrammeDirector(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestIfAssignProgrammeDirectorDtoIsNull() {
        // Arrange
        IProgrammeService programmeServiceDouble = mock(IProgrammeService.class);
        IProgrammeAssembler programmeAssemblerDouble = mock(IProgrammeAssembler.class);
        IProgrammeDirectorAssembler programmeDirectorAssemblerDouble = mock(IProgrammeDirectorAssembler.class);
        ITeacherService teacherServiceDouble = mock(ITeacherService.class);

        ProgrammeRestController controller = new ProgrammeRestController(
                programmeServiceDouble,
                programmeAssemblerDouble,
                programmeDirectorAssemblerDouble,
                teacherServiceDouble
        );

        // Act
        ResponseEntity<Void> response = controller.assignProgrammeDirector(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}