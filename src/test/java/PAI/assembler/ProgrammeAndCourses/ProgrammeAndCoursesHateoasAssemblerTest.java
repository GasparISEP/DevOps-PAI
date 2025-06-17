package PAI.assembler.ProgrammeAndCourses;

import PAI.controllerRest.StudentProgrammeEditionEnrolmentRestController;
import PAI.controllerRest.StudentRestController;
import PAI.dto.ProgrammeAndCourses.ProgrammeEditionEnrolmentDTO;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

class ProgrammeAndCoursesHateoasAssemblerTest {
    @Test
    void shouldWrapDtoWithHateoasLinks() {
        // Arrange
        ProgrammeAndCoursesHateoasAssembler assembler = new ProgrammeAndCoursesHateoasAssembler();

        int studentId = 1000001;
        UUID enrolmentUUID = UUID.randomUUID();

        ProgrammeEditionEnrolmentDTO programmeDto = new ProgrammeEditionEnrolmentDTO(
                studentId,
                "CSD",
                "2024",
                enrolmentUUID
        );

        CourseEditionEnrolmentDto courseDto = new CourseEditionEnrolmentDto(
                studentId,
                "CSD",
                "2024",
                "MATH101",
                "Calculus I",
                "2022-01-09"
        );

        StudentEnrolmentResultDto dto = new StudentEnrolmentResultDto(
                programmeDto,
                List.of(courseDto)
        );

        // Act
        EntityModel<StudentEnrolmentResultDto> model = assembler.toModel(dto);

        // Assert
        assertNotNull(model);
        assertEquals(dto, model.getContent());

        assertTrue(
                model.getLinks().hasLink("enrolled-course-editions"),
                "Should have 'enrolled-course-editions' link"
        );
        assertTrue(
                model.getLinks().hasLink("enrolled-programme-editions"),
                "Should have 'enrolled-programme-editions' link"
        );

        assertEquals(
                linkTo(methodOn(StudentRestController.class).findEnrolledCourseEditionsForStudent(studentId)).toUri().toString(),
                model.getLink("enrolled-course-editions").get().getHref()
        );

        assertEquals(
                linkTo(methodOn(StudentProgrammeEditionEnrolmentRestController.class).getProgrammeEditionEnrollmentsByStudentID(studentId)).toUri().toString(),
                model.getLink("enrolled-programme-editions").get().getHref()
        );
    }


}