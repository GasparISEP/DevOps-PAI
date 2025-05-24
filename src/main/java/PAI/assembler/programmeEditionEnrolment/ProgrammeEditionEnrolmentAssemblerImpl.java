package PAI.assembler.programmeEditionEnrolment;

import java.util.UUID;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.VOs.StudentID;
import PAI.dto.programmeEditionEnrolment.ProgrammeEditionEnrolmentResponseDto;

public class ProgrammeEditionEnrolmentAssemblerImpl implements IProgrammeEditionEnrolmentAssembler {

    
    @Override
    public ProgrammeEditionEnrolmentID toProgrammeEditionEnrolmentID(ProgrammeEditionEnrolmentResponseDto programmeEditionEnrolmentResponseDto) throws Exception {
        if (programmeEditionEnrolmentResponseDto == null) {
            throw new Exception("Programme edition enrolment response dto is null");
        }
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(programmeEditionEnrolmentResponseDto.programmeName());
        Acronym programmeAcronym = new Acronym(programmeEditionEnrolmentResponseDto.programmeAcronym());
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);
        UUID schoolYearUUID = UUID.fromString(programmeEditionEnrolmentResponseDto.schoolYearID());
        SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        StudentID studentID = new StudentID(programmeEditionEnrolmentResponseDto.studentID());
        return new ProgrammeEditionEnrolmentID(programmeEditionID, studentID);
    }
}
