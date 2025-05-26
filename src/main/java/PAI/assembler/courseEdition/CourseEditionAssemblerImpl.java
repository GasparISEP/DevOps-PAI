package PAI.assembler.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import org.springframework.stereotype.Component;

@Component
public class CourseEditionAssemblerImpl implements ICourseEditionAssembler {
    @Override
    public CreateCourseEditionCommand toCommand(CourseEditionRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("CourseEditionRequestDTO cannot be null");
        }
        return new CreateCourseEditionCommand(
                dto.programmeName(),
                dto.programmeAcronym(),
                dto.schoolYearID(),
                dto.courseAcronym(),
                dto.courseName(),
                dto.studyPlanImplementationDate()
        );
    }

    @Override
    public CourseEditionResponseDTO toResponseDTO(CourseEdition courseEdition) {
        if (courseEdition == null) {
            throw new IllegalArgumentException("CourseEdition cannot be null");
        }
        CourseInStudyPlanID cspID = courseEdition.getCourseInStudyPlanID();
        ProgrammeEditionID peID = courseEdition.getProgrammeEditionID();

        ProgrammeID programmeID = peID.getProgrammeID();
        SchoolYearID schoolYearID = peID.getSchoolYearID();

        return new CourseEditionResponseDTO(
                courseEdition.identity().toString(),

                programmeID.getProgrammeName(),
                programmeID.getProgrammeAcronym(),
                schoolYearID.getSchoolYearID(),

                cspID.getCourseID().getCourseAcronymValue(),
                cspID.getCourseID().getCourseNameValue(),
                cspID.getStudyPlanID().getLocalDate()
        );
    }
}

