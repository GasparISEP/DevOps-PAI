package PAI.mapper.programmeEdition;

import PAI.VOs.*;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionIdMapperImplTest {

    @Test
    void shouldCreateProgrammeEditionIdMapper() {
        //arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        //act
        ProgrammeEditionIdMapperImpl programmeEditionIdMapper = new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);
        //assert
        assertNotNull(programmeEditionIdMapper);
    }

    @Test
    void nullProgrammeIDMapperThrowsException() {
        //arrange
        IProgrammeIDMapper programmeIdMapper = null;
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);});
    }

    @Test
    void nullSchoolYearIDMapperThrowsException() {
        //arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = null;
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);});
    }

    @Test
    void shouldMapProgrammeEditionIdToDomain() throws Exception {
        // arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionIdMapperImpl mapper = new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);

        ProgrammeIDDataModel programmeIdDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        when(programmeEditionIdDataModel.getProgrammeIdDataModel()).thenReturn(programmeIdDataModel);
        when(programmeEditionIdDataModel.get_schoolYearIDDataModel()).thenReturn(schoolYearIDDataModel);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeIdMapper.toDomain(programmeIdDataModel)).thenReturn(programmeID);
        when(schoolYearIdMapper.toDomain(schoolYearIDDataModel)).thenReturn(schoolYearID);

        // act
        ProgrammeEditionID result = mapper.toDomain(programmeEditionIdDataModel);

        // assert
        assertNotNull(result);
        assertEquals(programmeID, result.getProgrammeID());
        assertEquals(schoolYearID, result.getSchoolYearID());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIDDataModelIsNull() {
        // arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionIdMapperImpl mapper = new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);

        ProgrammeIDDataModel programmeIdDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        when(programmeEditionIdDataModel.getProgrammeIdDataModel()).thenReturn(null);
        when(programmeEditionIdDataModel.get_schoolYearIDDataModel()).thenReturn(schoolYearIDDataModel);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeIdMapper.toDomain(programmeIdDataModel)).thenReturn(programmeID);
        when(schoolYearIdMapper.toDomain(schoolYearIDDataModel)).thenReturn(schoolYearID);
        //act + assert
        assertThrows(Exception.class, () -> {mapper.toDomain(programmeEditionIdDataModel);});
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDDataModelIsNull() {
        //arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionIdMapperImpl mapper = new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);

        ProgrammeIDDataModel programmeIdDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        when(programmeEditionIdDataModel.getProgrammeIdDataModel()).thenReturn(programmeIdDataModel);
        when(programmeEditionIdDataModel.get_schoolYearIDDataModel()).thenReturn(null);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeIdMapper.toDomain(programmeIdDataModel)).thenReturn(programmeID);
        when(schoolYearIdMapper.toDomain(schoolYearIDDataModel)).thenReturn(schoolYearID);
        //act + assert
        assertThrows(Exception.class, () -> {mapper.toDomain(programmeEditionIdDataModel);});
    }

    @Test
    void shouldMapProgrammeEditionIdToDataModel() throws Exception {
        //arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionIdMapperImpl mapper = new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);

        when(programmeEditionId.getProgrammeID()).thenReturn(programmeID);
        when(programmeEditionId.getSchoolYearID()).thenReturn(schoolYearID);

        when(programmeIdMapper.toData(programmeID)).thenReturn(programmeIDDataModel);
        when(schoolYearIdMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        //act
        ProgrammeEditionIdDataModel result = mapper.toDataModel(programmeEditionId);
        //assert
        assertNotNull(result);
        assertEquals(programmeIDDataModel, result.getProgrammeIdDataModel());
        assertEquals(schoolYearIDDataModel, result.get_schoolYearIDDataModel());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIdIsNull() {
        //arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionIdMapperImpl mapper = new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);

        when(programmeEditionId.getProgrammeID()).thenReturn(null);
        when(programmeEditionId.getSchoolYearID()).thenReturn(schoolYearID);

        when(programmeIdMapper.toData(programmeID)).thenReturn(programmeIDDataModel);
        when(schoolYearIdMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        //act + assert
        assertThrows(Exception.class, () -> {mapper.toDataModel(programmeEditionId);});
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDIsNull() {
        //arrange
        IProgrammeIDMapper programmeIdMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIdMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionIdMapperImpl mapper = new ProgrammeEditionIdMapperImpl(programmeIdMapper, schoolYearIdMapper);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);

        when(programmeEditionId.getProgrammeID()).thenReturn(programmeID);
        when(programmeEditionId.getSchoolYearID()).thenReturn(null);

        when(programmeIdMapper.toData(programmeID)).thenReturn(programmeIDDataModel);
        when(schoolYearIdMapper.toDataModel(schoolYearID)).thenReturn(schoolYearIDDataModel);
        //act + assert
        assertThrows(Exception.class, () -> {mapper.toDataModel(programmeEditionId);});
    }
}