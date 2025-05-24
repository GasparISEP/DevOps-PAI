package PAI.service.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.TeacherCategoryUpdateResponseDTO;
import PAI.dto.teacherCareerProgression.TeacherWorkingPercentageUpdateDTO;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryCommand;

import java.util.Optional;

public interface ITeacherCareerProgressionServiceV2 {
    Optional<TeacherCareerProgression> createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception;
    Optional<TeacherCareerProgression> updateTeacherCategoryInTeacherCareerProgression(UpdateTeacherCategoryCommand command) throws Exception;
    Optional<TeacherWorkingPercentageUpdateDTO> updateTeacherWorkingPercentageInTeacherCareerProgression(Date date, WorkingPercentage workingPercentage, TeacherID teacherAcronym) throws Exception;
}
