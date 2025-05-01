package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolmentFactoryImpl;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentFactory;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentMapperImplTest {

    @Test
    void should_map_domain_to_data_model_successfully() throws Exception {
        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(idMapper, factory);

        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolment domain = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        Date enrolmentDate = mock(Date.class);

        when(domain.identity()).thenReturn(enrolmentID);
        when(idMapper.toDataModel(enrolmentID)).thenReturn(Optional.of(idDataModel));
        when(domain.getEnrolmentDate()).thenReturn(enrolmentDate);
        when(enrolmentDate.getLocalDate()).thenReturn(LocalDate.now());
        when(domain.isEnrolmentActive()).thenReturn(new EnrolmentStatus(true).isEnrolmentActive());

        // act
        Optional<CourseEditionEnrolmentDataModel> result = mapper.toDataModel(domain);

        // assert
        assertTrue(result.isPresent());
        assertEquals(idDataModel, result.get().findId());
    }

    @Test
    void should_map_data_model_to_domain_successfully() throws Exception {

        // Arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(idMapper, factory);

        CourseEditionEnrolmentDataModel dataModel = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        CourseEditionEnrolmentID domainId = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolment domain = mock(CourseEditionEnrolment.class);

        StudentID studentId = mock(StudentID.class);
        CourseEditionID courseEditionId = mock(CourseEditionID.class);

        when(dataModel.findId()).thenReturn(idDataModel);
        when(dataModel.findEnrolmentDate()).thenReturn(LocalDate.now());
        when(dataModel.isActive()).thenReturn(true);
        when(idMapper.toDomain(idDataModel)).thenReturn(Optional.of(domainId));
        when(domainId.getStudentID()).thenReturn(studentId);
        when(domainId.getCourseEditionID()).thenReturn(courseEditionId);
        when(factory.createWithEnrolmentDate(any(), any(), any(), any())).thenReturn(domain);

        // act
        Optional<CourseEditionEnrolment> result = mapper.toDomain(dataModel);

        // assert
        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void should_return_optional_empty_if_domain_is_null() throws Exception {

        //arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);
        CourseEditionEnrolmentMapperImpl mapper = new CourseEditionEnrolmentMapperImpl(idMapper, factory);


        //act
        Optional<CourseEditionEnrolmentDataModel> result = mapper.toDataModel(null);

        //assert
        assertTrue(result.isEmpty(),"Domain cannot be null!");
    }

    @Test
    void should_throw_exception_when_mapper_is_null() {

        // arrange
        ICourseEditionEnrolmentFactory factory = mock(ICourseEditionEnrolmentFactory.class);

        // act & assert
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseEditionEnrolmentMapperImpl(null, factory),
                "Expected constructor to throw an IllegalArgumentException"
        );

        assertEquals("ID mapper and factory cannot be null", thrown.getMessage());
    }

    @Test
    void should_throw_exception_when_factory_is_null() {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);

        // act & assert
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseEditionEnrolmentMapperImpl(idMapper, null),
                "Expected constructor to throw an IllegalArgumentException"
        );

        assertEquals("ID mapper and factory cannot be null", thrown.getMessage());
    }
}