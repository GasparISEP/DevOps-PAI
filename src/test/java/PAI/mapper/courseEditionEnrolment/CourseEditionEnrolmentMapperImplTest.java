package PAI.mapper.courseEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentMapperImplTest {

    private ICourseEditionEnrolmentGeneratedIDMapper _generatedIDMapperDouble;
    private ICourseEditionEnrolmentIDMapper _idMapperDouble;
    private ICourseEditionEnrolmentFactory _idFactoryDouble;

    private CourseEditionEnrolment _ceeDomainDouble;
    private CourseEditionEnrolmentDataModel _ceeDataModelDouble;
    private CourseEditionEnrolmentID _ceeIDDomainDouble;
    private CourseEditionEnrolmentIDDataModel _ceeIDDataModelDouble;
    private CourseEditionEnrolmentGeneratedID _ceeGeneratedIDDouble;
    private CourseEditionEnrolmentGeneratedIDDataModel _ceeGeneratedIDDataModelDouble;
    private CourseEditionID _courseEditionIDDouble;
    private Date _enrolmentDateDouble;

    void createDoubles() {
        _generatedIDMapperDouble = mock(ICourseEditionEnrolmentGeneratedIDMapper.class);
        _idMapperDouble = mock(ICourseEditionEnrolmentIDMapper.class);
        _idFactoryDouble = mock(ICourseEditionEnrolmentFactory.class);

        _ceeDomainDouble = mock(CourseEditionEnrolment.class);
        _ceeDataModelDouble = mock(CourseEditionEnrolmentDataModel.class);
        _ceeIDDomainDouble = mock(CourseEditionEnrolmentID.class);
        _ceeIDDataModelDouble = mock(CourseEditionEnrolmentIDDataModel.class);
        _enrolmentDateDouble = mock(Date.class);
        _ceeGeneratedIDDouble = mock(CourseEditionEnrolmentGeneratedID.class);
        _courseEditionIDDouble = mock(CourseEditionID.class);
        _ceeGeneratedIDDataModelDouble = mock(CourseEditionEnrolmentGeneratedIDDataModel.class);
    }

    @Test
    void should_construct_successfully() {
        //Arrange
        createDoubles();

        //Act
        CourseEditionEnrolmentMapperImpl ceeMapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        //Assert
        assertNotNull(ceeMapper);
    }

    @Test
    void should_throw_exception_when_generatedID_mapper_is_null() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseEditionEnrolmentMapperImpl(null, _idMapperDouble, _idFactoryDouble));
    }

    @Test
    void should_throw_exception_when_domain_id_mapper_is_null() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, null, _idFactoryDouble));
    }

    @Test
    void should_throw_exception_when_factory_is_null() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, null));
    }

    //----------------------------------------------------------------------------

    @Test
    void should_return_optional_empty_if_parameter_domain_is_null() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        //Act
        Optional<CourseEditionEnrolmentDataModel> result = mapper.toDataModel(null);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void toDataModelReturnsEmptyWhenIdMappingFails() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        when(_ceeDomainDouble.identity()).thenReturn(mock(CourseEditionEnrolmentID.class));
        when(_idMapperDouble.toDataModel(any())).thenReturn(Optional.empty());

        //Act
        Optional<CourseEditionEnrolmentDataModel> result = mapper.toDataModel(_ceeDomainDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_dataModel_from_domain_successfully() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        when(_ceeDomainDouble.identity()).thenReturn(_ceeIDDomainDouble);
        when(_idMapperDouble.toDataModel(_ceeIDDomainDouble)).thenReturn(Optional.of(_ceeIDDataModelDouble));

        when(_ceeDomainDouble.getGeneratedID()).thenReturn(_ceeGeneratedIDDouble);
        when(_generatedIDMapperDouble.toDataModel(_ceeGeneratedIDDouble)).thenReturn(_ceeGeneratedIDDataModelDouble);

        when(_ceeDomainDouble.getEnrolmentDate()).thenReturn(_enrolmentDateDouble);
        when(_enrolmentDateDouble.getLocalDate()).thenReturn(LocalDate.now());
        when(_ceeDomainDouble.isEnrolmentActive()).thenReturn(new EnrolmentStatus(true).isEnrolmentActive());

        //Act
        Optional<CourseEditionEnrolmentDataModel> result = mapper.toDataModel(_ceeDomainDouble);

        //Assert
        assertEquals(_ceeIDDataModelDouble, result.get().findId());
    }

    @Test
    void should_return_optional_empty_in_toDataModel_if_domainIDDataModel_empty() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        when(_ceeDataModelDouble.findId()).thenReturn(_ceeIDDataModelDouble);
        when(_idMapperDouble.toDomain(_ceeIDDataModelDouble)).thenReturn(Optional.of(_ceeIDDomainDouble));

        when(_ceeIDDomainDouble.getStudentID()).thenReturn(null);
        when(_ceeIDDomainDouble.getCourseEditionID()).thenReturn(_courseEditionIDDouble);

        when(_idMapperDouble.toDomain(any())).thenReturn(Optional.of(_ceeIDDomainDouble));
        when(_ceeDataModelDouble.findEnrolmentDate()).thenReturn(LocalDate.now());

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(_ceeDataModelDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_domain_from_data_model_successfully() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        StudentID studentId = mock(StudentID.class);
        CourseEditionID courseEditionId = mock(CourseEditionID.class);

        when(_ceeDataModelDouble.findId()).thenReturn(_ceeIDDataModelDouble);
        when(_ceeDataModelDouble.findEnrolmentDate()).thenReturn(LocalDate.now());
        when(_ceeDataModelDouble.isActive()).thenReturn(true);
        when(_idMapperDouble.toDomain(_ceeIDDataModelDouble)).thenReturn(Optional.of(_ceeIDDomainDouble));
        when(_ceeIDDomainDouble.getStudentID()).thenReturn(studentId);
        when(_ceeIDDomainDouble.getCourseEditionID()).thenReturn(courseEditionId);
        when(_idFactoryDouble.createWithEnrolmentDateAndUUID(any(), any(), any(), any(), any())).thenReturn(_ceeDomainDouble);

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(_ceeDataModelDouble);

        //Assert
        assertEquals(_ceeDomainDouble, result.get());
    }

    @Test
    void toDomainReturnsEmptyWhenStudentOrCourseOrDateIsNull() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        when(_idMapperDouble.toDomain(any())).thenReturn(Optional.of(_ceeIDDomainDouble));
        when(_ceeIDDomainDouble.getStudentID()).thenReturn(null); // Podes testar os outros com variações
        when(_ceeIDDomainDouble.getCourseEditionID()).thenReturn(mock(CourseEditionID.class));
        when(_ceeDataModelDouble.findEnrolmentDate()).thenReturn(LocalDate.now());

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(_ceeDataModelDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_optional_empty_if_dataModel_is_null() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(null);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void toDomainReturnsEmptyWhenIdMappingFails() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        when(_idMapperDouble.toDomain(any())).thenReturn(Optional.empty());

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(_ceeDataModelDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testToDomain_returnsEmptyWhenStudentIDIsNull() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        CourseEditionEnrolmentDataModel dataModel = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolmentID idWithNullStudent = mock(CourseEditionEnrolmentID.class);

        when(dataModel.findId()).thenReturn(mock(CourseEditionEnrolmentIDDataModel.class));
        when(idWithNullStudent.getStudentID()).thenReturn(null);
        when(idWithNullStudent.getCourseEditionID()).thenReturn(mock(CourseEditionID.class));
        when(_idMapperDouble.toDomain(any())).thenReturn(Optional.of(idWithNullStudent));
        when(dataModel.findEnrolmentDate()).thenReturn(LocalDate.now());

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(dataModel);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_optional_empty_if_courseEditionID_is_null() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        when(_idMapperDouble.toDomain(any())).thenReturn(Optional.of(_ceeIDDomainDouble));
        when(_ceeIDDomainDouble.getStudentID()).thenReturn(mock(StudentID.class));
        when(_ceeIDDomainDouble.getCourseEditionID()).thenReturn(null);
        when(_ceeDataModelDouble.findEnrolmentDate()).thenReturn(LocalDate.now());

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(_ceeDataModelDouble);

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_optional_empty_if_enrolment_date_is_null() throws Exception {
        //Arrange
        createDoubles();
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(_generatedIDMapperDouble, _idMapperDouble, _idFactoryDouble);

        when(_idMapperDouble.toDomain(any())).thenReturn(Optional.of(_ceeIDDomainDouble));
        when(_ceeIDDomainDouble.getStudentID()).thenReturn(mock(StudentID.class));
        when(_ceeIDDomainDouble.getCourseEditionID()).thenReturn(mock(CourseEditionID.class));
        when(_ceeDataModelDouble.findEnrolmentDate()).thenReturn(null);

        //Act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(_ceeDataModelDouble);

        //Assert
        assertTrue(result.isEmpty());
    }
}