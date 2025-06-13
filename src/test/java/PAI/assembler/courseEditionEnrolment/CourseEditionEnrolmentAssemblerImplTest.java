package PAI.assembler.courseEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionEnrolmentAssemblerImplTest {

    private CourseEditionEnrolmentAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new CourseEditionEnrolmentAssemblerImpl();
    }

    @Test
    void toStudentID_shouldReturnCorrectStudentID() throws Exception {
        // Arrange
        int studentUniqueNumber = 1100000;

        // Act
        StudentID studentID = assembler.toStudentID(studentUniqueNumber);

        // Assert
        assertNotNull(studentID);
        assertEquals(1100000, studentID.getUniqueNumber());
    }

    @Test
    void toCourseEditionID_shouldReturnCorrectCourseEditionID() throws Exception {
        // Arrange
        CourseEditionEnrolmentDto dto = mock(CourseEditionEnrolmentDto.class);
        when(dto.programmeAcronym()).thenReturn("LEIC");
        when(dto.schoolYearId()).thenReturn("123e4567-e89b-12d3-a456-426614174000");
        when(dto.courseAcronym()).thenReturn("ESOFT");
        when(dto.courseName()).thenReturn("Engineering Software");
        when(dto.studyPlanDate()).thenReturn("01-01-2024");

        // Act
        CourseEditionID courseEditionID = assembler.toCourseEditionID(dto);

        // Assert
        assertNotNull(courseEditionID);
        assertEquals(dto.programmeAcronym(), courseEditionID.getProgrammeEditionID().getProgrammeID().getAcronym().getValue());
        assertEquals(dto.schoolYearId(), courseEditionID.getProgrammeEditionID().getSchoolYearID().getSchoolYearID().toString());
        assertEquals(dto.courseAcronym(), courseEditionID.getCourseInStudyPlanID().getCourseID().getAcronym().getValue());
        assertEquals(dto.courseName(), courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseNameValue());
        assertEquals(dto.studyPlanDate(), courseEditionID.getCourseInStudyPlanID().getStudyPlanID().getDate().toString());
    }

    @Test
    void toDto_shouldReturnCorrectDto() throws Exception {
        // Arrange
        // VO básicos
        Acronym programmeAcronym = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);
        UUID schoolYearUUID = UUID.randomUUID();
        SchoolYearID schoolYearID = new SchoolYearID(schoolYearUUID);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        Acronym courseAcronym = new Acronym("ESOFT");
        Name courseName = new Name("Engenharia de Software");
        CourseID courseID = new CourseID(courseAcronym, courseName);

        Date studyPlanDate = new Date(LocalDate.of(2023, 9, 1));
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
        StudentID studentID = new StudentID(1234567);
        Date enrolmentDate = new Date(LocalDate.of(2025, 6, 13));
        EnrolmentStatus isActive = new EnrolmentStatus(true);
        CourseEditionEnrolmentGeneratedID generatedID = new CourseEditionEnrolmentGeneratedID();

        CourseEditionEnrolment enrolment = new CourseEditionEnrolment(
                generatedID,
                studentID,
                courseEditionID,
                enrolmentDate,
                isActive
        );

        CourseEditionEnrolmentAssemblerImpl assembler = new CourseEditionEnrolmentAssemblerImpl();

        // Act
        CourseEditionEnrolmentDto dto = assembler.toDto(enrolment);

        // Assert
        assertEquals(1234567, dto.studentUniqueNumber());
        assertEquals("LEI", dto.programmeAcronym());
        assertEquals(schoolYearUUID.toString(), dto.schoolYearId());
        assertEquals("ESOFT", dto.courseAcronym());
        assertEquals("2023-09-01", dto.studyPlanDate()); // Date.toString() default ISO format
        assertEquals("", dto.courseName()); // Ainda não está disponível no domínio
    }


}
