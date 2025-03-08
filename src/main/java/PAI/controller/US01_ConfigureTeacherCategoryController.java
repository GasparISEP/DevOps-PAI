package PAI.controller;

import PAI.repository.TeacherCategoryRepository;

public class  US01_ConfigureTeacherCategoryController {
    private final TeacherCategoryRepository repository;

    public US01_ConfigureTeacherCategoryController(TeacherCategoryRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
    }

    public boolean configureTeacherCategory(String categoryName) throws Exception {
        if (!repository.registerTeacherCategory(categoryName)) {
            throw new Exception("Category already exists.");
        }
        return true; // if successful registered
    }
}
