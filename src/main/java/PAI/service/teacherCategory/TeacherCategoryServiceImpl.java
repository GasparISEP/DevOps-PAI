package PAI.service.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.assembler.teacherCategory.ITeacherCategoryInternalAssembler;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.domain.teacherCategory.ITeacherCategoryFactory;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.BusinessRuleViolationException;
import PAI.utils.ValidationUtils;
import org.springframework.stereotype.Service;

@Service
public class TeacherCategoryServiceImpl implements ITeacherCategoryService {

    private final ITeacherCategoryRepository repository;
    private final ITeacherCategoryFactory factory;
    private final ITeacherCategoryInternalAssembler assembler;

    public TeacherCategoryServiceImpl(ITeacherCategoryRepository repository,
                                      ITeacherCategoryFactory factory,
                                      ITeacherCategoryInternalAssembler assembler) {

        this.repository = ValidationUtils.validateNotNull
                (repository, "Teacher Category Repository Interface") ;

        this.factory = ValidationUtils.validateNotNull
                (factory, "Teacher Category Factory Interface") ;

        this.assembler = ValidationUtils.validateNotNull
                (assembler, "Teacher Category Assembler Interface") ;
    }

    @Override
    public TeacherCategoryDTO configureTeacherCategory(Name teacherCategoryName) throws Exception {

        TeacherCategory newTeacherCategory = factory.createTeacherCategory(teacherCategoryName);

        if (repository.existsByName(teacherCategoryName)) {
            throw new AlreadyRegisteredException("Teacher Category Name");
        }

        TeacherCategory teacherCategory = repository.save(newTeacherCategory);

        return assembler.toDTO(teacherCategory);
    }

    public boolean existsById(TeacherCategoryID teacherCategoryID) {
        return repository.containsOfIdentity(teacherCategoryID);
    }

    public Iterable<TeacherCategory> getAllTeacherCategories() {
        return repository.findAll();
    }

}
