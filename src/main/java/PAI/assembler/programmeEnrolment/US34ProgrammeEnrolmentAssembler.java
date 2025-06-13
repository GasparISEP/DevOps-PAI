package PAI.assembler.programmeEnrolment;

import PAI.VOs.ProgrammeSummary;
import PAI.VOs.US34ListOfProgrammes;
import PAI.dto.programmeEnrolment.ProgrammeSummaryDTO;
import PAI.dto.programmeEnrolment.US34ListOfProgrammesDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class US34ProgrammeEnrolmentAssembler implements IUS34ProgrammeEnrolmentAssembler {

    public US34ListOfProgrammesDTO toDto(US34ListOfProgrammes domain) {
        String name = domain.studentName().getName();

        List<ProgrammeSummaryDTO> progSummaryDTOs = new ArrayList<>();
        for (ProgrammeSummary ps : domain.programmeInfo()) {
            progSummaryDTOs.add(new ProgrammeSummaryDTO(
                    ps.programmeID().getProgrammeAcronym(),
                    ps.programmeName().getNameWithNumbersAndSpecialChars(),
                    ps.generatedID().getProgrammeEnrolmentGID().toString()
            ));
        }

        return new US34ListOfProgrammesDTO(progSummaryDTOs,name);
    }
}
