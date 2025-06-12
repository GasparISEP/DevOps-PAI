package PAI.assembler.ProgrammeAndCourses;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.dto.ProgrammeAndCourses.ProgrammeEditionEnrolmentDTO;
import PAI.dto.ProgrammeAndCourses.StudentEnrolmentResultDto;
import PAI.dto.ProgrammeAndCourses.StudentProgrammeEnrolmentRequestDto;
import PAI.dto.course.CourseIDDTO;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProgrammeAndCoursesAssembler implements IProgrammeAndCoursesAssembler {

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

    public StudentID toStudentID(StudentProgrammeEnrolmentRequestDto dto) {
        return new StudentID(dto.studentId());
    }

    public ProgrammeEditionID toProgrammeEditionID(StudentProgrammeEnrolmentRequestDto dto) throws Exception {
        Acronym acronym = new Acronym(dto.programmeAcronym());
        ProgrammeID programmeID =  new ProgrammeID(acronym);

        SchoolYearID schoolYearID = new SchoolYearID(UUID.fromString(dto.schoolYearId()));

        return new ProgrammeEditionID(programmeID, schoolYearID);
    }

    public List<CourseID> toCourseIDs(StudentProgrammeEnrolmentRequestDto dto) {
        List<CourseID> courseIDS = new ArrayList<>();
        for (CourseIDDTO courseDTO : dto.courseIds()) {
            Name name = new Name(courseDTO.name());
            Acronym acronym = new Acronym(courseDTO.acronym());
            CourseID courseID = new CourseID(acronym, name);
            courseIDS.add(courseID);
        }
        return courseIDS;
    }
}