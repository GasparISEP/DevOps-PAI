package PAI.assembler.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEditionEnrolment.StudentProgrammeEditionEnrolmentDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentProgrammeEditionEnrolmentAssemblerImplTest {

    private final StudentProgrammeEditionEnrolmentAssemblerImpl assembler = new StudentProgrammeEditionEnrolmentAssemblerImpl();

    @Test
    void toDTO_validProgrammeEdition_returnsCorrectDTO() throws Exception {
        // Arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new ProgrammeEditionID(programmeID, schoolYearID),
                programmeID,
                schoolYearID
        );

        // Act
        StudentProgrammeEditionEnrolmentDTO dto = assembler.toDTO(programmeEdition);

        // Assert
        assertEquals("LEI", dto.getProgrammeAcronym());
        assertEquals("Engenharia Informática", dto.getProgrammeName());
        assertEquals(schoolYearID.getSchoolYearID().toString(), dto.getSchoolYearId());
    }

    @Test
    void toDTO_nullProgrammeEdition_throwsException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toDTO(null));
    }
}
