package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionMapper;
import PAI.mapper.schoolYear.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDataModel
                (programmeIDDataModel, schoolYearIDDataModel)).thenReturn(Optional.of(programmeEditionDataModel));

        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIDDataModel);

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

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDDataModelByProgrammeIDAndSchoolYearIDDataModel
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

    @Test
    void shouldReturnProgrammeEditionIDsWithStartDateAfterGivenDate() throws Exception {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper
        );

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        java.time.LocalDate date = java.time.LocalDate.now();

        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        ProgrammeEditionIdDataModel dataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeIDDataModel, date))
                .thenReturn(List.of(dataModel));

        ProgrammeEditionID domainID = mock(ProgrammeEditionID.class);
        when(iProgrammeEditionIdMapper.toDomain(dataModel)).thenReturn(domainID);

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeID, date);

        // assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(domainID, result.get(0));
    }

    @Test
    void shouldThrowRuntimeExceptionWhenMappingFails() throws Exception {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper
        );

        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        LocalDate date = LocalDate.now();

        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);

        ProgrammeEditionIdDataModel dataModel = mock(ProgrammeEditionIdDataModel.class);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeIDDataModel, date))
                .thenReturn(List.of(dataModel));

        when(iProgrammeEditionIdMapper.toDomain(dataModel)).thenThrow(new RuntimeException("mapping failed"));

        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            repository.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeID, date);
        });

        assertTrue(exception.getMessage().contains("Erro ao mapear ProgrammeEditionIdDataModel"));
    }

    @Test
    void shouldReturnEmptyListWhenProgrammeIDIsNull() {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper
        );

        LocalDate date = LocalDate.now();

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(null, date);

        // assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenDateIsNull() {
        // arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper
        );

        ProgrammeID programmeID = mock(ProgrammeID.class);

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(programmeID, null);

        // assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnProgrammeEditionIDsWhenValidSchoolYearIDAndProgrammeIDsAreProvided() throws Exception {
        // Arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData,
                iProgrammeEditionMapper,
                iProgrammeEditionIdMapper,
                iProgrammeIDMapper,
                iSchoolYearIDMapper
        );

        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        List<ProgrammeID> programmeIDs = List.of(programmeID1, programmeID2);

        ProgrammeIDDataModel programmeIDDataModel1 = mock(ProgrammeIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel2 = mock(ProgrammeIDDataModel.class);
        List<ProgrammeIDDataModel> programmeIDDataModels = List.of(programmeIDDataModel1, programmeIDDataModel2);

        ProgrammeEditionIdDataModel peDataModel1 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionIdDataModel peDataModel2 = mock(ProgrammeEditionIdDataModel.class);
        List<ProgrammeEditionIdDataModel> dataModels = List.of(peDataModel1, peDataModel2);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);

        ProgrammeEditionID peDomainID1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID peDomainID2 = mock(ProgrammeEditionID.class);

        when(iSchoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(iProgrammeIDMapper.toData(programmeID1)).thenReturn(programmeIDDataModel1);
        when(iProgrammeIDMapper.toData(programmeID2)).thenReturn(programmeIDDataModel2);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDsBySchoolYearIdAndProgrammeIds(
                schoolYearIDDataModel, programmeIDDataModels)).thenReturn(dataModels);
        when(iProgrammeEditionIdMapper.toDomain(peDataModel1)).thenReturn(peDomainID1);
        when(iProgrammeEditionIdMapper.toDomain(peDataModel2)).thenReturn(peDomainID2);

        // Act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDs);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(peDomainID1));
        assertTrue(result.contains(peDomainID2));
    }
    @Test
    void shouldReturnEmptyListWhenNoProgrammeEditionIDsAreFound() {
        // Arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData,
                iProgrammeEditionMapper,
                iProgrammeEditionIdMapper,
                iProgrammeIDMapper,
                iSchoolYearIDMapper
        );

        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        List<ProgrammeID> programmeIDs = List.of(programmeID1, programmeID2);

        ProgrammeIDDataModel programmeIDDataModel1 = mock(ProgrammeIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel2 = mock(ProgrammeIDDataModel.class);
        List<ProgrammeIDDataModel> programmeIDDataModels = List.of(programmeIDDataModel1, programmeIDDataModel2);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);

        when(iSchoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(iProgrammeIDMapper.toData(programmeID1)).thenReturn(programmeIDDataModel1);
        when(iProgrammeIDMapper.toData(programmeID2)).thenReturn(programmeIDDataModel2);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDsBySchoolYearIdAndProgrammeIds(schoolYearIDDataModel, programmeIDDataModels)).thenReturn(List.of());

        // Act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDs);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenSchoolYearIDIsNull() {
        // Arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData,
                iProgrammeEditionMapper,
                iProgrammeEditionIdMapper,
                iProgrammeIDMapper,
                iSchoolYearIDMapper
        );

        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class));

        // Act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(null, programmeIDs);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenProgrammeIDsIsNull() {
        // Arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData,
                iProgrammeEditionMapper,
                iProgrammeEditionIdMapper,
                iProgrammeIDMapper,
                iSchoolYearIDMapper
        );

        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        // Act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, null);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenProgrammeIDsIsEmpty() {
        // Arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData,
                iProgrammeEditionMapper,
                iProgrammeEditionIdMapper,
                iProgrammeIDMapper,
                iSchoolYearIDMapper
        );

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        List<ProgrammeID> programmeIDs = List.of();

        // Act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDs);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenMethodfindProgrammeEditionIDsBySchoolYearIDAndProgrammeIDsFailsToMapProgrammeEditionIDFromDataModelToDomain() {
        // Arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);

        ProgrammeEditionRepositorySpringDataImpl repository = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData,
                iProgrammeEditionMapper,
                iProgrammeEditionIdMapper,
                iProgrammeIDMapper,
                iSchoolYearIDMapper
        );

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        List<ProgrammeID> programmeIDs = List.of(programmeID);

        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        ProgrammeEditionIdDataModel invalidDataModel = mock(ProgrammeEditionIdDataModel.class);

        when(iSchoolYearIDMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        when(iProgrammeIDMapper.toData(programmeID)).thenReturn(programmeIDDataModel);
        when(iProgrammeEditionRepositorySpringData.findProgrammeEditionIDsBySchoolYearIdAndProgrammeIds(schoolYearIDDataModel, List.of(programmeIDDataModel)))
                .thenReturn(List.of(invalidDataModel));

        when(iProgrammeEditionIdMapper.toDomain(invalidDataModel)).thenThrow(new RuntimeException("Invalid data model"));

        // Act
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            repository.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDs);
        });

        // Assert
        assertTrue(thrown.getMessage().contains("Error mapping ProgrammeEditionIdDataModel"));
        assertInstanceOf(RuntimeException.class, thrown.getCause());
        assertEquals("Invalid data model", thrown.getCause().getMessage());
    }
}