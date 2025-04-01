package PAI.repository.DegreeTypeRepoDDD;

import static org.junit.jupiter.api.Assertions.*;

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
        degreeTypeFactoryMock = Mockito.mock(IDegreeTypeFactoryInterface_2.class);
        degreeTypeListFactoryMock = Mockito.mock(IDegreeTypeListFactory_2.class);
        degreeTypeMock = Mockito.mock(DegreeType_2.class);

        List<DegreeType_2> degreeTypeList = new ArrayList<>();
        Mockito.when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);

        degreeTypeRepository = new DegreeTypeRepository_2(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        degreeTypeID = Mockito.mock(DegreeTypeID.class);
        name = Mockito.mock(Name.class);
        maxEcts = Mockito.mock(MaxEcts.class);
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
        Mockito.when(degreeTypeFactoryMock.addNewDegreeType_2(degreeTypeID, name, maxEcts))
                .thenReturn(degreeTypeMock);

        assertTrue(degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts));
    }

    @Test
    void testRegisterDegreeType_AlreadyExists() throws Exception {
        Mockito.when(degreeTypeFactoryMock.addNewDegreeType_2(degreeTypeID, name, maxEcts))
                .thenReturn(degreeTypeMock);

        degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts);
        assertFalse(degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts));
    }
}