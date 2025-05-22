package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.assembler.schoolYear.SchoolYearAssembler;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SchoolYearServiceImplTest {

    // Test case for constructor throwing exception when repository is null
    @Test
    void constructorShouldThrowExceptionWhenRepositoryIsNull() {
        // Arrange
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYearServiceImpl(null, schoolYearFactory,schoolYearMapperDTO);
        });

        assertEquals("schoolYearRepository cannot be null", exception.getMessage());
    }

    // Test case for constructor throwing exception when factory is null
    @Test
    void constructorShouldThrowExceptionWhenFactoryIsNull() {
        // Arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYearServiceImpl(schoolYearRepository, null,schoolYearMapperDTO);
        });

        assertEquals("schoolYearFactory cannot be null", exception.getMessage());
    }

    // Test constructor throws exception when both repository and factory are null
    @Test
    void constructorThrowsExceptionWhenRepositoryAndFactoryAreNull() {
        // Arrange
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYearServiceImpl(null, null, null);
        });

        assertEquals("schoolYearRepository cannot be null", exception.getMessage());
    }

    // Test case for adding a new school year when it does not already exist
    @Test
    void addSchoolYearShouldSaveSuccessfullyWhenSchoolYearDoesNotExist() throws Exception {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        SchoolYearDTO newSchoolYear = mock(SchoolYearDTO.class);
        Description descriptionInfo = mock(Description.class);
        Date startDateInfo = mock(Date.class);
        Date endDateInfo = mock(Date.class);

        SchoolYear schoolYear = mock(SchoolYear.class);

        // Mocks behaviour
        // Mock the factory to return a new school year
        when(schoolYearFactory.createSchoolYear(
                any(Description.class),
                any(Date.class),
                any(Date.class)
        )).thenReturn(schoolYear);

        when(schoolYearRepository.schoolYearExists(any())).thenReturn(false);
        when(schoolYearRepository.save(schoolYear)).thenReturn(schoolYear);

        when(schoolYearMapperDTO.toDTO(schoolYear)).thenReturn(newSchoolYear);

        // Act
        SchoolYear result = service.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        // Assert
        assertNotNull(result);
    }

    // Test case for adding a new school year when it already exists in the repository
    @Test
    void addSchoolYearShouldThrowExceptionWhenSchoolYearAlreadyExists() {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        SchoolYear existingSchoolYear = mock(SchoolYear.class);
        Description descriptionInfo = mock(Description.class);
        Date startDateInfo = mock(Date.class);
        Date endDateInfo = mock(Date.class);

        // Mocks behaviour
        // Mock the factory to return an existing school year
        when(schoolYearFactory.createSchoolYear(
                any(Description.class),
                any(Date.class),
                any(Date.class)
        )).thenReturn(existingSchoolYear);

        // Mock the repository to simulate that the school year already exists

        when(schoolYearRepository.schoolYearExists(any())).thenReturn(true);

        // Act & Assert: Verify the exception is thrown
        // We expect an exception to be thrown because the school year already exists in the repository
        Exception exception = assertThrows(Exception.class, () -> {
            service.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);
        });

        assertEquals("School year already exists.", exception.getMessage()); // Verify the exception message
    }

    @Test
    void ShouldNotAddSchoolYearWhenDescriptionIsNull() {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        Date startDateInfo = mock(Date.class);
        Date endDateInfo = mock(Date.class);

        // Assert
        assertThrows(Exception.class, () -> service.addSchoolYear(null,startDateInfo,endDateInfo));
    }

    @Test
    void ShouldNotAddSchoolYearWhenStartDateIsNull() {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        Description descriptionInfo = mock(Description.class);
        Date endDateInfo = mock(Date.class);

        // Assert
        assertThrows(Exception.class, () -> service.addSchoolYear(descriptionInfo,null,endDateInfo));
    }

    @Test
    void ShouldNotAddSchoolYearWhenEndDateIsNull() {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        Description descriptionInfo = mock(Description.class);
        Date startDateInfo = mock(Date.class);

        // Assert
        assertThrows(Exception.class, () -> service.addSchoolYear(descriptionInfo,startDateInfo,null));
    }

    // Test case for adding multiple different school years successfully
    @Test
    void addMultipleSchoolYearsSuccessfully() throws Exception {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        // Mock the school year objects
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);

        Description description2 = mock(Description.class);
        Date startDate2 = mock(Date.class);
        Date endDate2 = mock(Date.class);

        // Mocks behaviour
        // Mock the factory to return new school years
        when(schoolYearFactory.createSchoolYear(
                any(Description.class),
                any(Date.class),
                any(Date.class)
        )).thenReturn(schoolYear1,schoolYear2);

        // Mock the repository to return false for school year existence check (meaning the school year doesn't exist yet)
        when(schoolYearRepository.schoolYearExists(any())).thenReturn(false);
        when(schoolYearRepository.save(schoolYear1)).thenReturn(schoolYear1);

        when(schoolYearRepository.save(schoolYear2)).thenReturn(schoolYear2);

        // Act: Try to add two different school years
        SchoolYear result1 = service.addSchoolYear(description1, startDate1, endDate1);
        SchoolYear result2 = service.addSchoolYear(description2, startDate2, endDate2);

        // Assert: Verify that both school years are added successfully
        assertEquals(result1,schoolYear1);
        assertEquals(result2,schoolYear2);
    }

    @Test
    void shouldReturnOptionalSchoolYearIdWhenSchoolYearExists() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);
        SchoolYear schoolYear1 = mock(SchoolYear.class);

        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);
        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(Optional.of(schoolYear1));
        when(schoolYear1.identity()).thenReturn(schoolYearID1);
        //act
        Optional<SchoolYearID> result = service.getCurrentSchoolYearID();
        //assert
        assertTrue(result.isPresent());
        assertEquals(schoolYearID1,result.get());
    }

    @Test
    void shouldReturnOptionalEmptyWhenSchoolYearDoesNotExist() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);

        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(Optional.empty());
        //act
        Optional<SchoolYearID> result = service.getCurrentSchoolYearID();
        //assert
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnOptionalEmptyWhenRepositoryThrowsException() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);

        when(schoolYearRepository.getCurrentSchoolYear()).thenThrow(new NullPointerException());
        //act
        Optional<SchoolYearID> result = service.getCurrentSchoolYearID();
        //assert
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnTrueIfSchoolYearExistsByID(){
        //arrange
        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);

        when(schoolYearRepository.containsOfIdentity(schoolYearID1)).thenReturn(true);
        //act
        boolean result=service.schoolYearExistsById(schoolYearID1);
        //assert
        assertTrue(result);

    }


    @Test
    void shouldReturnFalseIfSchoolYearIDIsNull(){
        //arrange
        SchoolYearID schoolYearID1 = null;
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);

        //act
        boolean result=service.schoolYearExistsById(schoolYearID1);
        //assert
        assertFalse(result);

    }


    @Test
    void shouldReturnFalseIfSchoolYearIsNotInRepository(){
        //arrange
        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);

        when(schoolYearRepository.containsOfIdentity(schoolYearID1)).thenReturn(false);
        //act
        boolean result=service.schoolYearExistsById(schoolYearID1);
        //assert
        assertFalse(result);

    }

    @Test
    void shouldReturnTwoIfRepositoryContainsTwoSchoolYearIDs(){
        //arrange
        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);
        when(schoolYearRepository.findAll()).thenReturn(List.of(schoolYear,schoolYear2));

        //act
        List<SchoolYearID> result=service.getAllSchoolYearsIDs();
        //assert
        assertEquals(2, result.size());

    }

    @Test
    void shouldReturn0IfRepositoryRepositoryDoesNotContainSchoolYearIDs(){
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);
        when(schoolYearRepository.getAllSchoolYearsIDs()).thenReturn(List.of());

        //act
        List<SchoolYearID> result=service.getAllSchoolYearsIDs();
        //assert
        assertEquals(0, result.size());

    }
}
