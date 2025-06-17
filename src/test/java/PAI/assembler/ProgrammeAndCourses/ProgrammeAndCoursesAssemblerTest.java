package PAI.assembler.ProgrammeAndCourses;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.dto.ProgrammeAndCourses.ProgrammeEditionEnrolmentDTO;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.ProgrammeAndCourses.StudentProgrammeEnrolmentRequestDto;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeAndCoursesAssemblerTest {

    ProgrammeAndCoursesAssembler programmeAndCoursesAssembler = new ProgrammeAndCoursesAssembler();

    @Test
    void shouldTransformToStudentEnrolmentDTO() {
        // Arrange
        ProgrammeAndCoursesAssembler programmeAndCoursesAssembler = new ProgrammeAndCoursesAssembler();


        US34Response us34Response = mock(US34Response.class);
        ProgrammeEditionEnrolment programmeEditionEnrolment = mock(ProgrammeEditionEnrolment.class);
        StudentID studentID = mock(StudentID.class);
        Acronym acronym = mock(Acronym.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        UUID programmeEditionEnrolmentGeneratedID = UUID.randomUUID();
        ProgrammeEditionEnrolmentGeneratedID generatedID = mock(ProgrammeEditionEnrolmentGeneratedID.class);
        UUID uuid = UUID.randomUUID();



        when(us34Response.progEditionEnrolment()).thenReturn(programmeEditionEnrolment);
        when(programmeEditionEnrolment.findStudentInProgrammeEdition()).thenReturn(studentID);
        when(studentID.getUniqueNumber()).thenReturn(1000001);
        when(programmeEditionEnrolment.findProgrammeEditionInEnrolment()).thenReturn(programmeEditionID);
        when(programmeEditionEnrolment.getProgrammeEditionEnrolmentGeneratedID()).thenReturn(generatedID);
        when(generatedID.toUUID()).thenReturn(programmeEditionEnrolmentGeneratedID);


        when(programmeEditionID.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getProgrammeAcronym()).thenReturn("CSD");
        when(programmeEditionID.getSchoolYearID()).thenReturn(schoolYearID);
        when(schoolYearID.toString()).thenReturn("2024");

        CourseEditionEnrolment cee = mock(CourseEditionEnrolment.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        Date date = mock(Date.class);

        when(us34Response.listCourseEditionEnrolment()).thenReturn(List.of(cee));
        when(cee.knowStudent()).thenReturn(studentID);
        when(cee.knowCourseEdition()).thenReturn(courseEditionID);
        when(courseEditionID.getProgrammeEditionID()).thenReturn(programmeEditionID);
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
        assertEquals(programmeEditionEnrolmentGeneratedID, result.programmeEditionEnrolment().genID());

        assertNotNull(result.courseEditionEnrolments());
        assertEquals(1, result.courseEditionEnrolments().size());

        CourseEditionEnrolmentDto courseDto = result.courseEditionEnrolments().get(0);
        assertEquals(1000001, courseDto.studentUniqueNumber());
        assertEquals("CSD", courseDto.programmeAcronym());
        assertEquals("2024", courseDto.schoolYearId());
        assertEquals("MATH101", courseDto.courseAcronym());
        assertEquals("Calculus I", courseDto.courseName());
        assertEquals(programmeEditionEnrolmentGeneratedID, result.programmeEditionEnrolment().genID());
    }


    @Test
    void shouldConvertDtoToStudentID() {
        // Arrange
        int expectedStudentId = 1234567;
        StudentProgrammeEnrolmentRequestDto dto = new StudentProgrammeEnrolmentRequestDto(
                expectedStudentId,
                "ENG",
                "2024-2025",
                List.of(new CourseIDDTO("MAT", "Matemática"))
        );

        // Act
        StudentID result = programmeAndCoursesAssembler.toStudentID(dto);

        // Assert
        assertEquals(expectedStudentId, result.getUniqueNumber());
    }

    @Test
    void shouldConvertDtoToProgrammeEditionID() throws Exception {
        // Arrange
        String acronym = "ENG";
        UUID schoolYearUUID = UUID.randomUUID();
        StudentProgrammeEnrolmentRequestDto dto = new StudentProgrammeEnrolmentRequestDto(
                1234567,
                acronym,
                schoolYearUUID.toString(),
                List.of(new CourseIDDTO("MAT", "Matemática"))
        );

        // Act
        ProgrammeEditionID result = programmeAndCoursesAssembler.toProgrammeEditionID(dto);

        // Assert
        assertEquals(acronym, result.getProgrammeID().getProgrammeAcronym());
        assertEquals(schoolYearUUID, result.getSchoolYearID().getSchoolYearID());
    }

    @Test
    void shouldConvertCourseDTOsToCourseIDs() {
        // Arrange
        CourseIDDTO dto1 = new CourseIDDTO("MAT", "Matemática");
        CourseIDDTO dto2 = new CourseIDDTO("BIO", "Biologia");

        StudentProgrammeEnrolmentRequestDto request = new StudentProgrammeEnrolmentRequestDto(
                1,
                "ENG",
                UUID.randomUUID().toString(),
                List.of(dto1, dto2)
        );

        // Act
        List<CourseID> result = programmeAndCoursesAssembler.toCourseIDs(request);

        // Assert
        assertEquals(2, result.size());
        assertEquals("MAT", result.get(0).getAcronym().getAcronym());
        assertEquals("Matemática", result.get(0).getName().getName());
        assertEquals("BIO", result.get(1).getAcronym().getAcronym());
        assertEquals("Biologia", result.get(1).getName().getName());
    }


}