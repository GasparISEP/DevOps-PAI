package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    //--------toDomain Tests--------
    @Test
    void shouldReturnCourseEditionWhenToDomainMethodIsCalledWithValidArguments() throws Exception{
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionMapper mapper = new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);


        CourseEditionID cEID = mock(CourseEditionID.class);
        ProgrammeEditionID pEID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID cISPID = mock(CourseInStudyPlanID.class);

        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);

        when(cEIDMapper.toDomain(courseEditionDataModel.getCourseEditionIDDataModel())).thenReturn(cEID);
        when(pEIDMapper.dataModelToDomain(courseEditionDataModel.getProgrammeEditionIDDataModel())).thenReturn(pEID);
        when(cISPIDMapper.toDomain(courseEditionDataModel.getCourseInStudyPlanIDDataModel())).thenReturn(cISPID);

        CourseEdition expectedResult = mock(CourseEdition.class);
        when(courseEditionFactory.newCourseEdition_2(cEID, cISPID, pEID)).thenReturn(expectedResult);

        // Act
        CourseEdition courseEdition = mapper.toDomain(courseEditionDataModel, courseEditionFactory);

        // Assert
        assertNotNull(courseEdition);
        assertEquals(expectedResult, courseEdition);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionDataModelGivenAsArgumentInToDomainMethodIsNull() throws Exception{
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionMapper mapper = new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);

        CourseEditionDataModel courseEditionDataModel = null;
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {mapper.toDomain(courseEditionDataModel, courseEditionFactory);});

        // Assert
        assertEquals("courseEditionDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionFactoryGivenAsArgumentInToDomainMethodIsNull() throws Exception{
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionMapper mapper = new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);

        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);
        ICourseEditionFactory courseEditionFactory = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {mapper.toDomain(courseEditionDataModel, courseEditionFactory);});

        // Assert
        assertEquals("courseEditionFactory cannot be null", exception.getMessage());
    }

    //--------toDomain Tests--------
    @Test
    void shouldReturnACourseEditionDataModelWhenToDataModelMethodIsCalledWithValidArguments() throws Exception {
        // Arrange
        ICourseEditionIDMapper cEIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper pEIDMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper cISPIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionMapper mapper = new CourseEditionMapperImpl(cEIDMapper, pEIDMapper, cISPIDMapper);

        CourseEditionIDDataModel cEIDDM = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel pEIDDM = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel cISPIDDM = mock(CourseInStudyPlanIDDataModel.class);

        CourseEdition courseEdition = mock(CourseEdition.class);

        when(cEIDMapper.toDataModel(courseEdition.identity())).thenReturn(cEIDDM);
        when(pEIDMapper.domainToDataModel(courseEdition.getProgrammeEditionID())).thenReturn(pEIDDM);
        when(cISPIDMapper.toDataModel(courseEdition.getCourseInStudyPlanID())).thenReturn(cISPIDDM);

        // Act
        CourseEditionDataModel result = mapper.toDataModel(courseEdition);

        // Assert
        assertNotNull(courseEdition);
        assertEquals(cEIDDM, result.getCourseEditionIDDataModel());
        assertEquals(pEIDDM, result.getProgrammeEditionIDDataModel());
        assertEquals(cISPIDDM, result.getCourseInStudyPlanIDDataModel());
    }

}