package PAI.domain;

public class TeacherCategoryFactory implements TeacherCategoryFactoryInterface {

    @Override
    public TeacherCategory createTeacherCategory(String categoryName) throws Exception {
        return new TeacherCategory(categoryName);
    }
}
