package PAI.persistence.springdata;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.mapper.*;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentRepositorySpringDataImplTest {

    @Test
    void should_return_true_if_student_is_enrolled_in_course_edition() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepository,
                mapper,
                idMapper,
                studentIdMapper,
                courseEditionIdMapper
        );

        StudentID studentId = mock(StudentID.class);
        CourseEditionID courseEditionId = mock(CourseEditionID.class);

        StudentIDDataModel studentIdDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIdDataModel = mock(CourseEditionIDDataModel.class);

        when(studentIdMapper.domainToDataModel(studentId)).thenReturn(studentIdDataModel);
        when(courseEditionIdMapper.toDataModel(courseEditionId)).thenReturn(courseEditionIdDataModel);
        when(springDataRepository.existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(studentIdDataModel, courseEditionIdDataModel)).thenReturn(true);

        // act
        boolean result = repository.isStudentEnrolledInCourseEdition(studentId, courseEditionId);

        // assert
        assertTrue(result);
        verify(studentIdMapper).domainToDataModel(studentId);
        verify(courseEditionIdMapper).toDataModel(courseEditionId);
        verify(springDataRepository).existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(studentIdDataModel, courseEditionIdDataModel);
    }


    @Test
    void should_return_number_of_students_enrolled_in_course_edition() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepository,
                mapper,
                idMapper,
                studentIdMapper,
                courseEditionIdMapper
        );

        CourseEditionID courseEditionId = mock(CourseEditionID.class);
        CourseEditionIDDataModel courseEditionIdDataModel = mock(CourseEditionIDDataModel.class);

        when(courseEditionIdMapper.toDataModel(courseEditionId)).thenReturn(courseEditionIdDataModel);
        when(springDataRepository.countById_CourseEditionIDAndActiveIsTrue(courseEditionIdDataModel)).thenReturn(5L);

        // act
        int result = repository.numberOfStudentsEnrolledInCourseEdition(courseEditionId);

        // assert
        assertEquals(5, result);
        verify(springDataRepository).countById_CourseEditionIDAndActiveIsTrue(courseEditionIdDataModel);
    }


    @Test
    void should_save_courseEditionEnrolment_successfully() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepository,
                mapper,
                idMapper,
                studentIdMapper,
                courseEditionIdMapper
        );

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentDataModel enrolmentDataModel = mock(CourseEditionEnrolmentDataModel.class);

        when(mapper.toDataModel(enrolment)).thenReturn(Optional.of(enrolmentDataModel));
        when(springDataRepository.save(enrolmentDataModel)).thenReturn(enrolmentDataModel);
        when(mapper.toDomain(enrolmentDataModel)).thenReturn(Optional.of(enrolment));

        // act
        CourseEditionEnrolment savedEnrolment = repository.save(enrolment);

        // assert
        assertNotNull(savedEnrolment);
        assertEquals(enrolment, savedEnrolment);
        verify(mapper).toDataModel(enrolment);
        verify(springDataRepository).save(enrolmentDataModel);
        verify(mapper).toDomain(enrolmentDataModel);
    }

    @Test
    void should_return_all_courseEditionEnrolments_successfully() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepository,
                mapper,
                idMapper,
                studentIdMapper,
                courseEditionIdMapper
        );

        CourseEditionEnrolmentDataModel enrolmentDataModel1 = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolmentDataModel enrolmentDataModel2 = mock(CourseEditionEnrolmentDataModel.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);

        when(springDataRepository.findAll()).thenReturn(List.of(enrolmentDataModel1, enrolmentDataModel2));
        when(mapper.toDomain(enrolmentDataModel1)).thenReturn(Optional.of(enrolment1));
        when(mapper.toDomain(enrolmentDataModel2)).thenReturn(Optional.of(enrolment2));

        // act
        Iterable<CourseEditionEnrolment> result = repository.findAll();

        // assert
        assertNotNull(result);
        List<CourseEditionEnrolment> resultList = (List<CourseEditionEnrolment>) result;
        assertEquals(2, resultList.size());
        assertTrue(resultList.contains(enrolment1));
        assertTrue(resultList.contains(enrolment2));
        verify(springDataRepository).findAll();
        verify(mapper).toDomain(enrolmentDataModel1);
        verify(mapper).toDomain(enrolmentDataModel2);
    }

    @Test
    void should_return_CourseEditionEnrolment_given_valid_identity() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepository,
                mapper,
                idMapper,
                studentIdMapper,
                courseEditionIdMapper
        );

        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentIDDataModel enrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel enrolmentDataModel = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);

        when(idMapper.toDataModel(enrolmentID)).thenReturn(Optional.of(enrolmentIDDataModel));
        when(springDataRepository.findById(enrolmentIDDataModel)).thenReturn(Optional.of(enrolmentDataModel));
        when(mapper.toDomain(enrolmentDataModel)).thenReturn(Optional.of(enrolment));

        // act
        Optional<CourseEditionEnrolment> result = repository.ofIdentity(enrolmentID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(enrolment, result.get());

        verify(idMapper).toDataModel(enrolmentID);
        verify(springDataRepository).findById(enrolmentIDDataModel);
        verify(mapper).toDomain(enrolmentDataModel);
    }

    @Test
    void should_return_true_if_courseEditionEnrolment_exists() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepository,
                mapper,
                idMapper,
                studentIdMapper,
                courseEditionIdMapper
        );

        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentIDDataModel enrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);

        when(idMapper.toDataModel(enrolmentID)).thenReturn(Optional.of(enrolmentIDDataModel));
        when(springDataRepository.existsById(enrolmentIDDataModel)).thenReturn(true);

        // act
        boolean result = repository.containsOfIdentity(enrolmentID);

        // assert
        assertTrue(result);
        verify(idMapper).toDataModel(enrolmentID);
        verify(springDataRepository).existsById(enrolmentIDDataModel);
    }

    @Test
    void should_save_and_return_CourseEditionEnrolment() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepository,
                mapper,
                idMapper,
                studentIdMapper,
                courseEditionIdMapper
        );

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentDataModel dataModel = mock(CourseEditionEnrolmentDataModel.class);

        when(mapper.toDataModel(enrolment)).thenReturn(Optional.of(dataModel));
        when(springDataRepository.save(dataModel)).thenReturn(dataModel);
        when(mapper.toDomain(dataModel)).thenReturn(Optional.of(enrolment));

        // act
        CourseEditionEnrolment result = repository.save(enrolment);

        // assert
        assertNotNull(result);
        assertEquals(enrolment, result);
        verify(mapper).toDataModel(enrolment);
        verify(springDataRepository).save(dataModel);
        verify(mapper).toDomain(dataModel);
    }

    @Test
    void should_return_courseEditionEnrolment_when_student_and_courseEdition_exist() throws Exception {

        // arrange
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentDataModel ceeDataModel = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolment cee = mock(CourseEditionEnrolment.class);

        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo, mapper, idMapper, studentIDMapper, courseEditionIDMapper
        );

        when(studentIDMapper.domainToDataModel(studentID)).thenReturn(studentIDDataModel);
        when(courseEditionIDMapper.toDataModel(courseEditionID)).thenReturn(courseEditionIDDataModel);
        when(springDataRepo.findById_StudentIDAndId_CourseEditionID(studentIDDataModel, courseEditionIDDataModel))
                .thenReturn(Optional.of(ceeDataModel));
        when(mapper.toDomain(ceeDataModel)).thenReturn(Optional.of(cee));

        // act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(studentID, courseEditionID);

        // assert
        assertTrue(result.isPresent());
        assertEquals(cee, result.get());
    }

    @Test
    void testGetInternalSet_returnsMappedSet() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepoMock = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapperMock = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapperMock = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapperMock = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapperMock = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentDataModel dataModel1 = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolmentDataModel dataModel2 = mock(CourseEditionEnrolmentDataModel.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);

        List<CourseEditionEnrolmentDataModel> dataModels = List.of(dataModel1, dataModel2);
        when(springDataRepoMock.findAll()).thenReturn(dataModels);

        when(mapperMock.toDomain(dataModel1)).thenReturn(Optional.of(enrolment1));
        when(mapperMock.toDomain(dataModel2)).thenReturn(Optional.of(enrolment2));

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(
                        springDataRepoMock,
                        mapperMock,
                        idMapperMock,
                        studentIDMapperMock,
                        courseEditionIDMapperMock
                );

        // Act
        Set<CourseEditionEnrolment> result = repository.getInternalSet();

        // Assert
        assertEquals(2, result.size(), "Expected two enrolments in the internal set");
        assertTrue(result.contains(enrolment1), "Result should contain enrolment1");
        assertTrue(result.contains(enrolment2), "Result should contain enrolment2");
    }

    @Test
    void testGetInternalSet_mapperReturnsEmpty_doesNotIncludeInSet() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepoMock = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapperMock = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapperMock = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapperMock = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapperMock = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentDataModel dataModel1 = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolmentDataModel dataModel2 = mock(CourseEditionEnrolmentDataModel.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);

        List<CourseEditionEnrolmentDataModel> dataModels = List.of(dataModel1, dataModel2);
        when(springDataRepoMock.findAll()).thenReturn(dataModels);
        when(mapperMock.toDomain(dataModel1)).thenReturn(Optional.of(enrolment1));
        when(mapperMock.toDomain(dataModel2)).thenReturn(Optional.empty());  // este será ignorado

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(
                        springDataRepoMock,
                        mapperMock,
                        idMapperMock,
                        studentIDMapperMock,
                        courseEditionIDMapperMock
                );

        // Act
        Set<CourseEditionEnrolment> result = repository.getInternalSet();

        // Assert
        assertEquals(1, result.size(), "Expected only one valid enrolment in the set");
        assertTrue(result.contains(enrolment1));
    }

    @Test
    void testGetInternalSet_handlesExceptionGracefully() {
        // Arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepoMock = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapperMock = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapperMock = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapperMock = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapperMock = mock(ICourseEditionIDMapper.class);

        when(springDataRepoMock.findAll()).thenThrow(new RuntimeException("Database error"));

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(
                        springDataRepoMock,
                        mapperMock,
                        idMapperMock,
                        studentIDMapperMock,
                        courseEditionIDMapperMock
                );

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, repository::getInternalSet);
        assertTrue(thrown.getMessage().contains("Error retrieving the set"), "Should throw with appropriate message");
    }

}