package PAI.mapper.programmeEditionEnrolment;

import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentIDMapperImplTest {

    @Test
    void shouldCreateProgrammeEditionEnrolmentIDMapper() {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);

        ProgrammeEditionEnrolmentIDMapperImpl mapper = new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, studentIDMapper);

        assertNotNull(mapper);
    }

    @Test
    void nullProgrammeEditionIdMapperThrowsException() {
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentIDMapperImpl(null, studentIDMapper));
    }

    @Test
    void nullStudentIdMapperThrowsException() {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, null));
    }

    @Test
    void shouldMapToDomain() throws Exception {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapperImpl mapper = new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, studentIDMapper);

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
        ProgrammeEditionEnrolmentIDMapperImpl mapper = new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, studentIDMapper);

        Optional<ProgrammeEditionEnrolmentID> result = mapper.toDomain(null);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldMapToDataModel() throws Exception {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapperImpl mapper = new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, studentIDMapper);

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
        ProgrammeEditionEnrolmentIDMapperImpl mapper = new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, studentIDMapper);

        Optional<ProgrammeEditionEnrolmentIDDataModel> result = mapper.toDataModel(null);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyWhenToDomainThrowsException() throws Exception {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapperImpl mapper = new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, studentIDMapper);

        ProgrammeEditionIdDataModel editionDM = mock(ProgrammeEditionIdDataModel.class);
        StudentIDDataModel studentDM = mock(StudentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel dataModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        when(dataModel.getProgrammeEditionIdDataModel()).thenReturn(editionDM);
        when(dataModel.getStudentIdDataModel()).thenReturn(studentDM);
        when(programmeEditionIdMapper.toDomain(editionDM)).thenThrow(new RuntimeException("Simulated failure"));

        Optional<ProgrammeEditionEnrolmentID> result = mapper.toDomain(dataModel);

        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyWhenToDataModelThrowsException() throws Exception {
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ProgrammeEditionEnrolmentIDMapperImpl mapper = new ProgrammeEditionEnrolmentIDMapperImpl(programmeEditionIdMapper, studentIDMapper);

        ProgrammeEditionID editionID = mock(ProgrammeEditionID.class);
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionEnrolmentID domain = mock(ProgrammeEditionEnrolmentID.class);

        when(domain.getProgrammeEditionId()).thenReturn(editionID);
        when(domain.getStudentiD()).thenReturn(studentID);
        when(programmeEditionIdMapper.toDataModel(editionID)).thenThrow(new RuntimeException("Simulated failure"));

        Optional<ProgrammeEditionEnrolmentIDDataModel> result = mapper.toDataModel(domain);

        assertFalse(result.isPresent());
    }

}


