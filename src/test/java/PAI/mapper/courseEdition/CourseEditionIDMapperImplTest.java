package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
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
    void shouldThrowExceptionIfCourseEditionIDMapperImplReceivesANullProgrammeEditionIdMapper() throws Exception {
        // Arrange
        IProgrammeEditionIdMapper programmeEditionIdMapper = null;
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionIDMapperImpl(programmeEditionIdMapper, courseInStudyPlanIDMapper);});

        // Assert
        assertEquals(exception.getMessage(), "ProgrammeEditionIdMapper cannot be null");
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIDMapperImplReceivesANullCourseInStudyPlanMapper() throws Exception {
        // Arrange
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionIDMapperImpl(programmeEditionIdMapper, courseInStudyPlanIDMapper);});

        // Assert
        assertEquals(exception.getMessage(), "CourseInStudyPlanIDMapper cannot be null");
    }

    @Test
    void shouldThrowExceptionIfToDomainMethodReceivesANullCourseEditionIDDataModel() throws Exception {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = null;
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl(programmeEditionIdMapper, courseInStudyPlanIDMapper);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {mapper.toDomain(courseEditionIDDataModel);});

        // Assert
        assertNotNull(mapper);
        assertEquals(exception.getMessage(), "CourseEditionIDDataModel cannot be null");
    }

    @Test
    void shouldReturnACourseEditionIDWhenConvertDataModelToDomain() throws Exception {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl(programmeEditionIdMapper, courseInStudyPlanIDMapper);

        ProgrammeEditionIdDataModel pEIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        when(programmeEditionIdMapper.toDomain(pEIDDataModel)).thenReturn(pEID);

        CourseInStudyPlanIDDataModel cISPIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanID cISPID = mock(CourseInStudyPlanID.class);
        when(courseInStudyPlanIDMapper.toDomain(cISPIDDataModel)).thenReturn(cISPID);

        when(courseEditionIDDataModel.getProgrammeEditionIDDataModel()).thenReturn(pEIDDataModel);
        when(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel()).thenReturn(cISPIDDataModel);

        // Act
        CourseEditionID cEID = mapper.toDomain(courseEditionIDDataModel);

        // Assert
        assertNotNull(cEID);
    }

    @Test
    void shouldThrowExceptionIfToDataModelMethodReceivesANullCourseEditionID() throws Exception {
        // Arrange
        CourseEditionID courseEditionID = null;
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl(programmeEditionIdMapper, courseInStudyPlanIDMapper);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {mapper.toDataModel(courseEditionID);});

        // Assert
        assertNotNull(mapper);
        assertEquals(exception.getMessage(), "CourseEditionID cannot be null");
    }

    @Test
    void shouldReturnACourseEditionIDWhenConvertDomainToDataModel() throws Exception {
        // Arrange
        CourseEditionID cEID = mock(CourseEditionID.class);
        IProgrammeEditionIdMapper programmeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionIDMapper mapper = new CourseEditionIDMapperImpl(programmeEditionIdMapper, courseInStudyPlanIDMapper);

        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        ProgrammeEditionIdDataModel pEIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        when(programmeEditionIdMapper.toDataModel(pEID)).thenReturn(pEIDDataModel);

        CourseInStudyPlanID cISPID = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanIDDataModel cISPIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        when(courseInStudyPlanIDMapper.toDataModel(cISPID)).thenReturn(cISPIDDataModel);

        when(cEID.getProgrammeEditionID()).thenReturn(pEID);
        when(cEID.getCourseInStudyPlanID()).thenReturn(cISPID);

        // Act
        CourseEditionIDDataModel dataModel  = mapper.toDataModel(cEID);

        // Assert
        assertNotNull(dataModel);
        assertEquals(dataModel.getProgrammeEditionIDDataModel(), pEIDDataModel);
        assertEquals(dataModel.getCourseInStudyPlanIDDataModel(), cISPIDDataModel);
    }
}