package PAI.dto.teacherCareerProgression;
import PAI.VOs.*;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeacherCareerProgressionAssembler {

    public TeacherCareerProgressionAssembler() {
    }

    public TeacherCareerProgression toTeacherCareerProgression(TeacherCareerProgressionDTO teacherCareerProgressionDTO) {
        TeacherCareerProgressionID teacherCareerProgressionID = new TeacherCareerProgressionID(UUID.fromString(teacherCareerProgressionDTO.getTcpid()));
        Date date = new Date(teacherCareerProgressionDTO.getDate());
        TeacherCategoryID teacherCategoryID = new TeacherCategoryID(UUID.fromString(teacherCareerProgressionDTO.getTcid()));
        WorkingPercentage wp = new WorkingPercentage(teacherCareerProgressionDTO.getWorkingpercentage());
        TeacherID teacherID = new TeacherID(new TeacherAcronym(teacherCareerProgressionDTO.getTeacherid()));

        return new TeacherCareerProgression (teacherCareerProgressionID, date,teacherCategoryID, wp,teacherID);
    }

    public TeacherCareerProgressionResponseDTO toTeacherCareerProgressionDTO(TeacherCareerProgression teacherCareerProgression) {
        String tcpid = teacherCareerProgression.getID().toString();
        String date = teacherCareerProgression.getDate().toString();
        String tcid = teacherCareerProgression.getTeacherCategoryID().toString();
        int wp = teacherCareerProgression.getWorkingPercentage().getValue();
        String teacherid = teacherCareerProgression.getTeacherID().toString();

        return new TeacherCareerProgressionResponseDTO(tcpid,date, tcid,wp,teacherid);
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
}
