package PAI.dto.courseEdition;

import PAI.VOs.*;



public record CreateCourseEditionCommand(
        Acronym programmeAcronym,
        SchoolYearID schoolYearID,
        Acronym courseAcronym,
        Name courseName,
        Date studyPlanImplementationDate
) {
}
