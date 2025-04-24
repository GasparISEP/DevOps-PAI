package PAI.mapper;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentIDMapperTest {

    @Test
    void shouldCreateProgrammeEditionEnrolmentIDMapper() {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);

        ProgrammeEditionEnrolmentIDMapper mapper = new ProgrammeEditionEnrolmentIDMapper(programmeEditionIdMapper, studentIDMapper);

        assertNotNull(mapper);
    }

    @Test
    void nullProgrammeEditionIdMapperThrowsException() {
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentIDMapper(null, studentIDMapper));
    }

    @Test
    void nullStudentIdMapperThrowsException() {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentIDMapper(programmeEditionIdMapper, null));
    }

    @Test
    void shouldMapToDomain() throws Exception {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapper mapper = new ProgrammeEditionEnrolmentIDMapper(programmeEditionIdMapper, studentIDMapper);

        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel dataModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        StudentID studentID = mock(StudentID.class);

        when(dataModel.getProgrammeEditionIdDataModel()).thenReturn(programmeEditionIdDataModel);
        when(dataModel.getStudentIdDataModel()).thenReturn(studentIDDataModel);

        when(programmeEditionIdMapper.toDomain(programmeEditionIdDataModel)).thenReturn(programmeEditionID);
        when(studentIDMapper.dataModelToDomain(studentIDDataModel)).thenReturn(studentID);

        Optional<ProgrammeEditionEnrolmentID> result = mapper.toDomain(dataModel);

        assertTrue(result.isPresent());
        assertEquals(programmeEditionID, result.get().getProgrammeEditionId());
        assertEquals(studentID, result.get().getStudentiD());
    }

    @Test
    void shouldMapToDomainWhenDataModelIsNull() {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapper mapper = new ProgrammeEditionEnrolmentIDMapper(programmeEditionIdMapper, studentIDMapper);

        Optional<ProgrammeEditionEnrolmentID> result = mapper.toDomain(null);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldMapToDataModel() throws Exception {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapper mapper = new ProgrammeEditionEnrolmentIDMapper(programmeEditionIdMapper, studentIDMapper);

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionEnrolmentID domain = mock(ProgrammeEditionEnrolmentID.class);

        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);

        when(domain.getProgrammeEditionId()).thenReturn(programmeEditionID);
        when(domain.getStudentiD()).thenReturn(studentID);
        when(programmeEditionIdMapper.toDataModel(programmeEditionID)).thenReturn(programmeEditionIdDataModel);
        when(studentIDMapper.domainToDataModel(studentID)).thenReturn(studentIDDataModel);

        Optional<ProgrammeEditionEnrolmentIDDataModel> result = mapper.toDataModel(domain);

        assertTrue(result.isPresent());
        assertEquals(programmeEditionIdDataModel, result.get().getProgrammeEditionIdDataModel());
        assertEquals(studentIDDataModel, result.get().getStudentIdDataModel());
    }

    @Test
    void shouldMapToDataModelWhenDomainIsNull() {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapper mapper = new ProgrammeEditionEnrolmentIDMapper(programmeEditionIdMapper, studentIDMapper);

        Optional<ProgrammeEditionEnrolmentIDDataModel> result = mapper.toDataModel(null);

        assertFalse(result.isPresent());
    }
}


