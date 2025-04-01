package PAI.factory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;

public class TeacherCategoryFactoryImpl implements ITeacherCategoryFactory {

    @Override
    public TeacherCategory createTeacherCategory(Name name) {
        TeacherCategoryID id = new TeacherCategoryID();
        return new TeacherCategory(id, name);
    }
}
