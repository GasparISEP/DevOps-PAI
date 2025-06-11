package PAI.persistence.mem.studentGrade;

import PAI.VOs.*;
import PAI.domain.studentGrade.StudentGrade;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentGradeListFactoryImplTest {

    @Test
    void newArrayList() {
        // Arrange
        StudentGradeListFactoryImpl studentGradeListFactoryImpl = new StudentGradeListFactoryImpl();

        //act
        List<StudentGrade> s1 = studentGradeListFactoryImpl.newArrayList();

        // assert
        assertNotNull(s1);
    }

    @Test
    void newArrayList_ShouldReturnMutableList() {
        // Arrange
        StudentGradeListFactoryImpl factory = new StudentGradeListFactoryImpl();
        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        StudentGradeID studentGradeID = mock(StudentGradeID.class);
        StudentGradeGeneratedID studentGradeGeneratedID = mock(StudentGradeGeneratedID.class);

        // Act
        List<StudentGrade> list = factory.newArrayList();

        // Assert
        assertNotNull(list);
        assertDoesNotThrow(() -> list.add(new StudentGrade(grade,date,studentID,courseEditionID,studentGradeID, studentGradeGeneratedID)));
    }
}