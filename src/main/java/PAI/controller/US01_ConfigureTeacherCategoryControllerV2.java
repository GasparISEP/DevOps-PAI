package PAI.controller.v2;

import PAI.domain.TeacherCategoryV2;
import PAI.factory.v2.ITeacherCategoryFactoryV2;
import PAI.repository.v2.ITeacherCategoryRepository;
import PAI.VOs.Name;

/**
 * Controller for configuring TeacherCategoryV2.
 */
public class US01_ConfigureTeacherCategoryControllerV2 {

    private final ITeacherCategoryRepository repository;
    private final ITeacherCategoryFactoryV2 factory;

    public US01_ConfigureTeacherCategoryControllerV2(ITeacherCategoryRepository repository,
                                                     ITeacherCategoryFactoryV2 factory) {
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

        TeacherCategoryV2 newCategory = factory.createTeacherCategory(name);
        repository.save(newCategory);
        return true;
    }
}