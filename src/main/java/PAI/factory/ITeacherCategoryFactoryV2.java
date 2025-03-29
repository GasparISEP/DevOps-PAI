package PAI.factory;

import PAI.domain.TeacherCategoryV2;
import PAI.VOs.Name;

/**
 * Factory interface for creating TeacherCategoryV2 instances.
 */
public interface ITeacherCategoryFactoryV2 {
    TeacherCategoryV2 createTeacherCategory(Name name);
}