package PAI.persistence.springdata;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolmentListFactoryImpl;
import PAI.mapper.*;
import PAI.mapper.courseEdition.ICourseEditionIDMapper;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.mem.CourseEditionEnrolmentRepositoryImpl;
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
        // Arrange
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

        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        when(studentIdMapper.domainToDataModel(studentId)).thenReturn(studentIDDataModel);
        when(courseEditionIdMapper.toDataModel(courseEditionId)).thenReturn(courseEditionIDDataModel);
        when(springDataRepository.existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(studentIDDataModel, courseEditionIDDataModel)).thenReturn(true);

        // Act
        boolean result = repository.isStudentEnrolledInCourseEdition(studentId, courseEditionId);

        // Assert
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
        when(springDataRepository.countById_CourseEditionIDAndActiveIsTrue(courseEditionIdDataModel)).thenReturn(5L);

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
    void save_shouldThrowException_whenEntityIsNull() {
        // arrange
        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> repo.save(null));
    }

    @Test
    void save_shouldThrowException_whenMapperReturnsEmptyOptional() throws Exception {
        // arrange
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        when(mapper.toDataModel(any())).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mapper,
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        CourseEditionEnrolment dummyEntity = mock(CourseEditionEnrolment.class);

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> repo.save(dummyEntity));
    }

    @Test
    void save_shouldThrowException_whenMapperToDomainReturnsEmpty() throws Exception {
        // arrange
        CourseEditionEnrolmentDataModel savedDataModel = mock(CourseEditionEnrolmentDataModel.class);
        CourseEditionEnrolment entity = mock(CourseEditionEnrolment.class);

        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        when(mapper.toDataModel(entity)).thenReturn(Optional.of(savedDataModel));
        when(mapper.toDomain(savedDataModel)).thenReturn(Optional.empty());

        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        when(springDataRepo.save(savedDataModel)).thenReturn(savedDataModel);

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo,
                mapper,
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> repo.save(entity));
    }

    @Test
    void findAll_shouldThrowRuntimeException_whenMapperFails() throws Exception {

        // arrange
        CourseEditionEnrolmentDataModel badDataModel = mock(CourseEditionEnrolmentDataModel.class);

        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        when(springDataRepo.findAll()).thenReturn(List.of(badDataModel));

        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);

        when(mapper.toDomain(badDataModel)).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo,
                mapper,
                mock(ICourseEditionEnrolmentIDMapper.class),
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        // act & assert
        RuntimeException thrown = assertThrows(RuntimeException.class, repo::findAll);
        assertTrue(thrown.getCause() instanceof IllegalArgumentException);
        assertEquals("Could not map Course Edition Enrolment.", thrown.getCause().getMessage());
    }

    @Test
    void ofIdentity_shouldThrowRuntimeException_whenIdMapperThrowsException() throws Exception {
        CourseEditionEnrolmentID domainId = mock(CourseEditionEnrolmentID.class);

        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(domainId)).thenThrow(new Exception("Mapping error"));

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> repo.ofIdentity(domainId));
        assertEquals("Mapping error", thrown.getCause().getMessage());
    }

    @Test
    void ofIdentity_shouldReturnEmpty_whenIdMapperReturnsEmptyOptional() throws Exception {
        CourseEditionEnrolmentID domainId = mock(CourseEditionEnrolmentID.class);

        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(domainId)).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        Optional<CourseEditionEnrolment> result = repo.ofIdentity(domainId);
        assertTrue(result.isEmpty());
    }

    @Test
    void ofIdentity_shouldThrowRuntimeException_whenMapperThrowsException() throws Exception {
        CourseEditionEnrolmentID domainId = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        CourseEditionEnrolmentDataModel dataModel = mock(CourseEditionEnrolmentDataModel.class);

        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(domainId)).thenReturn(Optional.of(idDataModel));

        ICourseEditionEnrolmentRepositorySpringData springDataRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        when(springDataRepo.findById(idDataModel)).thenReturn(Optional.of(dataModel));

        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        when(mapper.toDomain(dataModel)).thenThrow(new Exception("Conversion failed"));

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springDataRepo,
                mapper,
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> repo.ofIdentity(domainId));
        assertEquals("Conversion failed", thrown.getCause().getMessage());
    }

    @Test
    void containsOfIdentity_shouldThrowRuntimeException_whenIdMapperFails() throws Exception {
        CourseEditionEnrolmentID id = mock(CourseEditionEnrolmentID.class);

        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(id)).thenThrow(new Exception("ID mapping failure"));

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> repo.containsOfIdentity(id));
        assertEquals("ID mapping failure", thrown.getCause().getMessage());
    }

    @Test
    void containsOfIdentity_shouldReturnFalse_whenIdMapperReturnsEmpty() throws Exception {
        CourseEditionEnrolmentID id = mock(CourseEditionEnrolmentID.class);

        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(id)).thenReturn(Optional.empty());

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                mock(ICourseEditionEnrolmentRepositorySpringData.class),
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        boolean result = repo.containsOfIdentity(id);
        assertFalse(result);
    }

    @Test
    void containsOfIdentity_shouldReturnTrue_whenEntityExists() throws Exception {
        CourseEditionEnrolmentID id = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);

        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        when(idMapper.toDataModel(id)).thenReturn(Optional.of(idDataModel));

        ICourseEditionEnrolmentRepositorySpringData springRepo = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        when(springRepo.existsById(idDataModel)).thenReturn(true);

        CourseEditionEnrolmentRepositorySpringDataImpl repo = new CourseEditionEnrolmentRepositorySpringDataImpl(
                springRepo,
                mock(ICourseEditionEnrolmentMapper.class),
                idMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        boolean result = repo.containsOfIdentity(id);
        assertTrue(result);
    }

//    @Test
//    void should_enrol_student_in_multiple_course_editions_when_none_are_enrolled() throws Exception {
//        // Arrange
//        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
//        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
//        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
//        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
//        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);
//
//        CourseEditionEnrolmentRepositorySpringDataImpl repository =
//                new CourseEditionEnrolmentRepositorySpringDataImpl(
//                        springDataRepository, mapper, idMapper, studentIdMapper, courseEditionIdMapper
//                );
//
//        StudentID studentId = mock(StudentID.class);
//        CourseEditionID courseEditionId1 = mock(CourseEditionID.class);
//        CourseEditionID courseEditionId2 = mock(CourseEditionID.class);
//        List<CourseEditionID> editions = List.of(courseEditionId1, courseEditionId2);
//
//        CourseEditionEnrolmentRepositorySpringDataImpl spyRepository = Mockito.spy(repository);
//        doReturn(Optional.empty()).when(spyRepository).findByStudentAndEdition(eq(studentId), any());
//
//        doNothing().when(spyRepository).save(any());
//
//        // Act
//        spyRepository.enrolStudentInProgrammeCourseEditions(studentId, editions);
//
//        // Assert
//        verify(spyRepository, times(2)).save(any());
//    }

    @Test
    void should_throw_exception_when_student_already_enrolled_in_course_edition() throws Exception {

        // arrange
        ICourseEditionEnrolmentRepositorySpringData springDataRepository = mock(ICourseEditionEnrolmentRepositorySpringData.class);
        ICourseEditionEnrolmentMapper mapper = mock(ICourseEditionEnrolmentMapper.class);
        ICourseEditionEnrolmentIDMapper idMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        IStudentIDMapper studentIdMapper = mock(IStudentIDMapper.class);
        ICourseEditionIDMapper courseEditionIdMapper = mock(ICourseEditionIDMapper.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository =
                new CourseEditionEnrolmentRepositorySpringDataImpl(
                        springDataRepository, mapper, idMapper, studentIdMapper, courseEditionIdMapper
                );

        StudentID studentId = mock(StudentID.class);
        CourseEditionID enrolledEdition = mock(CourseEditionID.class);
        List<CourseEditionID> editions = List.of(enrolledEdition);

        CourseEditionEnrolmentRepositorySpringDataImpl spyRepository = Mockito.spy(repository);
        CourseEditionEnrolment mockEnrolment = mock(CourseEditionEnrolment.class);

        doReturn(Optional.of(mockEnrolment)).when(spyRepository).findByStudentAndEdition(studentId, enrolledEdition);

        // Act & Assert
        assertThrows(IllegalStateException.class, () ->
                        spyRepository.enrolStudentInProgrammeCourseEditions(studentId, editions),
                "This course edition enrolment is already in the list."
        );
    }

    @Test
    void should_throw_exception_when_student_is_already_enrolled_in_course_edition() throws Exception {

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
        CourseEditionID edition1 = mock(CourseEditionID.class);
        List<CourseEditionID> editions = List.of(edition1);

        CourseEditionEnrolment existingEnrolment = mock(CourseEditionEnrolment.class);
        when(repository.findByStudentAndEdition(studentId, edition1)).thenReturn(Optional.of(existingEnrolment));

        // act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                repository.enrolStudentInProgrammeCourseEditions(studentId, editions)
        );

        assertEquals("Entity cannot be empty!", exception.getMessage());
    }

    @Test
    void should_return_empty_when_exception_is_thrown() throws Exception {

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

        when(studentIdMapper.domainToDataModel(studentId)).thenThrow(new RuntimeException("Simulated error"));

        // act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(studentId, courseEditionId);

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_empty_set() throws Exception {

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

        // act
        Set<CourseEditionEnrolment> result = repository.getInternalSet();

        // assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void should_return_true_when_id_exists() throws Exception {
        // arrange
        ICourseEditionEnrolmentIDMapper iCEEIDMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentRepositorySpringData iCEERepoSpringData = mock(ICourseEditionEnrolmentRepositorySpringData.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(
                iCEERepoSpringData,
                mock(ICourseEditionEnrolmentMapper.class),
                iCEEIDMapper,
                mock(IStudentIDMapper.class),
                mock(ICourseEditionIDMapper.class)
        );

        CourseEditionEnrolmentID id = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);

        when(iCEEIDMapper.toDataModel(id)).thenReturn(Optional.of(idDataModel));
        when(iCEERepoSpringData.existsById(idDataModel)).thenReturn(true);

        // act
        boolean result = repository.containsOfIdentity(id);

        // assert
        assertTrue(result);
    }


    @Test
    void should_return_false_when_id_does_not_exist() throws Exception {

        // arrange
        ICourseEditionEnrolmentIDMapper iCEEIDMapper = mock(ICourseEditionEnrolmentIDMapper.class);
        ICourseEditionEnrolmentRepositorySpringData iCEERepoSpringData = mock(ICourseEditionEnrolmentRepositorySpringData.class);

        CourseEditionEnrolmentRepositorySpringDataImpl repository = new CourseEditionEnrolmentRepositorySpringDataImpl(iCEERepoSpringData, mock(ICourseEditionEnrolmentMapper.class), mock(ICourseEditionEnrolmentIDMapper.class), mock(IStudentIDMapper.class), mock(ICourseEditionIDMapper.class));
        CourseEditionEnrolmentID id = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentIDDataModel idDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        when(iCEEIDMapper.toDataModel(id)).thenReturn(Optional.of(idDataModel));
        when(iCEERepoSpringData.existsById(idDataModel)).thenReturn(false);

        // act
        boolean result = repository.containsOfIdentity(id);

        // assert
        assertFalse(result);
    }
}