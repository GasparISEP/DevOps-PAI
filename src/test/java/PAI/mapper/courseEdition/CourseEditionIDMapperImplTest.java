package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.mapper.courseInStudyPlanID.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionIDMapperImplTest {

    @Test
    void shouldThrowExceptionIfToDomainMethodReceivesANullCourseEditionIDDataModel() throws Exception {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = null;
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl();

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {mapper.toDomain(courseEditionIDDataModel, programmeEditionIdMapper, courseInStudyPlanIDMapper);});

        // Assert
        assertNotNull(mapper);
        assertEquals(exception.getMessage(), "CourseEditionIDDataModel cannot be null");
    }

    @Test
    void shouldThrowExceptionIfToDomainMethodReceivesANullProgrammeEditionIdMapper() throws Exception {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = null;
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl();

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {mapper.toDomain(courseEditionIDDataModel, programmeEditionIdMapper, courseInStudyPlanIDMapper);});

        // Assert
        assertNotNull(mapper);
        assertEquals(exception.getMessage(), "ProgrammeEditionIdMapper cannot be null");
    }

    @Test
    void shouldThrowExceptionIfToDomainMethodReceivesANullCourseInStudyPlanIdMapper() throws Exception {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = null;
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl();

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {mapper.toDomain(courseEditionIDDataModel, programmeEditionIdMapper, courseInStudyPlanIDMapper);});

        // Assert
        assertNotNull(mapper);
        assertEquals(exception.getMessage(), "CourseInStudyPlanIDMapper cannot be null");
    }

    @Test
    void shouldReturnACourseEditionIDWhenConvertDataModelToDomain() throws Exception {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl();

        ProgrammeEditionIdDataModel pEIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        when(programmeEditionIdMapper.dataModelToDomain(pEIDDataModel)).thenReturn(pEID);

        CourseInStudyPlanIDDataModel cISPIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanID cISPID = mock(CourseInStudyPlanID.class);
        when(courseInStudyPlanIDMapper.toDomain(cISPIDDataModel)).thenReturn(cISPID);

        when(courseEditionIDDataModel.getProgrammeEditionIDDataModel()).thenReturn(pEIDDataModel);
        when(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel()).thenReturn(cISPIDDataModel);

        // Act
        CourseEditionID cEID = mapper.toDomain(courseEditionIDDataModel, programmeEditionIdMapper, courseInStudyPlanIDMapper);

        // Assert
        assertNotNull(cEID);
    }

    @Test
    void shouldReturnNullWhenConvertDomainToDataModel() throws Exception {
        // Arrange
        CourseEditionID cEID = mock(CourseEditionID.class);
        CourseEditionIDMapperImpl mapper = new CourseEditionIDMapperImpl();

        // Act
        CourseEditionIDDataModel dataModel  = mapper.toDataModel(cEID);

        // Assert
        assertNotNull(mapper);
        assertNull(dataModel);
    }
}