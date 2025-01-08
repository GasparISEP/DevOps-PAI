package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testCourseCreation_Valid() throws Exception{
        //arrange
        Semester semester = new Semester(1);
        //act
        Course course2 = new Course ("Informatics", "INF", 6,semester);
        //assert
        assertNotNull(course2);
    }
}