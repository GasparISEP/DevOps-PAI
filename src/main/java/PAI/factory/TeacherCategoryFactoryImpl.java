package PAI.factory;

import PAI.domain.TeacherCategory;

/**
 * Implementation of the {@link TeacherCategoryFactory} interface.
 * This class is responsible for creating instances of {@link TeacherCategory}.
 */
public class TeacherCategoryFactoryImpl implements TeacherCategoryFactory {

    /**
     * Creates a new {@link TeacherCategory} instance with the specified category name.
     *
     * @param categoryName the name of the teacher category to be created.
     * @return a new {@link TeacherCategory} object with the given category name.
     * @throws Exception if an error occurs during the creation of the teacher category.
     */
    @Override
    public TeacherCategory createTeacherCategory(String categoryName) throws Exception {
        return new TeacherCategory(categoryName);
    }
}
