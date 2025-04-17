package PAI.service;

import PAI.VOs.Name;
import PAI.repository.ITeacherCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherCategoryApplicationService {

    private final ITeacherCategoryRepository repository;

    public TeacherCategoryApplicationService(ITeacherCategoryRepository repository) {
        if (repository == null) throw new IllegalArgumentException("Repository cannot be null.");
        this.repository = repository;
    }

    public boolean registerCategory(String categoryName) throws Exception {
        Name name = new Name(categoryName);
        boolean created = repository.registerTeacherCategory(name);
        if (!created) {
            throw new Exception("Category already exists or could not be registered.");
        }
        return true;
    }
}
