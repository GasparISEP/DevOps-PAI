package PAI.factory;

import PAI.domain.TeacherCategory;

/**
 * Interface for creating teacher categories.
 * This interface defines the contract for creating instances of {@link TeacherCategory}.
 */
    public interface ITeacherCategoryFactory {

    /**
     * Creates a new teacher category with the specified name.
     *
     * @param categoryName the name of the teacher category to be created.
     * @return a {@link TeacherCategory} object with the given name.
     * @throws Exception if an error occurs during the creation of the teacher category.
     */
        TeacherCategory createTeacherCategory(String categoryName) throws Exception;
    }

