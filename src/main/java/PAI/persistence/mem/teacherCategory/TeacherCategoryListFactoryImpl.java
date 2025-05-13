package PAI.persistence.mem.teacherCategory;

import PAI.domain.teacherCategory.TeacherCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ITeacherCategoryListFactory that returns an empty list.
 * Useful for initializations or test environments where no data is preloaded.
 */

public class TeacherCategoryListFactoryImpl implements ITeacherCategoryListFactory {
    @Override
    public List<TeacherCategory> getTeacherCategoryList() {
        return new ArrayList<>();
    }
}