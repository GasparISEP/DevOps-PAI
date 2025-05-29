package PAI.service.teacherCategory;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.assembler.teacherCategory.ITeacherCategoryInternalAssembler;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.domain.teacherCategory.ITeacherCategoryFactory;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.dto.teacherCategory.TeacherCategoryDTO;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCategoryServiceImplTest {

    private ITeacherCategoryRepository repository;

    private ITeacherCategoryFactory factory;

    private ITeacherCategoryInternalAssembler assembler;

    private TeacherCategoryServiceImpl service; // SUT

    @BeforeEach
    void setUp() {
        repository = mock(ITeacherCategoryRepository.class);
        factory = mock(ITeacherCategoryFactory.class);
        assembler = mock(ITeacherCategoryInternalAssembler.class);
        service = new TeacherCategoryServiceImpl(repository, factory, assembler);
    }

    // Testing configureTeacherCategory method

    @Test
    void whenRegisterValidCategory_thenReturnsTrue() throws Exception {

        //Arrange
        Name doubleName = mock (Name.class);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);
        TeacherCategoryDTO doubleTeacherCategoryDTO = mock(TeacherCategoryDTO.class);

        when(repository.existsByName(doubleName)).thenReturn(false);
        when(factory.createTeacherCategory(doubleName)).thenReturn(doubleTeacherCategory);
        when(repository.save(doubleTeacherCategory)).thenReturn(doubleTeacherCategory);
        when(assembler.toDTO(doubleTeacherCategory)).thenReturn(doubleTeacherCategoryDTO);

        //Act
        TeacherCategoryDTO result = service.configureTeacherCategory(doubleName);

        //Assert
        assertNotNull(result);
    }

    @Test
    void whenRegisterExistingCategory_thenThrowsAlreadyRegisteredException() throws Exception {
        // Arrange
        Name doubleName = mock(Name.class);
        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);

        when(repository.existsByName(doubleName)).thenReturn(true);
        when(factory.createTeacherCategory(doubleName)).thenReturn(doubleTeacherCategory);

        // Act
        AlreadyRegisteredException ex = assertThrows(
                AlreadyRegisteredException.class,
                () -> service.configureTeacherCategory(doubleName)
        );

        // Assert
        assertTrue(ex.getMessage().contains("Teacher Category Name"));
    }


    // Testing existsById method

    @Test
    void whenIdExists_thenReturnsTrue() {

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(repository.containsOfIdentity(id)).thenReturn(true);

        //Act
        boolean result = service.existsById(id);

        //Assert
        assertTrue(result);
    }

    @Test
    void whenIdDoesNotExist_thenReturnsFalse() {

        //Arrange
        TeacherCategoryID id = mock(TeacherCategoryID.class);
        when(repository.containsOfIdentity(id)).thenReturn(false);

        //Act
        boolean result = service.existsById(id);

        //Assert
        assertFalse(result);
    }

    // testing getAllTeacherCategories method

    @Test
    void shouldReturnAllTeacherCategories() {

        //Arrange
        TeacherCategory cat1 = mock(TeacherCategory.class);
        TeacherCategory cat2 = mock(TeacherCategory.class);
        List<TeacherCategory> categories = List.of(cat1, cat2);

        when(repository.findAll()).thenReturn(categories);

        //Act
        Iterable<TeacherCategory> result = service.getAllTeacherCategories();

        //Assert
        assertEquals(2, ((List<TeacherCategory>) result).size());
    }

    @Test
    void shouldReturnEmptyListIfNoTeacherCategoriesExist() {

        //Arrange
        when(repository.findAll()).thenReturn(List.of());

        //Act
        Iterable<TeacherCategory> result = service.getAllTeacherCategories();

        //Assert
        assertTrue(((List<TeacherCategory>) result).isEmpty());
    }

    // Testing TeacherCategoryServiceImpl Constructor

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryFactoryIsNull() {
        //arrange
        ITeacherCategoryRepository doubleTeacherCategoryRepositoryInterface = mock (ITeacherCategoryRepository.class);
        ITeacherCategoryInternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryInternalAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryServiceImpl(doubleTeacherCategoryRepositoryInterface, null,
                    doubleTeacherCategoryAssemblerInterface);
        });

        //assert
        assertEquals("Teacher Category Factory Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryRepositoryIsNull() {
        //arrange
        ITeacherCategoryFactory doubleTeacherCategoryFactoryInterface = mock (ITeacherCategoryFactory.class);
        ITeacherCategoryInternalAssembler doubleTeacherCategoryAssemblerInterface = mock (ITeacherCategoryInternalAssembler.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryServiceImpl(null, doubleTeacherCategoryFactoryInterface,
                    doubleTeacherCategoryAssemblerInterface);
        });

        //assert
        assertEquals("Teacher Category Repository Interface cannot be null.", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionIfTeacherCategoryAssemblerIsNull() {
        //arrange
        ITeacherCategoryFactory doubleTeacherCategoryFactoryInterface = mock (ITeacherCategoryFactory.class);
        ITeacherCategoryRepository doubleTeacherCategoryRepositoryInterface = mock (ITeacherCategoryRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCategoryServiceImpl(doubleTeacherCategoryRepositoryInterface, doubleTeacherCategoryFactoryInterface,
                    null);
        });

        //assert
        assertEquals("Teacher Category Assembler Interface cannot be null.", exception.getMessage());
    }

    // testing getTeacherCategoryById method

    @Test
    void shouldReturnATeacherCategoryIfItIsAValidID() throws Exception {
        // arrange
        TeacherCategoryID doubleTeacherCategoryID = mock(TeacherCategoryID.class);
        when(doubleTeacherCategoryID.getValue()).thenReturn(UUID.randomUUID());

        TeacherCategory doubleTeacherCategory = mock(TeacherCategory.class);
        when(doubleTeacherCategory.identity()).thenReturn(doubleTeacherCategoryID);

        ITeacherCategoryRepository doubleRepository = mock(ITeacherCategoryRepository.class);
        when(doubleRepository.ofIdentity(doubleTeacherCategoryID)).thenReturn(Optional.of(doubleTeacherCategory));

        TeacherCategoryDTO doubleTeacherCategoryDTO = mock(TeacherCategoryDTO.class);
        ITeacherCategoryInternalAssembler assembler = mock(ITeacherCategoryInternalAssembler.class);
        when(assembler.toDTO(doubleTeacherCategory)).thenReturn(doubleTeacherCategoryDTO);

        ITeacherCategoryFactory doubleTeacherCategoryFactory = mock (ITeacherCategoryFactory.class);

        TeacherCategoryServiceImpl service = new TeacherCategoryServiceImpl(doubleRepository, doubleTeacherCategoryFactory, assembler);

        // act
        TeacherCategoryDTO result = service.getTeacherCategoryByID(doubleTeacherCategoryID);

        // assert
        assertEquals(doubleTeacherCategoryDTO, result);
    }

    @Test
    void shouldReturnATeacherCategoryIfIdDoesNotExists (){
        //arrange
        TeacherCategoryID doubleTeacherCategoryID = mock(TeacherCategoryID.class);

        ITeacherCategoryRepository doubleRepository = mock(ITeacherCategoryRepository.class);
        when(doubleRepository.ofIdentity(doubleTeacherCategoryID)).thenReturn(Optional.empty());

        ITeacherCategoryInternalAssembler assembler = mock(ITeacherCategoryInternalAssembler.class);

        ITeacherCategoryFactory doubleTeacherCategoryFactory = mock (ITeacherCategoryFactory.class);

        TeacherCategoryServiceImpl service = new TeacherCategoryServiceImpl(doubleRepository, doubleTeacherCategoryFactory, assembler);

        //act & assert
        assertThrows(NotFoundException.class, () -> {service.getTeacherCategoryByID(doubleTeacherCategoryID);});
    }

    @Test
    void shouldReturnAnExceptionIfIdIsNull (){
        //arrange


        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getTeacherCategoryByID(null);
        });

        //assert
        assertEquals("Teacher Category ID cannot be null!", exception.getMessage());
    }

    }
