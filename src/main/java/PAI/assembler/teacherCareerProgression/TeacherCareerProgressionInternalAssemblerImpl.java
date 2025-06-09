package PAI.assembler.teacherCareerProgression;

import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.dto.teacherCareerProgression.UpdateTeacherCategoryDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherCareerProgressionInternalAssemblerImpl implements ITeacherCareerProgressionInternalAssembler{

    @Override
    public UpdateTeacherCategoryDTO toDTO(TeacherCareerProgression teacherCareerProgression) {

        if (teacherCareerProgression == null) {
            throw new IllegalArgumentException("Teacher Career Progression cannot be null");
        }

        String date = teacherCareerProgression.getDate().toString();
        String teacherID = teacherCareerProgression.getTeacherID().toString();
        String teacherCategoryID = teacherCareerProgression.getTeacherCategoryID().toString();
        int workingPercentage = teacherCareerProgression.getWorkingPercentage().getValue();

        return new UpdateTeacherCategoryDTO(date, teacherID, teacherCategoryID, workingPercentage);
    }

    public List<UpdateTeacherCategoryDTO> toDTOList (Iterable<TeacherCareerProgression> teacherCareerProgressions) {
        if (teacherCareerProgressions == null) {
            throw new IllegalArgumentException("Teacher Career Progression List cannot be null");
        }

        List<UpdateTeacherCategoryDTO> listDTOs = new ArrayList<>();

        for (TeacherCareerProgression teacherCareerProgression : teacherCareerProgressions) {
            UpdateTeacherCategoryDTO updateTeacherCategoryDTO = toDTO(teacherCareerProgression);
            listDTOs.add(updateTeacherCategoryDTO);
        }
        return listDTOs;
    }
}
