package PAI.controllerRest;

import PAI.service.programme.IProgrammeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeRestControllerTest {

    @Test
    void shouldCreateController () {
        //Arrange
        IProgrammeService programmeServiceDouble = mock(IProgrammeService.class);

        //Act
        ProgrammeRestController programmeRestCtrl = new ProgrammeRestController(programmeServiceDouble);

        //Assert
        assertNotNull(programmeRestCtrl);
    }

    @Test
    void shouldThrowExceptionAndNotCreateControllerIFServiceNull () {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeRestController(null));
    }
}