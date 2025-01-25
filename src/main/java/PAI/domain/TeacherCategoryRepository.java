package PAI.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherCategoryRepository {

    private ArrayList<TeacherCategory> _teacherCategoryRepository;

    //CONSTRUTOR
    public TeacherCategoryRepository () {

        _teacherCategoryRepository = new ArrayList<>();
    }
    //VALIDAÇÕES

    public boolean registerTeacherCategory (String name) throws Exception {

        TeacherCategory teacherCategory = new TeacherCategory (name);

        if (isTeacherCategoryRegistered(teacherCategory))
            return false;

        _teacherCategoryRepository.add(teacherCategory);
        return true;
    }
    public boolean isTeacherCategoryRegistered(TeacherCategory teacherCategory) {

        return _teacherCategoryRepository.contains(teacherCategory);
    }
    // New method to retrieve a category by name (support all the operations required by the controller)
    public Optional<TeacherCategory> getTeacherCategoryByName(String name) {
        for (TeacherCategory category : _teacherCategoryRepository) {
            if (category.getName().equals(name)) {
                return Optional.of(category);
            }
        }
        return Optional.empty();
    }

    // New method to list all categories(support all the operations required by the controller)
    public List<TeacherCategory> listAllCategories() {
        return new ArrayList<>(_teacherCategoryRepository);
    }

}
