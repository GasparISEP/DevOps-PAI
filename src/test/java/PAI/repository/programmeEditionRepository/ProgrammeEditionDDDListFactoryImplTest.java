package PAI.repository.programmeEditionRepository;

import PAI.domain.ProgrammeEdition;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.repository.accessMethodRepositoryDDD.AccessMethodDDDListFactoryImpl;
import PAI.repository.accessMethodRepositoryDDD.IAccessMethodDDDListFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionDDDListFactoryImplTest {

    @Test
    void shouldCreateAccessMethodListFactory(){
        // Arrange

        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();

        // Act
        Set<ProgrammeEdition> programmeEditions = programmeEditionDDDListFactory.createProgrammeEditionList();

        //assert
        assertNotNull(programmeEditions);
        assertTrue(programmeEditions.isEmpty());
        assertInstanceOf(Set.class, programmeEditions);
    }
}