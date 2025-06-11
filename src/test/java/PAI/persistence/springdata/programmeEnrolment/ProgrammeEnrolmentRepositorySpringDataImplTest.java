package PAI.persistence.springdata.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programme.ProgrammeIDMapperImpl;
import PAI.mapper.programmeEnrolment.IProgrammeEnrolmentIDMapper;
import PAI.mapper.programmeEnrolment.IProgrammeEnrolmentMapper;
import PAI.mapper.programmeEnrolment.ProgrammeEnrolmentIDMapperImpl;
import PAI.mapper.programmeEnrolment.ProgrammeEnrolmentMapperImpl;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.student.StudentIDMapperImpl;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentRepositorySpringDataImplTest {

    @Test
    public void testConstructor_JpaRepoNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper iProgrammeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    null, idMapperDouble, iProgrammeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
            );
        });
        assertEquals("jpaRepo must not be null", thrown.getMessage());
    }


    @Test
    public void testConstructor_IdMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, null, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
            );
        });
        assertEquals("idMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_ProgrammeEnrolmentMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, idMapperDouble, null, iStudentIDMapperDouble, iProgrammeIDMapperDouble
            );
        });
        assertEquals("programmeEnrolmentMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_StudentIDMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);

        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, null, iProgrammeIDMapperDouble
            );
        });
        assertEquals("studentIDMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testConstructor_ProgrammeIDMapperNull_ThrowsException() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);

        // Act + Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEnrolmentRepositorySpringDataImpl(
                    jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, null
            );
        });
        assertEquals("programmeIDMapper must not be null", thrown.getMessage());
    }

    @Test
    public void testSave_SuccessfulSave_ReturnsMappedDomainObject() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        ProgrammeEnrolment domainDouble = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolmentDataModel dataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolmentDataModel savedDataModelDouble = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment mappedDomain = mock(ProgrammeEnrolment.class);

        when(mapperDouble.toDataModel(domainDouble)).thenReturn(dataModelDouble);
        when(jpaRepoDouble.save(dataModelDouble)).thenReturn(savedDataModelDouble);
        when(mapperDouble.toDomain(savedDataModelDouble)).thenReturn(mappedDomain);

        // Act
        ProgrammeEnrolment result = repository.save(domainDouble);

        // Assert
        assertEquals(mappedDomain, result);
    }

    @Test
    public void testContainsOfIdentity_ExistingEnrolment() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);


        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);


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
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        ProgrammeEnrolmentID domainIDDouble = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentIDDataModel dataIDDouble = mock(ProgrammeEnrolmentIDDataModel.class);

        when(idMapperDouble.domainToDataModel(domainIDDouble)).thenReturn(dataIDDouble);

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
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
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
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
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
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble);

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
    public void testIsStudentEnrolledShouldReturnTrue() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, studentIDMapperDouble, programmeIDMapperDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);

        when(studentIDMapperDouble.domainToDataModel(studentIDDouble)).thenReturn(studentIDDataModelDouble);
        when(programmeIDMapperDouble.toData(programmeIDDouble)).thenReturn(programmeIDDataModelDouble);

        when(jpaRepoDouble.existsByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(studentIDDataModelDouble, programmeIDDataModelDouble)).thenReturn(true);

        // Act
        boolean result = repository.isStudentEnrolled(studentIDDouble, programmeIDDouble);

        // Assert
        assertTrue(result);
    }



    @Test
    public void testIsStudentEnrolled_NotEnrolled() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper studentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, mapperDouble, studentIDMapperDouble, programmeIDMapperDouble
        );

        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);

        when(studentIDMapperDouble.domainToDataModel(studentIDDouble)).thenReturn(studentIDDataModelDouble);
        when(programmeIDMapperDouble.toData(programmeIDDouble)).thenReturn(programmeIDDataModelDouble);

        when(jpaRepoDouble.existsByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(studentIDDataModelDouble, programmeIDDataModelDouble)).thenReturn(false);

        // Act
        boolean result = repository.isStudentEnrolled(studentIDDouble, programmeIDDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testFindAll() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepoDouble = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapperDouble = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapperDouble = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper iStudentIDMapperDouble = mock(IStudentIDMapper.class);
        IProgrammeIDMapper iProgrammeIDMapperDouble = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepoDouble, idMapperDouble, programmeEnrolmentMapperDouble, iStudentIDMapperDouble, iProgrammeIDMapperDouble
        );

        ProgrammeEnrolmentDataModel dataModel1 = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolmentDataModel dataModel2 = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment domainEnrolment1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment domainEnrolment2 = mock(ProgrammeEnrolment.class);


        when(jpaRepoDouble.findAll()).thenReturn(List.of(dataModel1, dataModel2));


        when(programmeEnrolmentMapperDouble.toDomain(dataModel1)).thenReturn(domainEnrolment1);
        when(programmeEnrolmentMapperDouble.toDomain(dataModel2)).thenReturn(domainEnrolment2);

        // Act
        Iterable<ProgrammeEnrolment> result = repository.findAll();

        // Assert
        List<ProgrammeEnrolment> resultList = (List<ProgrammeEnrolment>) result;
        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(domainEnrolment1));
        assertTrue(resultList.contains(domainEnrolment2));
    }
    @Test
    public void testFindByStudentIDAndProgrammeID_Found() {
        // Arrange

        IProgrammeEnrolmentRepositorySpringData jpaRepo = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapper = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapper = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);

        // SUT
        var repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepo, idMapper, mapper, studentIDMapper, programmeIDMapper
        );

        // Test data
        StudentID domainSid = new StudentID(1234567);
        ProgrammeID domainPid = new ProgrammeID(new Acronym("CS101"));

        // 2) mocks dos data-models
        StudentIDDataModel sidDM = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel pidDM = mock(ProgrammeIDDataModel.class);

        // 3) stub para que o mapper converta o VO no mock
        when(studentIDMapper.domainToDataModel(domainSid)).thenReturn(sidDM);
        when(programmeIDMapper.toData(domainPid)).thenReturn(pidDM);

        // 4) stub do JPA repo a devolver um Optional com dataModel
        ProgrammeEnrolmentDataModel dataModel = mock(ProgrammeEnrolmentDataModel.class);
        when(jpaRepo.findByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(sidDM, pidDM))
                .thenReturn(Optional.of(dataModel));

        // 5) stub do mapper para converter dataModel no domínio
        ProgrammeEnrolment expectedDomain = mock(ProgrammeEnrolment.class);
        when(mapper.toDomain(dataModel)).thenReturn(expectedDomain);

        // Act
        Optional<ProgrammeEnrolment> result =
                repository.findByStudentIDAndProgrammeID(domainSid, domainPid);

        // Assert
        assertTrue(result.isPresent(), "Deveria encontrar um enrolment");
        assertEquals(expectedDomain, result.get());
    }

    @Test
    public void testFindByStudentIDAndProgrammeID_NotFound() {
        // Arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepo = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapper = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper mapper = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);

        var repository = new ProgrammeEnrolmentRepositorySpringDataImpl(
                jpaRepo, idMapper, mapper, studentIDMapper, programmeIDMapper
        );

        StudentID domainSid = new StudentID(1234567);
        ProgrammeID domainPid = new ProgrammeID(new Acronym("XX999"));

        StudentIDDataModel sidDM = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel pidDM = mock(ProgrammeIDDataModel.class);

        when(studentIDMapper.domainToDataModel(domainSid)).thenReturn(sidDM);
        when(programmeIDMapper.toData(domainPid)).thenReturn(pidDM);

        when(jpaRepo.findByProgrammeEnrolmentIDPeStudentIDAndProgrammeEnrolmentIDPeProgrammeID(sidDM, pidDM))
                .thenReturn(Optional.empty());

        // Act
        Optional<ProgrammeEnrolment> result =
                repository.findByStudentIDAndProgrammeID(domainSid, domainPid);

        // Assert
        assertFalse(result.isPresent(), "Não deveria encontrar enrolment para combinação inexistente");
    }

    @Test
    void shouldFindByGeneratedIDUsingSpringDataRepository() {
        // Arrange
        ProgrammeEnrolmentGeneratedID gid = new ProgrammeEnrolmentGeneratedID(UUID.randomUUID());

        // mocks
        IProgrammeEnrolmentRepositorySpringData jpaRepo = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapper = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapper = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);

        ProgrammeEnrolmentDataModel dm = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolment domain = mock(ProgrammeEnrolment.class);

        when(jpaRepo.findByProgrammeEnrolmentGID(gid.getProgrammeEnrolmentGID()))
                .thenReturn(Optional.of(dm));
        when(programmeEnrolmentMapper.toDomain(dm)).thenReturn(domain);

        ProgrammeEnrolmentRepositorySpringDataImpl repo =
                new ProgrammeEnrolmentRepositorySpringDataImpl(
                        jpaRepo, idMapper, programmeEnrolmentMapper, studentIDMapper, programmeIDMapper
                );

        // Act
        Optional<ProgrammeEnrolment> result = repo.findByGeneratedID(gid);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void shouldReturnEmptyWhenGeneratedIDNotFound() {
        // Arrange
        ProgrammeEnrolmentGeneratedID gid = new ProgrammeEnrolmentGeneratedID(UUID.randomUUID());

        // mocks
        IProgrammeEnrolmentRepositorySpringData jpaRepo = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IProgrammeEnrolmentIDMapper idMapper = mock(IProgrammeEnrolmentIDMapper.class);
        IProgrammeEnrolmentMapper programmeEnrolmentMapper = mock(IProgrammeEnrolmentMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);

        when(jpaRepo.findByProgrammeEnrolmentGID(gid.getProgrammeEnrolmentGID()))
                .thenReturn(Optional.empty());

        ProgrammeEnrolmentRepositorySpringDataImpl repo =
                new ProgrammeEnrolmentRepositorySpringDataImpl(
                        jpaRepo, idMapper, programmeEnrolmentMapper, studentIDMapper, programmeIDMapper
                );

        // Act
        Optional<ProgrammeEnrolment> result = repo.findByGeneratedID(gid);

        // Assert
        assertTrue(result.isEmpty(), "Deveria devolver Optional.empty() quando GID não é encontrado");
    }

    @Test
    void shouldReturnListOfProgrammeIDs() {
        // arrange
        IProgrammeEnrolmentRepositorySpringData jpaRepo = mock(IProgrammeEnrolmentRepositorySpringData.class);
        IStudentIDMapper studentIDMapper = mock(StudentIDMapperImpl.class);
        IProgrammeIDMapper programmeIDMapper = mock(ProgrammeIDMapperImpl.class);
        IProgrammeEnrolmentIDMapper iProgrammeEnrolmentIDMapper = mock(ProgrammeEnrolmentIDMapperImpl.class);
        IProgrammeEnrolmentMapper iProgrammeEnrolmentMapper = mock(ProgrammeEnrolmentMapperImpl.class);

        ProgrammeEnrolmentRepositorySpringDataImpl repository =
                new ProgrammeEnrolmentRepositorySpringDataImpl(jpaRepo, iProgrammeEnrolmentIDMapper, iProgrammeEnrolmentMapper, studentIDMapper, programmeIDMapper);

        StudentID studentID = mock(StudentID.class);

        ProgrammeEnrolmentID domainID1 = mock(ProgrammeEnrolmentID.class);
        ProgrammeEnrolmentID domainID2 = mock(ProgrammeEnrolmentID.class);

        ProgrammeEnrolment enrolments1 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment enrolments2 = mock(ProgrammeEnrolment.class);
        ProgrammeEnrolment enrolments3 = mock(ProgrammeEnrolment.class);

        ProgrammeEnrolmentDataModel enrolment1 = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolmentDataModel enrolment2 = mock(ProgrammeEnrolmentDataModel.class);
        ProgrammeEnrolmentDataModel enrolment3 = mock(ProgrammeEnrolmentDataModel.class);

        when(jpaRepo.findAll()).thenReturn(List.of(enrolment1, enrolment2, enrolment3));

        when(iProgrammeEnrolmentMapper.toDomain(enrolment1)).thenReturn(enrolments1);
        when(iProgrammeEnrolmentMapper.toDomain(enrolment2)).thenReturn(enrolments2);
        when(iProgrammeEnrolmentMapper.toDomain(enrolment3)).thenReturn(enrolments3);

        when(enrolments1.hasSameStudent(studentID)).thenReturn(true);
        when(enrolments2.hasSameStudent(studentID)).thenReturn(true);
        when(enrolments3.hasSameStudent(studentID)).thenReturn(false);

        when(enrolments1.identity()).thenReturn(domainID1);
        when(enrolments2.identity()).thenReturn(domainID2);

        // act
        List<ProgrammeEnrolmentID> result = repository.listOfProgrammesStudentIsEnrolledIn(studentID);

        // assert
        assertEquals(2, result.size());
        assertTrue(result.contains(domainID1));
        assertTrue(result.contains(domainID2));
    }

}
