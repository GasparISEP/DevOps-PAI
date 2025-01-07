package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryTest {

    @Test
    void shouldCreateTeacherCategory() throws Exception {
        //arrange
        //act
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        //assert
        assertNotNull(tc1);
    }

    @Test 
    void nullNameGeneratesExeception(){
        assertThrows(Exception.class, () -> new TeacherCategory(null));
    }

    @Test 
    void emptyNameGeneratesException(){
        assertThrows(Exception.class, () -> new TeacherCategory(""));
    }
}