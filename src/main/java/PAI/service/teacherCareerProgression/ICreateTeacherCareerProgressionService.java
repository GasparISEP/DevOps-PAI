package PAI.service.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherWorkingPercentageCommand;

import java.util.List;
import java.util.Optional;

public interface ICreateTeacherCareerProgressionService {

    Optional<TeacherCareerProgression> createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception;

    UpdateTeacherCategoryDTO updateTeacherCategory(UpdateTeacherCategoryCommand command) throws Exception;

    Optional<TeacherCareerProgression> updateTeacherWorkingPercentageInTeacherCareerProgression(UpdateTeacherWorkingPercentageCommand command) throws Exception;

    List<UpdateTeacherCategoryDTO> getAllTeacherCareerProgression () throws Exception;

    UpdateTeacherCategoryDTO getTeacherCareerProgressionByID (TeacherCareerProgressionID id);
}

