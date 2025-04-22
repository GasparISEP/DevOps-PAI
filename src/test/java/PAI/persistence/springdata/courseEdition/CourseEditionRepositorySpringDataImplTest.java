package PAI.persistence.springdata.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.mapper.courseEdition.ICourseEditionMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.repository.ICourseEditionRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        ICourseEditionRepository courseEditionRepositorySpringData;

        // Act
        courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);});

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

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);});

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

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);});

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

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);});

        // Assert
        assertEquals("ProgrammeEditionIdMapper cannot be null", exception.getMessage());
    }


    //-----findCourseEditionsByProgrammeEdition Tests-----
    @Test
    void shouldReturnEmptyListIfFindCourseEditionsByProgrammeEditionIDReceivesANullProgrammeEditionID() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);
        ProgrammeEditionID programmeEditionID = null;

        // Act
        List result = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnEmptyListIfThereAreNoCourseEditionsInTheRepositoryWithProgrammeEditionIDGiven() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        List result = courseEditionRepositorySpringData.findCourseEditionsByProgrammeEditionID(programmeEditionID);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    //-----findIdByCourseEdition Tests-----
    @Test
    void shouldReturnOptionalEmptyWhenFindIdByCourseEditionMethodIsCalled() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act
        Optional result = courseEditionRepositorySpringData.findIdByCourseEdition(courseEdition);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    //-----findWhichProgrammeEditionBelongsToACourseEdition-----
    @Test
    void shouldReturnNullWhenFindWhichProgrammeEditionBelongsToACourseEditionMethodIsCalled() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);
        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act
        ProgrammeEditionID result = courseEditionRepositorySpringData.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);

        // Assert
        assertNull(result);
    }

    //-----save Tests-----
    @Test
    void shouldReturnNullWhenSaveMethodIsReceivesANullCourseEdition() {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);
        CourseEdition courseEdition = null;

        // Act
        CourseEdition result = courseEditionRepositorySpringData.save(courseEdition);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnCourseEditionSavedWhenSaveMethodIsReceivesANonExistentCourseEditionInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
    void shouldReturnNullWhenSaveMethodIsReceivesAnExistentCourseEditionInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

        CourseEdition entity = mock(CourseEdition.class);
        CourseEditionID entityID = mock(CourseEditionID.class);
        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(courseEditionMapper.toDataModel(entity)).thenReturn(courseEditionDataModel);
        when(courseEditionDataModel.getCourseEditionIDDataModel()).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.save(courseEditionDataModel)).thenReturn(courseEditionDataModel);
        when(courseEditionMapper.toDomain(courseEditionDataModel)).thenReturn(entity);
        when(entity.identity()).thenReturn(entityID);
        when(courseEditionIDMapper.toDataModel(entityID)).thenReturn(courseEditionIDDataModel);
        when(courseEditionRepoSD.existsById(courseEditionIDDataModel)).thenReturn(true);

        // Act
        CourseEdition result = courseEditionRepositorySpringData.save(entity);

        // Assert
        assertNull(result);
    }

    @Test
    void shouldReturnNullWhenCourseEditionMapperThrowsAnException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

        CourseEdition entity = mock(CourseEdition.class);

        when(courseEditionMapper.toDataModel(entity)).thenThrow(IllegalArgumentException.class);

        // Act
        CourseEdition result = courseEditionRepositorySpringData.save(entity);

        // Assert
        assertNull(result);
    }

    //-----findAll Tests-----
    @Test
    void shouldReturnAListWithAllCourseEditionsExistentInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

        List<CourseEditionDataModel> courseEditionDataModels = List.of();
        when(courseEditionRepoSD.findAll()).thenReturn(courseEditionDataModels);

        // Act
        Iterable<CourseEdition> allCourseEditions = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNotNull(allCourseEditions);
        List<CourseEdition> courseEditionList = new ArrayList<>();
        allCourseEditions.forEach(courseEditionList::add);
        assertEquals(0, courseEditionList.size());
    }

    @Test
    void shouldReturnAnEmptyListIfCourseEditionMapperThrowsException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        when(courseEditionMapper.toDomain(dataModel3)).thenThrow(IllegalArgumentException.class);

        // Act
        Iterable<CourseEdition> allCourseEditions = courseEditionRepositorySpringData.findAll();

        // Assert
        assertNotNull(allCourseEditions);
        List<CourseEdition> courseEditionList = new ArrayList<>();
        allCourseEditions.forEach(courseEditionList::add);
        assertEquals(0, courseEditionList.size());
        assertFalse(courseEditionList.contains(courseEdition1));
        assertFalse(courseEditionList.contains(courseEdition2));
        assertFalse(courseEditionList.contains(courseEdition3));
    }

    @Test
    void shouldReturnAListOfCourseEditionsInTheRepositoryExcludingTheNullOnes() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);
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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
    void shouldReturnOptionalEmptyWhencourseEditionIDMapperThrowsException() throws Exception {
        // Arrange
        ICourseEditionRepositorySpringData courseEditionRepoSD = mock(ICourseEditionRepositorySpringData.class);
        ICourseEditionMapper courseEditionMapper = mock(ICourseEditionMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        Optional<CourseEditionDataModel> opt = mock(Optional.class);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

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
        ICourseEditionRepository courseEditionRepositorySpringData = new CourseEditionRepositorySpringDataImpl(courseEditionRepoSD, courseEditionMapper, courseEditionIDMapper, programmeEditionIdMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenThrow(IllegalArgumentException.class);

        // Act
        boolean result = courseEditionRepositorySpringData.containsOfIdentity(courseEditionID);

        // Assert
        assertFalse(result);
    }
}