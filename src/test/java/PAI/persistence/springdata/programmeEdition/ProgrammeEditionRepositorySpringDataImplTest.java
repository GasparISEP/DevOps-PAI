package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionRepositorySpringDataImplTest {

    // ProgrammeEditionRepositorySpringDataImpl CONSTRUCTOR TESTS
    @Test
    void shouldCreateProgrammeEditionRepositorySpringData() {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);
        // assert
        assertNotNull(programmeEditionRepositorySpringDataImpl);
    }

    @Test
    void shouldNotCreateProgrammeEditionRepositorySpringDataIfIProgrammeEditionRepositorySpringDataNull() {
        // arrange
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRepositorySpringDataImpl(
                null, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper)
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionRepositorySpringDataIfIProgrammeEditionMapperNull() {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, null, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper)
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionRepositorySpringDataIfIProgrammeEditionIdMapperNull() {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, null, iProgrammeIDMapper, iSchoolYearIDMapper)
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionRepositorySpringDataIfIProgrammeIDMapperNull() {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, null, iSchoolYearIDMapper)
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionRepositorySpringDataIfISchoolYearIDMapperNull() {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, null)
        );
    }

    // findProgrammeEditionIDByProgrammeIDAndSchoolYearID TESTS
    @Test
    void shouldReturnOptionalPresentWhenFindProgrammeEditionIDByProgrammeIDAndSchoolYearID() throws Exception {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);


        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        when(iSchoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);

        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDatasModels
                (programmeIDDataModel, schoolYearIDDataModel)).thenReturn(Optional.of(programmeEditionIDDataModel));

        ProgrammeEditionID programmeEditionIDMock = mock(ProgrammeEditionID.class);
        when(iProgrammeEditionIdMapper.toDomain(programmeEditionIDDataModel)).thenReturn(programmeEditionIDMock);
        // act
        Optional<ProgrammeEditionID> programmeEditionID = programmeEditionRepositorySpringDataImpl.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID, schoolYearID);
        // assert
        assertTrue(programmeEditionID.isPresent());
    }

    @Test
    void shouldReturnOptionalEmptyWhenFindProgrammeEditionIDByProgrammeIDAndSchoolYearID() throws Exception {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);


        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        when(iSchoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);

        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDatasModels
                (programmeIDDataModel, schoolYearIDDataModel)).thenReturn(Optional.empty());
        // act
        Optional<ProgrammeEditionID> programmeEditionID = programmeEditionRepositorySpringDataImpl.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID, schoolYearID);
        // assert
        assertTrue(programmeEditionID.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyWhenProgrammeIDNull() throws Exception {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        // act
        Optional<ProgrammeEditionID> programmeEditionID = programmeEditionRepositorySpringDataImpl.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(null, schoolYearID);
        // assert
        assertTrue(programmeEditionID.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyWhenSchoolYearIDNull() throws Exception {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        // act
        Optional<ProgrammeEditionID> programmeEditionID = programmeEditionRepositorySpringDataImpl.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID, null);
        // assert
        assertTrue(programmeEditionID.isEmpty());
    }

    // save TESTS
    @Test
    void shouldReturnOptionalProgrammeEditionWhenSave() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        when(iProgrammeEditionMapper.toDataModel(programmeEdition)).thenReturn(Optional.of(programmeEditionDataModel));

        when(iProgrammeEditionRepositorySpringData.save(programmeEditionDataModel)).thenReturn(programmeEditionDataModel);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel)).thenReturn(Optional.of(programmeEdition));
        //act
        ProgrammeEdition result = programmeEditionRepositorySpringDataImpl.save(programmeEdition);
        //assert
        assertNotNull(result);
        assertEquals(programmeEdition, result);
    }

    @Test
    void shouldReturnNullWhenParameterProgrammeEditionNull() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition programmeEdition = null;

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);

        when(iProgrammeEditionRepositorySpringData.save(programmeEditionDataModel)).thenReturn(programmeEditionDataModel);
        //act
        ProgrammeEdition result = programmeEditionRepositorySpringDataImpl.save(null);
        //assert
        assertNull(result);
        assertEquals(programmeEdition, result);
    }

    @Test
    void shouldReturnNullWhenProgrammeEditionDataModelEmpty() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        when(iProgrammeEditionMapper.toDataModel(programmeEdition)).thenReturn(Optional.empty());

        //act
        ProgrammeEdition result = programmeEditionRepositorySpringDataImpl.save(programmeEdition);
        //assert
        assertNull(result);
        assertNotEquals(programmeEdition, result);
    }

    @Test
    void shouldReturnNullWhenProgrammeEditionEmpty() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        when(iProgrammeEditionMapper.toDataModel(programmeEdition)).thenReturn(Optional.of(programmeEditionDataModel));

        when(iProgrammeEditionRepositorySpringData.save(programmeEditionDataModel)).thenReturn(programmeEditionDataModel);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel)).thenReturn(Optional.empty());
        //act
        ProgrammeEdition result = programmeEditionRepositorySpringDataImpl.save(programmeEdition);
        //assert
        assertNull(result);
        assertNotEquals(programmeEdition, result);
    }

    @Test
    void shouldReturnNullWhenProgrammeEditionMapperThrowsException() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        when(iProgrammeEditionMapper.toDataModel(programmeEdition)).thenReturn(Optional.of(programmeEditionDataModel));

        when(iProgrammeEditionRepositorySpringData.save(programmeEditionDataModel)).thenReturn(programmeEditionDataModel);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel)).thenThrow(IllegalArgumentException.class);
        //act
        ProgrammeEdition result = programmeEditionRepositorySpringDataImpl.save(programmeEdition);
        //assert
        assertNull(result);
        assertNotEquals(programmeEdition, result);
    }

    // findAll TESTS
    @Test
    void shouldReturnAListWithAllProgrammeEditionsExistentInTheRepository() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionDataModel programmeEditionDataModel1 = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel2 = mock(ProgrammeEditionDataModel.class);
        List<ProgrammeEditionDataModel> ProgrammeEditionDataModels = List.of(programmeEditionDataModel1, programmeEditionDataModel2);

        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);

        when(iProgrammeEditionRepositorySpringData.findAll()).thenReturn(ProgrammeEditionDataModels);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel1)).thenReturn(Optional.of(programmeEdition1));
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel2)).thenReturn(Optional.of(programmeEdition2));

        // act
        Iterable<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.findAll();

        // assert
        assertNotNull(result);
        List<ProgrammeEdition> resultList = new ArrayList<>();
        result.forEach(resultList::add);
        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(programmeEdition1));
        assertTrue(resultList.contains(programmeEdition2));
    }

    @Test
    void shouldReturnEmptyListIfNoProgrammeEditionsExistsInTheRepository() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        List<ProgrammeEditionDataModel> ProgrammeEditionDataModels = List.of();

        when(iProgrammeEditionRepositorySpringData.findAll()).thenReturn(ProgrammeEditionDataModels);
        // act
        Iterable<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.findAll();

        // assert
        assertNotNull(result);
        List<ProgrammeEdition> resultList = new ArrayList<>();
        result.forEach(resultList::add);
        assertEquals(0, resultList.size());
    }

    @Test
    void shouldReturnNullIfProgrammeEditionMapperThrowsException() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionDataModel programmeEditionDataModel1 = mock(ProgrammeEditionDataModel.class);
        List<ProgrammeEditionDataModel> ProgrammeEditionDataModels = List.of(programmeEditionDataModel1);

        when(iProgrammeEditionRepositorySpringData.findAll()).thenReturn(ProgrammeEditionDataModels);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel1)).thenThrow(IllegalArgumentException.class);

        // act
        Iterable<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.findAll();

        // assert
        assertNull(result);
    }

    // ofIdentity TESTS
    @Test
    void shouldReturnOptionalEmptyWhenProgrammeEditionIDNull() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = null;
        //act
        Optional<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.ofIdentity(programmeEditionID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalProgrammeEditionIfTheRepositoryContainsProgrammeEditionID() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);

        Optional<ProgrammeEditionDataModel> programmeEditionDataModel = mock(Optional.class);
        when(iProgrammeEditionRepositorySpringData.findById(programmeEditionIdDataModel)).thenReturn(programmeEditionDataModel);
        when(programmeEditionDataModel.isPresent()).thenReturn(true);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel.get())).thenReturn(Optional.of(programmeEdition));
        //act
        Optional<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.ofIdentity(programmeEditionID);
        //assert
        assertTrue(result.isPresent());
        assertEquals(programmeEdition, result.get());
    }

    @Test
    void shouldReturnOptionalEmptyIfTheRepositoryNotContainsProgrammeEditionID() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);

        Optional<ProgrammeEditionDataModel> programmeEditionDataModel = mock(Optional.class);
        when(iProgrammeEditionRepositorySpringData.findById(programmeEditionIdDataModel)).thenReturn(programmeEditionDataModel);
        when(programmeEditionDataModel.isPresent()).thenReturn(false);
        //act
        Optional<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.ofIdentity(programmeEditionID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfProgrammeEditionIdMapperThrowsException() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(iProgrammeEditionIdMapper.toDataModel(programmeEditionID)).thenThrow(IllegalArgumentException.class);

        //act
        Optional<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.ofIdentity(programmeEditionID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyIfProgrammeEditionMapperThrowsException() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);

        Optional<ProgrammeEditionDataModel> programmeEditionDataModel = mock(Optional.class);
        when(iProgrammeEditionRepositorySpringData.findById(programmeEditionIdDataModel)).thenReturn(programmeEditionDataModel);
        when(programmeEditionDataModel.isPresent()).thenReturn(true);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel.get())).thenThrow(IllegalArgumentException.class);

        //act
        Optional<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.ofIdentity(programmeEditionID);
        //assert
        assertTrue(result.isEmpty());
    }

    // containsOfIdentity TESTS
    @Test
    void shouldReturnFalseIfProgrammeEditionIDNull() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = null;
        //act
        boolean result = programmeEditionRepositorySpringDataImpl.containsOfIdentity(programmeEditionID);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIDExistsInTheRepository() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(iProgrammeEditionRepositorySpringData.existsById(iProgrammeEditionIdMapper.toDataModel(programmeEditionID))).thenReturn(true);
        //act
        boolean result = programmeEditionRepositorySpringDataImpl.containsOfIdentity(programmeEditionID);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIDDoesntExistsInTheRepository() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(iProgrammeEditionRepositorySpringData.existsById(iProgrammeEditionIdMapper.toDataModel(programmeEditionID))).thenReturn(false);
        //act
        boolean result = programmeEditionRepositorySpringDataImpl.containsOfIdentity(programmeEditionID);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIDMapperThrowsException() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(iProgrammeEditionIdMapper.toDataModel(programmeEditionID)).thenThrow(IllegalArgumentException.class);
        //act
        boolean result = programmeEditionRepositorySpringDataImpl.containsOfIdentity(programmeEditionID);
        //assert
        assertFalse(result);
    }

    // getProgrammeEditionsByProgrammeID TESTS
    @Test
    void shouldReturnListWithAllProgrammeEditionThatExistsInTheRepository() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        ProgrammeEditionDataModel programmeEditionDataModel1 = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel2 = mock(ProgrammeEditionDataModel.class);
        List<ProgrammeEditionDataModel> programmeEditionDataModels = List.of(programmeEditionDataModel1, programmeEditionDataModel2);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionByProgrammeIDDataModel(programmeIDDataModel)).thenReturn(programmeEditionDataModels);

        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel1)).thenReturn(Optional.of(programmeEdition1));
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel2)).thenReturn(Optional.of(programmeEdition2));

        //act
        List<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertNotNull(result);
        List<ProgrammeEdition> resultList = new ArrayList<>();
        result.forEach(resultList::add);
        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(programmeEdition1));
        assertTrue(resultList.contains(programmeEdition2));
    }

    @Test
    void shouldReturnEmptyListIfNoProgrammeEditionExistsInTheRepository() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        List<ProgrammeEditionDataModel> programmeEditionDataModels = List.of();
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionByProgrammeIDDataModel(programmeIDDataModel)).thenReturn(programmeEditionDataModels);
        //act
        List<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertNotNull(result);
        List<ProgrammeEdition> resultList = new ArrayList<>();
        result.forEach(resultList::add);
        assertEquals(0, resultList.size());
    }

    @Test
    void shouldNotAddToListIfProgrammeEditionIsNull() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        ProgrammeEditionDataModel programmeEditionDataModel1 = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel2 = mock(ProgrammeEditionDataModel.class);
        List<ProgrammeEditionDataModel> programmeEditionDataModels = List.of(programmeEditionDataModel1, programmeEditionDataModel2);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionByProgrammeIDDataModel(programmeIDDataModel)).thenReturn(programmeEditionDataModels);

        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel1)).thenReturn(Optional.of(programmeEdition1));
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel2)).thenReturn(Optional.empty());

        //act
        List<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertNotNull(result);
        List<ProgrammeEdition> resultList = new ArrayList<>();
        result.forEach(resultList::add);
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(programmeEdition1));
    }

    @Test
    void shouldReturnNullIfParameterProgrammeIDNull() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = null;
        //act
        List<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertNull(result);
    }
//
    @Test
    void shouldReturnNullIfProgrammeEditionMapperThrowsException2() throws Exception {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        ProgrammeEditionDataModel programmeEditionDataModel1 = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel2 = mock(ProgrammeEditionDataModel.class);
        List<ProgrammeEditionDataModel> programmeEditionDataModels = List.of(programmeEditionDataModel1, programmeEditionDataModel2);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionByProgrammeIDDataModel(programmeIDDataModel)).thenReturn(programmeEditionDataModels);

        ProgrammeEdition programmeEdition1 = mock(ProgrammeEdition.class);
        ProgrammeEdition programmeEdition2 = mock(ProgrammeEdition.class);
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel1)).thenReturn(Optional.of(programmeEdition1));
        when(iProgrammeEditionMapper.toDomain(programmeEditionDataModel2)).thenThrow(IllegalArgumentException.class);

        //act
        List<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertNull(result);
    }

//    @Test
//    void shouldReturnSchoolYearIDForThatProgrammeEdition() throws Exception {
//        //arrange
//        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
//        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
//        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
//        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
//        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
//        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
//                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);
//
//        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
//
//        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
//        when(iProgrammeEditionMapper.toDataModel(programmeEdition)).thenReturn(Optional.of(programmeEditionDataModel));
//
//        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
//        when(programmeEditionDataModel.getSchoolYearIDDataModel()).thenReturn(schoolYearIDDataModel);
//
//        SchoolYearID schoolYearID = mock(SchoolYearID.class);
//        when(iSchoolYearIDMapper.toDomain(schoolYearIDDataModel)).thenReturn(schoolYearID);
//        //act
//        SchoolYearID result = programmeEditionRepositorySpringDataImpl.getSchoolYearIDByProgrammeEdition(programmeEdition);
//        //assert
//        assertNotNull(result);
//    }

//    @Test
//    void shouldReturnNullIfProgrammeEditionDataModelNotExists() throws Exception {
//        //arrange
//        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
//        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
//        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
//        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
//        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
//        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
//                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);
//
//        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
//
//        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
//        when(iProgrammeEditionMapper.toDataModel(programmeEdition)).thenReturn(Optional.empty());
//        //act
//        SchoolYearID result = programmeEditionRepositorySpringDataImpl.getSchoolYearIDByProgrammeEdition(programmeEdition);
//        //assert
//        assertNull(result);
//    }

//    @Test
//    void shouldReturnNullIfParameterProgrammeEditionNull() throws Exception {
//        //arrange
//        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
//        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
//        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
//        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
//        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
//        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
//                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);
//
//        ProgrammeEdition programmeEdition = null;
//        //act
//        SchoolYearID result = programmeEditionRepositorySpringDataImpl.getSchoolYearIDByProgrammeEdition(programmeEdition);
//        //assert
//        assertNull(result);
//    }

//    @Test
//    void shouldReturnNullIfSchoolYearIDMapperThrowsException() throws Exception {
//        //arrange
//        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
//        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
//        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
//        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
//        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
//        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
//                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);
//
//        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
//
//        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
//        when(iProgrammeEditionMapper.toDataModel(programmeEdition)).thenReturn(Optional.of(programmeEditionDataModel));
//
//        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
//        when(programmeEditionDataModel.getSchoolYearIDDataModel()).thenReturn(schoolYearIDDataModel);
//
//        SchoolYearID schoolYearID = mock(SchoolYearID.class);
//        when(iSchoolYearIDMapper.toDomain(schoolYearIDDataModel)).thenThrow(IllegalArgumentException.class);
//        //act
//        SchoolYearID result = programmeEditionRepositorySpringDataImpl.getSchoolYearIDByProgrammeEdition(programmeEdition);
//        //assert
//        assertNull(result);
//    }
}