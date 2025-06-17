package PAI.dto.courseEdition;

import PAI.VOs.TeacherID;

import java.time.LocalDate;
import java.util.UUID;

public record CourseEditionServiceResponseDTO(

        UUID courseEditionGeneratedID,
        String programmeAcronym,
        UUID schoolYearID,
        String courseAcronym,
        String courseName,
        LocalDate studyPlanImplementationDate,
        String courseEditionID,
        String teacherID

) {
}
