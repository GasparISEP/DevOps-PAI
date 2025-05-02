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
import org.mockito.Mockito;

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
        when(springDataRepository.countById_CourseEditionIDAndActiveTrue(courseEditionIdDataModel)).thenReturn(5L);

        // act
        int result = repository.numberOfStudentsEnrolledInCourseEdition(courseEditionId);

        // assert
        assertEquals(5, result);
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

    @Test
    void testEnrolStudentInProgrammeCourseEditions_successfulEnrolment() throws Exception {

        // arrange
        StudentID studentID = mock(StudentID.class);
        CourseEditionID edition1 = mock(CourseEditionID.class);
        CourseEditionID edition2 = mock(CourseEditionID.class);
        List<CourseEditionID> courseEditions = List.of(edition1, edition2);

        ICourseEditionEnrolmentRepositorySpringData springRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper editionIDMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springRepo, mapper, idMapper, studentIDMapper, editionIDMapper
        );

        when(springRepo.findById_StudentIDAndId_CourseEditionID(any(), any()))
                .thenReturn(Optional.empty());
        when(mapper.toDomain(any())).thenReturn(Optional.of(mock(CourseEditionEnrolment.class)));
        when(mapper.toDataModel(any())).thenReturn(Optional.of(mock(CourseEditionEnrolmentDataModel.class)));
        when(springRepo.save(any())).thenReturn(mock(CourseEditionEnrolmentDataModel.class));

        // act & assert
        assertDoesNotThrow(() ->
                repository.enrolStudentInProgrammeCourseEditions(studentID, courseEditions)
        );
    }

    @Test
    void testEnrolStudentInProgrammeCourseEditions_enrolmentAlreadyExists_throwsException() throws Exception {

        // arrange
        StudentID studentID = mock(StudentID.class);
        CourseEditionID edition1 = mock(CourseEditionID.class);
        List<CourseEditionID> courseEditions = List.of(edition1);

        ICourseEditionEnrolmentRepositorySpringData springRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper editionIDMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springRepo, mapper, idMapper, studentIDMapper, editionIDMapper
        );

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        when(springRepo.findById_StudentIDAndId_CourseEditionID(any(), any()))
                .thenReturn(Optional.of(mock(CourseEditionEnrolmentDataModel.class)));
        when(mapper.toDomain(any())).thenReturn(Optional.of(enrolment));

        // act & assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                repository.enrolStudentInProgrammeCourseEditions(studentID, courseEditions)
        );

        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }

    @Test
    void testSave_throwsExceptionWhenEntityIsNull() {

        // arrange
        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.save(null);
        });

        assertEquals("Entity cannot be null!", exception.getMessage());
    }

    @Test
    void testSave_throwsExceptionWhenToDataModelReturnsEmpty() throws Exception {
        // Arrange
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);

        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        when(mapper.toDataModel(enrolment)).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mapper,
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.save(enrolment);
        });

        assertEquals("Entity cannot be empty!", exception.getMessage());
    }

    @Test
    void testSave_throwsExceptionWhenToDomainReturnsEmpty() throws Exception {

        // arrange
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentDataModel dataModel = mock(CourseEditionEnrolmentDataModel.class);

        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        when(mapper.toDataModel(enrolment)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomain(dataModel)).thenReturn(Optional.empty());

        ICourseEditionEnrolmentRepositorySpringData repoSpringData = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        when(repoSpringData.save(dataModel)).thenReturn(dataModel);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                repoSpringData,
                mapper,
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.save(enrolment);
        });

        assertEquals("Course Edition Enrolment cannot be empty!", exception.getMessage());
    }

    @Test
    void testFindAll_whenMapperThrowsException_thenThrowsRuntimeException() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData repoSpringData = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);

        CourseEditionEnrolmentDataModel faultyDataModel = mock(CourseEditionEnrolmentDataModel.class);
        when(repoSpringData.findAll()).thenReturn(List.of(faultyDataModel));
        when(mapper.toDomain(faultyDataModel)).thenThrow(new Exception("Mapping failed"));

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                repoSpringData,
                mapper,
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, repository::findAll);
        assertTrue(exception.getCause() instanceof Exception);
        assertEquals("Mapping failed", exception.getCause().getMessage());
    }

    @Test
    void testOfIdentity_whenToDataModelReturnsEmpty_thenReturnsEmptyOptional() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(any())).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        //act
        Optional<CourseEditionEnrolment> result = repository.ofIdentity(mock(CourseEditionEnrolmentID.class));

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testOfIdentity_whenToDataModelThrowsException_thenThrowsRuntimeException() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(any())).thenThrow(new RuntimeException("Mapper failed"));

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act + assert
        assertThrows(RuntimeException.class, () -> repository.ofIdentity(mock(CourseEditionEnrolmentID.class)));
    }

    @Test
    void testOfIdentity_whenToDomainThrowsException_thenThrowsRuntimeException() throws Exception {

        // assert
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentRepositorySpringData repoSpringData = mock(ICourseEditionEnrolmentRepositorySpringData.class);

        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        CourseEditionEnrolmentDataModel dataModel = mock(CourseEditionEnrolmentDataModel.class);

        when(idMapper.toDataModel(any())).thenReturn(Optional.of(idDataModel));
        when(repoSpringData.findById(idDataModel)).thenReturn(Optional.of(dataModel));
        when(mapper.toDomain(dataModel)).thenThrow(new Exception("Domain mapping error"));

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                repoSpringData,
                mapper,
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act + assert
        assertThrows(RuntimeException.class, () -> repository.ofIdentity(mock(CourseEditionEnrolmentID.class)));
    }

    @Test
    void testContainsOfIdentity_whenToDataModelThrows_thenThrowsRuntimeException() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(any())).thenThrow(new RuntimeException("Mapping failed"));

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act + assert
        assertThrows(RuntimeException.class, () -> repository.containsOfIdentity(mock(CourseEditionEnrolmentID.class)));
    }

    @Test
    void testContainsOfIdentity_whenToDataModelReturnsEmpty_thenReturnsFalse() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(any())).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act
        boolean result = repository.containsOfIdentity(mock(CourseEditionEnrolmentID.class));

        // assert
        assertFalse(result);
    }

    @Test
    void testContainsOfIdentity_whenValidID_thenReturnsExpectedValue() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);

        when(idMapper.toDataModel(any())).thenReturn(Optional.of(idDataModel));
        when(springDataRepo.existsById(idDataModel)).thenReturn(true);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo,
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act
        boolean result = repository.containsOfIdentity(mock(CourseEditionEnrolmentID.class));

        // assert
        assertTrue(result);
    }

    @Test
    void testContainsOfIdentity_whenDataModelIsPresent_thenRepositoryIsCalled() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);

        when(idMapper.toDataModel(any())).thenReturn(Optional.of(idDataModel));
        when(springDataRepo.existsById(idDataModel)).thenReturn(false);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo,
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act
        boolean result = repository.containsOfIdentity(mock(CourseEditionEnrolmentID.class));

        // assert
        assertFalse(result);
    }

    @Test
    void testConstructor_throwsException_whenCourseEditionIDMapperIsNull() {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData repo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = null;

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentRepositorySpringDataImpl(repo, mapper, idMapper, studentIDMapper, courseEditionIDMapper);});
    }

    @Test
    void testFindByStudentAndEdition_returnsEmpty_whenExceptionIsThrown() {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(springDataRepo, mapper, idMapper, studentIDMapper, courseEditionIDMapper);

        StudentID studentId = mock(StudentID.class);
        CourseEditionID courseEditionId = mock(CourseEditionID.class);

        when(studentIDMapper.domainToDataModel(studentId)).thenThrow(new RuntimeException("Simulated Error!"));

        // act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(studentId, courseEditionId);

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testIsStudentEnrolledInCourseEdition_returnsTrueWhenEnrolled() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(springDataRepo, mapper, idMapper, studentIDMapper, courseEditionIDMapper);

        StudentID studentId = mock(StudentID.class);
        CourseEditionID courseEditionId = mock(CourseEditionID.class);
        StudentIDDataModel studentDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseDataModel = mock(CourseEditionIDDataModel.class);

        when(studentIDMapper.domainToDataModel(studentId)).thenReturn(studentDataModel);
        when(courseEditionIDMapper.toDataModel(courseEditionId)).thenReturn(courseDataModel);
        when(springDataRepo.existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(studentDataModel, courseDataModel)).thenReturn(true);

        // act
        boolean result = repository.isStudentEnrolledInCourseEdition(studentId, courseEditionId);

        // assert
        assertTrue(result);
    }

    @Test
    void testFindAll_mapperReturnsEmpty_optionalTriggersWrappedException() throws Exception {
        // Arrange
        ICourseEditionEnrolmentRepositorySpringData repoSpringData = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentDataModel dummyDataModel = mock(CourseEditionEnrolmentDataModel.class);
        when(repoSpringData.findAll()).thenReturn(List.of(dummyDataModel));
        when(mapper.toDomain(dummyDataModel)).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(repoSpringData, mapper, idMapper, studentIDMapper, courseEditionIDMapper);

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, repository::findAll);
        assertTrue(thrown.getCause() instanceof IllegalArgumentException);
        assertEquals("Could not map Course Edition Enrolment.", thrown.getCause().getMessage());
    }

    @Test
    void shouldReturnFalseWhenStudentIsNotEnrolledInEdition() throws Exception {
        // Arrange
        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        IStudentIDMapper studentIDMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIDMapper = mock(ICourseEditionIDMapper.class);
        ICourseEditionEnrolmentRepositorySpringData repoSpringData = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper enrolmentMapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);

        when(studentIDMapper.domainToDataModel(mockStudentID)).thenReturn(studentIDDataModel);
        when(courseEditionIDMapper.toDataModel(mockCourseEditionID)).thenReturn(courseEditionIDDataModel);
        when(repoSpringData.existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(studentIDDataModel, courseEditionIDDataModel))
                .thenReturn(false);

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(
                        repoSpringData,
                        enrolmentMapper,
                        idMapper,
                        studentIDMapper,
                        courseEditionIDMapper
                );

        // Act
        boolean result = repository.isStudentEnrolledInCourseEdition(mockStudentID, mockCourseEditionID);

        // Assert
        assertFalse(result);
    }
}