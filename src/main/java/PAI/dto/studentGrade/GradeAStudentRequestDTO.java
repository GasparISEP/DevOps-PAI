package PAI.dto.studentGrade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record GradeAStudentRequestDTO(

        @Positive(message = "Grade must be a positive number.")
        double grade,

        @NotBlank(message = "Date is required.")
        String date,

        @NotBlank(message = "Student ID is required.")
        String studentID,

        @NotBlank(message = "Course Edition ID is required.")
        String courseEditionID

) {}