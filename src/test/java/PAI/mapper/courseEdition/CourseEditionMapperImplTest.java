package PAI.mapper.courseEdition;

import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionMapperImplTest {

    //--------Constructor Tests--------
    @Test
    void shouldCreateACourseEditionMapperImpl () {
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);

        // Act
        ICourseEditionMapper mapper = new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);

        // Assert
        assertNotNull(mapper);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper cEIDMapper = null;
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);});

        // Assert
        assertEquals("courseEditionIDMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = null;
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);});

        // Assert
        assertEquals("programmeEditionIdMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);});

        // Assert
        assertEquals("courseInStudyPlanIDMapper cannot be null", exception.getMessage());
    }


    @Test
    void shouldReturnNullWhenToDomainIsCalled() {
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionMapper mapper = new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);

        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);

        // Act
        CourseEdition courseEdition = mapper.toDomain(courseEditionDataModel, courseEditionFactory);

        // Assert
        assertNull(courseEdition);
    }

    @Test
    void shouldReturnNullWhenToDataModelIsCalled() {
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionMapper mapper = new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);

        CourseEdition courseEdition = mock(CourseEdition.class);

        // Act
        CourseEditionDataModel courseEditionDataModel = mapper.toDataModel(courseEdition);

        // Assert
        assertNull(courseEditionDataModel);
    }

}