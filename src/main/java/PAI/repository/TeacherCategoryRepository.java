package PAI.repository;

import PAI.domain.TeacherCategory;
import PAI.factory.TeacherCategoryFactoryImpl;
import PAI.factory.TeacherCategoryRepositoryListFactoryImpl;

import java.util.List;
import java.util.Optional;

public class TeacherCategoryRepository {

    private List<TeacherCategory> teacherCategoryRepositoryList;
    private TeacherCategoryFactoryImpl teacherCategoryFactory;// Store the factory


    // Constructor taking both the factory and the repository list factory
    public TeacherCategoryRepository(TeacherCategoryFactoryImpl teacherCategoryFactory, TeacherCategoryRepositoryListFactoryImpl teacherCategoryRepositoryListFactory) {
        this.teacherCategoryFactory = teacherCategoryFactory;
        this.teacherCategoryRepositoryList = teacherCategoryRepositoryListFactory.getTeacherCategoryList();
    }


    public boolean registerTeacherCategory (String name) throws Exception {

        TeacherCategory teacherCategory = teacherCategoryFactory.createTeacherCategory(name);

        if (getTeacherCategoryByName(name).isPresent()) {
            return false; // Return false if category is already present
        }
        teacherCategoryRepositoryList.add(teacherCategory); // Add the category if not found
        return true;
    }

        public Optional<TeacherCategory> getTeacherCategoryByName(String name) {
            for (TeacherCategory category : teacherCategoryRepositoryList) {
                if (category.getName().equalsIgnoreCase(name)) {
                    return Optional.of(category);
                }
            }
            return Optional.empty();  // Return Optional.empty() if not found
        }

    // Method to get the list of Teacher Categories
        public List<TeacherCategory> getTeacherCategoryList() throws IllegalStateException {
            if (teacherCategoryRepositoryList.isEmpty()) {
                throw new IllegalStateException("Teacher Category list is empty.");
            }
            return teacherCategoryRepositoryList;
        }

}
