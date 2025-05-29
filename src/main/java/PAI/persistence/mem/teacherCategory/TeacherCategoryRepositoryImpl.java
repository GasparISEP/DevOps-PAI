package PAI.persistence.mem.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;

import java.util.List;
import java.util.Optional;

public class TeacherCategoryRepositoryImpl implements ITeacherCategoryRepository {

    private final List<TeacherCategory> teacherCategories;

    public TeacherCategoryRepositoryImpl(ITeacherCategoryListFactory teacherCategoryListFactory) {

        if (teacherCategoryListFactory == null) {
            throw new IllegalArgumentException("Factory instances cannot be null.");
        }

        this.teacherCategories = teacherCategoryListFactory.getTeacherCategoryList();
    }

    @Override
    public TeacherCategory save(TeacherCategory teacherCategory) {
        teacherCategories.add(teacherCategory);
        return teacherCategory;
    }

    @Override
    public Iterable<TeacherCategory> findAll() {
        return teacherCategories;
    }

    @Override
    public Optional<TeacherCategory> findByName(Name name) {
        return teacherCategories.stream()
                .filter(tc -> tc.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<TeacherCategoryID> getTeacherCategoryIDFromName(Name name) {
        return teacherCategories.stream()
                .filter(tc -> tc.getName().equals(name))
                .map(TeacherCategory::identity)
                .findFirst();
    }

    @Override
    public Optional<TeacherCategory> ofIdentity(TeacherCategoryID id) {
        return teacherCategories.stream()
                .filter(tc -> tc.identity().equals(id))
                .findFirst();
    }

    @Override
    public boolean containsOfIdentity(TeacherCategoryID id) {
        if (!ofIdentity(id).isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existsByName(Name name) {
        return teacherCategories.stream()
                .anyMatch(tc -> tc.getName().equals(name));
    }

    @Override
    public List<TeacherCategory> getTeacherCategoryList() {
        return teacherCategories;
    }
}
