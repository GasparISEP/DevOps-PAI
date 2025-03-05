package PAI.factory;

import static org.junit.jupiter.api.Assertions.*;

import PAI.domain.Course;
import PAI.domain.ProgrammeCourseListFactoryImpl;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ProgrammeCourseListFactoryImplTest {

    @Test
    public void shouldCreateProgrammeCourseList() {
        // arrange
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl = new ProgrammeCourseListFactoryImpl();
        // act
        List<Course> courseArrayList = programmeCourseListFactoryImpl.createCourseList();
        // assert
        assertNotNull(courseArrayList);
    }
}
