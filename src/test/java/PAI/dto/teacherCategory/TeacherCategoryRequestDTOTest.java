package PAI.dto.teacherCategory;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryRequestDTOTest {

    @Test
    void shouldCreateTeacherCategoryRequestDTOCorrectly() {
        // Arrange
        String name = "Assistant Professor";

        // Act
        TeacherCategoryRequestDTO teacherCategoryRequestDTO = new TeacherCategoryRequestDTO (name);

        // Assert
        assertEquals(name, teacherCategoryRequestDTO.name());
    }

    @Test
    void shouldHandleNullId() {
        // Arrange
        String name = null;

        // Act
        TeacherCategoryRequestDTO teacherCategoryRequestDTO = new TeacherCategoryRequestDTO (name);

        // Assert
        assertEquals(null, teacherCategoryRequestDTO.name());
    }

    @Test
    void shouldFailTheValidationWhenNameIsNull() {
        //arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //act
        TeacherCategoryRequestDTO dto = new TeacherCategoryRequestDTO(null);
        Set<ConstraintViolation<TeacherCategoryRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The name field is required!")));
    }

    @Test
    void shouldFailTheValidationWhenNameIsEmpty() {
        //arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //act
        TeacherCategoryRequestDTO dto = new TeacherCategoryRequestDTO(" ");
        Set<ConstraintViolation<TeacherCategoryRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The name field cannot be blank!")));
    }

    @Test
    void shouldFailTheValidationWhenNameHasLessThanThreeCharacters() {
        //arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //act
        TeacherCategoryRequestDTO dto = new TeacherCategoryRequestDTO("12");
        Set<ConstraintViolation<TeacherCategoryRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The name must be between 3 and 255 characters long!")));
    }

    @Test
    void shouldFailTheValidationWhenNameHasMoreThanTwoHundredAndFiftyFiveCharacters() {
        //arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //act
        TeacherCategoryRequestDTO dto = new TeacherCategoryRequestDTO("a".repeat(256));
        Set<ConstraintViolation<TeacherCategoryRequestDTO>> violations = validator.validate(dto);

        //assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("The name must be between 3 and 255 characters long!")));
    }
}