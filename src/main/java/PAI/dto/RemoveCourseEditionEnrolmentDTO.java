package PAI.dto;
import java.util.UUID;

public record RemoveCourseEditionEnrolmentDTO(
        //ToDO Switch for CourseEditionIdDTO, colocar verificações
        //ProgrammeEditionID
        String programmeName,
        String programmeAcronym,
        UUID schoolYearId,
        //CourseInStudyPlan
        String courseAcronym,
        String courseName,
        String studyPlanProgrammeName,
        String studyPlanProgrammeAcronym,
        String studyPlanProgrammeDate,
        //StudentId
        int studentID
) {}
