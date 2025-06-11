package PAI.assembler.ProgrammeAndCourses;

import PAI.VOs.US34Response;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.dto.ProgrammeAndCourses.ProgrammeEditionEnrolmentDTO;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeAndCoursesAssembler {

    public StudentEnrolmentResultDto toDto(US34Response domain) {
        ProgrammeEditionEnrolment programmeEditionEnrolment = domain.progEditionEnrolment();

        ProgrammeEditionEnrolmentDTO programmeDTO = new ProgrammeEditionEnrolmentDTO(
                programmeEditionEnrolment.findStudentInProgrammeEdition().getUniqueNumber(),
                programmeEditionEnrolment.findProgrammeEditionInEnrolment().getProgrammeID().getProgrammeAcronym(),
                programmeEditionEnrolment.findProgrammeEditionInEnrolment().getSchoolYearID().toString());


        List<CourseEditionEnrolmentDto> courseDTOs = new ArrayList<>();
        for (CourseEditionEnrolment cee : domain.listCourseEditionEnrolment()) {
            courseDTOs.add(new CourseEditionEnrolmentDto(
                    cee.knowStudent().getUniqueNumber(),
                    cee.knowCourseEdition().getProgrammeEditionID().getProgrammeID().getProgrammeAcronym(),
                    cee.knowCourseEdition().getProgrammeEditionID().getSchoolYearID().toString(),
                    cee.knowCourseEdition().getCourseInStudyPlanID().getCourseID().getCourseAcronymValue(),
                    cee.knowCourseEdition().getCourseInStudyPlanID().getStudyPlanID().getDate().toString(),
                    cee.knowCourseEdition().getCourseInStudyPlanID().getCourseID().getCourseNameValue()
            ));
        }

        return new StudentEnrolmentResultDto(programmeDTO, courseDTOs);
    }

}
