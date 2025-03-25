package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.IDegreeTypeListFactory;
import PAI.factory.DegreeTypeFactoryImpl;
import PAI.factory.IDegreeTypeFactoryInterface;
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
        IDegreeTypeListFactory IDegreeTypeListFactory = mock (IDegreeTypeListFactory.class);
        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl, IDegreeTypeListFactory);

        //act
        degreeTypeRepository.registerDegreeType("Master",300);

    }

    @Test
    public void DoesntCreateListWithNullDTLFI() {

        IDegreeTypeFactoryInterface IDegreeTypeFactoryInterface = mock(IDegreeTypeFactoryInterface.class);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DegreeTypeRepository(IDegreeTypeFactoryInterface, null));

        assertEquals("Factory cannot be null!", exception.getMessage());
    }

    @Test
    public void shouldReturnFalseWhenDegreeTypeIsDuplicated() throws Exception {
        // Arrange
        IDegreeTypeListFactory IDegreeTypeListFactory = mock(IDegreeTypeListFactory.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(IDegreeTypeListFactory.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl, IDegreeTypeListFactory);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Master", 300);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenOnlyNameIsEqual() throws Exception {
        // Arrange
        IDegreeTypeListFactory IDegreeTypeListFactory = mock(IDegreeTypeListFactory.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(IDegreeTypeListFactory.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl, IDegreeTypeListFactory);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Master", 200);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenOnlyMaxEctsIsEqual() throws Exception {
        // Arrange
        IDegreeTypeListFactory IDegreeTypeListFactory = mock(IDegreeTypeListFactory.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(IDegreeTypeListFactory.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl, IDegreeTypeListFactory);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Bachelor", 300);

        // Assert
        assertTrue(result);
    }

}