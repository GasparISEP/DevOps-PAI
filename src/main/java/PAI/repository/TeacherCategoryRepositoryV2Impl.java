package PAI.repository;

import PAI.domain.TeacherCategoryV2;
import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of the TeacherCategoryV2 repository.
 */
public class TeacherCategoryRepositoryV2Impl implements ITeacherCategoryRepository {

    private final List<TeacherCategoryV2> categories = new ArrayList<>();

    @Override
    public TeacherCategoryV2 save(TeacherCategoryV2 entity) {
        categories.add(entity);
        return entity;
    }

    @Override
    public Iterable<TeacherCategoryV2> findAll() {
        return new ArrayList<>(categories);
    }

    @Override
    public Optional<TeacherCategoryV2> ofIdentity(TeacherCategoryID id) {
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
}