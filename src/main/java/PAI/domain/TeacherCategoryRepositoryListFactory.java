package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class TeacherCategoryRepositoryListFactory implements TeacherCategoryRepositoryListFactoryInterface{

    @Override
    public List <TeacherCategory> getTeacherCategoryList()  {
        return new ArrayList<>();
    }
}
