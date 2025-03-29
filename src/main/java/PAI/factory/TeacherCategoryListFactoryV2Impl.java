package PAI.factory;

import PAI.domain.TeacherCategoryV2;

import java.util.ArrayList;
import java.util.List;

public class TeacherCategoryListFactoryV2Impl implements ITeacherCategoryListFactoryV2 {
    @Override
    public List<TeacherCategoryV2> getTeacherCategoryList() {
        return new ArrayList<>();
    }
}