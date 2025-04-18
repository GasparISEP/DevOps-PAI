package PAI.mapper.programmeEdition.impl;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionIdMapperImplTest {

    @Test
    void shouldReturnProgrammeEditionIdDataModelToDomain() throws Exception {
        //arrange
        IProgrammeEditionIdMapper mapper = new ProgrammeEditionIdMapperImpl();
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        when(programmeEditionIdDataModel.getProgrammeName()).thenReturn("ProgrammeName");
        when(programmeEditionIdDataModel.getProgrammeAcronym()).thenReturn("LEI");

        when(programmeEditionIdDataModel.getSchoolYearId()).thenReturn(UUID.randomUUID().toString());
        //act
        ProgrammeEditionID programmeEditionId = mapper.dataModelToDomain(programmeEditionIdDataModel);
        //assert
        assertNotNull(programmeEditionId);
    }

    @Test
    void shouldReturnDomainToDataModel() throws Exception {
        //arrange
        IProgrammeEditionIdMapper mapper = new ProgrammeEditionIdMapperImpl();
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
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mapper.domainToDataModel(doubleProgramme, doubleSchoolYear);
        //assert
        assertNotNull(programmeEditionIdDataModel);
    }
}