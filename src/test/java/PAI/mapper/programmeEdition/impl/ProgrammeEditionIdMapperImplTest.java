package PAI.mapper.programmeEdition.impl;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeEditionID;
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
}