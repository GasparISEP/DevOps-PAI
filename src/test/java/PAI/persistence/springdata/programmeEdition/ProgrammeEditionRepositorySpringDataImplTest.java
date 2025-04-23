package PAI.persistence.springdata.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.IProgrammeIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionRepositorySpringDataImplTest {

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
    void shouldCreateProgrammeEdition() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);
        // act
        boolean result = programmeEditionRepositorySpringDataImpl.createProgrammeEdition(programmeID, schoolYearID);
        // assert
        assertFalse(result);
    }

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
    void save() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition entity = mock(ProgrammeEdition.class);
        //act
        ProgrammeEdition programmeEdition = programmeEditionRepositorySpringDataImpl.save(entity);
        //assert
        assertNull(programmeEdition);

    }

    @Test
    void findAll() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);
        //act
        Iterable<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.findAll();
        //assert
        assertNull(result);
    }

    @Test
    void ofIdentity() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        //act
        Optional<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.ofIdentity(programmeEditionID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void containsOfIdentity() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        //act
        boolean result = programmeEditionRepositorySpringDataImpl.containsOfIdentity(programmeEditionID);
        //assert
        assertFalse(result);
    }

    @Test
    void getProgrammeEditionsByProgrammeID() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        List<ProgrammeEdition> result = programmeEditionRepositorySpringDataImpl.getProgrammeEditionsByProgrammeID(programmeID);
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getSchoolYearIDByProgrammeEdition() {
        //arrange
        IProgrammeEditionRepositorySpringData iProgrammeEditionRepositorySpringData = mock(IProgrammeEditionRepositorySpringData.class);
        IProgrammeEditionMapper iProgrammeEditionMapper = mock(IProgrammeEditionMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper iProgrammeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper iSchoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionRepositorySpringDataImpl programmeEditionRepositorySpringDataImpl = new ProgrammeEditionRepositorySpringDataImpl(
                iProgrammeEditionRepositorySpringData, iProgrammeEditionMapper, iProgrammeEditionIdMapper, iProgrammeIDMapper, iSchoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        //act
        SchoolYearID schoolYearID = programmeEditionRepositorySpringDataImpl.getSchoolYearIDByProgrammeEdition(programmeEdition);
        //assert
        assertNull(schoolYearID);
    }
}