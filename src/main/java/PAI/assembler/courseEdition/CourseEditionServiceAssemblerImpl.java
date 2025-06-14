package PAI.assembler.courseEdition;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CourseEditionServiceResponseDTO;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class CourseEditionServiceAssemblerImpl implements ICourseEditionServiceAssembler {

    @Override
    public CourseEditionServiceResponseDTO toServiceResponseDTO(CourseEdition courseEdition) {
        if (courseEdition == null) {
            throw new IllegalArgumentException("CourseEdition cannot be null");
        }
        UUID generatedId = courseEdition.getCourseEditionGeneratedID().getCourseEditionGeneratedID();
        CourseInStudyPlanID cspID = courseEdition.getCourseInStudyPlanID();
        ProgrammeEditionID peID = courseEdition.getProgrammeEditionID();

        ProgrammeID programmeID = peID.getProgrammeID();
        SchoolYearID schoolYearID = peID.getSchoolYearID();

        String formattedID = URLEncoder.encode(
                programmeID.getProgrammeAcronym() + "-" +
                        schoolYearID.getSchoolYearID() + "_" +
                        cspID.getCourseID().getCourseAcronymValue() + "-" +
                        cspID.getCourseID().getCourseNameValue() + "-" +
                        cspID.getStudyPlanID().getProgrammeID().getProgrammeAcronym() + "-" +
                        cspID.getStudyPlanID().getLocalDate().toString(), // yyyy-MM-dd
                StandardCharsets.UTF_8
        );

        return new CourseEditionServiceResponseDTO(
                generatedId,
                programmeID.getProgrammeAcronym(),
                schoolYearID.getSchoolYearID(),
                cspID.getCourseID().getCourseAcronymValue(),
                cspID.getCourseID().getCourseNameValue(),
                cspID.getStudyPlanID().getLocalDate(),
                formattedID
        );
    }
}
