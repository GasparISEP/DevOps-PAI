package PAI.controller;

import PAI.VOs.Name;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.repository.ITeacherCategoryRepository;

/**
 * Controller for configuring TeacherCategoryV2.
 */
public class US01_ConfigureTeacherCategoryController {

    private final ITeacherCategoryRepository repository;
    private final ITeacherCategoryFactory factory;

    public US01_ConfigureTeacherCategoryController(ITeacherCategoryRepository repository,
                                                   ITeacherCategoryFactory factory) {
        if (repository == null || factory == null) {
            throw new IllegalArgumentException("Repository and Factory cannot be null.");
        }
        this.repository = repository;
        this.factory = factory;
    }

    public boolean configureTeacherCategory(String categoryName) throws Exception {
        Name name = new Name(categoryName);

        if (repository.existsByName(name)) {
            throw new Exception("Category already exists.");
        }

        TeacherCategory newCategory = factory.createTeacherCategory(name);
        repository.save(newCategory);
        return true;
    }
}