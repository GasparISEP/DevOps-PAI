package PAI.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradeListFactoryImplTest {

    @Test
    void newArrayList() {
        // Arrange
        StudentGradeListFactoryImpl studentGradeListFactoryImpl = new StudentGradeListFactoryImpl();

        // act + assert
        assertNotNull(studentGradeListFactoryImpl);    }
}