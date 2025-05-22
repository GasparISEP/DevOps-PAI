package PAI.controllerRest;

import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.service.programme.IProgrammeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeRestControllerTest {

    private IProgrammeService _programmeServiceDouble;
    private IProgrammeAssembler _programmeAssemblerDouble;

    private void createDoubles() {
        _programmeServiceDouble = mock(IProgrammeService.class);
        _programmeAssemblerDouble = mock(ProgrammeAssembler.class);
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
}