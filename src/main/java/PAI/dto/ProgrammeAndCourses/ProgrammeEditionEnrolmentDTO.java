package PAI.dto.ProgrammeAndCourses;

import java.util.UUID;

public record ProgrammeEditionEnrolmentDTO(
        int studentId,
        String programmeAcronym,
        String schoolYearId,
        UUID genID
) {
}
