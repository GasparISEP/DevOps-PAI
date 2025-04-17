package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.ddd.IRepository;
import PAI.domain.TeacherCategory;

import java.util.List;
import java.util.Optional;

/**
 * Interface for the TeacherCategory repository.
 * This interface extends the IRepository interface and provides methods specific to TeacherCategory.
 */

public interface ITeacherCategoryRepository extends IRepository<TeacherCategoryID, TeacherCategory> {
    boolean existsByName(Name name);

    // New method: registers a new teacher category.
    boolean registerTeacherCategory(Name teacherCategoryName);

    List<TeacherCategory> getTeacherCategoryList();

    Optional<TeacherCategory> findByName(Name name);

    Optional<TeacherCategoryID> getTeacherCategoryIDFromName(Name name);
}