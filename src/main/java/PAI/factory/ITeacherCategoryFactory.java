package PAI.factory;

import PAI.domain.TeacherCategory;
import PAI.VOs.Name;

/**
 * Factory interface for creating TeacherCategoryV2 instances.
 */
public interface ITeacherCategoryFactory {
    TeacherCategory createTeacherCategory(Name name);
}