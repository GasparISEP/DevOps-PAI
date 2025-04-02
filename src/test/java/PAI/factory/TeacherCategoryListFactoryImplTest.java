package PAI.factory;

import PAI.domain.TeacherCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeacherCategoryListFactoryImplTest {

    @Test
    void shouldReturnEmptyList() {
        TeacherCategoryListFactoryImpl factory = new TeacherCategoryListFactoryImpl();
        List<TeacherCategory> list = factory.getTeacherCategoryList();

        assertNotNull(list);
        Assertions.assertTrue(list.isEmpty());
    }
}
