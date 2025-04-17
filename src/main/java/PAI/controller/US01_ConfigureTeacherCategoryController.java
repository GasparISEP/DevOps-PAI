package PAI.controller;

import PAI.VOs.Name;
import PAI.repository.ITeacherCategoryRepository;

public class US01_ConfigureTeacherCategoryController {

    private final ITeacherCategoryRepository repository;

    public US01_ConfigureTeacherCategoryController(ITeacherCategoryRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }
        this.repository = repository;
    }

    /**
     * Attempts to configure (register) a new TeacherCategory.
     *
     * @param categoryName the name of the category to create
     * @return true if the category was created; otherwise an exception is thrown
     * @throws Exception if the category already exists or registration fails
     */
    public boolean configureTeacherCategory(String categoryName) throws Exception {
        Name name = new Name(categoryName);

        // Delegate creation + duplicate check to the repository
        boolean created = repository.registerTeacherCategory(name);
        if (!created) {
            throw new Exception("Category already exists or could not be registered.");
        }
        return true;
    }
}
