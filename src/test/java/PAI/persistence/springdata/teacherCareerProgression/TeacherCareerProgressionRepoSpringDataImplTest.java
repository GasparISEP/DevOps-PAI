package PAI.persistence.springdata.teacherCareerProgression;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
import PAI.mapper.TeacherCareerProgression.ITeacherCareerProgressionIDMapper;
import PAI.mapper.TeacherCareerProgression.ITeacherCareerProgressionMapper;
import PAI.persistence.datamodel.TeacherCareerProgressionDataModel;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherCareerProgressionRepoSpringDataImplTest {

    @Test
    void shouldCreateTeacherCareerProgressionRepositorySpringData() {
        // arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepoSD = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        // act
        TeacherCareerProgressionRepoSpringDataImpl result = new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepoSD, iTCPMapper,iTCPIdMapper);
        // assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenRepositoryIsNull() {
        // arrange
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionRepoSpringDataImpl(null, iTCPMapper, iTCPIdMapper);
        });
    }

    @Test
    void shouldThrowExceptionWhenMapperIsNull() {
        // arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepoSD = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepoSD , null, iTCPIdMapper);
        });
    }

    @Test
    void shouldThrowExceptionWhenIDMapperIsNull() {
        // arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepoSD = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepoSD , iTCPMapper, null);
        });
    }

    @Test
    void shouldFindLastTCPFromATeacherID() {
        // arrange
        ITeacherCareerProgressionRepoSpringData mockRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper mockMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl repo = new TeacherCareerProgressionRepoSpringDataImpl(mockRepo, mockMapper,iTCPIdMapper);
        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);

        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        String acronym = "ABC";
        Optional<TeacherCareerProgressionDataModel> tcpDataModel = Optional.of(mock(TeacherCareerProgressionDataModel.class));


        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn(acronym);
        when(mockRepo.findTopByTeacherIdOrderByDateDesc(acronym)).thenReturn(tcpDataModel);

        when(mockMapper.toDomain(tcpDataModel.get())).thenReturn(tcp);

        // act
        Optional<TeacherCareerProgression> result = repo.findLastTCPFromTeacherID(teacherID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(tcp, result.get());
    }

    @Test
    void shouldNotFindLastTCPFromATeacherIDBecauseListIsEmpty() {
        // arrange
        ITeacherCareerProgressionRepoSpringData mockRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper mockMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl repo = new TeacherCareerProgressionRepoSpringDataImpl(mockRepo, mockMapper,iTCPIdMapper);

        TeacherID teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        String acronym = "ABC";


        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn(acronym);
        when(mockRepo.findTopByTeacherIdOrderByDateDesc(acronym)).thenReturn(Optional.empty());

        // act
        Optional<TeacherCareerProgression> result = repo.findLastTCPFromTeacherID(teacherID);

        // assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldSaveTCP() {
        // Arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl tcpRepoSD = new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepo, iTCPMapper,iTCPIdMapper);

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionDataModel tcpDataModel = mock(TeacherCareerProgressionDataModel.class);
        TeacherCareerProgression savedTCP = mock(TeacherCareerProgression.class);

        when(iTCPMapper.toDataModel(tcp)).thenReturn(tcpDataModel);
        when(iTCPRepo.save(tcpDataModel)).thenReturn(tcpDataModel);
        when(iTCPMapper.toDomain(tcpDataModel)).thenReturn(savedTCP);

        // Act
        TeacherCareerProgression result = tcpRepoSD.save(tcp);

        // Assert
        assertNotNull(result);
        assertEquals(savedTCP, result);
    }


    @Test
    void shouldThrowIfTCPIssueIsNull() {
        // Arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl repo = new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepo, iTCPMapper, iTCPIdMapper);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> repo.save(null),
                "TCP cannot be null."
        );
    }

    @Test
    void shouldFindAllTCPs() {
        // Arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl tcpRepoSD = new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepo, iTCPMapper,iTCPIdMapper);

        TeacherCareerProgressionDataModel tcpDataModel1 = mock(TeacherCareerProgressionDataModel.class);
        TeacherCareerProgressionDataModel tcpDataModel2 = mock(TeacherCareerProgressionDataModel.class);
        TeacherCareerProgression tcp1 = mock(TeacherCareerProgression.class);
        TeacherCareerProgression tcp2 = mock(TeacherCareerProgression.class);

        List<TeacherCareerProgressionDataModel> dataModelList = List.of(tcpDataModel1, tcpDataModel2);
        when(iTCPRepo.findAll()).thenReturn(dataModelList);

        when(iTCPMapper.toDomain(tcpDataModel1)).thenReturn(tcp1);
        when(iTCPMapper.toDomain(tcpDataModel2)).thenReturn(null);

        // Act
        Iterable<TeacherCareerProgression> result = tcpRepoSD.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, ((List<TeacherCareerProgression>) result).size());
        assertTrue(((List<TeacherCareerProgression>) result).contains(tcp1));
        assertFalse(((List<TeacherCareerProgression>) result).contains(tcp2));
    }


    @Test
    void shouldReturnTCPIfFoundById() {
        // Arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl tcpRepoSD = new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepo, iTCPMapper, iTCPIdMapper);

        TeacherCareerProgressionID id = mock(TeacherCareerProgressionID.class);
        TeacherCareerProgressionIDDataModel idDataModel = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel tcpDataModel = mock(TeacherCareerProgressionDataModel.class);
        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);

        when(iTCPIdMapper.domainToDataModel(id)).thenReturn(idDataModel);
        when(iTCPRepo.findById(idDataModel)).thenReturn(Optional.of(tcpDataModel));
        when(iTCPMapper.toDomain(tcpDataModel)).thenReturn(tcp);

        // Act
        Optional<TeacherCareerProgression> result = tcpRepoSD.ofIdentity(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(tcp, result.get());
    }

    @Test
    void shouldReturnEmptyOptionalIfNotFoundById() {
        // Arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl tcpRepoSD = new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepo, iTCPMapper,iTCPIdMapper);

        TeacherCareerProgressionID id = mock(TeacherCareerProgressionID.class);
        TeacherCareerProgressionIDDataModel idDataModel = mock(TeacherCareerProgressionIDDataModel.class);

        when(iTCPIdMapper.domainToDataModel(id)).thenReturn(idDataModel);
        when(iTCPRepo.findById(idDataModel)).thenReturn(Optional.empty());

        // Act
        Optional<TeacherCareerProgression> result = tcpRepoSD.ofIdentity(id);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueIfContainsTCPById() {
        // Arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl repo = new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepo, iTCPMapper, iTCPIdMapper);

        TeacherCareerProgressionID id = mock(TeacherCareerProgressionID.class);
        TeacherCareerProgressionIDDataModel idDataModel = mock(TeacherCareerProgressionIDDataModel.class);

        when(iTCPIdMapper.domainToDataModel(id)).thenReturn(idDataModel);
        when(iTCPRepo.existsById(idDataModel)).thenReturn(true);

        // Act
        boolean result = repo.containsOfIdentity(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfDoesNotContainTCPById() {
        // Arrange
        ITeacherCareerProgressionRepoSpringData iTCPRepo = mock(ITeacherCareerProgressionRepoSpringData.class);
        ITeacherCareerProgressionMapper iTCPMapper = mock(ITeacherCareerProgressionMapper.class);
        ITeacherCareerProgressionIDMapper iTCPIdMapper = mock(ITeacherCareerProgressionIDMapper.class);
        TeacherCareerProgressionRepoSpringDataImpl repo =
                new TeacherCareerProgressionRepoSpringDataImpl(iTCPRepo, iTCPMapper, iTCPIdMapper);

        TeacherCareerProgressionID id = mock(TeacherCareerProgressionID.class);
        TeacherCareerProgressionIDDataModel idDataModel = mock(TeacherCareerProgressionIDDataModel.class);

        when(iTCPIdMapper.domainToDataModel(id)).thenReturn(idDataModel);
        when(iTCPRepo.existsById(idDataModel)).thenReturn(false);

        // Act
        boolean result = repo.containsOfIdentity(id);

        // Assert
        assertFalse(result);
    }



}