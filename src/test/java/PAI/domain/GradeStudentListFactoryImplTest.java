package PAI.domain;

import PAI.factory.GradeStudentListFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeStudentListFactoryImplTest {

    @Test
    void newArrayList() {
        // Arrange
        GradeStudentListFactoryImpl gradeStudentListFactoryImpl = new GradeStudentListFactoryImpl();

        // act + assert
        assertNotNull(gradeStudentListFactoryImpl);    }
}