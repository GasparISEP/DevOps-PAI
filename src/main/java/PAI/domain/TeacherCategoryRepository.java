package PAI.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherCategoryRepository {

    private List<TeacherCategory> _teacherCategoryRepository;
    private TeacherCategoryFactory teacherCategoryFactory;// Store the factory

    // Constructor
    public TeacherCategoryRepository () {
        this(new TeacherCategoryFactory());
    }

    public TeacherCategoryRepository(TeacherCategoryFactory teacherCategoryFactory) {
        this.teacherCategoryFactory = teacherCategoryFactory; // Initialize the factory
        _teacherCategoryRepository = new ArrayList<>();
    }


    public boolean registerTeacherCategory (String name) throws Exception {

        TeacherCategory teacherCategory = teacherCategoryFactory.createTeacherCategory(name);

        if (getTeacherCategoryByName(name).isPresent()) {
            return false; // Return false if category is already present
        }
        _teacherCategoryRepository.add(teacherCategory); // Add the category if not found
        return true;
    }

        public Optional<TeacherCategory> getTeacherCategoryByName(String name) {
            for (TeacherCategory category : _teacherCategoryRepository) {
                if (category.getName().equalsIgnoreCase(name)) {
                    return Optional.of(category);
                }
            }
            return Optional.empty();  // Return Optional.empty() if not found
        }

    // Method to get the list of Teacher Categories
        public List<TeacherCategory> getTeacherCategoryList() throws IllegalStateException {
            if (_teacherCategoryRepository.isEmpty()) {
                throw new IllegalStateException("Teacher Category list is empty.");
            }
            return _teacherCategoryRepository;
        }

}
