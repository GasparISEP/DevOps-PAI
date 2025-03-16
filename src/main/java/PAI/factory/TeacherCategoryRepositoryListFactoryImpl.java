package PAI.factory;

import PAI.domain.TeacherCategory;

import java.util.ArrayList;
import java.util.List;

public class TeacherCategoryRepositoryListFactoryImpl implements TeacherCategoryRepositoryListFactory {

    @Override
    public List <TeacherCategory> getTeacherCategoryList()  {
        return new ArrayList<>();
    }
}
