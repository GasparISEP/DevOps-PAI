package PAI.controllerRest;

import static org.junit.jupiter.api.Assertions.*;
import PAI.assembler.teacher.ITeacherAssembler;
import PAI.service.teacher.ITeacherService;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

class TeacherRestControllerTest {

    @Test
    void shouldCreateTeacherRestController() {
        // Arrange
        ITeacherService teacherService = mock(ITeacherService.class);
        ITeacherAssembler teacherAssembler = mock(ITeacherAssembler.class);
        TeacherRestController controller = new TeacherRestController(teacherService, teacherAssembler);
        // Act + Assert
        assertNotNull(controller);
    }

}
