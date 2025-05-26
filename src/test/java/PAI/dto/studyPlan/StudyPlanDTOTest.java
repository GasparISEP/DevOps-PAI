package PAI.dto.studyPlan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanDTOTest {

    private String _programmeName;
    private String _programmeAcronym;
    private LocalDate _dateDouble;
    private StudyPlanDTO _studyPlanDTO;
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
        _studyPlanDTO = new StudyPlanDTO(_programmeName, _programmeAcronym,
                                        _dateDouble, _durationInYearsDouble, _MaxEtcsDouble);
    }

    @Test
    void shouldReturnProgrammeNameWhenGetProgrammeNameIsCalled() {
        // Arrange

        // Act
        String result = _studyPlanDTO.getProgrammeName();

        // Assert
        assertSame(result, _programmeName);
    }

    @Test
    void shouldReturnProgrammeAcronymWhenGetProgrammeAcronymIsCalled() {
        // Arrange

        // Act
        String result = _studyPlanDTO.getProgrammeAcronym();

        // Assert
        assertSame(result, _programmeAcronym);
    }

    @Test
    void shouldReturnStartDateWhenGetStartDateIsCalled() {
        // Arrange

        // Act
        LocalDate result = _studyPlanDTO.getStartDate();

        // Assert
        assertSame(result, _dateDouble);
    }

    @Test
    void shouldReturnDurationInyearsWhenGetDurationInYearsIsCalled() {
        // Arrange

        // Act
        int result = _studyPlanDTO.getDurationInYears();

        // Assert
        assertSame(result, _durationInYearsDouble);
    }

    @Test
    void shouldReturnMaxEtcsWhenGetMaxEtcsIsCalled() {
        //Arrange

        // Act
        int result = _studyPlanDTO.getMaxEcts();

        // Assert
        assertSame(result, _MaxEtcsDouble);
    }
}