package PAI.assembler.courseEdition;

import PAI.controllerRest.CourseEditionRestController;
import PAI.controllerRest.SchoolYearRestController;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CourseEditionHATEOASAssembler implements ICourseEditionHATEOASAssembler {

    @Override
    public EntityModel<CourseEditionResponseDTO> toModel(CourseEditionResponseDTO dto) {
        EntityModel<CourseEditionResponseDTO> courseEditionResponseDTOEntityModel;
        List<Link> iterable = new ArrayList<>();

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .getCourseEditionById(dto.courseEditionGeneratedID())).
                    withSelfRel();

            iterable.add(link);

        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .createCourseEdition(null))
                    .withRel("create-course-edition");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                            .getCourseEditionAverageGrade(dto.programmeAcronym(),
                                    dto.schoolYearID().toString(),
                                    dto.courseAcronym(),
                                    dto.courseName(),
                                    dto.studyPlanImplementationDate().toString()))
                            .withRel("average-grade");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .getNumberOfStudentsInCourseEdition(dto.courseEditionGeneratedID()))
                    .withRel("Number-of-students");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .getCourseEditionApprovalRate(
                            dto.programmeAcronym(),
                            dto.schoolYearID().toString(),
                            dto.courseAcronym(),
                            dto.courseName(),
                            null))
                    .withRel("approval-rate");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = Link.of("/students/{studentId}/courses-edition-enrolments")
                    .withRel("enrol-student-in-course-edition");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .removeStudentEnrolmentFromACourseEdition(null))
                    .withRel("remove-student-enrolment");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .defineRucForCourseEdition(dto.courseEditionGeneratedID(), null))
                    .withRel("define-ruc");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(CourseEditionRestController.class)
                    .gradeAStudent(null)).withRel("grade-student");

            iterable.add(link);
        } catch (Exception ignored) {}

        try {
            Link link = linkTo(methodOn(SchoolYearRestController.class)
                            .getSchoolYearByID(dto.schoolYearID().toString()))
                            .withRel("school-year");

            iterable.add(link);
        } catch (Exception e){}

        courseEditionResponseDTOEntityModel = EntityModel.of(dto, iterable);

        return courseEditionResponseDTOEntityModel;
    }
}
