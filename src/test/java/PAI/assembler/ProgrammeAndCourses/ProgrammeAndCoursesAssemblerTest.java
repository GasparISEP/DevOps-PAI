package PAI.assembler.ProgrammeAndCourses;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.dto.ProgrammeAndCourses.ProgrammeEditionEnrolmentDTO;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeAndCoursesAssemblerTest {
    @Test
    void shouldTransformToStudentEnrolmentDTO(){
        // Arrange
        ProgrammeAndCoursesAssembler programmeAndCoursesAssembler = new ProgrammeAndCoursesAssembler();

        US34Response us34Response = mock(US34Response.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        StudentID studentID = mock(StudentID.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        // Setup mocks for programme enrolment
        when(us34Response.progEditionEnrolment()).thenReturn(programmeEditionEnrolment);
        when(programmeEditionEnrolment.findStudentInProgrammeEdition()).thenReturn(studentID);
        when(studentID.getUniqueNumber()).thenReturn(1000001);
        when(programmeEditionEnrolment.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionID);

        when(programmeEditionID.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getProgrammeAcronym()).thenReturn("CSD");
        when(programmeEditionID.getSchoolYearID()).thenReturn(schoolYearID);
        when(schoolYearID.toString()).thenReturn("2024");

        // Setup mocks for course enrolments
        CourseEditionEnrolment cee = mock(CourseEditionEnrolment.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Date date = mock(Date.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(us34Response.listCourseEditionEnrolment()).thenReturn(List.of(cee));
        when(cee.knowStudent()).thenReturn(studentID);
        when(cee.knowCourseEdition()).thenReturn(courseEditionID);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEditionID.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);
        when(courseEditionID.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);
        when(courseInStudyPlanID.getCourseID()).thenReturn(courseID);
        when(courseID.getCourseAcronymValue()).thenReturn("MATH101");
        when(courseID.getCourseNameValue()).thenReturn("Calculus I");
        when(courseInStudyPlanID.getStudyPlanID()).thenReturn(studyPlanID);
        when(studyPlanID.getDate()).thenReturn(date);
        when(date.toString()).thenReturn("09-01-2022");

        // Act
        StudentEnrolmentResultDto result = programmeAndCoursesAssembler.toDto(us34Response);

        // Assert
        assertNotNull(result);
        assertEquals(1000001, result.programmeEditionEnrolment().studentId());
        assertEquals("CSD", result.programmeEditionEnrolment().programmeAcronym());
        assertEquals("2024", result.programmeEditionEnrolment().schoolYearId());

        assertEquals(1, result.courseEditionEnrolments().size());
        CourseEditionEnrolmentDto courseDto = result.courseEditionEnrolments().get(0);
        assertEquals(1000001, courseDto.studentUniqueNumber());
        assertEquals("CSD", courseDto.programmeAcronym());
        assertEquals("2024", courseDto.schoolYearId());
        assertEquals("MATH101", courseDto.courseAcronym());

    }





}