package PAI.repository;

import PAI.domain.TeacherCategory;
import PAI.factory.TeacherCategoryFactoryImpl;
import PAI.factory.TeacherCategoryRepositoryListFactoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing teacher categories.
 * This class provides functionality to register, retrieve, and manage a list of {@link TeacherCategory} instances.
 */
public class TeacherCategoryRepository {

    private List<TeacherCategory> teacherCategoryRepositoryList;
    private TeacherCategoryFactoryImpl teacherCategoryFactory;// Store the factory

    /**
     * Constructor for initializing the repository with a factory and list factory.
     *
     * @param teacherCategoryFactory the factory responsible for creating teacher categories.
     * @param teacherCategoryRepositoryListFactory the factory responsible for providing the list of teacher categories.
     */
    public TeacherCategoryRepository(TeacherCategoryFactoryImpl teacherCategoryFactory, TeacherCategoryRepositoryListFactoryImpl teacherCategoryRepositoryListFactory) {
        this.teacherCategoryFactory = teacherCategoryFactory;
        this.teacherCategoryRepositoryList = teacherCategoryRepositoryListFactory.getTeacherCategoryList();
    }

    /**
     * Registers a new teacher category if it doesn't already exist.
     *
     * @param name the name of the teacher category to be registered.
     * @return {@code true} if the category was successfully registered; {@code false} if the category already exists.
     * @throws Exception if an error occurs while creating the teacher category.
     */
    public boolean registerTeacherCategory (String name) throws Exception {

        TeacherCategory teacherCategory = teacherCategoryFactory.createTeacherCategory(name);

        if (getTeacherCategoryByName(name).isPresent()) {
            return false; // Return false if category is already present
        }
        teacherCategoryRepositoryList.add(teacherCategory); // Add the category if not found
        return true;
    }

    /**
     * Retrieves a teacher category by its name.
     *
     * @param name the name of the teacher category to retrieve.
     * @return an {@link Optional} containing the teacher category if found, or {@link Optional#empty()} if not found.
     */
    public Optional<TeacherCategory> getTeacherCategoryByName(String name) {
        for (TeacherCategory category : teacherCategoryRepositoryList) {
            if (category.getName().equalsIgnoreCase(name)) {
                return Optional.of(category);
            }
        }
        return Optional.empty();  // Return Optional.empty() if not found
    }

    /**
     * Retrieves the list of all teacher categories.
     *
     * @return a list of {@link TeacherCategory} objects.
     * @throws IllegalStateException if the teacher category list is empty.
     */
    public List<TeacherCategory> getTeacherCategoryList() throws IllegalStateException {
           if (teacherCategoryRepositoryList.isEmpty()) {
               throw new IllegalStateException("Teacher Category list is empty.");
           }
           return teacherCategoryRepositoryList;
    }

}
