package PAI.assembler.courseEdition;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseEditionServiceAssemblerImpl implements ICourseEditionServiceAssembler {

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
