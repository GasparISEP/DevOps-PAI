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
        CourseInStudyPlanID cspID = courseEdition.getCourseInStudyPlanID();
        ProgrammeEditionID peID = courseEdition.getProgrammeEditionID();

        ProgrammeID programmeID = peID.getProgrammeID();
        SchoolYearID schoolYearID = peID.getSchoolYearID();

        return new CourseEditionResponseDTO(
                courseEdition.identity().toString(),

                programmeID.getProgrammeName().toString(),
                programmeID.getProgrammeAcronym().toString(),
                schoolYearID.getSchoolYearID(),

                cspID.getCourseID().getAcronym().toString(),
                cspID.getCourseID().getName().toString(),
                cspID.getStudyPlanID().getLocalDate()
        );
    }
}

