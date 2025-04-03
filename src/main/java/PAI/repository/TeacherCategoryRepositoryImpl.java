package PAI.repository;

import PAI.domain.TeacherCategory;
import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of the TeacherCategoryV2 repository.
 */
public class TeacherCategoryRepositoryImpl implements ITeacherCategoryRepository {

    private final List<TeacherCategory> categories = new ArrayList<>();

    @Override
    public List<TeacherCategory> getTeacherCategoryList() {
        return new ArrayList<>(categories);
    }

    @Override
    public Optional<TeacherCategory> findByName(Name name) {
        return categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    @Override
    public TeacherCategory save(TeacherCategory entity) {
        categories.add(entity);
        return entity;
    }

    @Override
    public Iterable<TeacherCategory> findAll() {
        return new ArrayList<>(categories);
    }

    @Override
    public Optional<TeacherCategory> ofIdentity(TeacherCategoryID id) {
        return categories.stream()
                .filter(c -> c.identity().equals(id))
                .findFirst();
    }

    @Override
    public boolean containsOfIdentity(TeacherCategoryID id) {
        return categories.stream()
                .anyMatch(c -> c.identity().equals(id));
    }

    @Override
    public boolean existsByName(Name name) {
        return categories.stream()
                .anyMatch(c -> c.getName().equals(name));
    }

    public Optional<TeacherCategoryID> getTeacherCategoryIDFromName (Name name) {
        for (TeacherCategory category : categories) {
            if (category.getName().equals(name)) {
                return Optional.of(category.getId());
            }
        }
        return Optional.empty();
    }
}