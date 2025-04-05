package PAI.repository.DegreeTypeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeTypeDDD.DegreeType;
import PAI.domain.DegreeTypeDDD.IDegreeTypeFactoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DegreeTypeRepository_Test {

    private IDegreeTypeFactoryInterface degreeTypeFactoryMock;
    private IDegreeTypeListFactory degreeTypeListFactoryMock;
    private DegreeTypeRepository degreeTypeRepository;
    private DegreeTypeID degreeTypeID;
    private Name name;
    private MaxEcts maxEcts;
    private DegreeType degreeTypeMock;

    @BeforeEach
    void setUp() {
        degreeTypeFactoryMock = mock(IDegreeTypeFactoryInterface.class);
        degreeTypeListFactoryMock = mock(IDegreeTypeListFactory.class);
        degreeTypeMock = mock(DegreeType.class);

        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);

        degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        degreeTypeID = mock(DegreeTypeID.class);
        name = mock(Name.class);
        maxEcts = mock(MaxEcts.class);
    }

    @Test
    void testConstructor_NullFactory_ShouldThrowException() {
        Exception exception1 = assertThrows(NullPointerException.class, () ->
                new DegreeTypeRepository(null, degreeTypeListFactoryMock)
        );
        assertEquals("Factory cannot be null", exception1.getMessage());

        Exception exception2 = assertThrows(NullPointerException.class, () ->
                new DegreeTypeRepository(degreeTypeFactoryMock, null)
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
        IDegreeTypeFactoryInterface degreeTypeFactoryDouble = mock(IDegreeTypeFactoryInterface.class);
        IDegreeTypeListFactory degreeTypeListFactoryDouble = mock(IDegreeTypeListFactory.class);

        DegreeType degreeType1Double = mock(DegreeType.class);
        DegreeType degreeType2Double = mock(DegreeType.class);
        List<DegreeType> expectedList = List.of(degreeType1Double, degreeType2Double);

        when(degreeTypeListFactoryDouble.createDegreeType_2List()).thenReturn(expectedList);

            //SUT
        DegreeTypeRepository repository = new DegreeTypeRepository(degreeTypeFactoryDouble, degreeTypeListFactoryDouble);

        // Act
        List<DegreeType> result = repository.getAllDegreeTypes();

        // Assert
        assertEquals(expectedList, result);
        assertTrue(result.contains(degreeType1Double));
        assertTrue(result.contains(degreeType2Double));
    }
}