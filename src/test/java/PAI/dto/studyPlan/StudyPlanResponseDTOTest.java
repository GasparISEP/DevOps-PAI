package PAI.dto.studyPlan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanResponseDTOTest {

    private LocalDate _dateDouble;
    private StudyPlanResponseDTO _studyPlanResponseDTO;
    private int _durationInYearsDouble;
    private int _quantityOfEtcsDouble;

    @BeforeEach
    void setup(){
        // Arrange
        _dateDouble = mock(LocalDate.class);
        _durationInYearsDouble = 3;
        _quantityOfEtcsDouble = 30;
        _studyPlanResponseDTO = new StudyPlanResponseDTO(_dateDouble, _durationInYearsDouble, _quantityOfEtcsDouble);
    }

    @Test
    void shouldReturnStartDateWhenGetStartDateIsCalled(){
        // Arrange

        // Act
        LocalDate result = _studyPlanResponseDTO.getStartDate();

        // Assert
        assertEquals(result, _dateDouble);
    }

    @Test
    void shouldReturnDurationInyearsWhenGetDurationInYearsIsCalled(){
        // Arrange

        // Act
        int result = _studyPlanResponseDTO.getDurationInYears();

        // Assert
        assertEquals(result, _durationInYearsDouble);
    }

    @Test
    void shouldReturnQuantityOfEtcsWhenGetQuantityOfEtcsIsCalled(){
        //Arrange

        // Act
        int result = _studyPlanResponseDTO.getQuantityOfEtcs();

        // Assert
        assertEquals(result, _quantityOfEtcsDouble);

    }
}