package PAI.dto.Teacher;

import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import org.springframework.stereotype.Component;

@Component
public class TeacherWithRelevantDataAssembler {

    public TeacherWithRelevantDataAssembler() {}

    public TeacherWithRelevantDataDTO toDTOWithNameAndCategory(Teacher teacher, TeacherCategory teacherCategory, TeacherCareerProgression teacherCareerProgression){
        String name = teacher.getName().getName();
        String acronym = teacher.getTeacherID().toString();
        String category = teacherCategory.getName().getName();
        int workingPercentage = teacherCareerProgression.getWorkingPercentage().getValue();
        return new TeacherWithRelevantDataDTO(name, acronym, category, workingPercentage);
    }
}
