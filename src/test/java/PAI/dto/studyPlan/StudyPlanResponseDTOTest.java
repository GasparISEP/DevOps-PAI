package PAI.dto.studyPlan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanResponseDTOTest {

    private String _programmeName;
    private String _programmeAcronym;
    private LocalDate _dateDouble;
    private StudyPlanResponseDTO _studyPlanResponseDTO;
    private int _durationInYearsDouble;
    private int _MaxEtcsDouble;

    @BeforeEach
    void setup() {
        // Arrange
        _programmeName = "Biology";
        _programmeAcronym = "BIO2";
        _dateDouble = mock(LocalDate.class);
        _durationInYearsDouble = 3;
        _MaxEtcsDouble = 30;
        _studyPlanResponseDTO = new StudyPlanResponseDTO(_programmeName, _programmeAcronym,
                _dateDouble, _durationInYearsDouble, _MaxEtcsDouble);
    }

    @Test
    void shouldReturnProgrammeNameWhenGetProgrammeNameIsCalled() {
        // Arrange

        // Act
        String result = _studyPlanResponseDTO.getProgrammeName();

        // Assert
        assertSame(result, _programmeName);
    }

    @Test
    void shouldReturnProgrammeAcronymWhenGetProgrammeAcronymIsCalled() {
        // Arrange

        // Act
        String result = _studyPlanResponseDTO.getProgrammeAcronym();

        // Assert
        assertSame(result, _programmeAcronym);
    }

    @Test
    void shouldReturnStartDateWhenGetStartDateIsCalled() {
        // Arrange

        // Act
        LocalDate result = _studyPlanResponseDTO.getStartDate();

        // Assert
        assertSame(result, _dateDouble);
    }

    @Test
    void shouldReturnDurationInyearsWhenGetDurationInYearsIsCalled() {
        // Arrange

        // Act
        int result = _studyPlanResponseDTO.getDurationInYears();

        // Assert
        assertSame(result, _durationInYearsDouble);
    }

    @Test
    void shouldReturnMaxEtcsWhenGetMaxEtcsIsCalled() {
        //Arrange

        // Act
        int result = _studyPlanResponseDTO.getMaxEcts();

        // Assert
        assertSame(result, _MaxEtcsDouble);
    }
}