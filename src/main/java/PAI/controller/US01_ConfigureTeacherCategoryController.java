package PAI.controller;

import PAI.repository.TeacherCategoryRepository;

/**
 * Controller for configuring the teacher category.
 * This class is responsible for handling the configuration of new teacher categories.
 */
public class  US01_ConfigureTeacherCategoryController {

    private final TeacherCategoryRepository repository;

    /**
     * Constructor for the controller, initializes the repository.
     *
     * @param repository the repository responsible for managing teacher categories.
     * @throws IllegalArgumentException if the repository is null.
     */
    public US01_ConfigureTeacherCategoryController(TeacherCategoryRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
    }

    /**
     * Configures a new teacher category.
     * Attempts to register a new teacher category using the provided name.
     *
     * @param categoryName the name of the teacher category to be configured.
     * @return {@code true} if the category was successfully registered.
     * @throws Exception if the category already exists in the repository.
     */
    public boolean configureTeacherCategory(String categoryName) throws Exception {
        if (!repository.registerTeacherCategory(categoryName)) {
            throw new Exception("Category already exists.");
        }
        return true; // if successful registered
    }
}
