package PAI.dto.programmeEditionEnrolment;

import jakarta.validation.constraints.NotBlank;

public record ProgrammeEditionEnrolmentResponseDto(
    @NotBlank
    int studentID,

    @NotBlank
    String programmeName,

    @NotBlank
    String programmeAcronym,

    @NotBlank
    String schoolYearDescription,

    @NotBlank
    String schoolYearID

    ) {


}
