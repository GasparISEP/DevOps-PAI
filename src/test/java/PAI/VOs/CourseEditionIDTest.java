package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseEditionIDTest {

    @Test
    void shouldReturnCourseEditionIDNotNull() {
        //Act
        CourseEditionID courseEditionID = new CourseEditionID();
        //Assert
        assertNotNull(courseEditionID);
    }
}