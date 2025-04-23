package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.mapper.ITeacherIDMapper;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.TeacherIDDataModel;
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

    // Tests successful creation of CourseEditionMapperImpl
    @Test
    void shouldCreateACourseEditionMapperImpl () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);

        // Act
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);

        // Assert
        assertNotNull(iCourseEditionMapper);
    }

    // Tests if exception is thrown when courseEditionIDMapper is null
    @Test
    void shouldThrowExceptionIfCourseEditionIDMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = null;
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);});

        // Assert
        assertEquals("courseEditionIDMapper cannot be null", exception.getMessage());
    }

    // Tests if exception is thrown when programmeEditionIdMapper is null
    @Test
    void shouldThrowExceptionIfProgrammeEditionIDMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = null;
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);});

        // Assert
        assertEquals("programmeEditionIdMapper cannot be null", exception.getMessage());
    }

    // Tests if exception is thrown when courseInStudyPlanIDMapper is null
    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIDMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = null;
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);});

        // Assert
        assertEquals("courseInStudyPlanIDMapper cannot be null", exception.getMessage());
    }

    // Tests if exception is thrown when courseEditionFactory is null
    @Test
    void shouldThrowExceptionIfCourseEditionFactoryGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);});

        // Assert
        assertEquals("teacherIDMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfTeacherIdMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = null;
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);});

        // Assert
        assertEquals("courseEditionFactory cannot be null", exception.getMessage());
    }

    //--------toDomain Tests--------

    // Tests if CourseEdition is returned when method toDomain is called with valid arguments
    @Test
    void shouldReturnCourseEditionWhenToDomainMethodIsCalledWithValidArguments() throws Exception{
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        TeacherID teacherID = mock(TeacherID.class);

        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);

        when(iCourseEditionIDMapper.toDomain(courseEditionDataModel.getCourseEditionIDDataModel())).thenReturn(courseEditionID);
        when(iProgrammeEditionIdMapper.toDomain(courseEditionDataModel.getProgrammeEditionIDDataModel())).thenReturn(programmeEditionID);
        when(iCourseInStudyPlanIDMapper.toDomain(courseEditionDataModel.getCourseInStudyPlanIDDataModel())).thenReturn(courseInStudyPlanID);
        when(teacherIDMapper.toDomain(courseEditionDataModel.getTeacherIDDataModel())).thenReturn(teacherID);

        CourseEdition expectedResult = mock(CourseEdition.class);
        when(courseEditionFactory.newCourseEdition_2(courseEditionID, courseInStudyPlanID, programmeEditionID)).thenReturn(expectedResult);

        // Act
        CourseEdition courseEdition = iCourseEditionMapper.toDomain(courseEditionDataModel);

        // Assert
        assertNotNull(courseEdition);
        assertEquals(expectedResult, courseEdition);
    }

    // Tests if exception is thrown when method toDomain is called with courseEditionDataModel as null
    @Test
    void shouldThrowExceptionIfCourseEditionDataModelGivenAsArgumentInToDomainMethodIsNull() throws Exception{
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);

        CourseEditionDataModel courseEditionDataModel = null;
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {iCourseEditionMapper.toDomain(courseEditionDataModel);});

        // Assert
        assertEquals("courseEditionDataModel cannot be null", exception.getMessage());
    }

    //--------toDataModel Tests--------
    // Tests if CourseEdition is returned when method toDataModel is called with valid arguments
    @Test
    void shouldReturnACourseEditionDataModelWhenToDataModelMethodIsCalledWithValidArguments() throws Exception {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapperapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapperapper, courseEditionFactory, teacherIDMapper);

        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);

        CourseEdition courseEdition = mock(CourseEdition.class);

        when(iCourseEditionIDMapper.toDataModel(courseEdition.identity())).thenReturn(courseEditionIDDataModel);
        when(iProgrammeEditionIdMapper.toDataModel(courseEdition.getProgrammeEditionID())).thenReturn(programmeEditionIdDataModel);
        when(iCourseInStudyPlanIDMapperapper.toDataModel(courseEdition.getCourseInStudyPlanID())).thenReturn(courseInStudyPlanIDDataModel);
        when(teacherIDMapper.toDataModel(courseEdition.getRuc())).thenReturn(teacherIDDataModel);

        // Act
        CourseEditionDataModel result = iCourseEditionMapper.toDataModel(courseEdition);

        // Assert
        assertNotNull(courseEdition);
        assertEquals(courseEditionIDDataModel, result.getCourseEditionIDDataModel());
        assertEquals(programmeEditionIdDataModel, result.getProgrammeEditionIDDataModel());
        assertEquals(courseInStudyPlanIDDataModel, result.getCourseInStudyPlanIDDataModel());
        assertEquals(teacherIDDataModel, result.getTeacherIDDataModel());
    }

    // Tests if exception is thrown when method toDataModel is called with courseEdition as null
    @Test
    void shouldThrowExceptionIfCourseEditionGivenAsArgumentInToDataModelMethodIsNull() throws Exception {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, teacherIDMapper);

        CourseEdition courseEdition = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {iCourseEditionMapper.toDataModel(courseEdition);});

        // Assert
        assertEquals("courseEdition cannot be null", exception.getMessage());
    }
}