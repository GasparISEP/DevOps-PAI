package PAI.mapper.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEdition.ICourseEditionFactory;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.mapper.teacher.ITeacherIDMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
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
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);

        // Act
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);

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
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);});

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
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);});

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
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);});

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
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);});

        // Assert
        assertEquals("teacherIDMapper cannot be null", exception.getMessage());
    }

    // Tests if exception is thrown when ICourseEditionGeneratedIDMapper is null
    @Test
    void shouldThrowExceptionIfICourseEditionGeneratedIDMapperIsNull () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);});

        // Assert
        assertEquals("courseEditionGeneratedIDMapper cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfTeacherIdMapperGivenInTheConstructorIsNull () {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = null;
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);});

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
        ICourseEditionGeneratedIDMapper iCourseEditionGeneratedIDMapper= mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory, iCourseEditionGeneratedIDMapper, teacherIDMapper);

        CourseEditionGeneratedID courseEditionGeneratedID=mock(CourseEditionGeneratedID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        TeacherID teacherID = mock(TeacherID.class);

        CourseEditionIDDataModel ceIdDM = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel peIdDM = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel cispIdDM = mock(CourseInStudyPlanIDDataModel.class);
        TeacherIDDataModel tIdDM = mock(TeacherIDDataModel.class);

        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);

        when(courseEditionDataModel.getCourseEditionIDDataModel()).thenReturn(ceIdDM);
        when(courseEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(peIdDM);
        when(courseEditionDataModel.getCourseInStudyPlanIDDataModel()).thenReturn(cispIdDM);
        when(courseEditionDataModel.getTeacherIDDataModel()).thenReturn(tIdDM);

        when(iCourseEditionIDMapper.toDomain(courseEditionDataModel.getCourseEditionIDDataModel())).thenReturn(courseEditionID);
        when(iProgrammeEditionIdMapper.toDomain(courseEditionDataModel.getProgrammeEditionIDDataModel())).thenReturn(programmeEditionID);
        when(iCourseInStudyPlanIDMapper.toDomain(courseEditionDataModel.getCourseInStudyPlanIDDataModel())).thenReturn(courseInStudyPlanID);
        when(teacherIDMapper.toDomain(courseEditionDataModel.getTeacherIDDataModel())).thenReturn(teacherID);
        when(iCourseEditionGeneratedIDMapper.toDomain(courseEditionDataModel.getCourseEditionGeneratedIDDataModel())).thenReturn(courseEditionGeneratedID);

        CourseEdition expectedResult = mock(CourseEdition.class);
        when(courseEditionFactory.createCourseEditionFromDataModel(courseEditionID, courseInStudyPlanID, programmeEditionID, courseEditionGeneratedID, teacherID)).thenReturn(expectedResult);

        // Act
        CourseEdition courseEdition = iCourseEditionMapper.toDomain(courseEditionDataModel);

        // Assert
        assertNotNull(courseEdition);
        assertEquals(expectedResult, courseEdition);
    }

    // Tests if exception is thrown when method toDomain is called with courseEditionDataModel as null
    @Test
    void shouldThrowExceptionIfCourseEditionDataModelGivenAsArgumentInToDomainMethodIsNull(){
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);

        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);

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
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);


        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);

        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(courseEdition.getRuc()).thenReturn(teacherID);
        when(teacherIDMapper.toDataModel(teacherID)).thenReturn(teacherIDDataModel);

        when(iCourseEditionIDMapper.toDataModel(courseEdition.identity())).thenReturn(courseEditionIDDataModel);
        when(courseEditionIDDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIdDataModel);
        when(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel()).thenReturn(courseInStudyPlanIDDataModel);
        when(iProgrammeEditionIdMapper.toDataModel(courseEdition.getProgrammeEditionID())).thenReturn(programmeEditionIdDataModel);
        when(iCourseInStudyPlanIDMapper.toDataModel(courseEdition.getCourseInStudyPlanID())).thenReturn(courseInStudyPlanIDDataModel);
        when(teacherIDMapper.toDataModel(courseEdition.getRuc())).thenReturn(teacherIDDataModel);
        when(courseEditionGeneratedIDMapper.toDataModel(courseEdition.getCourseEditionGeneratedID())).thenReturn(courseEditionGeneratedIDDataModel);

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
    void shouldThrowExceptionIfCourseEditionGivenAsArgumentInToDataModelMethodIsNull() {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper = mock(ICourseEditionGeneratedIDMapper.class);
        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(iCourseEditionIDMapper, iProgrammeEditionIdMapper, iCourseInStudyPlanIDMapper, courseEditionFactory,courseEditionGeneratedIDMapper, teacherIDMapper);

CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel= mock(CourseEditionGeneratedIDDataModel.class);
        CourseEdition courseEdition = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {iCourseEditionMapper.toDataModel(courseEdition);});

        // Assert
        assertEquals("courseEdition cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnCourseEditionDataModelWithoutTeacherWhenRucIsNull() throws Exception {
        // Arrange
        ICourseEditionIDMapper iCourseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        IProgrammeEditionIdMapper iProgrammeEditionIdMapper = mock(IProgrammeEditionIdMapper.class);
        ICourseInStudyPlanIDMapper iCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
        ICourseEditionFactory courseEditionFactory = mock(ICourseEditionFactory.class);
        ITeacherIDMapper teacherIDMapper = mock(ITeacherIDMapper.class);
        ICourseEditionGeneratedIDMapper courseEditionGeneratedIDMapper=mock(ICourseEditionGeneratedIDMapper.class);

        ICourseEditionMapper iCourseEditionMapper = new CourseEditionMapperImpl(
                iCourseEditionIDMapper,
                iProgrammeEditionIdMapper,
                iCourseInStudyPlanIDMapper,
                courseEditionFactory,
                courseEditionGeneratedIDMapper,
                teacherIDMapper
        );
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEdition courseEdition = mock(CourseEdition.class);

        when(courseEdition.getRuc()).thenReturn(null);
        when(iCourseEditionIDMapper.toDataModel(courseEdition.identity())).thenReturn(courseEditionIDDataModel);
        when(courseEditionGeneratedIDMapper.toDataModel(courseEdition.getCourseEditionGeneratedID())).thenReturn(courseEditionGeneratedIDDataModel);

        // Act
        CourseEditionDataModel result = iCourseEditionMapper.toDataModel(courseEdition);
        // Assert
        assertNotNull(result);
        assertEquals(courseEditionIDDataModel, result.getCourseEditionIDDataModel());
        assertNull(result.getTeacherIDDataModel());
    }
}