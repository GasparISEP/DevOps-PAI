package PAI.VOs;

import PAI.domain.programme.Programme;

public record ProgrammeSummary (
        ProgrammeID programmeID,
        NameWithNumbersAndSpecialChars programmeName,
        ProgrammeEnrolmentGeneratedID generatedID
){
}
