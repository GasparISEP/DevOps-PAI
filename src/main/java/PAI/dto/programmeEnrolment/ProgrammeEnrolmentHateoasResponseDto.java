package PAI.dto.programmeEnrolment;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ProgrammeEnrolmentHateoasResponseDto(
        @NotNull
        UUID programmeEnrolmentGID,

        @NotNull
        String programmeId,

        @NotNull
        int studentId,

        @NotNull
        String accessMethodID,

        @NotNull
        LocalDate date,

        String programmeName,
        String studentName
        ){}