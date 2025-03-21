package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.DegreeTypeFactoryImpl;
import PAI.factory.DegreeTypeFactoryInterface;
import PAI.factory.DegreeTypeListFactoryInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class DegreeTypeRepositoryTest {
    @Test
    public void createList() throws Exception {

        //arrange

        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock (DegreeTypeFactoryImpl.class);
        DegreeTypeListFactoryInterface degreeTypeListFactoryInterface = mock (DegreeTypeListFactoryInterface.class);
        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl, degreeTypeListFactoryInterface);

        //act
        degreeTypeRepository.registerDegreeType("Master",300);

    }

    @Test
    public void DoesntCreateListWithNullDTLFI() {

        DegreeTypeFactoryInterface degreeTypeFactoryInterface = mock(DegreeTypeFactoryInterface.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DegreeTypeRepository(degreeTypeFactoryInterface, null));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

    @Test
    public void shouldReturnFalseWhenDegreeTypeIsDuplicated() throws Exception {
        // Arrange
        DegreeTypeListFactoryInterface degreeTypeListFactoryInterface = mock(DegreeTypeListFactoryInterface.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(degreeTypeListFactoryInterface.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl,degreeTypeListFactoryInterface);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Master", 300);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenOnlyNameIsEqual() throws Exception {
        // Arrange
        DegreeTypeListFactoryInterface degreeTypeListFactoryInterface = mock(DegreeTypeListFactoryInterface.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(degreeTypeListFactoryInterface.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl,degreeTypeListFactoryInterface);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Master", 200);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenOnlyMaxEctsIsEqual() throws Exception {
        // Arrange
        DegreeTypeListFactoryInterface degreeTypeListFactoryInterface = mock(DegreeTypeListFactoryInterface.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(degreeTypeListFactoryInterface.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl,degreeTypeListFactoryInterface);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Bachelor", 300);

        // Assert
        assertTrue(result);
    }

}