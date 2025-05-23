package PAI.dto.teacherCareerProgression;
import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeacherCareerProgressionAssembler implements ITeacherCareerProgressionAssembler {

    public TeacherCareerProgressionAssembler() {
    }

    public TeacherCareerProgression toDomain(TeacherCareerProgressionDTO teacherCareerProgressionDTO) {
        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.fromString(teacherCareerProgressionDTO.getTcpid()));
        Date date = new Date(teacherCareerProgressionDTO.getDate());
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.fromString(teacherCareerProgressionDTO.getTcid()));
        WorkingPercentage wp = new WorkingPercentage(teacherCareerProgressionDTO.getWorkingpercentage());
        TeacherID teacherID = new TeacherID(new TeacherAcronym(teacherCareerProgressionDTO.getTeacherid()));

        return new TeacherCareerProgression (teacherCareerProgressionID, date,teacherCategoryID, wp,teacherID);
    }

    public TeacherWorkingPercentageUpdateDTO toTeacherWorkingPercentageUpdateDTO(TeacherCareerProgression teacherCareerProgression) {
        String date = teacherCareerProgression.getDate().toString();
        int wp = teacherCareerProgression.getWorkingPercentage().getValue();
        String teacherId = teacherCareerProgression.getTeacherID().toString();

        return new TeacherWorkingPercentageUpdateDTO(date,wp,teacherId);
    }

    public TeacherCareerProgressionID toTeacherCareerProgressionID(TeacherCareerProgressionDTO teacherCareerProgressionDTO) {
        return new TeacherCareerProgressionID(UUID.fromString(teacherCareerProgressionDTO.getTcpid()));
    }

    public Date todate (TeacherCareerProgressionDTO teacherCareerProgressionDTO) {
        return new Date(teacherCareerProgressionDTO.getDate());
    }

    public TeacherCategoryID toTeacherCategoryID (TeacherCareerProgressionDTO teacherCareerProgressionDTO) {
        return new TeacherCategoryID(UUID.fromString(teacherCareerProgressionDTO.getTcid()));
    }

    public WorkingPercentage toWorkingPercentage (TeacherCareerProgressionDTO teacherCareerProgressionDTO) {
        return new WorkingPercentage(teacherCareerProgressionDTO.getWorkingpercentage());
    }

    public TeacherID toTeacherID (TeacherCareerProgressionDTO teacherCareerProgressionDTO) {
        return new TeacherID(new TeacherAcronym(teacherCareerProgressionDTO.getTeacherid()));
    }

    public TeacherCategoryUpdateResponseDTO UpdateCategoryToDTO (TeacherCareerProgression teacherCareerProgression){

        String date = teacherCareerProgression.getDate().toString();
        String teacherID = teacherCareerProgression.getTeacherID().toString();
        String teacherCategory = teacherCareerProgression.getTeacherCategoryID().toString();
        int workingPercent = teacherCareerProgression.getWorkingPercentage().getValue();
        return new TeacherCategoryUpdateResponseDTO(date,teacherID,teacherCategory,workingPercent);
    }
}
