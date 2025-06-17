package PAI.dto.programmeEnrolment;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentHateoasResponseDtoTest {


    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDTO() {
        ProgrammeEnrolmentHateoasResponseDto dto = new ProgrammeEnrolmentHateoasResponseDto(
                UUID.randomUUID(),
                "CSD",
                12345,
                "ACCESS_TEST",
                LocalDate.of(2023, 9, 1),
                "Engenharia Informática",
                "Gaspar F."
        );

        Set<ConstraintViolation<ProgrammeEnrolmentHateoasResponseDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Deveria ser válido");
    }

    @Test
    void testNullableOptionalFields() {
        ProgrammeEnrolmentHateoasResponseDto dto = new ProgrammeEnrolmentHateoasResponseDto(
                UUID.randomUUID(),
                "CSD",
                12345,
                "ACCESS_DIRECT",
                LocalDate.of(2023, 9, 1),
                null,
                null
        );

        Set<ConstraintViolation<ProgrammeEnrolmentHateoasResponseDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Campos opcionais nulos não devem causar erros");
    }

    @Test
    void testMissingRequiredFields() {
        ProgrammeEnrolmentHateoasResponseDto dto = new ProgrammeEnrolmentHateoasResponseDto(
                null,
                null,
                0,
                null,
                null,
                null,
                null
        );

        Set<ConstraintViolation<ProgrammeEnrolmentHateoasResponseDto>> violations = validator.validate(dto);

        assertEquals(4, violations.size()); // Apenas 4 campos são @NotNull
    }

}