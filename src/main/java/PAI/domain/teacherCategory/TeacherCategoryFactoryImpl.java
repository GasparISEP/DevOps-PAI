package PAI.domain.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryFactoryImpl implements ITeacherCategoryFactory {

    @Override
    public TeacherCategory createTeacherCategory(Name name) {
        TeacherCategoryID id = new TeacherCategoryID();
        return new TeacherCategory(id, name);
    }
}
