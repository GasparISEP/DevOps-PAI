package PAI.persistence.mem;

import PAI.VOs.*;
import PAI.VOs.Date;
import PAI.domain.courseEditionEnrolment.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentRepositoryImplTest {


    //test save Method

    @Test
    void should_throw_exception_if_identity_is_null() throws IllegalArgumentException {

        //arrange
        ICourseEditionEnrolmentListFactory doubleCEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCEELF);

        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            repository.save(null);
        });
        assertEquals(exception.getMessage(),"Entity cannot be null");
    }

    @Test
    void shouldReturnTrueWithAValidCourseEditionEnrollment() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);


        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);
        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);

        //act
        CourseEditionEnrolment result = repository.save(doubleCee1);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldAllowEnrollmentOfDifferentStudentsInDifferentCourseEditions() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);

        StudentID doubleStID2 = mock(StudentID.class);
        CourseEditionID doubleCeID2 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID2, doubleCeID2)).thenReturn(doubleCee2);

        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);
        when(doubleEnrolmentset.add(doubleCee2)).thenReturn(true);

        repository.save(doubleCee1);

        //act
        CourseEditionEnrolment result = repository.save(doubleCee2);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldAllowEnrollmentOfDifferentStudentsInSameCourseEdition() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);

        StudentID doubleStID2 = mock(StudentID.class);
        CourseEditionEnrolment doubleCee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID2, doubleCeID1)).thenReturn(doubleCee2);

        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);
        when(doubleEnrolmentset.add(doubleCee2)).thenReturn(true);

        repository.save(doubleCee1);

        //act
        CourseEditionEnrolment result = repository.save(doubleCee2);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldAllowEnrollmentOfSameStudentInDifferentCourseEditions() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeListFactory);
        Set<CourseEditionEnrolment> doubleEnrolmentset = mock(Set.class);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee1);

        CourseEditionID doubleCeID2 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID2)).thenReturn(doubleCee2);

        when(doubleEnrolmentset.add(doubleCee1)).thenReturn(true);
        when(doubleEnrolmentset.add(doubleCee2)).thenReturn(true);

        repository.save(doubleCee1);

        //act
        CourseEditionEnrolment result = repository.save(doubleCee2);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrollmentAlreadyExists() {
        // arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory doubleCeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        Set<CourseEditionEnrolment> enrolmentSet = mock(Set.class);

        when(doubleCeeListFactory.getCourseEditionEnrolmentList()).thenReturn(enrolmentSet);

        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleCeeListFactory);

        StudentID doubleStID1 = mock(StudentID.class);
        CourseEditionID doubleCeID1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleCee = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStID1, doubleCeID1)).thenReturn(doubleCee);
        when(enrolmentSet.add(doubleCee)).thenReturn(false);

        // act & assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            repository.save(doubleCee);
        });

        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }

    //test isStudentEnrolledInCourseEdition method

    @Test
    void shouldConfirmStudentIsEnrollInACourseEdition() throws Exception {
        // arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory ceeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);

        Set<CourseEditionEnrolment> mockSet = new HashSet<>();
        when(ceeListFactory.getCourseEditionEnrolmentList()).thenReturn(mockSet);

        CourseEditionEnrolmentRepositoryImpl repository =
                new CourseEditionEnrolmentRepositoryImpl(ceeListFactory);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID ce1 = mock(CourseEditionID.class);

        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);
        when(doubleCeeFactory.createCourseEditionEnrolment(studentID, ce1))
                .thenReturn(cee1);
        when(cee1.knowStudent()).thenReturn(studentID);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrolmentActive()).thenReturn(true);

        // act
        repository.save(cee1);

        // assert
        assertTrue(repository.isStudentEnrolledInCourseEdition(studentID, ce1));
    }


    @Test
    void shouldConfirmStudentIsNotEnrollInACourseEdition() throws Exception {
        // arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);

        Set<CourseEditionEnrolment> mockSet = new HashSet<>();
        when(CeeListFactory.getCourseEditionEnrolmentList()).thenReturn(mockSet);


        CourseEditionEnrolmentRepositoryImpl repository =
                new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID studentID = mock(StudentID.class);
        CourseEditionID ce1 = mock(CourseEditionID.class);

        StudentID student2 = mock(StudentID.class);


        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);


        when(doubleCeeFactory.createCourseEditionEnrolment(studentID, ce1)).thenReturn(cee1);


        when(cee1.knowStudent()).thenReturn(studentID);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrolmentActive()).thenReturn(true);


        repository.save(cee1);

        // act

        boolean result = repository.isStudentEnrolledInCourseEdition(student2, ce1);

        // assert

        assertFalse(result);
    }

    @Test
    void shouldReturnCourseEditionFromEnrollment() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEEnrollments = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, doubleCe1)).thenReturn(courseEEnrollments);

        when(courseEEnrollments.hasStudent(doubleSt1)).thenReturn(true);

        when(courseEEnrollments.hasCourseEdition(doubleCe1)).thenReturn(true);

        repository.save(courseEEnrollments);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertTrue(result.isPresent(), "The student should be enrolled in the course edition.");

    }


    @Test
    void shouldThrowOptionalEmptyWhenStudentIsNull() {
        // Arrange
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(null, doubleCe1);

        //Assert
        assertTrue(result.isEmpty(), "Expected Optional.empty() when student is null");

    }

    @Test
    void shouldThrowOptionalEmptyWhenCourseEditionIsNull() {
        // Arrange
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, null);

        //Assert
        assertTrue(result.isEmpty(), "Expected Optional.empty() when courseEdition is null");

    }

    @Test
    void shouldReturnOptionalEmptyWhenNoEnrollmentFound() {
        // Arrange
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "The result should be empty if the student is not enrolled in the course edition.");
    }


    @Test
    void shouldReturnEmptyWhenStudentIsNotEnrolledInCourseEdition() {
        // Arrange
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID doubleSt1 = mock(StudentID.class);
        CourseEditionID doubleCe1 = mock(CourseEditionID.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "Expected no enrollment to be found");
    }


    @Test
    void shouldReturnNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repo = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        StudentID doubleStudent1 = mock(StudentID.class);
        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent1, doubleCourseEdition1)).thenReturn(cee1);

        when(cee1.knowCourseEdition()).thenReturn(doubleCourseEdition1);

        repo.save(cee1);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void ShouldReturnZeroWhenThereAreNoEnrolmentsInACourse() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repo = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock(CourseEditionID.class);

        StudentID doubleStudent1 = mock(StudentID.class);
        StudentID doubleStudent2 = mock(StudentID.class);

        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment cee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent1, doubleCourseEdition2)).thenReturn(cee1);
        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent2, doubleCourseEdition2)).thenReturn(cee2);

        when(cee1.knowCourseEdition()).thenReturn(doubleCourseEdition2);
        when(cee2.knowCourseEdition()).thenReturn(doubleCourseEdition2);

        repo.save(cee1);
        repo.save(cee2);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoStudents() throws Exception {
        // Arrange
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repo = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        // Create a course edition
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }


    @Test
    void shouldEnrollStudentWhenStudentNotEnrolled() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID doubleStudent = mock(StudentID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionEnrolment doubleEnrollment = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment);
        when(doubleEnrollment.hasStudent(doubleStudent)).thenReturn(true);
        when(doubleEnrollment.hasCourseEdition(doubleCourseEdition1)).thenReturn(true);

        // Act
        repository.save(doubleEnrollment);

        // Assert
        assertTrue(repository.findByStudentAndEdition(doubleStudent, doubleCourseEdition1).isPresent());
    }

    @Test
    void shouldThrowExceptionWhenStudentAlreadyEnrolled() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID doubleStudent = mock(StudentID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock(CourseEditionID.class);
        List<CourseEditionID> courseEditions = List.of(doubleCourseEdition1, doubleCourseEdition2);

        CourseEditionEnrolment doubleEnrollment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment doubleEnrollment2 = mock(CourseEditionEnrolment.class);

        when(doubleFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment1);

        when(doubleFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition2)).thenReturn(doubleEnrollment2);

        when(doubleEnrollment1.hasStudent(doubleStudent)).thenReturn(true);

        when(doubleEnrollment2.hasStudent(doubleStudent)).thenReturn(true);

        when(doubleEnrollment1.hasCourseEdition(doubleCourseEdition1)).thenReturn(true);

        when(doubleEnrollment2.hasCourseEdition(doubleCourseEdition2)).thenReturn(true);
        repository.enrolStudentInProgrammeCourseEditions(doubleStudent, courseEditions);

        //act
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            repository.enrolStudentInProgrammeCourseEditions(doubleStudent, courseEditions);
        });

        //assert
        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());

    }

    @Test
    void shouldReturnZeroWhenThereAreNoEnrollmentsInCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(CeeListFactory);

        StudentID doubleStudent = mock(StudentID.class);
        CourseEditionID doubleCourseEdition1 = mock(CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock(CourseEditionID.class);
        CourseEditionEnrolment courseEditionEnrolment = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition2)).thenReturn(courseEditionEnrolment);

        // Act

        int studentsEnrolled = repository.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }


    @Test
    void should_save_courseEditionEnrolment() {

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);

        when(enrolment.identity()).thenReturn(enrolmentID);

        //act
        CourseEditionEnrolment enrolmentSaved = repository.save(enrolment);

        //assert
        assertNotNull(enrolmentSaved);
        assertTrue(repository.containsOfIdentity(enrolmentSaved.identity()));
    }

    @Test
    void should_return_all_courseEditionEnrolments() {

        // arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);

        when(enrolment1.identity()).thenReturn(mock(CourseEditionEnrolmentID.class));
        when(enrolment2.identity()).thenReturn(mock(CourseEditionEnrolmentID.class));

        repository.save(enrolment1);
        repository.save(enrolment2);

        // act
        List<CourseEditionEnrolment> enrolments = new ArrayList<>();
        repository.findAll().forEach(enrolments::add);

        // assert
        assertEquals(2, enrolments.size());
        assertTrue(enrolments.contains(enrolment1));
        assertTrue(enrolments.contains(enrolment2));
    }


    @Test
    void should_find_enrolment_by_identity() {

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);

        when(enrolment.identity()).thenReturn(enrolmentID);
        repository.save(enrolment);

        //act
        Optional<CourseEditionEnrolment> enrolmentFound = repository.ofIdentity(enrolmentID);

        //assert
        assertTrue(enrolmentFound.isPresent());
        assertEquals(enrolment, enrolmentFound.get());
    }

    @Test
    void should_check_if_repository_contains_enrolment_by_identity() {

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);
        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);

        when(enrolment.identity()).thenReturn(enrolmentID);
        repository.save(enrolment);

        //act + assert
        assertTrue(repository.containsOfIdentity(enrolmentID));
    }

    @Test
    void should_return_true_when_ID_exists(){

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolmentID enrolmentID = mock(CourseEditionEnrolmentID.class);

        when(enrolment.identity()).thenReturn(enrolmentID);

        repository.save(enrolment);

        //act
        boolean idExists = repository.containsOfIdentity(enrolmentID);

        //assert
        assertTrue(idExists);
    }

    @Test
    void should_return_false_if_ID_doesnt_exists(){

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        CourseEditionEnrolmentID idDoesntExists = mock(CourseEditionEnrolmentID.class);

        //act
        boolean idExists = repository.containsOfIdentity(idDoesntExists);

        //arrange
        assertFalse(idExists);
    }

    @Test
    void should_return_false_if_ID_is_null(){

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        //act
        boolean idExists = repository.containsOfIdentity(null);

        //assert
        assertFalse(idExists);
    }

    @Test
    void should_return_empty_if_ID_doesnt_exists() {

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        CourseEditionEnrolmentID idDoesntExists = mock(CourseEditionEnrolmentID.class);

        //act
        Optional<CourseEditionEnrolment> idExists = repository.ofIdentity(idDoesntExists);

        //assert
        assertTrue(idExists.isEmpty());
    }

    @Test
    void should_return_empty_if_ID_is_null() {

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        //act
        Optional<CourseEditionEnrolment> idExists = repository.ofIdentity(null);

        //assert
        assertTrue(idExists.isEmpty());
    }

    @Test
    void should_return_correct_ID_when_several_exists() {

        //arrange
        ICourseEditionEnrolmentListFactory doubleICEELF = mock(ICourseEditionEnrolmentListFactory.class);
        CourseEditionEnrolmentRepositoryImpl repository = new CourseEditionEnrolmentRepositoryImpl(doubleICEELF);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment3 = mock(CourseEditionEnrolment.class);

        CourseEditionEnrolmentID id1 = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentID id2 = mock(CourseEditionEnrolmentID.class);
        CourseEditionEnrolmentID id3 = mock(CourseEditionEnrolmentID.class);

        when(enrolment1.identity()).thenReturn(id1);
        when(enrolment2.identity()).thenReturn(id2);
        when(enrolment3.identity()).thenReturn(id3);

        repository.save(enrolment1);
        repository.save(enrolment2);
        repository.save(enrolment3);

        //act
        Optional<CourseEditionEnrolment> idExists = repository.ofIdentity(id2);

        //assert
        assertTrue(idExists.isPresent());
        assertEquals(enrolment2,idExists.get());
    }

    @Test
    void testGetInternalSet_emptyRepository() {
        // Arrange
        ICourseEditionEnrolmentListFactory listFactoryMock = mock(ICourseEditionEnrolmentListFactory.class);
        Set<CourseEditionEnrolment> emptySet = new HashSet<>();
        when(listFactoryMock.getCourseEditionEnrolmentList()).thenReturn(emptySet);

        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(listFactoryMock);

        // Act
        Set<CourseEditionEnrolment> result = courseEditionEnrolmentRepository.getInternalSet();

        // Assert
        assertTrue(result.isEmpty(), "The internal set should be empty when no enrolments are added.");
    }

    @Test
    void testGetInternalSet_withOneEnrolment() {
        // Arrange
        StudentID studentID1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        when(enrolment1.knowStudent()).thenReturn(studentID1);
        when(enrolment1.knowCourseEdition()).thenReturn(courseEditionID1);

        ICourseEditionEnrolmentListFactory listFactoryMock = mock(ICourseEditionEnrolmentListFactory.class);
        Set<CourseEditionEnrolment> enrolmentSet = new HashSet<>();
        enrolmentSet.add(enrolment1);
        when(listFactoryMock.getCourseEditionEnrolmentList()).thenReturn(enrolmentSet);

        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(listFactoryMock);

        // Act
        Set<CourseEditionEnrolment> result = courseEditionEnrolmentRepository.getInternalSet();

        // Assert
        assertFalse(result.isEmpty(), "The internal set should not be empty when there is one enrolment.");
        assertTrue(result.contains(enrolment1), "The internal set should contain the first enrolment.");
    }

    @Test
    void testGetInternalSet_withMultipleEnrolments() {
        // Arrange
        StudentID studentID1 = mock(StudentID.class);
        StudentID studentID2 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionID2 = mock(CourseEditionID.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment enrolment2 = mock(CourseEditionEnrolment.class);
        when(enrolment1.knowStudent()).thenReturn(studentID1);
        when(enrolment1.knowCourseEdition()).thenReturn(courseEditionID1);
        when(enrolment2.knowStudent()).thenReturn(studentID2);
        when(enrolment2.knowCourseEdition()).thenReturn(courseEditionID2);

        ICourseEditionEnrolmentListFactory listFactoryMock = mock(ICourseEditionEnrolmentListFactory.class);
        Set<CourseEditionEnrolment> enrolmentSet = new HashSet<>();
        enrolmentSet.add(enrolment1);
        enrolmentSet.add(enrolment2);
        when(listFactoryMock.getCourseEditionEnrolmentList()).thenReturn(enrolmentSet);

        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(listFactoryMock);

        // Act
        Set<CourseEditionEnrolment> result = courseEditionEnrolmentRepository.getInternalSet();

        // Assert
        assertFalse(result.isEmpty(), "The internal set should not be empty when there are multiple enrolments.");
        assertTrue(result.contains(enrolment1), "The internal set should contain the first enrolment.");
        assertTrue(result.contains(enrolment2), "The internal set should contain the second enrolment.");
    }

    @Test
    void testGetInternalSet_afterAddingEnrolment() {
        // Arrange
        StudentID studentID1 = mock(StudentID.class);
        CourseEditionID courseEditionID1 = mock(CourseEditionID.class);

        CourseEditionEnrolment enrolment1 = mock(CourseEditionEnrolment.class);
        when(enrolment1.knowStudent()).thenReturn(studentID1);
        when(enrolment1.knowCourseEdition()).thenReturn(courseEditionID1);

        ICourseEditionEnrolmentListFactory listFactoryMock = mock(ICourseEditionEnrolmentListFactory.class);
        Set<CourseEditionEnrolment> enrolmentSet = new HashSet<>();
        enrolmentSet.add(enrolment1);
        when(listFactoryMock.getCourseEditionEnrolmentList()).thenReturn(enrolmentSet);

        CourseEditionEnrolmentRepositoryImpl courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepositoryImpl(listFactoryMock);

        // Act
        Set<CourseEditionEnrolment> result = courseEditionEnrolmentRepository.getInternalSet();

        // Assert
        assertFalse(result.isEmpty(), "The internal set should not be empty after adding an enrolment.");
        assertTrue(result.contains(enrolment1), "The internal set should contain the added enrolment.");
    }


}
