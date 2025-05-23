package PAI.dto.studentGrade;

public record StudentGradeResponseDTO(
        String _studentGradeId,
        String _courseEditionId,
        String _programmeEditionID,
        String _courseInStudyPlanID,
        String _programmeId,
        String _schoolYearId,
        String _courseId,
        String _studyPlanId,
        int _studentUniqueNumber,
        int _grade,
        String _date
) {}
