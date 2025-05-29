package PAI.persistence.mem.teacherCategory;

import PAI.domain.teacherCategory.TeacherCategory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherCategoryListFactoryImplTest {

    @Test
    void shouldReturnEmptyList() {
        //Arrange
        TeacherCategoryListFactoryImpl factory = new TeacherCategoryListFactoryImpl();

        //Act
        List<TeacherCategory> list = factory.getTeacherCategoryList();

        //Assert
        assertEquals(ArrayList.class, list.getClass());
    }
}
