package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.factory.ITeacherCategoryFactory;
import PAI.factory.ITeacherCategoryListFactory;

import java.util.List;
import java.util.Optional;

public class TeacherCategoryRepositoryImpl implements ITeacherCategoryRepository {

    private final ITeacherCategoryFactory teacherCategoryFactory;
    private final List<TeacherCategory> teacherCategories;

    public TeacherCategoryRepositoryImpl(ITeacherCategoryFactory teacherCategoryFactory,
                                         ITeacherCategoryListFactory teacherCategoryListFactory) {
        if (teacherCategoryFactory == null || teacherCategoryListFactory == null) {
            throw new IllegalArgumentException("Factory instances cannot be null.");
        }
        this.teacherCategoryFactory = teacherCategoryFactory;
        this.teacherCategories = teacherCategoryListFactory.getTeacherCategoryList();
    }

    /**
     * Attempts to register a new TeacherCategory.
     * It creates the aggregate via the factory, checks for duplicates, and then saves it.
     *
     * @param teacherCategoryName the name for the new teacher category
     * @return true if successfully registered, false if a duplicate exists or an error occurs.
     */
    @Override
    public boolean registerTeacherCategory(Name teacherCategoryName) {
        try {
            // Use factory to create a new TeacherCategory.
            TeacherCategory teacherCategory = teacherCategoryFactory.createTeacherCategory(teacherCategoryName);

            // Check for duplicate by name.
            if (existsByName(teacherCategoryName)) {
                return false;
            }

            // Persist the new TeacherCategory.
            save(teacherCategory);
            return true;
        } catch (Exception e) {
            // In a full implementation, you might log the error here.
            return false;
        }
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
                .map(TeacherCategory::getId)
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
        return teacherCategories.stream()
                .anyMatch(tc -> tc.identity().equals(id));
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
