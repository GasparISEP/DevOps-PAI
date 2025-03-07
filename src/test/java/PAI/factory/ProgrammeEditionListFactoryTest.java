package PAI.factory;

import PAI.domain.ProgrammeEdition;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionListFactoryTest {

    @Test
    void shouldCreateNewArrayListInterface() {
        // Arrange
        ProgrammeEditionListFactory programmeEditionListFactory = new ProgrammeEditionListFactory();

        // Act
        List<ProgrammeEdition> listProgrammeEditions = programmeEditionListFactory.createProgrammeEditionArrayList();

        // Assert
        assertNotNull(listProgrammeEditions);
        assertInstanceOf(ArrayList.class, listProgrammeEditions);
    }
}