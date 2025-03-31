package PAI.repository;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.ddd.IRepository;
import PAI.domain.TeacherCategoryV2;

/**
 * Repository interface for TeacherCategoryV2.
 */
public interface ITeacherCategoryRepository extends IRepository<TeacherCategoryID, TeacherCategoryV2> {
    boolean existsByName(Name name);
}
