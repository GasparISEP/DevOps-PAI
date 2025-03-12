package PAI.factory;

import PAI.domain.ProgrammeEdition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionListFactoryImplTest {

    @Test
    void shouldCreateNewArrayListInterface() {
        // Arrange
        ProgrammeEditionListFactoryImpl programmeEditionListFactoryImpl = new ProgrammeEditionListFactoryImpl();

        // Act
        List<ProgrammeEdition> listProgrammeEditions = programmeEditionListFactoryImpl.createProgrammeEditionArrayList();

        // Assert
        assertNotNull(listProgrammeEditions);
        assertInstanceOf(ArrayList.class, listProgrammeEditions);
    }
}