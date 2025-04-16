package PAI.persistence.datamodel;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionIdDataModelTest {

    @Test
    void shouldCreateProgrammeEditionIdDataModel() {
        //arrange
        //act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel();
        //assert
        assertNotNull(programmeEditionIdDataModel);
    }

    @Test
    void shouldCreateProgrammeEditionIdDataModelWithParameters() {
        //arrange
        Programme doubleProgramme = mock(Programme.class);
        SchoolYear doubleSchoolYear = mock(SchoolYear.class);

        NameWithNumbersAndSpecialChars doubleName = mock(NameWithNumbersAndSpecialChars.class);
        when(doubleProgramme.getProgrammeName()).thenReturn(doubleName);
        when(doubleName.toString()).thenReturn("Programme Name");

        Acronym doubleAcronym = mock(Acronym.class);
        when(doubleProgramme.getAcronym()).thenReturn(doubleAcronym);
        when(doubleAcronym.toString()).thenReturn("Acronym");

        SchoolYearID doubleSchoolYearID = mock(SchoolYearID.class);
        when(doubleSchoolYear.identity()).thenReturn(doubleSchoolYearID);
        when(doubleSchoolYearID.toString()).thenReturn(UUID.randomUUID().toString());
        //act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(doubleProgramme, doubleSchoolYear);
        //assert
        assertNotNull(programmeEditionIdDataModel);
    }

}