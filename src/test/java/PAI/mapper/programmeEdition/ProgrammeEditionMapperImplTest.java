package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionMapperImplTest {

    @Test
    void shouldCreateProgrammeEditionMapperWithParameters() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        // act
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory);
        // assert
        assertNotNull(programmeEditionMapper);
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWithProgrammeEditionFactoryNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(null));
    }

    @Test
    void shouldMapProgrammeEditionToProgrammeEditionDataModel() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        // act
        Optional<ProgrammeEditionDataModel> pEDM = programmeEditionMapper.toDataModel(programmeEdition);
        // assert
        assertTrue(pEDM.isEmpty());
    }

    @Test
    void shouldMapProgrammeEditionDataModelToProgrammeEdition() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        // act
        Optional<ProgrammeEdition> programmeEdition = programmeEditionMapper.toDomain(programmeEditionDataModel);
        // assert
        assertTrue(programmeEdition.isEmpty());
    }
}