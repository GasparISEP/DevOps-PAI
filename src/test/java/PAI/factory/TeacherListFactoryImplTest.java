package PAI.factory;

import PAI.domain.Teacher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherListFactoryImplTest {
    @Test
    void shouldCreateTeacherList() {
        // Arrange
        TeacherListFactoryImpl teacherListFactoryImpl = new TeacherListFactoryImpl();

        // Act
        List<Teacher> teacherList = teacherListFactoryImpl.newArrayList();

        // Assert
        assertNotNull(teacherList);
    }

}