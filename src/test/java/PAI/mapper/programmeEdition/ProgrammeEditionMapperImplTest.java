package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.schoolYearID.ISchoolYearIDMapper;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProgrammeEditionMapperImplTest {

    @Test
    void shouldCreateProgrammeEditionMapperWithParameters() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper);
        // assert
        assertNotNull(programmeEditionMapper);
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenProgrammeEditionFactoryNull() {
        // arrange
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(null, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper));
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenProgrammeEditionIDMapperNull() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(programmeEditionFactory, null, programmeIDMapper, schoolYearIDMapper));
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenProgrammeIDMapperNull() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, null, schoolYearIDMapper));
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenSchoolYearIDMapperNull() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, null));
    }

    //toDataModel TESTS
    @Test
    void shouldMapProgrammeEditionToProgrammeEditionDataModel() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEdition.identity()).thenReturn(programmeEditionID);
        when(programmeEditionIDMapper.toDataModel(programmeEdition.identity())).thenReturn(programmeEditionIdDataModel);

        // act
        Optional<ProgrammeEditionDataModel> pEDM = programmeEditionMapper.toDataModel(programmeEdition);
        // assert
        assertTrue(pEDM.isPresent());
    }

    @Test
    void shouldNotMapProgrammeEditionToProgrammeEditionDataModelIfProgrammeEditionNull() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper);
        // act
        Optional<ProgrammeEditionDataModel> pEDM = programmeEditionMapper.toDataModel(null);
        // assert
        assertTrue(pEDM.isEmpty());
    }

    @Test
    void shouldNotMapProgrammeEditionIfProgrammeEditionIDMapperToDataModelThrowsException() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEdition.identity()).thenReturn(programmeEditionID);
        when(programmeEditionIDMapper.toDataModel(programmeEdition.identity())).thenThrow(IllegalArgumentException.class);

        // act
        Optional<ProgrammeEditionDataModel> pEDM = programmeEditionMapper.toDataModel(programmeEdition);
        // assert
        assertTrue(pEDM.isEmpty());
    }

    @Test
    void shouldNotMapProgrammeEditionIfProgrammeEditionDataModelCreationFails() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEdition.identity()).thenReturn(programmeEditionID);
        when(programmeEditionIDMapper.toDataModel(programmeEdition.identity())).thenReturn(programmeEditionIdDataModel);

        try (MockedConstruction<ProgrammeEditionDataModel> mocked = mockConstruction(ProgrammeEditionDataModel.class,
                (mock, context) -> {
                    throw new RuntimeException("Failed to create ProgrammeEditionDataModel");
                })) {

        // act
        Optional<ProgrammeEditionDataModel> result = programmeEditionMapper.toDataModel(programmeEdition);

        // assert
        assertTrue(result.isEmpty());
        }
    }

//    toDomain TESTS
    @Test
    void shouldMapProgrammeEditionDataModelToProgrammeEdition() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
        when(programmeEditionIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel())).thenReturn(programmeEditionID);

        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        when(programmeEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
        when(programmeEditionDataModel.getProgrammeEditionIDDataModel().getProgrammeIdDataModel()).thenReturn(programmeIDDataModel);
        when(programmeIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().getProgrammeIdDataModel())).thenReturn(programmeID);


        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeEditionDataModel.getProgrammeEditionIDDataModel().getSchoolYearIDDataModel()).thenReturn(schoolYearIDDataModel);
        when(schoolYearIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel().getSchoolYearIDDataModel())).thenReturn(schoolYearID);

        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, schoolYearID)).thenReturn(mockProgrammeEdition);
        // act
        Optional<ProgrammeEdition> programmeEdition = programmeEditionMapper.toDomain(programmeEditionDataModel);
        // assert
        assertTrue(programmeEdition.isPresent());
    }

    @Test
    void shouldNotMapProgrammeEditionDataModelToProgrammeEditionIfProgrammeEditionDataModelNull() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper);
        // act
        Optional<ProgrammeEdition> programmeEdition = programmeEditionMapper.toDomain(null);
        // assert
        assertTrue(programmeEdition.isEmpty());
    }
}