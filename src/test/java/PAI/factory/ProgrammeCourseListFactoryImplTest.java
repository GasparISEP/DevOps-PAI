package PAI.domain;

import static org.junit.jupiter.api.Assertions.*;

import PAI.factory.ProgrammeCourseListFactory;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ProgrammeCourseListFactoryImplTest {

    @Test
    public void shouldCreateProgrammeCourseList() {
        // arrange
        ProgrammeCourseListFactory programmeCourseListFactory = new ProgrammeCourseListFactory();
        // act
        List<Course> courseArrayList = programmeCourseListFactory.createCourseList();
        // assert
        assertNotNull(courseArrayList);
    }
}
