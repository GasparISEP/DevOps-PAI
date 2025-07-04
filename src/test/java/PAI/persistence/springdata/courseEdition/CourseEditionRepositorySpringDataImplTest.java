package PAI.persistence.springdata.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.mapper.courseEdition.ICourseEditionGeneratedIDMapper;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionRepositorySpringDataImplTest {

    //-----Constructor Tests-----
    @Test
    void shouldCreateCourseEditionRepositorySpringDataImplWhenConstructorIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData;
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        // Act
        courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        // Assert
        assertNotNull(courseEditionRepositorySpringData);
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionRepositorySpringData() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = null;
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);});

        // Assert
        assertEquals("CourseEditionRepositorySpringData cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionMapper() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = null;
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);});

        // Assert
        assertEquals("CourseEditionMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseEditionIDMapper() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = null;
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);});

        // Assert
        assertEquals("CourseEditionIDMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullProgrammeEditionIdMapper() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = null;
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);
        });
        assertEquals("ProgrammeEditionIdMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalledWithNullCourseInStudyPlanIDMapper() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = null;
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);
        });
        assertEquals("CourseInStudyPlanIDMapper cannot be null", exception.getMessage());
    }

    //-----findCourseEditionsByProgrammeEdition Tests-----
    @Test
    void shouldReturnEmptyListIfFindCourseEditionsByProgrammeEditionIDReceivesANullProgrammeEditionID() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);
        ProgrammeEditionID programmeEditionID = null;
        // Act
        List result = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        assertTrue(result instanceof ArrayList);
        assertNotSame(Collections.emptyList(), result);
        List anotherResult = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);
        assertNotSame(result, anotherResult);
    }

    @Test
    void shouldReturnEmptyListIfThereAreNoCourseEditionsInTheRepositoryWithProgrammeEditionIDGiven() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        List<CourseEditionIDDataModel> courseEditionIDsDataModel = List.of();
        when(courseEditionRepoSD.findCourseEditionIDByProgrammeEditionIDDataModel(programmeEditionIdDataModel)).thenReturn(courseEditionIDsDataModel);

        // Act
        List result = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAListIfThereAreCourseEditionsInTheRepositoryWithProgrammeEditionIDGiven() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        CourseEditionIDDataModel cEIDDataModel1 = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel cEIDDataModel2 = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel cEIDDataModel3 = mock(CourseEditionIDDataModel.class);
        List<CourseEditionIDDataModel> courseEditionIDsDataModel = List.of(cEIDDataModel1, cEIDDataModel2, cEIDDataModel3);
        when(courseEditionRepoSD.findCourseEditionIDByProgrammeEditionIDDataModel(programmeEditionIdDataModel)).thenReturn(courseEditionIDsDataModel);
        when(cEIDDataModel1.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
        when(cEIDDataModel2.getProgrammeEditionIDDataModel()).thenReturn(ProgrammeEditionIdDataModel.class.newInstance());
        when(cEIDDataModel3.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);

        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);
        when(courseEditionIDMapper.toDomain(cEIDDataModel1)).thenReturn(courseEditionID1);
        when(courseEditionIDMapper.toDomain(cEIDDataModel2)).thenReturn(null);
        when(courseEditionIDMapper.toDomain(cEIDDataModel3)).thenReturn(courseEditionID2);

        // Act
        List result = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(courseEditionID1));
        assertTrue(result.contains(courseEditionID2));
    }

    @Test
    void shouldReturnEmptyListIfProgrammeEditionIdMapperThrowsException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        List result = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        assertTrue(result instanceof ArrayList);
        assertNotSame(Collections.emptyList(), result);
        List anotherResult = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);
        assertNotSame(result, anotherResult);
    }

    //-----save Tests-----
    @Test
    void shouldThrowIllegalArgumentExceptionWhenCourseEditionIsNull() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);

        ICourseEditionRepository courseEditionRepositorySpringData =
                new CourseEditionRepositorySpringDataImpl(
                        courseEditionRepoSD,
                        courseEditionMapper,
                        courseEditionIDMapper,
                        programmeEditionIdMapper,
                        courseInStudyPlanIDMapper,
                        courseEditionGeneratedIDMapper
                );

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            courseEditionRepositorySpringData.save(null);
        });
    }


    @Test
    void shouldReturnCourseEditionSavedWhenSaveMethodIsReceivesAValidCourseEdition() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEdition entity = mock(CourseEdition.class);
        CourseEditionID entityID = mock(CourseEditionID.class);
        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(courseEditionMapper.toDataModel(entity)).thenReturn(courseEditionDataModel);
        when(courseEditionRepoSD.save(courseEditionDataModel)).thenReturn(courseEditionDataModel);
        when(courseEditionMapper.toDomain(courseEditionDataModel)).thenReturn(entity);
        when(entity.identity()).thenReturn(entityID);
        when(courseEditionIDMapper.toDataModel(entityID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.existsById(courseEditionIDDataModel)).thenReturn(false);

        // Act
        CourseEdition result = courseEditionRepositorySpringData.save(entity);

        // Assert
        assertNotNull(result);
        assertEquals(entity, result);
        verify(courseEditionRepoSD).save(courseEditionDataModel);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCourseEditionMapperFails() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);

        ICourseEditionRepository courseEditionRepositorySpringData =
                new CourseEditionRepositorySpringDataImpl(
                        courseEditionRepoSD,
                        courseEditionMapper,
                        courseEditionIDMapper,
                        programmeEditionIdMapper,
                        courseInStudyPlanIDMapper,
                        courseEditionGeneratedIDMapper
                );

        CourseEdition entity = mock(CourseEdition.class);
        when(courseEditionMapper.toDataModel(entity)).thenThrow(IllegalArgumentException.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            courseEditionRepositorySpringData.save(entity);
        });
    }


    //-----findAll Tests-----
    @Test
    void shouldReturnAListWithAllCourseEditionsExistentInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);
        CourseEdition courseEdition3 = mock(CourseEdition.class);

        CourseEditionDataModel dataModel1 = mock(CourseEditionDataModel.class);
        CourseEditionDataModel dataModel2 = mock(CourseEditionDataModel.class);
        CourseEditionDataModel dataModel3 = mock(CourseEditionDataModel.class);

        List<CourseEditionDataModel> courseEditionDataModels = List.of(dataModel1, dataModel2, dataModel3);
        when(courseEditionRepoSD.findAll()).thenReturn(courseEditionDataModels);

        when(courseEditionMapper.toDomain(dataModel1)).thenReturn(courseEdition1);
        when(courseEditionMapper.toDomain(dataModel2)).thenReturn(courseEdition2);
        when(courseEditionMapper.toDomain(dataModel3)).thenReturn(courseEdition3);

        // Act
        Iterable<CourseEdition> allCourseEditions = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNotNull(allCourseEditions);
        List<CourseEdition> courseEditionList = new ArrayList<>();
        allCourseEditions.forEach(courseEditionList::add);
        assertEquals(3, courseEditionList.size());
        assertTrue(courseEditionList.contains(courseEdition1));
        assertTrue(courseEditionList.contains(courseEdition2));
        assertTrue(courseEditionList.contains(courseEdition3));
    }

    @Test
    void shouldReturnAnEmptyListIfThereIsNoCourseEditionsInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        List<CourseEditionDataModel> courseEditionDataModels = List.of();
        when(courseEditionRepoSD.findAll()).thenReturn(courseEditionDataModels);

        // Act
        Iterable<CourseEdition> allCourseEditions = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNotNull(allCourseEditions);
        List<CourseEdition> courseEditionList = new ArrayList<>();
        allCourseEditions.forEach(courseEditionList::add);
        assertEquals(0, courseEditionList.size());
        assertEquals(0, ((List<CourseEdition>) allCourseEditions).size());
        assertNotSame(Collections.emptyList(), allCourseEditions);
    }

    @Test
    void shouldReturnAnEmptyListIfCourseEditionMapperThrowsException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);

        CourseEditionDataModel dataModel1 = mock(CourseEditionDataModel.class);
        CourseEditionDataModel dataModel2 = mock(CourseEditionDataModel.class);
        CourseEditionDataModel dataModel3 = mock(CourseEditionDataModel.class);

        List<CourseEditionDataModel> courseEditionDataModels = List.of(dataModel1, dataModel2, dataModel3);
        when(courseEditionRepoSD.findAll()).thenReturn(courseEditionDataModels);

        when(courseEditionMapper.toDomain(dataModel1)).thenReturn(courseEdition1);
        when(courseEditionMapper.toDomain(dataModel2)).thenReturn(courseEdition2);
        when(courseEditionMapper.toDomain(dataModel3)).thenThrow(IllegalArgumentException.class);

        // Act
        Iterable<CourseEdition> allCourseEditions = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNotNull(allCourseEditions);
        List<CourseEdition> courseEditionList = new ArrayList<>();
        allCourseEditions.forEach(courseEditionList::add);
        assertNotNull(courseEditionList);
        assertEquals(0, courseEditionList.size());
        assertTrue(allCourseEditions instanceof Iterable);
        assertNotSame(Collections.emptyList(), courseEditionList);
        assertNotSame(Collections.emptyList(), allCourseEditions);
    }

    @Test
    void shouldReturnAListOfCourseEditionsInTheRepositoryExcludingTheNullOnes() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition3 = mock(CourseEdition.class);

        CourseEditionDataModel dataModel1 = mock(CourseEditionDataModel.class);
        CourseEditionDataModel dataModel2 = null;
        CourseEditionDataModel dataModel3 = mock(CourseEditionDataModel.class);

        List<CourseEditionDataModel> courseEditionDataModels = Arrays.asList(dataModel1, dataModel2, dataModel3);
        when(courseEditionRepoSD.findAll()).thenReturn(courseEditionDataModels);

        when(courseEditionMapper.toDomain(dataModel1)).thenReturn(courseEdition1);
        when(courseEditionMapper.toDomain(dataModel2)).thenReturn(null);
        when(courseEditionMapper.toDomain(dataModel3)).thenReturn(courseEdition3);

        // Act
        Iterable<CourseEdition> allCourseEditions = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNotNull(allCourseEditions);
        List<CourseEdition> courseEditionList = new ArrayList<>();
        allCourseEditions.forEach(courseEditionList::add);
        assertEquals(2, courseEditionList.size());
        assertTrue(courseEditionList.contains(courseEdition1));
        assertFalse(courseEditionList.contains(null));
        assertTrue(courseEditionList.contains(courseEdition3));
    }

    //-----ofIdentity Tests-----
    @Test
    void shouldReturnOptionalEmptyWhenOfIdentityMethodIsReceivesANullCourseEditionID() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);
        CourseEditionID courseEditionID = null;

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.ofIdentity(courseEditionID);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    @Test
    void shouldReturnOptionalWithCourseEditionIfTheRepositoryContainsIt() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        Optional<CourseEditionDataModel> opt = mock(Optional.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.findById(courseEditionIDDataModel)).thenReturn(opt);
        when(opt.isPresent()).thenReturn(true);
        when(courseEditionMapper.toDomain(opt.get())).thenReturn(courseEdition);

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.ofIdentity(courseEditionID);

        // Assert
        assertTrue(result.isPresent());
        assertTrue(result.get().equals(courseEdition));
    }

    @Test
    void shouldReturnOptionalEmptyWhenRepositoryDoesNotContainACourseEditionWithTheGivenCourseEditionID() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        Optional<CourseEditionDataModel> opt = mock(Optional.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.findById(courseEditionIDDataModel)).thenReturn(opt);
        when(opt.isPresent()).thenReturn(false);

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.ofIdentity(courseEditionID);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyWhenCourseEditionIDMapperThrowsException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.ofIdentity(courseEditionID);

        // Assert
        assertFalse(result.isPresent());
    }

    //-----containsOfIdentity Tests-----
    @Test
    void shouldReturnFalseWhenToContainsOfIdentityMethodReceivesANullCourseEditionID() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEditionID courseEditionID = null;

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseEditionIDAlreadyExistsInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.existsById(courseEditionIDDataModel)).thenReturn(true);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIDDoesNotExistsInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.existsById(courseEditionIDDataModel)).thenReturn(false);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIDMapperThrowsAnException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }

    //-----findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID Tests-----
    @Test
    void findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_ShouldReturnMatchingCourseEditions() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(courseInStudyPlanIDMapper.toDataModel(courseInStudyPlanID)).thenReturn(courseInStudyPlanIDDataModel);
        when(courseEditionRepoSD.findCourseEditionsByProgrammeEditionIDDataModelAndCourseInStudyPlanIDDataModel(
                programmeEditionIdDataModel, courseInStudyPlanIDDataModel))
                .thenReturn(List.of(courseEditionDataModel));
        when(courseEditionMapper.toDomain(courseEditionDataModel)).thenReturn(courseEdition);
        when(courseEdition.identity()).thenReturn(courseEditionID);

        // Act
        List<CourseEditionID> result = courseEditionRepositorySpringData
                .findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(courseEditionID, result.get(0));
    }

    @Test
    void findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_ShouldThrowExceptionWhenProgrammeEditionIDIsNull() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(null, courseInStudyPlanID));
        assertEquals("ProgrammeEditionID cannot be null", exception.getMessage());
    }

    @Test
    void findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_ShouldThrowExceptionWhenCourseInStudyPlanIDIsNull() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, null));
        assertEquals("CourseInStudyPlanID cannot be null", exception.getMessage());
    }

    @Test
    void findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_ShouldReturnEmptyListWhenNoMatches() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);

        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(courseInStudyPlanIDMapper.toDataModel(courseInStudyPlanID)).thenReturn(courseInStudyPlanIDDataModel);
        when(courseEditionRepoSD.findCourseEditionsByProgrammeEditionIDDataModelAndCourseInStudyPlanIDDataModel(
                programmeEditionIdDataModel, courseInStudyPlanIDDataModel))
                .thenReturn(Collections.emptyList());

        // Act
        List<CourseEditionID> result = courseEditionRepositorySpringData
                .findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_ShouldHandleNullMappedCourseEditions() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);

        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(courseInStudyPlanIDMapper.toDataModel(courseInStudyPlanID)).thenReturn(courseInStudyPlanIDDataModel);
        when(courseEditionRepoSD.findCourseEditionsByProgrammeEditionIDDataModelAndCourseInStudyPlanIDDataModel(
                programmeEditionIdDataModel, courseInStudyPlanIDDataModel))
                .thenReturn(List.of(courseEditionDataModel));
        when(courseEditionMapper.toDomain(courseEditionDataModel)).thenReturn(null);

        // Act
        List<CourseEditionID> result = courseEditionRepositorySpringData
                .findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(programmeEditionID, courseInStudyPlanID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID_ShouldThrowExceptionWhenMapperFails() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);

        when(programmeEditionIdMapper.toDataModel(programmeEditionID))
                .thenThrow(new RuntimeException("Mapping error"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () ->
                courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionIDAndCourseInStudyPlanID(
                        programmeEditionID, courseInStudyPlanID));
        assertEquals("Error trying to find CourseEditions by ProgrammeEditionID and CourseInStudyPlanID", 
                exception.getMessage());
    }
    @Test
    void findCourseEditionByGeneratedId_ShouldReturnOptional_WhenFound() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionGeneratedID domainID = mock(CourseEditionGeneratedID.class);
        CourseEditionGeneratedIDDataModel dataID = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel dataModel = mock(CourseEditionDataModel.class);
        CourseEdition domainModel = mock(CourseEdition.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);

        when(courseEditionGeneratedIDMapper.toDataModel(domainID)).thenReturn(dataID);
        when(courseEditionRepoSD.findCourseEditionByGeneratedId(dataID)).thenReturn(dataModel);
        when(courseEditionMapper.toDomain(dataModel)).thenReturn(domainModel);

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.findCourseEditionByGeneratedId(domainID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(domainModel, result.get());
    }
    @Test
    void findCourseEditionByGeneratedId_ShouldReturnAnEmptyOptional_WhenNotFound() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionGeneratedID domainID = mock(CourseEditionGeneratedID.class);
        CourseEditionGeneratedIDDataModel dataID = mock(CourseEditionGeneratedIDDataModel.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(
                courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper, courseInStudyPlanIDMapper,courseEditionGeneratedIDMapper);


        when(courseEditionGeneratedIDMapper.toDataModel(domainID)).thenReturn(dataID);
        when(courseEditionRepoSD.findCourseEditionByGeneratedId(dataID)).thenReturn(null);

        // Act
        Optional<CourseEdition> result = courseEditionRepositorySpringData.findCourseEditionByGeneratedId(domainID);


        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void findByProgrammeIDAndCourseID_returnsSingleMatch() {
        // Arrange
        ICourseEditionRepositorySpringData repoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper mapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper idMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper progIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper coursePlanIdMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper genIdMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionRepositorySpringDataImpl repo = new CourseEditionRepositorySpringDataImpl(repoSD, mapper, idMapper, progIdMapper, coursePlanIdMapper, genIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionDataModel dataModel = mock(CourseEditionDataModel.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(repoSD.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(courseEdition);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);
        when(programmeEditionID.getProgrammeID()).thenReturn(programmeID);
        when(courseInStudyPlanID.getCourseID()).thenReturn(courseID);

        // Act
        List<CourseEdition> result = repo.findByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(courseEdition, result.get(0));
    }

    @Test
    void findByProgrammeIDAndCourseID_returnsMultipleMatches() {
        // Arrange
        ICourseEditionRepositorySpringData repoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper mapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper idMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper progIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper coursePlanIdMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper genIdMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionRepositorySpringDataImpl repo = new CourseEditionRepositorySpringDataImpl(repoSD, mapper, idMapper, progIdMapper, coursePlanIdMapper, genIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        ProgrammeEditionID programmeEditionID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionID2 = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanID2 = mock(CourseInStudyPlanID.class);
        CourseEditionDataModel dataModel1 = mock(CourseEditionDataModel.class);
        CourseEditionDataModel dataModel2 = mock(CourseEditionDataModel.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);

        when(repoSD.findAll()).thenReturn(List.of(dataModel1, dataModel2));
        when(mapper.toDomain(dataModel1)).thenReturn(courseEdition1);
        when(mapper.toDomain(dataModel2)).thenReturn(courseEdition2);
        when(courseEdition1.getProgrammeEditionID()).thenReturn(programmeEditionID1);
        when(courseEdition1.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID1);
        when(programmeEditionID1.getProgrammeID()).thenReturn(programmeID);
        when(courseInStudyPlanID1.getCourseID()).thenReturn(courseID);
        when(courseEdition2.getProgrammeEditionID()).thenReturn(programmeEditionID2);
        when(courseEdition2.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID2);
        when(programmeEditionID2.getProgrammeID()).thenReturn(programmeID);
        when(courseInStudyPlanID2.getCourseID()).thenReturn(courseID);

        // Act
        List<CourseEdition> result = repo.findByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(courseEdition1));
        assertTrue(result.contains(courseEdition2));
    }

    @Test
    void findByProgrammeIDAndCourseID_returnsEmptyWhenOnlyProgrammeIDMatches() {
        // Arrange
        ICourseEditionRepositorySpringData repoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper mapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper idMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper progIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper coursePlanIdMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper genIdMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionRepositorySpringDataImpl repo = new CourseEditionRepositorySpringDataImpl(repoSD, mapper, idMapper, progIdMapper, coursePlanIdMapper, genIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionDataModel dataModel = mock(CourseEditionDataModel.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(repoSD.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(courseEdition);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);
        when(programmeEditionID.getProgrammeID()).thenReturn(programmeID);
        when(courseInStudyPlanID.getCourseID()).thenReturn(mock(CourseID.class)); // different courseID

        // Act
        List<CourseEdition> result = repo.findByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findByProgrammeIDAndCourseID_returnsEmptyWhenOnlyCourseIDMatches() {
        // Arrange
        ICourseEditionRepositorySpringData repoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper mapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper idMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper progIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper coursePlanIdMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper genIdMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionRepositorySpringDataImpl repo = new CourseEditionRepositorySpringDataImpl(repoSD, mapper, idMapper, progIdMapper, coursePlanIdMapper, genIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionDataModel dataModel = mock(CourseEditionDataModel.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(repoSD.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(courseEdition);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);
        when(programmeEditionID.getProgrammeID()).thenReturn(mock(ProgrammeID.class)); // different programmeID
        when(courseInStudyPlanID.getCourseID()).thenReturn(courseID);

        // Act
        List<CourseEdition> result = repo.findByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findByProgrammeIDAndCourseID_returnsEmptyWhenNeitherMatches() {
        // Arrange
        ICourseEditionRepositorySpringData repoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper mapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper idMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper progIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper coursePlanIdMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper genIdMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionRepositorySpringDataImpl repo = new CourseEditionRepositorySpringDataImpl(repoSD, mapper, idMapper, progIdMapper, coursePlanIdMapper, genIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseEditionDataModel dataModel = mock(CourseEditionDataModel.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(repoSD.findAll()).thenReturn(List.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(courseEdition);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);
        when(programmeEditionID.getProgrammeID()).thenReturn(mock(ProgrammeID.class)); // different programmeID
        when(courseInStudyPlanID.getCourseID()).thenReturn(mock(CourseID.class)); // different courseID

        // Act
        List<CourseEdition> result = repo.findByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void findByProgrammeIDAndCourseID_returnsEmptyWhenRepositoryIsEmpty() {
        // Arrange
        ICourseEditionRepositorySpringData repoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper mapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper idMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper progIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper coursePlanIdMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionGeneratedIDMapper genIdMapper = mock(ICourseEditionGeneratedIDMapper.class);
        CourseEditionRepositorySpringDataImpl repo = new CourseEditionRepositorySpringDataImpl(repoSD, mapper, idMapper, progIdMapper, coursePlanIdMapper, genIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        CourseID courseID = mock(CourseID.class);
        when(repoSD.findAll()).thenReturn(List.of());

        // Act
        List<CourseEdition> result = repo.findByProgrammeIDAndCourseID(programmeID, courseID);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

