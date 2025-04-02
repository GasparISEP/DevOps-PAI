package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.ddd.IRepository;
import PAI.domain.TeacherCategory;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for TeacherCategoryV2.
 */
public interface ITeacherCategoryRepository extends IRepository<TeacherCategoryID, TeacherCategory> {
    boolean existsByName(Name name);

    List<TeacherCategory> getTeacherCategoryList();
    Optional<TeacherCategory> findByName(Name name);
}
