package PAI.controllerRest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CourseRESTControllerTest {

    @Test
    void testConstructorNotNull () {
        // Act
        CourseRESTController controller = new CourseRESTController();

        // Assert
        assertNotNull(controller);
    }
}