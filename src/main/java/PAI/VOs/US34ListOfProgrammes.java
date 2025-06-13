package PAI.VOs;

import PAI.domain.programme.Programme;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.List;

public record US34ListOfProgrammes (
        List<Programme> programme,
        List<ProgrammeEnrolment> programmeEnrolment
) {
}
