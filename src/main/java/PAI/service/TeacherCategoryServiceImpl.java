package PAI.service;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.repository.ITeacherCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherCategoryServiceImpl implements ITeacherCategoryService {

    private final ITeacherCategoryRepository repository;
    private final ITeacherCategoryFactory factory;

    public TeacherCategoryServiceImpl(ITeacherCategoryRepository repository, ITeacherCategoryFactory factory) {
        if (repository == null || factory == null)
            throw new IllegalArgumentException("Dependencies cannot be null.");
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public boolean registerCategory(String categoryName) throws Exception {
        Name name = new Name(categoryName);

        if (repository.existsByName(name)) {
            throw new Exception("Category already exists or could not be registered.");
        }

        TeacherCategory newCategory = factory.createTeacherCategory(name);
        repository.save(newCategory);
        return true;
    }

    public boolean existsById(TeacherCategoryID teacherCategoryID) {
        return repository.containsOfIdentity(teacherCategoryID);
    }
}
