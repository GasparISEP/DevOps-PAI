package PAI.factory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryFactorySpring implements ITeacherCategoryFactory {
    @Override
    public TeacherCategory createTeacherCategory(Name name) {
        return new TeacherCategory(new TeacherCategoryID(), name);
    }
}