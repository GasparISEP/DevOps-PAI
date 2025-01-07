package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryTest {

    @Test
    void shouldCreateTeacherCategory() {
        //arrange
        //act
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        //assert
        assertNotNull(tc1);
    }
}