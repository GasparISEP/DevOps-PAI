package PAI.factory;

import PAI.domain.TeacherCategory;

public class TeacherCategoryFactoryImpl implements TeacherCategoryFactory {

    @Override
    public TeacherCategory createTeacherCategory(String categoryName) throws Exception {
        return new TeacherCategory(categoryName);
    }
}
