package PAI.dto.ProgrammeAndCourses;

import PAI.dto.course.CourseIDDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StudentProgrammeEnrolmentRequestDtoTest {

    @Test
    void testRecordConstructionAndAccessors() {
        int studentId = 123;
        String programmeAcronym = "ISEP";
        String schoolYearId = UUID.randomUUID().toString();
        CourseIDDTO course1 = new CourseIDDTO("Mathematics", "MAT");
        CourseIDDTO course2 = new CourseIDDTO("Computing", "COM");

        List<CourseIDDTO> courseIds = List.of(course1, course2);

        StudentProgrammeEnrolmentRequestDto dto =
                new StudentProgrammeEnrolmentRequestDto(studentId, programmeAcronym, schoolYearId, courseIds);

        assertEquals(studentId, dto.studentId());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearId, dto.schoolYearId());
        assertEquals(courseIds, dto.courseIds());
    }

    @Test
    void testEmptyCourseList() {
        StudentProgrammeEnrolmentRequestDto dto =
                new StudentProgrammeEnrolmentRequestDto(321, "BIO", "2025-2026", List.of());

        assertNotNull(dto.courseIds());
        assertTrue(dto.courseIds().isEmpty());
    }
}