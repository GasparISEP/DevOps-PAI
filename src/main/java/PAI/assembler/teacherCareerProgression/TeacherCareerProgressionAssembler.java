package PAI.assembler.teacherCareerProgression;
import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;

import PAI.dto.teacherCareerProgression.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeacherCareerProgressionAssembler implements ITeacherCareerProgressionAssembler {

    public TeacherCareerProgressionAssembler() {
    }

    @Override
    public UpdateTeacherWorkingPercentageResponseDTO toUpdateWorkingPercentageDTO(TeacherCareerProgression teacherCareerProgression){
        String date = teacherCareerProgression.getDate().toString();
        String teacherID = teacherCareerProgression.getTeacherID().getTeacherAcronym().getAcronym();
        String teacherCategoryID = teacherCareerProgression.getTeacherCategoryID().toString();
        int workingPercentage = teacherCareerProgression.getWorkingPercentage().getValue();
        return new UpdateTeacherWorkingPercentageResponseDTO(date,teacherID,teacherCategoryID,workingPercentage);
    }
    @Override
    public UpdateTeacherWorkingPercentageCommand toUpdateTeacherWorkingPercentageCommand(String teacherIDStr, UpdateTeacherWorkingPercentageRequestDTO request) {
        Date date = new Date(request.date());
        TeacherID teacherID = new TeacherID(new TeacherAcronym(teacherIDStr));
        WorkingPercentage workingPercentage = new WorkingPercentage(request.workingPercentage());
        return new UpdateTeacherWorkingPercentageCommand(date,teacherID,workingPercentage);
    }


    @Override
    public UpdateTeacherCategoryResponseDTO toUpdateTeacherCategoryResponseDTO(UpdateTeacherCategoryDTO updateTeacherCategoryDTO){

        String date = updateTeacherCategoryDTO.date();
        String teacherID = updateTeacherCategoryDTO.teacherID();
        String teacherCategoryID = updateTeacherCategoryDTO.teacherCategoryID();
        int workingPercentage = updateTeacherCategoryDTO.workingPercentage();

        return new UpdateTeacherCategoryResponseDTO(date,teacherID,teacherCategoryID,workingPercentage);
    }

    @Override
    public UpdateTeacherCategoryCommand toUpdateTeacherCategoryCommand(String teacherId, UpdateTeacherCategoryRequestDTO request) {

        Date date = new Date(request.date());
        TeacherID teacherID = new TeacherID(new TeacherAcronym(teacherId));
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.fromString(request.teacherCategoryID()));

        return new UpdateTeacherCategoryCommand(date,teacherID,teacherCategoryID);
    }
}
