package PAI.dto.ProgrammeAndCourses;

import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentEnrolmentResultDtoTest {

    @Test
    void shouldCreateDtoWithValidProgrammeAndCourses() {
        ProgrammeEditionEnrolmentDTO programmeDto = new ProgrammeEditionEnrolmentDTO(
                1000001,
                "CSD",
                "2024-2025"
        );

        CourseEditionEnrolmentDto course1 = new CourseEditionEnrolmentDto(
                1000001,
                "CSD",
                "2024-2025",
                "WEB",
                "Web Development",
                "2023-07-04"
        );

        CourseEditionEnrolmentDto course2 = new CourseEditionEnrolmentDto(
                1000001,
                "CSD",
                "2024-2025",
                "DB",
                "Databases",
                "2023-07-04"
        );

        StudentEnrolmentResultDto resultDto = new StudentEnrolmentResultDto(programmeDto, List.of(course1, course2));

        assertNotNull(resultDto);
        assertEquals("CSD", resultDto.programmeEditionEnrolment().programmeAcronym());
        assertEquals(2, resultDto.courseEditionEnrolments().size());
        assertEquals("WEB", resultDto.courseEditionEnrolments().get(0).courseAcronym());
    }

    @Test
    void shouldAllowEmptyCourseList() {
        ProgrammeEditionEnrolmentDTO programmeDto = new ProgrammeEditionEnrolmentDTO(
                1000002,
                "ENG",
                "2023-2024"
        );

        StudentEnrolmentResultDto resultDto = new StudentEnrolmentResultDto(programmeDto, List.of());

        assertNotNull(resultDto);
        assertEquals("ENG", resultDto.programmeEditionEnrolment().programmeAcronym());
        assertTrue(resultDto.courseEditionEnrolments().isEmpty());
    }

    @Test
    void shouldHandleNullCourseList() {
        ProgrammeEditionEnrolmentDTO programmeDto = new ProgrammeEditionEnrolmentDTO(
                1000003,
                "BIO",
                "2022-2023"
        );

        StudentEnrolmentResultDto resultDto = new StudentEnrolmentResultDto(programmeDto, null);

        assertNotNull(resultDto);
        assertEquals("BIO", resultDto.programmeEditionEnrolment().programmeAcronym());
        assertNull(resultDto.courseEditionEnrolments());
    }

    @Test
    void shouldAllowNullProgrammeEditionEnrolment() {
        StudentEnrolmentResultDto resultDto = new StudentEnrolmentResultDto(null, List.of());

        assertNull(resultDto.programmeEditionEnrolment());
        assertTrue(resultDto.courseEditionEnrolments().isEmpty());
    }


}