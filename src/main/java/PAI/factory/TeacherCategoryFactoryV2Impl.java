package PAI.factory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategoryV2;

/**
 * Implementation of the TeacherCategoryV2 factory.
 */
public class TeacherCategoryFactoryV2Impl implements ITeacherCategoryFactoryV2 {

    @Override
    public TeacherCategoryV2 createTeacherCategory(Name name) {
        TeacherCategoryID id = new TeacherCategoryID();
        return new TeacherCategoryV2(id, name);
    }
}
