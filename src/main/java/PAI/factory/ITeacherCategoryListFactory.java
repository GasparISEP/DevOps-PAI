package PAI.factory;

import PAI.domain.TeacherCategory;

import java.util.List;

/**
 * Interface for retrieving a list of teacher categories.
 * This interface defines the contract for getting a list of {@link TeacherCategory} instances.
 */
public interface ITeacherCategoryListFactory {

    /**
     * Retrieves a list of all available teacher categories.
     *
     * @return a {@link List} of {@link TeacherCategory} objects.
     */
    List<TeacherCategory> getTeacherCategoryList();
}
