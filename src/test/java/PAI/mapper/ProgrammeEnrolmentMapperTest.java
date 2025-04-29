package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.factory.ProgrammeEnrolmentFactoryImpl;
import PAI.mapper.accessMethod.AccessMethodIDMapperImpl;
import PAI.mapper.accessMethod.IAccessMethodIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programme.ProgrammeIDMapperImpl;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentMapperTest {

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

    private void createDoubles() {
        _peFactoryDouble = mock(ProgrammeEnrolmentFactoryImpl.class);
        _peIDMapperDouble = mock(ProgrammeEnrolmentIDMapper.class);
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
    }

    @Test
    public void testConstructorWithParameters() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeEnrolmentMapper peMapper = new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);

        //Assert
        assertNotNull(peMapper);
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeEnrolmentFactoryNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapper(null, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeEnrolmentIDMapperNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapper(_peFactoryDouble, null, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfProgrammeIDMapperNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, null, _studentIDMapperDouble, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfStudentIDMapperNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, null, _amIDMapperDouble));
    }

    @Test
    public void shouldReturnExceptionAndNotConstructIfAccessMethodIDMapperNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, null));
    }

    @Test
    void shouldReturnExceptionAndNotCreateDomainProgrammeEnrolmentIfDataModelNull () {
        //Arrange
        createDoubles();

        ProgrammeEnrolmentMapper peMapper = new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peMapper.toDomain(null));
    }

    @Test
    void shouldReturnDomainProgrammeEnrolmentFromDataModel() {
        //Arrange
        createDoubles();

        ProgrammeEnrolmentMapper peMapper = new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);

        when(_peDMDouble.getStudentID()).thenReturn(_studentIDDataModelDouble);
        when(_studentIDMapperDouble.dataModelToDomain(_studentIDDataModelDouble)).thenReturn(_studentIDDouble);

        when(_peDMDouble.getAccessMethodID()).thenReturn(_amIDDataModelDouble);
        when(_amIDMapperDouble.toVO(_amIDDataModelDouble)).thenReturn(Optional.of(_amIDDouble));

        when(_peDMDouble.getProgrammeID()).thenReturn(_programmeIDDataModelDouble);
        when(_programmeIDMapperDouble.toDomain(_programmeIDDataModelDouble)).thenReturn(_programmeIDDouble);

        when(_peDMDouble.getDate()).thenReturn(_localDateDouble);

        when(_peFactoryDouble.createProgrammeEnrolment(eq(_studentIDDouble), eq(_amIDDouble), eq(_programmeIDDouble), any(Date.class))).thenReturn(_peDouble);

        //Act
        ProgrammeEnrolment result = peMapper.toDomain(_peDMDouble);

        //Assert
        assertEquals(_peDouble, result);
    }

    @Test
    void shouldReturnExceptionAndNotCreateDataModelProgrammeEnrolmentIfDomainNull () {
        //Arrange
        createDoubles();

        ProgrammeEnrolmentMapper peMapper = new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> peMapper.toDataModel(null));
    }

    @Test
    void shouldReturnDataModelProgrammeEnrolmentFromDomain() {
        //Arrange
        createDoubles();

        ProgrammeEnrolmentMapper peMapper = new ProgrammeEnrolmentMapper(_peFactoryDouble, _peIDMapperDouble, _programmeIDMapperDouble, _studentIDMapperDouble, _amIDMapperDouble);

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

        when(_peFactoryDouble.createProgrammeEnrolment(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble)).thenReturn(_peDouble);

        ProgrammeEnrolmentDataModel expected = new ProgrammeEnrolmentDataModel(_peIDDataModelDouble, _programmeIDDataModelDouble, _studentIDDataModelDouble, _amIDDataModelDouble, _localDateDouble);

        //Act
        ProgrammeEnrolmentDataModel result = peMapper.toDataModel(_peDouble);

        //Assert
        assertAll(
                () -> assertEquals(expected.getProgrammeEnrolmentID(), result.getProgrammeEnrolmentID()),
                () -> assertEquals(expected.getStudentID(), result.getStudentID()),
                () -> assertEquals(expected.getProgrammeID(), result.getProgrammeID()),
                () -> assertEquals(expected.getAccessMethodID(), result.getAccessMethodID()),
                () -> assertEquals(expected.getDate(), result.getDate())
        );
    }
}