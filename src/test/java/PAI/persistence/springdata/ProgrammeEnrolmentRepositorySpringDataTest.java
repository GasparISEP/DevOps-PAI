package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.factory.ProgrammeEnrolmentFactoryImpl;
import PAI.mapper.ProgrammeEnrolmentIDMapper;
import PAI.mapper.ProgrammeEnrolmentMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ProgrammeEnrolmentRepositorySpringDataTest {

    @Test
    public void testConstructor_JpaRepoNull_ThrowsException() {
        // Arrange
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringData(
                    null, idMapperDouble, null, null
            );
        });
        assertEquals("jpaRepo must not be null", thrown.getMessage());
    }


    @Test
    public void testConstructor_IdMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringData(
                    jpaRepoDouble, null, null, null
            );
        });
        assertEquals("idMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_ProgrammeEnrolmentMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentFactoryImpl programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringData(
                    jpaRepoDouble, idMapperDouble, null, programmeEnrolmentFactoryDouble
            );
        });
        assertEquals("programmeEnrolmentMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_ProgrammeEnrolmentFactoryNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(ProgrammeEnrolmentMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringData(
                    jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, null
            );
        });
        assertEquals("programmeEnrolmentFactory must not be null", thrown.getMessage());
    }


    @Test
    public void testEnrolStudents_EnrolmentRepeated_ThrowsException() throws Exception {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper mapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl factoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, mapperDouble, factoryDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date enrolmentDateDouble = mock(Date.class);

        ProgrammeEnrolment newEnrolmentDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentDataModel existingEnrolmentDataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment existingEnrolmentDouble = mock(ProgrammeEnrolment.class);

        // Mocking the factory to create a new enrolment
        when(factoryDouble.createProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, enrolmentDateDouble))
                .thenReturn(newEnrolmentDouble);

        // Mocking the repository's findAll() to return an existing enrolment in data model
        when(jpaRepoDouble.findAll()).thenReturn(List.of(existingEnrolmentDataModelDouble));

        // Mocking the mapper to convert the data model to domain model
        when(mapperDouble.toDomain(existingEnrolmentDataModelDouble)).thenReturn(existingEnrolmentDouble);

        // Mocking the existing enrolment to indicate that it's already enrolled
        when(existingEnrolmentDouble.hasSameEnrolment(newEnrolmentDouble)).thenReturn(true);

        // Act + Assert
        assertThrows(Exception.class, () -> repository.enrolStudents(studentIDDouble, accessMethodIDDouble, programmeIDDouble, enrolmentDateDouble));
    }

    @Test
    public void testContainsOfIdentity_ExistingEnrolment() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, programmeEnrolmentFactoryDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        // Mocking idMapper to return a valid DataModel for the given ID
        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);

        // Mocking jpaRepo.existsById to return true (indicating the ID exists)
        when(jpaRepoDouble.existsById(dataIDDouble)).thenReturn(true);

        // Act
        boolean result = repository.containsOfIdentity(domainIDDouble);

        // Assert
        assertTrue(result);
    }



    @Test
    public void testContainsOfIdentity_NotFound() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl programmeEnrolmentFactoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, programmeEnrolmentFactoryDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        // Mocking idMapper to return a valid DataModel for the given ID
        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);

        // Mocking jpaRepo.existsById to return false (indicating the ID does not exist)
        when(jpaRepoDouble.existsById(dataIDDouble)).thenReturn(false);

        // Act
        boolean result = repository.containsOfIdentity(domainIDDouble);

        // Assert
        assertFalse(result);
    }



    @Test
    public void testSave_MapperReturnsNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper mapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl factoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, mapperDouble, factoryDouble
        );

        ProgrammeEnrolment domainDouble = mock(ProgrammeEnrolment.class);


        when(mapperDouble.toDataModel(domainDouble)).thenReturn(null);

        // Act + Assert
        IllegalStateException thrownException = assertThrows(IllegalStateException.class, () -> repository.save(domainDouble));

        assertEquals("Data model is null", thrownException.getMessage());
    }


    @Test
    public void testFindById_Found() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper mapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl factoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, mapperDouble, factoryDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment expectedDouble = mock(ProgrammeEnrolment.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);
        when(jpaRepoDouble.findById(dataIDDouble)).thenReturn(Optional.of(dataModelDouble));
        when(mapperDouble.toDomain(dataModelDouble)).thenReturn(expectedDouble);

        // Act
        Optional<ProgrammeEnrolment> result = repository.ofIdentity(domainIDDouble);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedDouble, result.get());
    }


    @Test
    public void testFindById_NotFound() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper mapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl factoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, mapperDouble, factoryDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);
        when(jpaRepoDouble.findById(dataIDDouble)).thenReturn(Optional.empty());

        // Act
        Optional<ProgrammeEnrolment> result = repository.ofIdentity(domainIDDouble);

        // Assert
        assertTrue(result.isEmpty());
    }


    @Test
    public void testEnrolStudents_Successful() throws Exception {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper mapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl factoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, mapperDouble, factoryDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date enrolmentDateDouble = mock(Date.class);

        ProgrammeEnrolment newEnrolmentDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);

        // Mocking the factory to create a new enrolment
        when(factoryDouble.createProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, enrolmentDateDouble))
                .thenReturn(newEnrolmentDouble);

        // Mocking the mapper to return a valid DataModel
        when(mapperDouble.toDataModel(newEnrolmentDouble)).thenReturn(dataModelDouble);

        // Mocking the repository's findAll() to return an empty list (simulating no existing enrolment)
        when(jpaRepoDouble.findAll()).thenReturn(Collections.emptyList());

        // Mocking the repository's save() to return the saved DataModel
        when(jpaRepoDouble.save(any(ProgrammeEnrolmentDataModel.class))).thenReturn(dataModelDouble);

        // Act
        boolean result = repository.enrolStudents(studentIDDouble, accessMethodIDDouble, programmeIDDouble, enrolmentDateDouble);

        // Assert
        assertTrue(result);
    }


    @Test
    public void testIsStudentEnrolledShouldReturnTrue() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper mapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl factoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, mapperDouble, factoryDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        // Mocking the data model
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment existingEnrolmentDouble = mock(ProgrammeEnrolment.class);

        // Setting up the mapper to return the domain object
        when(mapperDouble.toDomain(dataModelDouble)).thenReturn(existingEnrolmentDouble);
        when(existingEnrolmentDouble.hasSameStudent(studentIDDouble)).thenReturn(true);
        when(existingEnrolmentDouble.hasSameProgramme(programmeIDDouble)).thenReturn(true);

        // Mocking the repository to return a list of data models
        when(jpaRepoDouble.findAll()).thenReturn(List.of(dataModelDouble));

        // Act
        boolean result = repository.isStudentEnrolled(studentIDDouble, programmeIDDouble);

        // Assert
        assertTrue(result);
    }


    @Test
    public void testIsStudentEnrolled_NotEnrolled() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        ProgrammeEnrolmentIDMapper idMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
        ProgrammeEnrolmentMapper mapperDouble = mock(ProgrammeEnrolmentMapper.class);
        ProgrammeEnrolmentFactoryImpl factoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);

        ProgrammeEnrolmentRepositorySpringData repository = new ProgrammeEnrolmentRepositorySpringData(
                jpaRepoDouble, idMapperDouble, mapperDouble, factoryDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        when(jpaRepoDouble.findAll()).thenReturn(List.of());

        // Act
        boolean result = repository.isStudentEnrolled(studentIDDouble, programmeIDDouble);

        // Assert
        assertFalse(result);
    }
}
