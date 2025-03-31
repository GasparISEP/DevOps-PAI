package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseNameTest {

    @Test
    void shouldCreateCourseName() throws Exception{
        //act
        CourseName courseName = new CourseName("Mestrado em Engenharia Informática");
        //assert
        assertNotNull(courseName);
    }

}