package PAI.mapper;

import PAI.VOs.Date;
import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEditionEnrolment;
import PAI.factory.IProgrammeEditionEnrolmentFactory;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionEnrolmentMapperImplTest {



    @Test
    void testConstructorWithNullProgrammeEditionEnrolmentIDMapper() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentMapperImpl(null, mock(IProgrammeEditionEnrolmentFactory.class));
        });
    }

    @Test
    void testConstructorWithNullProgrammeEditionEnrolmentFactory() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentMapperImpl(mock(IProgrammeEditionEnrolmentIDMapper.class),
                    null);
        });
    }

    @Test
    void testToDomainWhenDataModelIsNull() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentIDMapper mockProgrammeEditionEnrolmentIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IProgrammeEditionEnrolmentFactory mockProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        ProgrammeEditionEnrolmentMapperImpl mapper = new ProgrammeEditionEnrolmentMapperImpl(
                mockProgrammeEditionEnrolmentIDMapper, mockProgrammeEditionEnrolmentFactory
        );

        // Act
        Optional<ProgrammeEditionEnrolment> result = mapper.toDomain(null);

        // Assert
        assertFalse(result.isPresent(), "Expected Optional to be empty when dataModel is null.");
    }

    @Test
    void testToDomainWhenDataModelIsValid() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentIDMapper mockProgrammeEditionEnrolmentIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IProgrammeEditionEnrolmentFactory mockProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        ProgrammeEditionEnrolmentMapperImpl mapper = new ProgrammeEditionEnrolmentMapperImpl(
                mockProgrammeEditionEnrolmentIDMapper, mockProgrammeEditionEnrolmentFactory
        );

        ProgrammeEditionEnrolmentDataModel dataModel = mock(ProgrammeEditionEnrolmentDataModel.class);
        ProgrammeEditionEnrolmentID pEEID = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentIDDataModel idDataModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        when(dataModel.getProgrammeEditionEnrolmentIDDataModel()).thenReturn(idDataModel);
        when(mockProgrammeEditionEnrolmentIDMapper.toDomain(idDataModel)).thenReturn(Optional.ofNullable(pEEID));
        when(pEEID.getStudentiD()).thenReturn(mock(StudentID.class));
        when(pEEID.getProgrammeEditionId()).thenReturn(mock(ProgrammeEditionID.class));
        when(dataModel.getEnrolmentDate()).thenReturn(LocalDate.now());
        when(mockProgrammeEditionEnrolmentFactory.createWithEnrolmentDate(any(), any(), any(), any()))
                .thenReturn(mock(ProgrammeEditionEnrolment.class));

        // Act
        Optional<ProgrammeEditionEnrolment> result = mapper.toDomain(dataModel);

        // Assert
        assertTrue(result.isPresent(), "Expected a non-empty Optional.");
        assertNotNull(result.get(), "Expected ProgrammeEditionEnrolment to be created.");
    }

    @Test
    void testToDataModelWhenDomainIsNull() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentIDMapper mockProgrammeEditionEnrolmentIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IProgrammeEditionEnrolmentFactory mockProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        ProgrammeEditionEnrolmentMapperImpl mapper = new ProgrammeEditionEnrolmentMapperImpl(
                mockProgrammeEditionEnrolmentIDMapper, mockProgrammeEditionEnrolmentFactory
        );

        // Act
        Optional<ProgrammeEditionEnrolmentDataModel> result = mapper.toDataModel(null);

        // Assert
        assertFalse(result.isPresent(), "Expected Optional to be empty when domain is null.");
    }

    @Test
    void testToDataModelWhenDomainIsValid() throws Exception {
        // Arrange
        IProgrammeEditionEnrolmentIDMapper mockProgrammeEditionEnrolmentIDMapper = mock(IProgrammeEditionEnrolmentIDMapper.class);
        IProgrammeEditionEnrolmentFactory mockProgrammeEditionEnrolmentFactory = mock(IProgrammeEditionEnrolmentFactory.class);
        ProgrammeEditionEnrolmentMapperImpl mapper = new ProgrammeEditionEnrolmentMapperImpl(
                mockProgrammeEditionEnrolmentIDMapper, mockProgrammeEditionEnrolmentFactory
        );

        ProgrammeEditionEnrolment domain = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolmentID pEEID = mock(ProgrammeEditionEnrolmentID.class);
        when(domain.identity()).thenReturn(pEEID);
        when(domain.getEnrolmentDate()).thenReturn(Date.now());

        ProgrammeEditionEnrolmentIDDataModel idDataModel = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        when(mockProgrammeEditionEnrolmentIDMapper.toDataModel(pEEID)).thenReturn(Optional.ofNullable(idDataModel));

        // Act
        Optional<ProgrammeEditionEnrolmentDataModel> result = mapper.toDataModel(domain);

        // Assert
        assertTrue(result.isPresent(), "Expected a non-empty Optional.");
        assertEquals(idDataModel, result.get().getProgrammeEditionEnrolmentIDDataModel(), "The ID DataModel should match.");
    }


}