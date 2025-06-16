package PAI.VOs;
import java.util.List;

public record US34ListOfProgrammes (
        List<ProgrammeSummary> programmeInfo,
        Name studentName
) {
}
