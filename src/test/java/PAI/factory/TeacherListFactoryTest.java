package PAI.factory;

import PAI.domain.SchoolYear;
import PAI.domain.Teacher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherListFactoryTest {
    @Test
    void shouldCreateTeacherList() {
        // Arrange
        TeacherListFactory teacherListFactory = new TeacherListFactory();

        // Act
        List<Teacher> teacherList = teacherListFactory.newArrayList();

        // Assert
        assertNotNull(teacherList);
    }

}