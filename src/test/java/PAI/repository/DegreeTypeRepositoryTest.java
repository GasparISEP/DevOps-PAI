package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.DegreeTypeFactoryImpl;
import PAI.factory.DegreeTypeListFactory;
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
        DegreeTypeListFactory degreeTypeListFactory = mock (DegreeTypeListFactory.class);
        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl, degreeTypeListFactory);

        //act
        degreeTypeRepository.registerDegreeType("Master",300);

    }

    @Test
    public void shouldReturnFalseWhenDegreeTypeIsDuplicated() throws Exception {
        // Arrange
        DegreeTypeListFactory degreeTypeListFactory = mock(DegreeTypeListFactory.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(degreeTypeListFactory.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl,degreeTypeListFactory);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Master", 300);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenOnlyNameIsEqual() throws Exception {
        // Arrange
        DegreeTypeListFactory degreeTypeListFactory = mock(DegreeTypeListFactory.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(degreeTypeListFactory.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl,degreeTypeListFactory);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Master", 200);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenOnlyMaxEctsIsEqual() throws Exception {
        // Arrange
        DegreeTypeListFactory degreeTypeListFactory = mock(DegreeTypeListFactory.class);
        DegreeTypeFactoryImpl degreeTypeFactoryImpl = mock(DegreeTypeFactoryImpl.class);

        List<DegreeType> degreeTypes = spy(new ArrayList<>());

        DegreeType masterDegree = mock(DegreeType.class);

        when(degreeTypeFactoryImpl.addNewDegreeType("Master", 300)).thenReturn(masterDegree);
        when(degreeTypeListFactory.createDegreeTypeList()).thenReturn(degreeTypes);

        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryImpl,degreeTypeListFactory);

        degreeTypeRepository.registerDegreeType("Master", 300);

        //Act
        boolean result = degreeTypeRepository.registerDegreeType("Bachelor", 300);

        // Assert
        assertTrue(result);
    }

}