package PAI.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherCategoryRepository {

    private List<TeacherCategory> _teacherCategoryRepository;


    //CONSTRUTOR

    public TeacherCategoryRepository () {this(new TeacherCategoryFactory());
    }

    public TeacherCategoryRepository (TeacherCategoryFactory doubleTeacherCategoryFactory) {

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

    // Method to get the list of Teacher Categories
    public List<TeacherCategory> getTeacherCategoryList() throws IllegalStateException {
        if (_teacherCategoryRepository.isEmpty()) {
            throw new IllegalStateException("Teacher Category list is empty.");
        }
        return _teacherCategoryRepository;
    }

}
