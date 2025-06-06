package PAI.dto.programmeEditionEnrolment;

import jakarta.validation.constraints.NotBlank;

public record ProgrammeEditionEnrolmentDetailDto(
    @NotBlank
    int studentID,

    @NotBlank
    String programmeAcronym,

    @NotBlank
    String schoolYearDescription,

    @NotBlank
    String schoolYearID

    ) {


}
