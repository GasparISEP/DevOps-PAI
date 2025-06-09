package PAI.mapper.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.IProgrammeEnrolmentFactory;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.programmeEnrolment.ProgrammeEnrolmentFactoryImpl;
import PAI.mapper.accessMethod.AccessMethodIDMapperImpl;
import PAI.mapper.accessMethod.IAccessMethodIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programme.ProgrammeIDMapperImpl;
import PAI.mapper.student.IStudentIDMapper;
import PAI.mapper.student.StudentIDMapperImpl;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.programmeEnrolment.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentMapperImplTest {

    private IProgrammeEnrolmentFactory _peFactoryDouble;
    private IProgrammeEnrolmentIDMapper _peIDMapperDouble;
    private IProgrammeIDMapper _programmeIDMapperDouble;
    private IStudentIDMapper _studentIDMapperDouble;
    private IAccessMethodIDMapper _amIDMapperDouble;
    private ProgrammeEnrolmentID _peIDDouble;
    private ProgrammeEnrolmentIDDataModel _peIDDataModelDouble;
    private StudentID _studentIDDouble;
    private StudentIDDataModel _studentIDDataModelDouble;
    private ProgrammeID _programmeIDDouble;
    private ProgrammeIDDataModel _programmeIDDataModelDouble;
    private AccessMethodID _amIDDouble;
    private AccessMethodIDDataModel _amIDDataModelDouble;
    private ProgrammeEnrolment _peDouble;
    private ProgrammeEnrolmentDataModel _peDMDouble;
    private Date _dateDouble;
    private LocalDate _localDateDouble;
    private ProgrammeEnrolmentGeneratedID _generatedIDDouble;
    private UUID _uuidDouble;

    // Arrange
    private void createDoubles() {
        _peFactoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);
        _peIDMapperDouble = mock(ProgrammeEnrolmentIDMapperImpl.class);
        _programmeIDMapperDouble = mock(ProgrammeIDMapperImpl.class);
        _studentIDMapperDouble = mock(StudentIDMapperImpl.class);
        _amIDMapperDouble = mock(AccessMethodIDMapperImpl.class);
        _peIDDouble = mock(ProgrammeEnrolmentID.class);
        _peIDDataModelDouble = mock(ProgrammeEnrolmentIDDataModel.class);
        _studentIDDouble = mock(StudentID.class);
        _studentIDDataModelDouble = mock(StudentIDDataModel.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _programmeIDDataModelDouble = mock(ProgrammeIDDataModel.class);
        _amIDDouble = mock(AccessMethodID.class);
        _amIDDataModelDouble = mock(AccessMethodIDDataModel.class);
        _peDouble = mock(ProgrammeEnrolment.class);
        _peDMDouble = mock(ProgrammeEnrolmentDataModel.class);
        _dateDouble = mock(Date.class);
        _localDateDouble = mock(LocalDate.class);
        _generatedIDDouble = mock(ProgrammeEnrolmentGeneratedID.class);
        _uuidDouble = UUID.randomUUID();
    }

    @Test
    public void testConstructorWithParameters() {
        // Arrange
        createDoubles();
        // Act
        ProgrammeEnrolmentMapperImpl
                peMapper =
                new ProgrammeEnrolmentMapperImpl(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);
        // Assert
        assertNotNull(peMapper);
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeEnrolmentFactoryNull() {
        // Arrange
        createDoubles();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapperImpl(null, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeEnrolmentIDMapperNull() {
        // Arrange
        createDoubles();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapperImpl(_peFactoryDouble, null, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeIDMapperNull() {
        // Arrange
        createDoubles();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapperImpl(_peFactoryDouble, _peIDMapperDouble, null, _studentIDMapperDouble, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfStudentIDMapperNull() {
        // Arrange
        createDoubles();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapperImpl(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, null, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfAccessMethodIDMapperNull() {
        // Arrange
        createDoubles();
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapperImpl(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, null));
    }

    @Test
    void shouldReturnExceptionAndNotCreateDomainProgrammeEnrolmentIfDataModelNull() {
        // Arrange
        createDoubles();
        ProgrammeEnrolmentMapperImpl
                peMapper =
                new ProgrammeEnrolmentMapperImpl(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peMapper.toDomain(null));
    }

    @Test
    void shouldReturnDomainProgrammeEnrolmentFromDataModel() {
        // Arrange
        createDoubles();
        ProgrammeEnrolmentMapperImpl peMapper = new ProgrammeEnrolmentMapperImpl(
                _peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble
        );

        when(_peIDDataModelDouble.getStudentID()).thenReturn(_studentIDDataModelDouble);
        when(_peDMDouble.getProgrammeEnrolmentID()).thenReturn(_peIDDataModelDouble);
        when(_studentIDMapperDouble.dataModelToDomain(_studentIDDataModelDouble)).thenReturn(_studentIDDouble);

        when(_peDMDouble.getAccessMethodID()).thenReturn(_amIDDataModelDouble);
        when(_amIDMapperDouble.toVO(_amIDDataModelDouble)).thenReturn(Optional.of(_amIDDouble));

        when(_peIDDataModelDouble.getProgrammeID()).thenReturn(_programmeIDDataModelDouble);
        when(_programmeIDMapperDouble.toDomain(_programmeIDDataModelDouble)).thenReturn(_programmeIDDouble);

        when(_peDMDouble.getDate()).thenReturn(_localDateDouble);
        when(_peDMDouble.getProgrammeEnrolmentGID()).thenReturn(_uuidDouble);

        // Act
        ProgrammeEnrolment result = peMapper.toDomain(_peDMDouble);

        // Assert
        assertAll(
                () -> assertEquals(_studentIDDouble, result.getStudentID()),
                () -> assertEquals(_amIDDouble, result.getAccessMethodID()),
                () -> assertEquals(_programmeIDDouble, result.getProgrammeID()),
                () -> assertEquals(new Date(_localDateDouble), result.getDate()),
                () -> assertEquals(new ProgrammeEnrolmentGeneratedID(_uuidDouble), result.getProgrammeEnrolmentGeneratedID())
        );
    }

    @Test
    void shouldReturnExceptionAndNotCreateDataModelProgrammeEnrolmentIfDomainNull() {
        // Arrange
        createDoubles();
        ProgrammeEnrolmentMapperImpl
                peMapper =
                new ProgrammeEnrolmentMapperImpl(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peMapper.toDataModel(null));
    }

    @Test
    void shouldReturnDataModelProgrammeEnrolmentFromDomain() {
        // Arrange
        createDoubles();
        ProgrammeEnrolmentMapperImpl peMapper = new ProgrammeEnrolmentMapperImpl(
                _peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble
        );

        // Mocks para valores encapsulados no EmbeddedID
        when(_peIDDataModelDouble.getStudentID()).thenReturn(_studentIDDataModelDouble);
        when(_peIDDataModelDouble.getProgrammeID()).thenReturn(_programmeIDDataModelDouble);

        when(_peDouble.getProgrammeEnrolmentID()).thenReturn(_peIDDouble);
        when(_peIDMapperDouble.domainToDataModel(_peIDDouble)).thenReturn(_peIDDataModelDouble);
        when(_peDouble.getStudentID()).thenReturn(_studentIDDouble);
        when(_studentIDMapperDouble.domainToDataModel(_studentIDDouble)).thenReturn(_studentIDDataModelDouble);
        when(_peDouble.getAccessMethodID()).thenReturn(_amIDDouble);
        when(_amIDMapperDouble.toDataModel(_amIDDouble)).thenReturn(Optional.of(_amIDDataModelDouble));
        when(_peDouble.getProgrammeID()).thenReturn(_programmeIDDouble);
        when(_programmeIDMapperDouble.toData(_programmeIDDouble)).thenReturn(_programmeIDDataModelDouble);
        when(_peDouble.getDate()).thenReturn(_dateDouble);
        when(_dateDouble.getLocalDate()).thenReturn(_localDateDouble);
        when(_peDouble.getProgrammeEnrolmentGeneratedID()).thenReturn(_generatedIDDouble);
        when(_generatedIDDouble.getProgrammeEnrolmentGID()).thenReturn(_uuidDouble);

        // Act
        ProgrammeEnrolmentDataModel result = peMapper.toDataModel(_peDouble);

        // Assert
        assertAll(
                () -> assertEquals(_peIDDataModelDouble, result.getProgrammeEnrolmentID()),
                () -> assertEquals(_studentIDDataModelDouble, result.getProgrammeEnrolmentID().getStudentID()),
                () -> assertEquals(_programmeIDDataModelDouble, result.getProgrammeEnrolmentID().getProgrammeID()),
                () -> assertEquals(_amIDDataModelDouble, result.getAccessMethodID()),
                () -> assertEquals(_localDateDouble, result.getDate()),
                () -> assertEquals(_uuidDouble, result.getProgrammeEnrolmentGID())
        );
    }
}