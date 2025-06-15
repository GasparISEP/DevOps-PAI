package PAI.dto.programmeEnrolment;

import java.util.List;

public record US34ListOfProgrammesDTO (
        List<ProgrammeSummaryDTO> programmeInfo,
        String studentName
){
}
