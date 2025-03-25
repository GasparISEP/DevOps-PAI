package PAI.factory;

import PAI.domain.TeacherCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link ITeacherCategoryListFactory} interface.
 * This class is responsible for providing a list of {@link TeacherCategory} instances.
 */
public class TeacherCategoryListFactoryImpl implements ITeacherCategoryListFactory {

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves an empty list of {@link TeacherCategory} objects.
     * This implementation simply returns a new empty {@link ArrayList}.
     * </p>
     *
     * @return an empty {@link List} of {@link TeacherCategory} objects.
     */
    @Override
    public List <TeacherCategory> getTeacherCategoryList()  {
        return new ArrayList<>();
    }
}
