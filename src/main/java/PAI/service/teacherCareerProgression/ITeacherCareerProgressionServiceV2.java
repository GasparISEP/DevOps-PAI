package PAI.service.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageCommand;

import java.util.Optional;

public interface ITeacherCareerProgressionServiceV2 {
    Optional<TeacherCareerProgression> createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception;
    UpdateTeacherCategoryDTO updateTeacherCategory(UpdateTeacherCategoryCommand command) throws Exception;
    Optional<TeacherCareerProgression> updateTeacherWorkingPercentageInTeacherCareerProgression(UpdateTeacherWorkingPercentageCommand command) throws Exception;
}
