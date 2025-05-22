package PAI.service.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.domain.teacherCategory.ITeacherCategoryFactory;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
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
    public TeacherCategory configureTeacherCategory(Name teacherCategoryName) throws Exception {

        TeacherCategory newCategory = factory.createTeacherCategory(teacherCategoryName);

        if (repository.existsByName(teacherCategoryName)) {
            throw new Exception("Teacher Category already exists or could not be registered.");
        }

        return repository.save(newCategory);
    }

    public boolean existsById(TeacherCategoryID teacherCategoryID) {
        return repository.containsOfIdentity(teacherCategoryID);
    }

    public Iterable<TeacherCategory> getAllTeacherCategories() {
        return repository.findAll();
    }
}
