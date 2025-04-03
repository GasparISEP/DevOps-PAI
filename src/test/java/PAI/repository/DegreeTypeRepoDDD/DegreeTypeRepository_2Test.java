package PAI.repository.DegreeTypeRepoDDD;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeTypeDDD.DegreeType_2;
import PAI.factory.IDegreeTypeFactoryInterface_2;
import PAI.factory.IDegreeTypeListFactory_2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

class DegreeTypeRepository_2Test {

    private IDegreeTypeFactoryInterface_2 degreeTypeFactoryMock;
    private IDegreeTypeListFactory_2 degreeTypeListFactoryMock;
    private DegreeTypeRepository_2 degreeTypeRepository;
    private DegreeTypeID degreeTypeID;
    private Name name;
    private MaxEcts maxEcts;
    private DegreeType_2 degreeTypeMock;

    @BeforeEach
    void setUp() {
        degreeTypeFactoryMock = mock(IDegreeTypeFactoryInterface_2.class);
        degreeTypeListFactoryMock = mock(IDegreeTypeListFactory_2.class);
        degreeTypeMock = mock(DegreeType_2.class);

        List<DegreeType_2> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);

        degreeTypeRepository = new DegreeTypeRepository_2(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        degreeTypeID = mock(DegreeTypeID.class);
        name = mock(Name.class);
        maxEcts = mock(MaxEcts.class);
    }

    @Test
    void testConstructor_NullFactory_ShouldThrowException() {
        Exception exception1 = assertThrows(NullPointerException.class, () ->
                new DegreeTypeRepository_2(null, degreeTypeListFactoryMock)
        );
        assertEquals("Factory cannot be null", exception1.getMessage());

        Exception exception2 = assertThrows(NullPointerException.class, () ->
                new DegreeTypeRepository_2(degreeTypeFactoryMock, null)
        );
        assertEquals("Factory cannot be null", exception2.getMessage());
    }

    @Test
    void testRegisterDegreeType_Success() throws Exception {
        when(degreeTypeFactoryMock.addNewDegreeType_2(degreeTypeID, name, maxEcts))
                .thenReturn(degreeTypeMock);

        assertTrue(degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts));
    }

    @Test
    void testRegisterDegreeType_AlreadyExists() throws Exception {
        when(degreeTypeFactoryMock.addNewDegreeType_2(degreeTypeID, name, maxEcts))
                .thenReturn(degreeTypeMock);

        degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts);
        assertFalse(degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts));
    }

    @Test
    void shouldReturnListOfAllDegreeTypesAvailable() {
        // Arrange
        IDegreeTypeFactoryInterface_2 degreeTypeFactoryDouble = mock(IDegreeTypeFactoryInterface_2.class);
        IDegreeTypeListFactory_2 degreeTypeListFactoryDouble = mock(IDegreeTypeListFactory_2.class);

        DegreeType_2 degreeType1Double = mock(DegreeType_2.class);
        DegreeType_2 degreeType2Double = mock(DegreeType_2.class);
        List<DegreeType_2> expectedList = List.of(degreeType1Double, degreeType2Double);

        when(degreeTypeListFactoryDouble.createDegreeType_2List()).thenReturn(expectedList);

            //SUT
        DegreeTypeRepository_2 repository = new DegreeTypeRepository_2(degreeTypeFactoryDouble, degreeTypeListFactoryDouble);

        // Act
        List<DegreeType_2> result = repository.getAllDegreeTypes();

        // Assert
        assertEquals(expectedList, result);
        assertTrue(result.contains(degreeType1Double));
        assertTrue(result.contains(degreeType2Double));
    }
}