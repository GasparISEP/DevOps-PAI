package PAI.factory;

import static org.junit.jupiter.api.Assertions.*;

import PAI.domain.Course;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ProgrammeCourseListFactoryImplTest {

    @Test
    public void shouldCreateProgrammeCourseList() {
        // arrange
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        // act
        List<Course> courseArrayList = programmeCourseListFactoryImpl1.createCourseList();
        // assert
        assertNotNull(courseArrayList);
    }
}
