package PAI.controllerRest;

import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.dto.Programme.ProgrammeRequestDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.service.programme.IProgrammeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeRestControllerTest {

    private IProgrammeService _programmeServiceDouble;
    private IProgrammeAssembler _programmeAssemblerDouble;
    private ProgrammeRequestDTO _programmeRequestDTODouble;
    private ProgrammeVOsDTO _programmeVOsDTODouble;
    private ProgrammeResponseDTO _programmeResponseDTODouble;

    private void createDoubles() {
        _programmeServiceDouble = mock(IProgrammeService.class);
        _programmeAssemblerDouble = mock(ProgrammeAssembler.class);
        _programmeRequestDTODouble = mock(ProgrammeRequestDTO.class);
        _programmeVOsDTODouble = mock(ProgrammeVOsDTO.class);
        _programmeResponseDTODouble = mock(ProgrammeResponseDTO.class);
    }

    @Test
    void shouldCreateController() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble);

        //Assert
        assertNotNull(programmeRestCtrl);
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfProgrammeServiceNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(null, _programmeAssemblerDouble));
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIfProgrammeAssemblerNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(_programmeServiceDouble, null));
    }

    @Test
    void shouldSendProgrammeResponseDTO() throws Exception {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble);

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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble);

        //Act
        ResponseEntity<ProgrammeResponseDTO> result = programmeRestCtrl.registerProgramme(null);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void shouldSendBadResponseIfProgrammeResponseDTOIsNull() throws Exception {
        //Arrange
        createDoubles();
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble);

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
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(_programmeServiceDouble, _programmeAssemblerDouble);

        when(_programmeAssemblerDouble.fromDTOToDomain(_programmeRequestDTODouble)).thenReturn(_programmeVOsDTODouble);
        when(_programmeServiceDouble.registerProgramme(_programmeVOsDTODouble)).thenThrow(new Exception("Programme is already registered."));

        //Act
        ResponseEntity<ProgrammeResponseDTO> result = programmeRestCtrl.registerProgramme(_programmeRequestDTODouble);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}