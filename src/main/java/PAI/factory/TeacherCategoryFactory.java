package PAI.factory;

import PAI.domain.TeacherCategory;
import PAI.domain.TeacherCategoryFactoryInterface;

public class TeacherCategoryFactory implements TeacherCategoryFactoryInterface {

    @Override
    public TeacherCategory createTeacherCategory(String categoryName) throws Exception {
        return new TeacherCategory(categoryName);
    }
}
