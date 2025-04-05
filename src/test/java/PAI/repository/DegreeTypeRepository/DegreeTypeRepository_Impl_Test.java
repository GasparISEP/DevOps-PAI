package PAI.repository.DegreeTypeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeType.DegreeType;
import PAI.domain.DegreeType.IDegreeTypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DegreeTypeRepository_Impl_Test {

    private IDegreeTypeFactory degreeTypeFactoryMock;
    private IDegreeTypeListFactory degreeTypeListFactoryMock;
    private DegreeTypeRepositoryImpl degreeTypeRepositoryImpl;
    private DegreeTypeID degreeTypeID;
    private Name name;
    private MaxEcts maxEcts;
    private DegreeType degreeTypeMock;

    @BeforeEach
    void setUp() {
        degreeTypeFactoryMock = mock(IDegreeTypeFactory.class);
        degreeTypeListFactoryMock = mock(IDegreeTypeListFactory.class);
        degreeTypeMock = mock(DegreeType.class);

        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);

        degreeTypeRepositoryImpl = new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        degreeTypeID = mock(DegreeTypeID.class);
        name = mock(Name.class);
        maxEcts = mock(MaxEcts.class);
    }

    @Test
    void testConstructor_NullFactory_ShouldThrowException() {
        Exception exception1 = assertThrows(NullPointerException.class, () ->
                new DegreeTypeRepositoryImpl(null, degreeTypeListFactoryMock)
        );
        assertEquals("Factory cannot be null", exception1.getMessage());

        Exception exception2 = assertThrows(NullPointerException.class, () ->
                new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, null)
        );
        assertEquals("Factory cannot be null", exception2.getMessage());
    }

    @Test
    void testRegisterDegreeType_Success() throws Exception {
        when(degreeTypeFactoryMock.addNewDegreeType_2(degreeTypeID, name, maxEcts))
                .thenReturn(degreeTypeMock);

        assertTrue(degreeTypeRepositoryImpl.registerDegreeType(degreeTypeID, name, maxEcts));
    }

    @Test
    void testRegisterDegreeType_AlreadyExists() throws Exception {
        when(degreeTypeFactoryMock.addNewDegreeType_2(degreeTypeID, name, maxEcts))
                .thenReturn(degreeTypeMock);

        degreeTypeRepositoryImpl.registerDegreeType(degreeTypeID, name, maxEcts);
        assertFalse(degreeTypeRepositoryImpl.registerDegreeType(degreeTypeID, name, maxEcts));
    }

    @Test
    void shouldReturnListOfAllDegreeTypesAvailable() {
        // Arrange
        IDegreeTypeFactory degreeTypeFactoryDouble = mock(IDegreeTypeFactory.class);
        IDegreeTypeListFactory degreeTypeListFactoryDouble = mock(IDegreeTypeListFactory.class);

        DegreeType degreeType1Double = mock(DegreeType.class);
        DegreeType degreeType2Double = mock(DegreeType.class);
        List<DegreeType> expectedList = List.of(degreeType1Double, degreeType2Double);

        when(degreeTypeListFactoryDouble.createDegreeType_2List()).thenReturn(expectedList);

            //SUT
        DegreeTypeRepositoryImpl repository = new DegreeTypeRepositoryImpl(degreeTypeFactoryDouble, degreeTypeListFactoryDouble);

        // Act
        List<DegreeType> result = repository.getAllDegreeTypes();

        // Assert
        assertEquals(expectedList, result);
        assertTrue(result.contains(degreeType1Double));
        assertTrue(result.contains(degreeType2Double));
    }
}