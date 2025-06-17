package PAI.persistence.mem.programmeEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEditionEnrolment.IProgrammeEditionEnrolmentFactory;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.student.Student;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentRepositoryImplTest {

    @Test
    void shouldReturnTrueIfStudentIsEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory mockListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(mockListFactory);

        StudentID stId1 = mock(StudentID.class);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
        when(enrolMock1.hasSameStudent(stId1)).thenReturn(true);
        when(enrolMock1.hasSameProgrammeEdition(peId1)).thenReturn(true);

        repository.save(enrolMock1);

        // Act
        boolean result = repository.isStudentEnrolledInThisProgrammeEdition(stId1, peId1);

        // Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfStudentNullNotEnrolledInProgrammeEdition() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(enrolMock1.hasSameProgrammeEdition(peId1)).thenReturn(true);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(peId1);
        // Act + Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(null, peId1));
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionNull() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        StudentID stId1 = mock(StudentID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(enrolMock1.hasSameStudent(stId1)).thenReturn(true);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(null);

        // Act + Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(stId1, null));
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionNullAndStudentNull() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        // Act + Assert
        assertFalse(repository.isStudentEnrolledInThisProgrammeEdition(null, null));
    }

    @Test
    void shouldReturnFalseIfStudentIsNotEnrolledInProgrammeEdition() throws Exception {
        // Arrange

        IProgrammeEditionEnrolmentService doubleService = mock(IProgrammeEditionEnrolmentService.class);

        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        Student mockStudent = mock(Student.class);
        StudentID stId1 = mock(StudentID.class);
        ProgrammeEditionID peId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID peId2 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);

        when(mockStudent.identity()).thenReturn(stId1);

        when(enrolMock1.findStudentInProgrammeEdition()).thenReturn(stId1);

        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(peId1);

        when(doubleIPEEF.newProgrammeEditionEnrolment(stId1, peId1)).thenReturn(enrolMock1);

        when(doubleService.enrolStudentInProgrammeEdition(stId1, peId1)).thenReturn(true);

        doubleService.enrolStudentInProgrammeEdition(stId1, peId1);

        // Act
        boolean result = repository.isStudentEnrolledInThisProgrammeEdition(stId1, peId2);

        // Assert
        assertFalse(result);
    }


//    @Test
//    void shouldReturnCorrectCountWhenStudentsAreEnrolledInDepartmentAndSchoolYear() throws Exception {
//        // Arrange
//        SchoolYearID schoolYear1Double = mock(SchoolYearID.class);
//
//        ProgrammeID programmeID1 = mock(ProgrammeID.class);
//        ProgrammeID programmeID2 = mock(ProgrammeID.class);
//        List<ProgrammeID> programmeIDs = List.of(programmeID1, programmeID2);
//
//        ProgrammeEditionID editionId1Double = mock(ProgrammeEditionID.class);
//        ProgrammeEditionID editionId2Double = mock(ProgrammeEditionID.class);
//        ProgrammeEditionID editionId3Double = mock(ProgrammeEditionID.class);
//
//        StudentID studentId1Double = mock(StudentID.class);
//        StudentID studentId2Double = mock(StudentID.class);
//        StudentID studentId3Double = mock(StudentID.class);
//
//        Student student1Double = mock(Student.class);
//        Student student2Double = mock(Student.class);
//        Student student3Double = mock(Student.class);
//
//        when(student1Double.identity()).thenReturn(studentId1Double);
//        when(student2Double.identity()).thenReturn(studentId2Double);
//        when(student3Double.identity()).thenReturn(studentId3Double);
//
//        IProgrammeEditionEnrolmentService doubleService = mock(IProgrammeEditionEnrolmentService.class);
//
//        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
//        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(ProgrammeEditionEnrolmentListFactoryImpl.class);
//        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);
//
//        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
//        when(enrolMock1.findStudentInProgrammeEdition()).thenReturn(studentId1Double);
//        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(editionId1Double);
//        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId1Double, editionId1Double)).thenReturn(enrolMock1);
//
//        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
//        when(enrolMock2.findStudentInProgrammeEdition()).thenReturn(studentId2Double);
//        when(enrolMock2.findProgrammeEditionInEnrolment()).thenReturn(editionId2Double);
//        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId2Double, editionId2Double)).thenReturn(enrolMock2);
//
//        ProgrammeEditionEnrolment enrolMock3 = mock(ProgrammeEditionEnrolment.class);
//        when(enrolMock3.findStudentInProgrammeEdition()).thenReturn(studentId3Double);
//        when(enrolMock3.findProgrammeEditionInEnrolment()).thenReturn(editionId3Double);
//        when(doubleIPEEF.newProgrammeEditionEnrolment(studentId3Double, editionId3Double)).thenReturn(enrolMock3);
//
//        when(doubleService.enrolStudentInProgrammeEdition(studentId1Double, editionId1Double)).thenReturn(true);
//        when(doubleService.enrolStudentInProgrammeEdition(studentId2Double, editionId2Double)).thenReturn(true);
//        when(doubleService.enrolStudentInProgrammeEdition(studentId3Double, editionId3Double)).thenReturn(false);
//
//        doubleService.enrolStudentInProgrammeEdition(studentId1Double, editionId1Double);
//        doubleService.enrolStudentInProgrammeEdition(studentId2Double, editionId2Double);
//        doubleService.enrolStudentInProgrammeEdition(studentId3Double, editionId3Double);
//
//        when(enrolMock1.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1Double, programmeIDs)).thenReturn(true);
//        when(enrolMock2.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1Double, programmeIDs)).thenReturn(true);
//        when(enrolMock3.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1Double, programmeIDs)).thenReturn(false);
//
//        repository.save(enrolMock1);
//        repository.save(enrolMock2);
//        repository.save(enrolMock3);
//        // Act
//        int result = repository.countEnrolledStudentsByProgrammeEditionIds(programmeIDs);
//
//        // Assert
//        assertEquals(2, result);
//    }

//    @Test
//    void shouldReturnZeroWhenNoStudentsAreEnrolledInDepartmentAndSchoolYear() {
//        // Arrange
//        SchoolYearID schoolYear1Double = mock(SchoolYearID.class);
//
//        ProgrammeID programmeID1 = mock(ProgrammeID.class);
//        ProgrammeID programmeID2 = mock(ProgrammeID.class);
//        List<ProgrammeID> programmeIDs = List.of(programmeID1, programmeID2);
//
//        ProgrammeEditionID editionId1Double = mock(ProgrammeEditionID.class);
//        ProgrammeEditionID editionId2Double = mock(ProgrammeEditionID.class);
//
//        StudentID mockStudentID1 = mock(StudentID.class);
//        StudentID mockStudentID2 = mock(StudentID.class);
//
//        Student student1Double = mock(Student.class);
//        Student student2Double = mock(Student.class);
//        when(student1Double.identity()).thenReturn(mockStudentID1);
//        when(student2Double.identity()).thenReturn(mockStudentID2);
//
//        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
//        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
//        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);
//
//        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
//        when(enrolMock1.findStudentInProgrammeEdition()).thenReturn(mockStudentID1);
//        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(editionId1Double);
//        when(doubleIPEEF.newProgrammeEditionEnrolment(mockStudentID1, editionId1Double)).thenReturn(enrolMock1);
//
//        ProgrammeEditionEnrolment enrolMock2 = mock(ProgrammeEditionEnrolment.class);
//        when(enrolMock2.findStudentInProgrammeEdition()).thenReturn(mockStudentID2);
//        when(enrolMock2.findProgrammeEditionInEnrolment()).thenReturn(editionId2Double);
//        when(doubleIPEEF.newProgrammeEditionEnrolment(mockStudentID2, editionId2Double)).thenReturn(enrolMock2);
//
//        repository.save(enrolMock1);
//        repository.save(enrolMock2);
//
//        // Act
//        int result = repository.countEnrolledStudentsByProgrammeEditionIds(programmeIDs);
//
//        // Assert
//        assertEquals(0, result);
//    }

//    @Test
//    void shouldReturnCorrectCountWhenStudentsAreEnrolledInMultipleEditions() {
//        // Arrange
//        SchoolYearID schoolYear1Double = mock(SchoolYearID.class);
//
//        ProgrammeID programmeID1 = mock(ProgrammeID.class);
//        ProgrammeID programmeID2 = mock(ProgrammeID.class);
//        List<ProgrammeID> programmeIDs = List.of(programmeID1, programmeID2);
//
//        ProgrammeEditionID editionId1Double = mock(ProgrammeEditionID.class);
//        ProgrammeEditionID editionId2Double = mock(ProgrammeEditionID.class);
//
//        StudentID studentID1Double = mock(StudentID.class);
//
//        IProgrammeEditionEnrolmentFactory doubleIPEEF = mock(IProgrammeEditionEnrolmentFactory.class);
//        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
//        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);
//
//        ProgrammeEditionEnrolment enrolMock1 = mock(ProgrammeEditionEnrolment.class);
//        when(enrolMock1.findStudentInProgrammeEdition()).thenReturn(studentID1Double);
//        when(enrolMock1.findProgrammeEditionInEnrolment()).thenReturn(editionId1Double);
//        when(doubleIPEEF.newProgrammeEditionEnrolment(studentID1Double, editionId1Double)).thenReturn(enrolMock1);
//
//        ProgrammeEditionEnrolment enrollMock2 = mock(ProgrammeEditionEnrolment.class);
//        when(enrollMock2.findStudentInProgrammeEdition()).thenReturn(studentID1Double);
//        when(enrollMock2.findProgrammeEditionInEnrolment()).thenReturn(editionId2Double);
//        when(doubleIPEEF.newProgrammeEditionEnrolment(studentID1Double, editionId2Double)).thenReturn(enrollMock2);
//
//        when(enrolMock1.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1Double, programmeIDs)).thenReturn(true);
//        when(enrollMock2.isEnrolmentAssociatedToProgrammeAndSchoolYear(schoolYear1Double, programmeIDs)).thenReturn(true);
//
//        repository.save(enrolMock1);
//        repository.save(enrollMock2);
//
//        // Act
//        int result = repository.countEnrolledStudentsByProgrammeEditionIds(programmeIDs);
//
//        // Assert
//        assertEquals(1, result);
//    }

    // testing find Programme Edition ID's that student is enrolled (active status)

    @Test
    void should_return_a_list_of_programmeEditionIDs_that_student_is_enrolled (){
        // arrange
        IProgrammeEditionEnrolmentListFactory doubleListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        Set<ProgrammeEditionEnrolment> doubleSet = mock(Set.class);
        when(doubleListFactory.newListProgrammeEditionEnrolment()).thenReturn(doubleSet);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleListFactory);

        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment doublePeeEnrolment = mock(ProgrammeEditionEnrolment.class);

        when(doublePeeEnrolment.findStudentInProgrammeEdition()).thenReturn(doubleStudentId);
        when(doublePeeEnrolment.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId);
        when(doublePeeEnrolment.isEnrolmentActive()).thenReturn(true);

        when(doubleSet.add(doublePeeEnrolment)).thenReturn(true);
        when(doubleSet.stream()).thenReturn(Stream.of(doublePeeEnrolment));

        repository.save(doublePeeEnrolment);

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId);

        // assert
        assertEquals(1, result.size());
    }

    @Test
    void should_return_a_empty_list_when_student_is_not_enrolled_in_any_programmeEdition (){
        // arrange
        IProgrammeEditionEnrolmentListFactory doubleListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        Set<ProgrammeEditionEnrolment> doubleSet = mock(Set.class);
        when(doubleListFactory.newListProgrammeEditionEnrolment()).thenReturn(doubleSet);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleListFactory);

        StudentID doubleStudentId1 = mock(StudentID.class);
        StudentID doubleStudentId2 = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment doublePeeEnrolment = mock(ProgrammeEditionEnrolment.class);

        when(doublePeeEnrolment.findStudentInProgrammeEdition()).thenReturn(doubleStudentId1);
        when(doublePeeEnrolment.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId);
        when(doublePeeEnrolment.isEnrolmentActive()).thenReturn(true);

        when(doubleSet.add(doublePeeEnrolment)).thenReturn(true);
        when(doubleSet.stream()).thenReturn(Stream.of(doublePeeEnrolment));

        repository.save(doublePeeEnrolment);

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId2);

        // assert
        assertEquals(0, result.size());
    }

    @Test
    void should_return_a_empty_list_when_student_is_enrolled_in_programmeEdition_with_inactive_status (){
        // arrange
        IProgrammeEditionEnrolmentListFactory doubleListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        Set<ProgrammeEditionEnrolment> doubleSet = mock(Set.class);
        when(doubleListFactory.newListProgrammeEditionEnrolment()).thenReturn(doubleSet);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleListFactory);

        StudentID doubleStudentId1 = mock(StudentID.class);
        StudentID doubleStudentId2 = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment doublePeeEnrolment = mock(ProgrammeEditionEnrolment.class);

        when(doublePeeEnrolment.findStudentInProgrammeEdition()).thenReturn(doubleStudentId1);
        when(doublePeeEnrolment.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId);
        when(doublePeeEnrolment.isEnrolmentActive()).thenReturn(false);

        when(doubleSet.add(doublePeeEnrolment)).thenReturn(true);
        when(doubleSet.stream()).thenReturn(Stream.of(doublePeeEnrolment));

        repository.save(doublePeeEnrolment);

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId2);

        // assert
        assertEquals(0, result.size());
    }

    @Test
    void should_return_a_list_of_two_programmeEditionIDs_that_student_is_enrolled (){
        // arrange
        IProgrammeEditionEnrolmentListFactory doubleListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        Set<ProgrammeEditionEnrolment> doubleSet = mock(Set.class);
        when(doubleListFactory.newListProgrammeEditionEnrolment()).thenReturn(doubleSet);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleListFactory);

        StudentID doubleStudentId1 = mock(StudentID.class);
        ProgrammeEditionID doubleProgrammeEditionId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID doubleProgrammeEditionId2 = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment doublePeEnrolment1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment doublePeEnrolment2 = mock(ProgrammeEditionEnrolment.class);

        when(doublePeEnrolment1.findStudentInProgrammeEdition()).thenReturn(doubleStudentId1);
        when(doublePeEnrolment2.findStudentInProgrammeEdition()).thenReturn(doubleStudentId1);
        when(doublePeEnrolment1.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId1);
        when(doublePeEnrolment2.findProgrammeEditionInEnrolment()).thenReturn(doubleProgrammeEditionId2);
        when(doublePeEnrolment1.isEnrolmentActive()).thenReturn(true);
        when(doublePeEnrolment2.isEnrolmentActive()).thenReturn(true);

        when(doubleSet.add(doublePeEnrolment1)).thenReturn(true);
        when(doubleSet.add(doublePeEnrolment2)).thenReturn(true);
        when(doubleSet.stream()).thenReturn(Stream.of(doublePeEnrolment1, doublePeEnrolment2));

        repository.save(doublePeEnrolment1);
        repository.save(doublePeEnrolment2);

        // act
        List<ProgrammeEditionID> result = repository.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId1);

        // assert
        assertEquals(2, result.size());
    }

    @Test
    void save_ShouldAddProgrammeEditionEnrolment() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        Set<ProgrammeEditionEnrolment> mockList = new HashSet<>();
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(mockList);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        when(enrolment.identity()).thenReturn(mock(ProgrammeEditionEnrolmentID.class));

        // Act
        ProgrammeEditionEnrolment savedEnrolment = repository.save(enrolment);

        // Assert
        assertEquals(enrolment, savedEnrolment);
        assertTrue(mockList.contains(enrolment));
    }

    @Test
    void findAll_ShouldReturnAllSavedEnrolments() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>(Collections.singletonList(enrolment));
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        // Act
        Iterable<ProgrammeEditionEnrolment> result = repository.findAll();

        // Assert
        assertTrue(result.iterator().hasNext());
        assertEquals(enrolment, result.iterator().next());
    }

    @Test
    void ofIdentity_ShouldReturnEnrolmentIfExists() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        when(enrolment.identity()).thenReturn(enrolmentID);
        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>(Collections.singletonList(enrolment));
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        // Act
        Optional<ProgrammeEditionEnrolment> result = repository.ofIdentity(enrolmentID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(enrolment, result.get());
    }

    @Test
    void containsOfIdentity_ShouldReturnTrueIfEnrolmentExists() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        when(enrolment.identity()).thenReturn(enrolmentID);
        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>(Collections.singletonList(enrolment));
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        // Act
        boolean exists = repository.containsOfIdentity(enrolmentID);

        // Assert
        assertTrue(exists);
    }

    @Test
    void containsOfIdentity_ShouldReturnFalseIfEnrolmentDoesNotExist() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(new HashSet<>());

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        // Act
        boolean exists = repository.containsOfIdentity(enrolmentID);

        // Assert
        assertFalse(exists);
    }

    @Test
    void should_throw_exception_if_identity_is_null() throws IllegalArgumentException {

        //arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //act + assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->{
            repository.save(null);
        });
        assertEquals(exception.getMessage(),"Entity cannot be null");
    }


    @Test
    void should_return_true_when_ID_exists(){

        //arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        ProgrammeEditionEnrolmentID enrolmentID = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);


        when(enrolment.sameAs(enrolment)).thenReturn(false);
        when(enrolment.identity()).thenReturn(enrolmentID);
        repository.save(enrolment);


        //act
        boolean idExists = repository.containsOfIdentity(enrolmentID);

        //assert
        assertTrue(idExists);
    }

    @Test
    void should_return_correct_ID_when_several_exists() {

        //arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        ProgrammeEditionEnrolment enrolment1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolment2 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolment3 = mock(ProgrammeEditionEnrolment.class);

        ProgrammeEditionEnrolmentID id1 = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentID id2 = mock(ProgrammeEditionEnrolmentID.class);
        ProgrammeEditionEnrolmentID id3 = mock(ProgrammeEditionEnrolmentID.class);

        when(enrolment1.identity()).thenReturn(id1);
        when(enrolment2.identity()).thenReturn(id2);
        when(enrolment3.identity()).thenReturn(id3);

        repository.save(enrolment1);
        repository.save(enrolment2);
        repository.save(enrolment3);

        //act
        Optional<ProgrammeEditionEnrolment> idExists = repository.ofIdentity(id2);

        //assert
        assertTrue(idExists.isPresent());
        assertEquals(enrolment2,idExists.get());
    }

    @Test
    void testEqualsReflexivity() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act + Assert
        assertEquals(repository, repository);
    }


    @Test
    void testEqualsNull() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act+Assert
        assertNotEquals(repo1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act
        String differentClassObject = "string";

        //Assert
        assertNotEquals(repo1, differentClassObject);
    }

    @Test
    void testHashCodeReturnsSameValueForSameObject() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        //Act
        int hash1 = repo1.hashCode();
        int hash2 = repo1.hashCode();

        //Assert
        assertEquals(hash1, hash2);
    }

    @Test
    void testHashCodeInHashSet() {
        //Arrange
        IProgrammeEditionEnrolmentListFactory doubleIPEELF = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF);

        IProgrammeEditionEnrolmentListFactory doubleIPEELF2 = mock(IProgrammeEditionEnrolmentListFactory.class);
        ProgrammeEditionEnrolmentRepositoryImpl repo2 = new ProgrammeEditionEnrolmentRepositoryImpl(doubleIPEELF2);

        //Act
        HashSet<ProgrammeEditionEnrolmentRepositoryImpl> set = new HashSet<>();
        set.add(repo1);
        set.add(repo2);

        //Assert
        assertTrue(set.contains(repo2));
    }

    @Test
    void shouldGetAllProgrammeEditionsEnrolments() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);

        when(programmeEditionEnrolment.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionID);

        Set<ProgrammeEditionEnrolment> allProgrammeEditionEnrolments = Set.of(programmeEditionEnrolment);

        IProgrammeEditionEnrolmentListFactory iProgrammeEditionEnrolmentListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        when(iProgrammeEditionEnrolmentListFactory.newListProgrammeEditionEnrolment()).thenReturn(allProgrammeEditionEnrolments);

        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImpl =
                new ProgrammeEditionEnrolmentRepositoryImpl(iProgrammeEditionEnrolmentListFactory);

        // act
        List<ProgrammeEditionEnrolment> result = programmeEditionEnrolmentRepositoryImpl.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEditionID);
        // assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyListIfNoProgrammeEditionsEnrolmentsFound() {
        // arrange
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);

        when(programmeEditionEnrolment.findProgrammeEditionInEnrolment()).thenReturn(null);

        Set<ProgrammeEditionEnrolment> allProgrammeEditionEnrolments = Set.of();

        IProgrammeEditionEnrolmentListFactory iProgrammeEditionEnrolmentListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        when(iProgrammeEditionEnrolmentListFactory.newListProgrammeEditionEnrolment()).thenReturn(allProgrammeEditionEnrolments);

        ProgrammeEditionEnrolmentRepositoryImpl programmeEditionEnrolmentRepositoryImpl =
                new ProgrammeEditionEnrolmentRepositoryImpl(iProgrammeEditionEnrolmentListFactory);

        // act
        List<ProgrammeEditionEnrolment> result = programmeEditionEnrolmentRepositoryImpl.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEditionID);
        // assert
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnEmptyWhenStudentIdOrProgrammeEditionIdIsNull() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(new HashSet<>());

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        // Act & Assert
        assertTrue(repository.findByStudentAndProgrammeEdition(null, mock(ProgrammeEditionID.class)).isEmpty());
        assertTrue(repository.findByStudentAndProgrammeEdition(mock(StudentID.class), null).isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenNoEnrolmentMatches() {
        // Arrange
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        when(enrolment.hasSameStudent(studentID)).thenReturn(false); // força mismatch

        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>();
        enrolments.add(enrolment);

        IProgrammeEditionEnrolmentFactory factory = mock(IProgrammeEditionEnrolmentFactory.class);
        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        // Act
        Optional<ProgrammeEditionEnrolment> result = repository.findByStudentAndProgrammeEdition(studentID, programmeEditionID);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEnrolmentWhenMatchFound() {
        // Arrange
        StudentID studentID = mock(StudentID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);
        when(enrolment.hasSameStudent(studentID)).thenReturn(true);
        when(enrolment.hasSameProgrammeEdition(programmeEditionID)).thenReturn(true);

        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>();
        enrolments.add(enrolment);

        IProgrammeEditionEnrolmentListFactory listFactory = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(listFactory.newListProgrammeEditionEnrolment()).thenReturn(enrolments);

        ProgrammeEditionEnrolmentRepositoryImpl repository = new ProgrammeEditionEnrolmentRepositoryImpl(listFactory);

        // Act
        Optional<ProgrammeEditionEnrolment> result = repository.findByStudentAndProgrammeEdition(studentID, programmeEditionID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(enrolment, result.get());
    }

    @Test
    void testGetInternalSet_returnsSameInstance() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory mockListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        Set<ProgrammeEditionEnrolment> mockSet = new HashSet<>();
        when(mockListFactory.newListProgrammeEditionEnrolment()).thenReturn(mockSet);

        ProgrammeEditionEnrolmentRepositoryImpl repo =
                new ProgrammeEditionEnrolmentRepositoryImpl(mockListFactory);

        // Act & Assert
        assertSame(mockSet, repo.getInternalSet());
    }

    @Test
    void testGetInternalSet_reflectsModifications() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory mockListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        Set<ProgrammeEditionEnrolment> mockSet = new HashSet<>();
        when(mockListFactory.newListProgrammeEditionEnrolment()).thenReturn(mockSet);

        ProgrammeEditionEnrolmentRepositoryImpl repo =
                new ProgrammeEditionEnrolmentRepositoryImpl(mockListFactory);

        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        // Act
        repo.getInternalSet().add(enrolment);

        // Assert
        assertTrue(repo.getInternalSet().contains(enrolment));
    }

    @Test
    void testGetInternalSet_isInitiallyEmpty() {
        // Arrange
        IProgrammeEditionEnrolmentListFactory mockListFactory = mock(IProgrammeEditionEnrolmentListFactory.class);

        Set<ProgrammeEditionEnrolment> emptySet = new HashSet<>();
        when(mockListFactory.newListProgrammeEditionEnrolment()).thenReturn(emptySet);

        ProgrammeEditionEnrolmentRepositoryImpl repo =
                new ProgrammeEditionEnrolmentRepositoryImpl(mockListFactory);

        // Act & Assert
        assertTrue(repo.getInternalSet().isEmpty());
    }

    @Test
    void testHashCode_IsCalledWithNonEmptySet() {
        ProgrammeEditionEnrolment enrolment = mock(ProgrammeEditionEnrolment.class);

        Set<ProgrammeEditionEnrolment> set = new HashSet<>();
        set.add(enrolment);

        IProgrammeEditionEnrolmentListFactory factory = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(factory.newListProgrammeEditionEnrolment()).thenReturn(set);

        ProgrammeEditionEnrolmentRepositoryImpl repo = new ProgrammeEditionEnrolmentRepositoryImpl(factory);

        int hash = repo.hashCode();

        assertNotEquals(0, hash);
    }

    @Test
    void testEquals_SameInstance() {
        IProgrammeEditionEnrolmentListFactory factory = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(factory.newListProgrammeEditionEnrolment()).thenReturn(new HashSet<>());

        ProgrammeEditionEnrolmentRepositoryImpl repo = new ProgrammeEditionEnrolmentRepositoryImpl(factory);

        assertEquals(repo, repo);
    }

    @Test
    void testEquals_DifferentInstancesSameContent() {
        Set<ProgrammeEditionEnrolment> enrolments = new HashSet<>();

        IProgrammeEditionEnrolmentListFactory factory1 = mock(IProgrammeEditionEnrolmentListFactory.class);
        IProgrammeEditionEnrolmentListFactory factory2 = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(factory1.newListProgrammeEditionEnrolment()).thenReturn(enrolments);
        when(factory2.newListProgrammeEditionEnrolment()).thenReturn(enrolments);

        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(factory1);
        ProgrammeEditionEnrolmentRepositoryImpl repo2 = new ProgrammeEditionEnrolmentRepositoryImpl(factory2);

        assertEquals(repo1, repo2);
        assertEquals(repo1.hashCode(), repo2.hashCode());
    }

    @Test
    void testEquals_DifferentContent() {
        ProgrammeEditionEnrolment enrolment1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment enrolment2 = mock(ProgrammeEditionEnrolment.class);

        Set<ProgrammeEditionEnrolment> enrolments1 = new HashSet<>();
        enrolments1.add(enrolment1);

        Set<ProgrammeEditionEnrolment> enrolments2 = new HashSet<>();
        enrolments2.add(enrolment2);

        IProgrammeEditionEnrolmentListFactory factory1 = mock(IProgrammeEditionEnrolmentListFactory.class);
        IProgrammeEditionEnrolmentListFactory factory2 = mock(IProgrammeEditionEnrolmentListFactory.class);

        when(factory1.newListProgrammeEditionEnrolment()).thenReturn(enrolments1);
        when(factory2.newListProgrammeEditionEnrolment()).thenReturn(enrolments2);

        ProgrammeEditionEnrolmentRepositoryImpl repo1 = new ProgrammeEditionEnrolmentRepositoryImpl(factory1);
        ProgrammeEditionEnrolmentRepositoryImpl repo2 = new ProgrammeEditionEnrolmentRepositoryImpl(factory2);

        assertNotEquals(repo1, repo2);
    }


    @Test
    void testEquals_NullAndOtherType() {
        IProgrammeEditionEnrolmentListFactory factory = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(factory.newListProgrammeEditionEnrolment()).thenReturn(new HashSet<>());

        ProgrammeEditionEnrolmentRepositoryImpl repo = new ProgrammeEditionEnrolmentRepositoryImpl(factory);

        assertNotEquals(null, repo);
        assertNotEquals(repo, "some string");
    }

    @Test
    void testHashCode_Consistency() {
        IProgrammeEditionEnrolmentListFactory factory = mock(IProgrammeEditionEnrolmentListFactory.class);
        when(factory.newListProgrammeEditionEnrolment()).thenReturn(new HashSet<>());

        ProgrammeEditionEnrolmentRepositoryImpl repo = new ProgrammeEditionEnrolmentRepositoryImpl(factory);

        int hash1 = repo.hashCode();
        int hash2 = repo.hashCode();

        assertEquals(hash1, hash2);
    }

}