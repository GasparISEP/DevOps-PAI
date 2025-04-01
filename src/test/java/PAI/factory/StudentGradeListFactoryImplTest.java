package PAI.factory;

import PAI.domain.StudentGrade;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradeListFactoryImplTest {

    @Test
    void newArrayList() {
        // Arrange
        StudentGradeListFactoryImpl studentGradeListFactoryImpl = new StudentGradeListFactoryImpl();

        //act
        List<StudentGrade> s1 = studentGradeListFactoryImpl.newArrayList();

        // assert
        assertNotNull(s1);    }
}