package PAI.domain;

public class TeacherCategoryFactory {

    public TeacherCategory createTeacherCategory(String categoryName) throws Exception {
        return new TeacherCategory(categoryName);
    }
}
