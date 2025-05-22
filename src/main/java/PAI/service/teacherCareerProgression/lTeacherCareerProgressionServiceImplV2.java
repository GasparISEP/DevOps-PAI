package PAI.service.teacherCareerProgression;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.TeacherCareerProgressionDTO;

import java.util.Optional;

public interface lTeacherCareerProgressionServiceImplV2 {
    Optional<TeacherCareerProgression> createTeacherCareerProgression (Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage wp, TeacherID teacherID) throws Exception;
    Optional<TeacherCareerProgressionDTO> updateTeacherCategoryInTeacherCareerProgression(Date date, TeacherCategoryID teacherCategoryID, TeacherID teacherAcronym) throws Exception;

}
