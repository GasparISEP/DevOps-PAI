package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeStudentListFactoryTest {

    @Test
    void newArrayList() {
        // Arrange
        GradeStudentListFactory gradeStudentListFactory = new GradeStudentListFactory();

        // act + assert
        assertNotNull(gradeStudentListFactory);    }
}