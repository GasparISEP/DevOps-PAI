package PAI.service;

import PAI.VOs.Name;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.repository.ITeacherCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherCategoryApplicationService {

    private final ITeacherCategoryRepository repository;
    private final ITeacherCategoryFactory factory;

    public TeacherCategoryApplicationService(ITeacherCategoryRepository repository, ITeacherCategoryFactory factory) {
        if (repository == null || factory == null)
            throw new IllegalArgumentException("Dependencies cannot be null.");
        this.repository = repository;
        this.factory = factory;
    }

    public boolean registerCategory(String categoryName) throws Exception {
        Name name = new Name(categoryName);

        if (repository.existsByName(name)) {
            throw new Exception("Category already exists or could not be registered.");
        }

        TeacherCategory newCategory = factory.createTeacherCategory(name);
        repository.save(newCategory);
        return true;
    }
}