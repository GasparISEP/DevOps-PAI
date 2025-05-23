package PAI.assembler.programmeEditionEnrolment;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentProgrammeEditionEnrolmentAssemblerImpl implements IStudentProgrammeEditionEnrolmentAssembler {

    public StudentProgrammeEditionEnrolmentDTO toDTO(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null) {
            throw new IllegalArgumentException("ProgrammeEdition cannot be null");
        }

        ProgrammeID programmeID = programmeEdition.findProgrammeIDInProgrammeEdition();
        SchoolYearID schoolYearID = programmeEdition.findSchoolYearIDInProgrammeEdition();

        return new StudentProgrammeEditionEnrolmentDTO(
                programmeID.getAcronym().toString(),
                programmeID.getName().toString(),
                schoolYearID.getSchoolYearID().toString()
        );
    }
}
