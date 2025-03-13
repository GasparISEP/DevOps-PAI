package PAI.factory;

import PAI.domain.TeacherCategory;
import PAI.domain.TeacherCategoryFactoryInterface;

public class TeacherCategoryFactoryImpl implements TeacherCategoryFactoryInterface {

    @Override
    public TeacherCategory createTeacherCategory(String categoryName) throws Exception {
        return new TeacherCategory(categoryName);
    }
}
