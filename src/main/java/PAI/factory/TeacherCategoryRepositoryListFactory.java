package PAI.factory;

import PAI.domain.TeacherCategory;
import PAI.domain.TeacherCategoryRepositoryListFactoryInterface;

import java.util.ArrayList;
import java.util.List;

public class TeacherCategoryRepositoryListFactory implements TeacherCategoryRepositoryListFactoryInterface {

    @Override
    public List <TeacherCategory> getTeacherCategoryList()  {
        return new ArrayList<>();
    }
}
