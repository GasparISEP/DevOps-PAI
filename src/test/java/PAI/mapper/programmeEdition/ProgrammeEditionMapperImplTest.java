package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.domain.schoolYear.SchoolYear;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.schoolYear.ISchoolYearIDMapper;
import PAI.mapper.schoolYear.SchoolYearIDMapperImpl;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionGeneratedIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.Optional;
import java.util.UUID;

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
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);
        // act
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);
        // assert
        assertNotNull(programmeEditionMapper);
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenProgrammeEditionFactoryNull() {

        // arrange
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(null, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper));

        // assert
        assertEquals("ProgrammeEditionFactory cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenProgrammeEditionIDMapperNull() {

        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(programmeEditionFactory,
                null, programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper));

        // assert
        assertEquals("ProgrammeEditionIDMapper cannot be null.", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenProgrammeIDMapperNull() {

        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                null, schoolYearIDMapper, programmeEditionGeneratedIDMapper));

        // assert
        assertEquals("ProgrammeIDMapper cannot be null.", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenSchoolYearIDMapperNull() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, null, programmeEditionGeneratedIDMapper));
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWhenProgrammeEditionGeneratedIDMapperNull() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, null));
    }

    //toDataModel TESTS
    @Test
    void shouldMapProgrammeEditionToProgrammeEditionDataModel() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(
                programmeEditionFactory, programmeEditionIDMapper, programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEdition.identity()).thenReturn(programmeEditionID);
        when(programmeEditionIDMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);

        ProgrammeEditionGeneratedID programmeEditionGeneratedID = mock(ProgrammeEditionGeneratedID.class);
        ProgrammeEditionGeneratedIDDataModel programmeEditionGeneratedIDDataModel = mock(ProgrammeEditionGeneratedIDDataModel.class);
        when(programmeEdition.getProgrammeEditionGeneratedGID()).thenReturn(programmeEditionGeneratedID);
        when(programmeEditionGeneratedIDMapper.toDataModel(programmeEditionGeneratedID)).thenReturn(programmeEditionGeneratedIDDataModel);

        //Act
        Optional<ProgrammeEditionDataModel> pEDM = programmeEditionMapper.toDataModel(programmeEdition);

        //Assert
        assertTrue(pEDM.isPresent());
    }

    @Test
    void shouldNotMapProgrammeEditionToProgrammeEditionDataModelIfProgrammeEditionNull() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);
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
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);

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
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);

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
    // Arrange
    IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
    IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
    IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
    ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
    IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

    ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(
            programmeEditionFactory,
            programmeEditionIDMapper,
            programmeIDMapper,
            schoolYearIDMapper,
            programmeEditionGeneratedIDMapper
    );

    ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
    ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
    ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
    SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
    ProgrammeEditionGeneratedIDDataModel programmeEditionGeneratedIDDataModel = mock(ProgrammeEditionGeneratedIDDataModel.class);

    ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
    ProgrammeID programmeID = mock(ProgrammeID.class);
    SchoolYearID schoolYearID = mock(SchoolYearID.class);
    ProgrammeEdition expectedProgrammeEdition = mock(ProgrammeEdition.class);


    when(programmeEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
    when(programmeEditionIdDataModel.getProgrammeIdDataModel()).thenReturn(programmeIDDataModel);
    when(programmeEditionIdDataModel.getSchoolYearIDDataModel()).thenReturn(schoolYearIDDataModel);
    when(programmeEditionDataModel.getProgrammeEditionGeneratedIDDataModel()).thenReturn(programmeEditionGeneratedIDDataModel);

    when(programmeEditionIDMapper.toDomain(programmeEditionIdDataModel)).thenReturn(programmeEditionID);
    when(programmeIDMapper.toDomain(programmeIDDataModel)).thenReturn(programmeID);
    when(schoolYearIDMapper.toDomain(schoolYearIDDataModel)).thenReturn(schoolYearID);


    UUID generatedId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    when(programmeEditionGeneratedIDDataModel.getGeneratedId()).thenReturn(generatedId);


    ProgrammeEditionGeneratedID programmeEditionGeneratedID = new ProgrammeEditionGeneratedID(generatedId);

    when(programmeEditionFactory.createProgrammeEdition(
            programmeEditionID,
            programmeID,
            schoolYearID,
            programmeEditionGeneratedID
    )).thenReturn(expectedProgrammeEdition);

    // Act
    Optional<ProgrammeEdition> result = programmeEditionMapper.toDomain(programmeEditionDataModel);

    // Assert
    assertTrue(result.isPresent(), "Result should be present");
    assertEquals(expectedProgrammeEdition, result.get(), "Result should match expected programme edition");
}


    @Test
    void shouldNotMapProgrammeEditionDataModelToProgrammeEditionIfProgrammeEditionDataModelNull() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);
        // act
        Optional<ProgrammeEdition> programmeEdition = programmeEditionMapper.toDomain(null);
        // assert
        assertTrue(programmeEdition.isEmpty());
    }

    @Test
    void shouldNotMapProgrammeEditionDataModelIfProgrammeEditionIDMapperToDomainThrowsException() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(programmeEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
        when(programmeEditionIDMapper.toDomain(programmeEditionDataModel.getProgrammeEditionIDDataModel())).thenThrow(IllegalArgumentException.class);

        // act
        Optional<ProgrammeEdition> programmeEdition = programmeEditionMapper.toDomain(programmeEditionDataModel);
        // assert
        assertTrue(programmeEdition.isEmpty());
    }

    @Test
    void shouldNotMapProgrammeEditionDataModelIfProgrammeEditionFactoryCreateProgrammeEditionThrowsException() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);

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
        ProgrammeEditionGeneratedID programmeEditionGeneratedID = mock(ProgrammeEditionGeneratedID.class);
        when(programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, schoolYearID, programmeEditionGeneratedID)).thenThrow(IllegalArgumentException.class);
        // act
        Optional<ProgrammeEdition> programmeEdition = programmeEditionMapper.toDomain(programmeEditionDataModel);
        // assert
        assertTrue(programmeEdition.isEmpty());
    }

    @Test
    void shouldNotMapProgrammeEditionDataModelIfProgrammeEditionFactoryCreateProgrammeEditionFails() throws Exception {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory, programmeEditionIDMapper,
                programmeIDMapper, schoolYearIDMapper, programmeEditionGeneratedIDMapper);

        ProgrammeEditionDataModel programmeEditionDataModel = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(programmeEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
        when(programmeEditionIDMapper.toDomain(programmeEditionIdDataModel)).thenReturn(programmeEditionID);
        when(programmeIDMapper.toDomain(programmeEditionIdDataModel.getProgrammeIdDataModel())).thenReturn(programmeID);
        when(schoolYearIDMapper.toDomain(programmeEditionIdDataModel.getSchoolYearIDDataModel())).thenReturn(schoolYearID);

        ProgrammeEditionGeneratedID programmeEditionGeneratedID = mock(ProgrammeEditionGeneratedID.class);
        when(programmeEditionFactory.createProgrammeEdition(programmeEditionID, programmeID, schoolYearID, programmeEditionGeneratedID))
                .thenThrow(new RuntimeException("Failed to create ProgrammeEdition"));

        // act
        Optional<ProgrammeEdition> result = programmeEditionMapper.toDomain(programmeEditionDataModel);

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyOptional_WhenProgrammeEditionIDMapperThrowsException() throws Exception {

        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionIdMapper programmeEditionIDMapper = mock(IProgrammeEditionIdMapper.class);
        IProgrammeIDMapper programmeIDMapper = mock(IProgrammeIDMapper.class);
        ISchoolYearIDMapper schoolYearIDMapper = mock(ISchoolYearIDMapper.class);
        IProgrammeEditionGeneratedIDMapper programmeEditionGeneratedIDMapper = mock(IProgrammeEditionGeneratedIDMapper.class);

        ProgrammeEditionMapperImpl mapper = new ProgrammeEditionMapperImpl(
                programmeEditionFactory,
                programmeEditionIDMapper,
                programmeIDMapper,
                schoolYearIDMapper,
                programmeEditionGeneratedIDMapper
        );

        ProgrammeEditionDataModel dataModel = mock(ProgrammeEditionDataModel.class);
        ProgrammeEditionIdDataModel idDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionGeneratedIDDataModel generatedIDDataModel = mock(ProgrammeEditionGeneratedIDDataModel.class);

        when(dataModel.getProgrammeEditionIDDataModel()).thenReturn(idDataModel);
        when(dataModel.getProgrammeEditionGeneratedIDDataModel()).thenReturn(generatedIDDataModel);

        when(programmeEditionIDMapper.toDomain(idDataModel)).thenThrow(new RuntimeException("Failed to create ProgrammeEdition"));

        // act
        Optional<ProgrammeEdition> result = mapper.toDomain(dataModel);

        // assert
        assertTrue(result.isEmpty());
    }
}