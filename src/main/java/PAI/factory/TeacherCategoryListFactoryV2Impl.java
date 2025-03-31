package PAI.factory;

import PAI.domain.TeacherCategoryV2;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ITeacherCategoryListFactoryV2 that returns an empty list.
 * Useful for initializations or test environments where no data is preloaded.
 */

public class TeacherCategoryListFactoryV2Impl implements ITeacherCategoryListFactoryV2 {
    @Override
    public List<TeacherCategoryV2> getTeacherCategoryList() {
        return new ArrayList<>();
    }
}