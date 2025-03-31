package PAI.factory;

import PAI.domain.TeacherCategoryV2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeacherCategoryListFactoryV2ImplTest {

    @Test
    void shouldReturnEmptyList() {
        TeacherCategoryListFactoryV2Impl factory = new TeacherCategoryListFactoryV2Impl();
        List<TeacherCategoryV2> list = factory.getTeacherCategoryList();

        assertNotNull(list);
        Assertions.assertTrue(list.isEmpty());
    }
}
