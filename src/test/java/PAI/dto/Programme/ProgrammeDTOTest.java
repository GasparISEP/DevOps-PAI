package PAI.dto.Programme;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeDTOTest {

    private Validator validator;

    static class ProgrammeTestData {
        String name = "Engenharia Informática";
        String acronym = "EIN";
        int maxECTS = 30;
        int quantSemesters = 6;
        String degreeTypeID = "123e4567-e89b-12d3-a456-426614174000";
        String departmentID = "DEI";
        String teacherID = "NPS";
    }

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldCreateProgrammeDTOWithValidValues() {
        ProgrammeTestData data = new ProgrammeTestData();

        ProgrammeDTO programmeDTO = new ProgrammeDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        assertEquals(data.name, programmeDTO.name());
        assertEquals(data.acronym, programmeDTO.acronym());
        assertEquals(data.maxECTS, programmeDTO.maxECTS());
        assertEquals(data.quantSemesters, programmeDTO.quantSemesters());
        assertEquals(data.degreeTypeID, programmeDTO.degreeTypeID());
        assertEquals(data.departmentID, programmeDTO.departmentID());
        assertEquals(data.teacherID, programmeDTO.teacherID());
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        ProgrammeTestData data = new ProgrammeTestData();
        ProgrammeDTO programmeDTO = new ProgrammeDTO(data.name, data.acronym, data.maxECTS, data.quantSemesters, data.degreeTypeID, data.departmentID, data.teacherID);

        Set<ConstraintViolation<ProgrammeDTO>> violations = validator.validate(programmeDTO);
        assertTrue(violations.isEmpty());
    }

    static Stream<Arguments> invalidFields() {
        return Stream.of(
                Arguments.of("", "EIN", 30, 6, "123e4567-e89b-12d3-a456-426614174000", "DEI", "NPS", "Name is required"),
                Arguments.of("Engenharia Informática", "", 30, 6, "123e4567-e89b-12d3-a456-426614174000", "DEI", "NPS", "Acronym is required"),
                Arguments.of("Engenharia Informática", "EIN", 0, 6, "123e4567-e89b-12d3-a456-426614174000", "DEI", "NPS", "maxECTS must be greater than 0"),
                Arguments.of("Engenharia Informática", "EIN", 30, 0, "123e4567-e89b-12d3-a456-426614174000", "DEI", "NPS", "quantSemesters must be greater than 0"),
                Arguments.of("Engenharia Informática", "EIN", 30, 6, "", "DEI", "NPS", "Degree Type ID is required"),
                Arguments.of("Engenharia Informática", "EIN", 30, 6, "123e4567-e89b-12d3-a456-426614174000", "", "NPS", "Department ID is required"),
                Arguments.of("Engenharia Informática", "EIN", 30, 6, "123e4567-e89b-12d3-a456-426614174000", "DEI", "", "Teacher ID is required")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void shouldFailValidationWithExpectedMessage(String name, String acronym, int maxECTS, int quantSemesters, String degreeTypeID, String departmentID, String teacherID, String expectedMessage) {

        ProgrammeDTO programmeDTO = new ProgrammeDTO(name, acronym, maxECTS, quantSemesters, degreeTypeID, departmentID, teacherID);
        Set<ConstraintViolation<ProgrammeDTO>> violations = validator.validate(programmeDTO);

        String actualMessage = violations.iterator().next().getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldFailValidationWhenAllFieldsAreInvalid() {
        ProgrammeDTO programmeDTO = new ProgrammeDTO("", "", 0, 0, "", "", "");

        Set<ConstraintViolation<ProgrammeDTO>> violations = validator.validate(programmeDTO);

        assertEquals(7, violations.size());
    }


}