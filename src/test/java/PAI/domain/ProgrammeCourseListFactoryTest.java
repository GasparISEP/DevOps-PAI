package PAI.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProgrammeCourseListFactoryTest {

    @Test
    public void shouldCreateProgrammeCourseList() {
        // arrange
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();
        // act
        ArrayList<Course> courseArrayList = programmeCourseListFactory.createCourseList();
        // assert
        assertNotNull(courseArrayList);
    }
}
