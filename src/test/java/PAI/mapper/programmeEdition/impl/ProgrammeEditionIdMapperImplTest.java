package PAI.mapper.programmeEdition.impl;

import PAI.VOs.*;
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
    void shouldMapDomainToDataModelWithProgrammeEditionId() throws Exception {
        // arrange
        IProgrammeEditionIdMapper mapper = new ProgrammeEditionIdMapperImpl();

        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        when(programmeName.toString()).thenReturn("Programme Name");

        Acronym programmeAcronym = mock(Acronym.class);
        when(programmeAcronym.toString()).thenReturn("Acronym");

        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(programmeID.getName()).thenReturn(programmeName);
        when(programmeID.getAcronym()).thenReturn(programmeAcronym);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(schoolYearID.toString()).thenReturn(UUID.randomUUID().toString());

        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
        when(programmeEditionId.getProgrammeID()).thenReturn(programmeID);
        when(programmeEditionId.getSchoolYearID()).thenReturn(schoolYearID);

        // act
        ProgrammeEditionIdDataModel result = mapper.domainToDataModel(programmeEditionId);

        // assert
        assertNotNull(result);
        assertEquals("Programme Name", result.getProgrammeName());
        assertEquals("Acronym", result.getProgrammeAcronym());
        assertEquals(schoolYearID.toString(), result.getSchoolYearId());
    }
}