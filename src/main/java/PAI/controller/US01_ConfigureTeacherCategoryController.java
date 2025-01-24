package PAI.controller;

import PAI.domain.TeacherCategory;
import PAI.domain.TeacherCategoryRepository;

import java.util.List;
import java.util.Optional;

public class US01_ConfigureTeacherCategoryController {
    private TeacherCategoryRepository repository;

    public US01_ConfigureTeacherCategoryController(TeacherCategoryRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
    }

    public void addCategory(String categoryName) throws Exception {
        if (!repository.registerTeacherCategory(categoryName)) {
            throw new Exception("Category already exists.");
        }
    }

    public TeacherCategory getCategory(String categoryName) throws Exception {
        Optional<TeacherCategory> category = repository.getTeacherCategoryByName(categoryName);
        return category.orElseThrow(() -> new Exception("Invalid category name: " + categoryName));
    }

    public List<TeacherCategory> listCategories() {
        return repository.listAllCategories();
    }
}