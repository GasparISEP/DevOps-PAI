package PAI.repository.degreeTypeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.IDegreeTypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class DegreeTypeRepositoryImplTest {

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

    @Test
    void testSaveAddsDegreeTypeAndReturnsIt() throws Exception {
        // Criar um repositório com uma lista real
        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);
        degreeTypeRepositoryImpl = new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        DegreeType degreeType = mock(DegreeType.class);
        DegreeType savedDegreeType = degreeTypeRepositoryImpl.save(degreeType);

        assertEquals(degreeType, savedDegreeType, "O método save deve retornar o mesmo DegreeType passado");
        assertTrue(degreeTypeRepositoryImpl.getAllDegreeTypes().contains(degreeType), "O DegreeType deve ser adicionado ao repositório");
    }

    @Test
    void testFindAllReturnsAllSavedDegreeTypes() throws Exception {
        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);
        degreeTypeRepositoryImpl = new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        DegreeType degreeType1 = mock(DegreeType.class);
        DegreeType degreeType2 = mock(DegreeType.class);
        degreeTypeRepositoryImpl.save(degreeType1);
        degreeTypeRepositoryImpl.save(degreeType2);

        Iterable<DegreeType> iterable = degreeTypeRepositoryImpl.findAll();
        List<DegreeType> resultList = new ArrayList<>();
        iterable.forEach(resultList::add);

        assertEquals(2, resultList.size(), "findAll deve retornar todos os DegreeTypes guardados");
        assertTrue(resultList.contains(degreeType1), "A lista deve conter degreeType1");
        assertTrue(resultList.contains(degreeType2), "A lista deve conter degreeType2");
    }

    @Test
    void testOfIdentityReturnsCorrectDegreeTypeWhenExists() throws Exception {
        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);
        degreeTypeRepositoryImpl = new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        DegreeTypeID id = new DegreeTypeID("DT001");
        DegreeType degreeType = mock(DegreeType.class);
        when(degreeType.identity()).thenReturn(id);

        degreeTypeRepositoryImpl.save(degreeType);
        Optional<DegreeType> result = degreeTypeRepositoryImpl.ofIdentity(id);

        assertTrue(result.isPresent(), "ofIdentity deve retornar um Optional com o DegreeType quando este existir");
        assertEquals(degreeType, result.get(), "ofIdentity deve retornar o DegreeType correto");
    }

    @Test
    void testOfIdentityReturnsEmptyWhenDegreeTypeDoesNotExist() throws Exception {
        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);
        degreeTypeRepositoryImpl = new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        DegreeTypeID id = new DegreeTypeID("DT002");
        Optional<DegreeType> result = degreeTypeRepositoryImpl.ofIdentity(id);

        assertFalse(result.isPresent(), "ofIdentity deve retornar um Optional vazio quando o DegreeType não existir");
    }

    @Test
    void testContainsOfIdentityReturnsTrueWhenDegreeTypeExists() throws Exception {
        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);
        degreeTypeRepositoryImpl = new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        DegreeTypeID id = new DegreeTypeID("DT003");
        DegreeType degreeType = mock(DegreeType.class);
        when(degreeType.identity()).thenReturn(id);

        degreeTypeRepositoryImpl.save(degreeType);
        assertTrue(degreeTypeRepositoryImpl.containsOfIdentity(id), "containsOfIdentity deve retornar true quando o DegreeType existir");
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenDegreeTypeDoesNotExist() throws Exception {
        List<DegreeType> degreeTypeList = new ArrayList<>();
        when(degreeTypeListFactoryMock.createDegreeType_2List()).thenReturn(degreeTypeList);
        degreeTypeRepositoryImpl = new DegreeTypeRepositoryImpl(degreeTypeFactoryMock, degreeTypeListFactoryMock);

        DegreeTypeID id = new DegreeTypeID("DT004");
        assertFalse(degreeTypeRepositoryImpl.containsOfIdentity(id), "containsOfIdentity deve retornar false quando o DegreeType não existir");
    }
}