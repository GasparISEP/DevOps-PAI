package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.schoolYear.SchoolYearAssembler;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearCommandDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearIDDescriptionResponseDTO;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        SchoolYearCommandDTO schoolYearCommandDTO = mock(SchoolYearCommandDTO.class);

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
        when(schoolYearCommandDTO.getDescription()).thenReturn(descriptionInfo);
        when(schoolYearCommandDTO.getStartDate()).thenReturn(startDateInfo);
        when(schoolYearCommandDTO.getEndDate()).thenReturn(endDateInfo);

        // Act
        SchoolYear result = service.addSchoolYear(schoolYearCommandDTO);

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
        SchoolYearCommandDTO schoolYearCommandDTO = mock(SchoolYearCommandDTO.class);

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
            service.addSchoolYear(schoolYearCommandDTO);
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

        // Assert
        assertThrows(Exception.class, () -> service.addSchoolYear(null));
    }

    @Test
    void ShouldNotAddSchoolYearDMWhenSchoolYearIDIsNull() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        Description descriptionInfo = mock(Description.class);
        Date startDateInfo = mock(Date.class);
        Date endDate = mock(Date.class);

        //act
        Exception exception = assertThrows(Exception.class, () -> service.addSchoolYearDM(null, descriptionInfo, startDateInfo, endDate));

        //assert
        assertEquals("Not possible to create a school year", exception.getMessage());
    }

    @Test
    void ShouldNotAddSchoolYearDMWhenDescriptionIsNull() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        UUID schoolYearID = mock(UUID.class);
        Date startDateInfo = mock(Date.class);
        Date endDate = mock(Date.class);

        //act
        Exception exception = assertThrows(Exception.class, () -> service.addSchoolYearDM(schoolYearID, null, startDateInfo, endDate));

        //assert
        assertEquals("Not possible to create a school year", exception.getMessage());
    }

    @Test
    void ShouldNotAddSchoolYearDMWhenStartDateIsNull() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        UUID schoolYearID = mock(UUID.class);
        Description descriptionInfo = mock(Description.class);
        Date endDate = mock(Date.class);

        //act
        Exception exception = assertThrows(Exception.class, () -> service.addSchoolYearDM(schoolYearID, descriptionInfo, null, endDate));

        //assert
        assertEquals("Not possible to create a school year", exception.getMessage());
    }

    @Test
    void ShouldNotAddSchoolYearDMWhenEndDateIsNull() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        UUID schoolYearID = mock(UUID.class);
        Description descriptionInfo = mock(Description.class);
        Date startDateInfo = mock(Date.class);

        //act
        Exception exception = assertThrows(Exception.class, () -> service.addSchoolYearDM(schoolYearID, descriptionInfo, startDateInfo, null));

        //assert
        assertEquals("Not possible to create a school year", exception.getMessage());
    }

    @Test
    void addSchoolYearDMShouldThrowExceptionWhenSchoolYearAlreadyExists() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        SchoolYear existingSchoolYear = mock(SchoolYear.class);
        UUID schoolYearID = mock(UUID.class);
        Description descriptionInfo = mock(Description.class);
        Date startDateInfo = mock(Date.class);
        Date endDateInfo = mock(Date.class);

        when(schoolYearFactory.recreateSchoolYear(
                any(UUID.class),
                any(Description.class),
                any(Date.class),
                any(Date.class)
        )).thenReturn(existingSchoolYear);

        when(schoolYearRepository.schoolYearExists(any())).thenReturn(true);

        //act
        Exception exception = assertThrows(Exception.class, () -> {
            service.addSchoolYearDM(schoolYearID, descriptionInfo, startDateInfo, endDateInfo);
        });

        //assert
        assertEquals("School year already exists.", exception.getMessage());
    }

    @Test
    void addSchoolYearDMShouldSaveSuccessfullyWhenSchoolYearDoesNotExist() throws Exception {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory,schoolYearMapperDTO);

        SchoolYearDTO newSchoolYear = mock(SchoolYearDTO.class);
        UUID schoolYearID = mock(UUID.class);
        Description descriptionInfo = mock(Description.class);
        Date startDateInfo = mock(Date.class);
        Date endDateInfo = mock(Date.class);

        SchoolYear schoolYear = mock(SchoolYear.class);

        when(schoolYearFactory.recreateSchoolYear(
                any(UUID.class),
                any(Description.class),
                any(Date.class),
                any(Date.class)
        )).thenReturn(schoolYear);

        when(schoolYearRepository.schoolYearExists(any())).thenReturn(false);
        when(schoolYearRepository.save(schoolYear)).thenReturn(schoolYear);

        when(schoolYearMapperDTO.toDTO(schoolYear)).thenReturn(newSchoolYear);

        //act
        SchoolYear result = service.addSchoolYearDM(schoolYearID, descriptionInfo, startDateInfo, endDateInfo);

        // Assert
        assertNotNull(result);
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
        SchoolYearCommandDTO schoolYearCommandDTO = mock(SchoolYearCommandDTO.class);
        SchoolYearCommandDTO schoolYearCommandDTO1 = mock(SchoolYearCommandDTO.class);

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

        when(schoolYearCommandDTO.getDescription()).thenReturn(description1);
        when(schoolYearCommandDTO.getStartDate()).thenReturn(startDate1);
        when(schoolYearCommandDTO.getEndDate()).thenReturn(endDate1);
        when(schoolYearCommandDTO1.getDescription()).thenReturn(description2);
        when(schoolYearCommandDTO1.getStartDate()).thenReturn(startDate2);
        when(schoolYearCommandDTO1.getEndDate()).thenReturn(endDate2);

        // Act: Try to add two different school years
        SchoolYear result1 = service.addSchoolYear(schoolYearCommandDTO);
        SchoolYear result2 = service.addSchoolYear(schoolYearCommandDTO1);

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

    @Test
    void shouldReturnListOfSchoolYears () {
        // Arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);

        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);
        SchoolYear schoolYear3 = mock(SchoolYear.class);
        List<SchoolYear> schoolYears = List.of(schoolYear1, schoolYear2, schoolYear3);
        when(schoolYearRepository.findAll()).thenReturn(schoolYears);

        CurrentSchoolYearDTO schoolYearDTO1 = mock(CurrentSchoolYearDTO.class);
        CurrentSchoolYearDTO schoolYearDTO2 = mock(CurrentSchoolYearDTO.class);
        CurrentSchoolYearDTO schoolYearDTO3 = mock(CurrentSchoolYearDTO.class);

        when(schoolYearMapperDTO.toCurrentSchoolYearDTO(schoolYear1)).thenReturn(schoolYearDTO1);
        when(schoolYearMapperDTO.toCurrentSchoolYearDTO(schoolYear2)).thenReturn(schoolYearDTO2);
        when(schoolYearMapperDTO.toCurrentSchoolYearDTO(schoolYear3)).thenReturn(schoolYearDTO3);

        // Act
        Iterable<CurrentSchoolYearDTO> result = service.getAllSchoolYears();

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        List<CurrentSchoolYearDTO> listResult = new ArrayList<>();
        result.forEach(listResult::add);
        assertEquals(3, listResult.size());
        assertTrue(listResult.contains(schoolYearDTO1));
        assertTrue(listResult.contains(schoolYearDTO2));
        assertTrue(listResult.contains(schoolYearDTO3));
    }

    @Test
    void shouldReturnEmptyListOfSchoolYearsIfThereAreNoSchoolYears () {
        // Arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);

        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);

        List<SchoolYear> schoolYears = List.of();
        when(schoolYearRepository.findAll()).thenReturn(schoolYears);

        // Act
        Iterable<CurrentSchoolYearDTO> result = service.getAllSchoolYears();

        // Assert
        assertNotNull(result);
        assertFalse(result.iterator().hasNext());
        List<CurrentSchoolYearDTO> listResult = new ArrayList<>();
        result.forEach(listResult::add);
        assertEquals(0, listResult.size());
    }

    @Test
    void shouldReturnCurrentSchoolYearDTO(){
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        CurrentSchoolYearDTO currentSchoolYearResponseDTO = mock(CurrentSchoolYearDTO.class);

        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(Optional.of(schoolYear1));
        when(schoolYearMapperDTO.toCurrentSchoolYearDTO(schoolYear1)).thenReturn(currentSchoolYearResponseDTO);

        //act
        Optional<CurrentSchoolYearDTO> result = service.getCurrentSchoolYear();

        //assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyIfNoCurrentSchoolYearInRepository(){
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(SchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory,schoolYearMapperDTO);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        CurrentSchoolYearDTO currentSchoolYearResponseDTO = mock(CurrentSchoolYearDTO.class);

        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(Optional.empty());
        when(schoolYearMapperDTO.toCurrentSchoolYearDTO(schoolYear1)).thenReturn(currentSchoolYearResponseDTO);

        //act
        Optional<CurrentSchoolYearDTO> result = service.getCurrentSchoolYear();

        //assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldGetSchoolYearByID() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(SchoolYearRepositoryImpl.class);
        ISchoolYearFactory schoolYearFactory = mock(SchoolYearFactoryImpl.class);
        ISchoolYearAssembler schoolYearAssembler = mock(SchoolYearAssembler.class);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearAssembler);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        when(schoolYearRepository.findBySchoolYearID(schoolYearID)).thenReturn(Optional.of(schoolYear));

        //act
        Optional<SchoolYear> opt1 = schoolYearService.getSchoolYearByID(schoolYearID);

        //assert
        assertTrue(opt1.isPresent());
    }

    @Test
    void getAllSchoolYearsIDDescriptions_returnsCorrectDTOs() {
        ISchoolYearRepository repo = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearAssembler = mock(ISchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(repo, schoolYearFactory, schoolYearAssembler);

        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(schoolYear.identity()).thenReturn(schoolYearID);
        when(schoolYearID.toString()).thenReturn("id1");
        Description description = mock(Description.class);
        when(schoolYear.getDescription()).thenReturn(description);
        when(description.getDescription()).thenReturn("2015");
        when(repo.findAll()).thenReturn(List.of(schoolYear));

        List<SchoolYearIDDescriptionResponseDTO> result = service.getAllSchoolYearsIDDescriptions();

        assertEquals(1, result.size());
        assertEquals("id1", result.get(0).id());
        assertEquals("2015", result.get(0).description());
    }

    @Test
    void getSchoolYearsByIDs_shouldReturnListOfSchoolYears() {
        // Arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(ISchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);

        SchoolYearID id1 = mock(SchoolYearID.class);
        SchoolYearID id2 = mock(SchoolYearID.class);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);
        when(schoolYearRepository.findBySchoolYearID(id1)).thenReturn(Optional.of(schoolYear1));
        when(schoolYearRepository.findBySchoolYearID(id2)).thenReturn(Optional.of(schoolYear2));

        // Act
        List<SchoolYear> result = service.getSchoolYearsByIDs(List.of(id1, id2));

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(schoolYear1));
        assertTrue(result.contains(schoolYear2));
    }

    @Test
    void getSchoolYearsByIDs_shouldThrowIfAnyIDNotFound() {
        // Arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(ISchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);

        SchoolYearID id1 = mock(SchoolYearID.class);
        SchoolYearID id2 = mock(SchoolYearID.class);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        when(schoolYearRepository.findBySchoolYearID(id1)).thenReturn(Optional.of(schoolYear1));
        when(schoolYearRepository.findBySchoolYearID(id2)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> service.getSchoolYearsByIDs(List.of(id1, id2)));
    }

    @Test
    void getSchoolYearsByIDs_shouldReturnEmptyListIfInputIsEmpty() {
        // Arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearAssembler schoolYearMapperDTO = mock(ISchoolYearAssembler.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);

        // Act
        List<SchoolYear> result = service.getSchoolYearsByIDs(List.of());

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
