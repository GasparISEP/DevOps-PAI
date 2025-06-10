package PAI.dto.studyPlan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanDTOTest {

    private String _programmeAcronym;
    private LocalDate _dateDouble;
    private StudyPlanDTO _studyPlanDTO;
    private int _durationInYearsDouble;
    private int _MaxEtcsDouble;
    private UUID _uuid;

    @BeforeEach
    void setup() {
        // Arrange
        _programmeAcronym = "BIO2";
        _dateDouble = mock(LocalDate.class);
        _durationInYearsDouble = 3;
        _MaxEtcsDouble = 30;
        _uuid = UUID.randomUUID();

        _studyPlanDTO = new StudyPlanDTO(_programmeAcronym,
                                        _dateDouble, _durationInYearsDouble, _MaxEtcsDouble, _uuid);
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

    @Test
    void shouldReturnUUIDWhenGetUUIDIsCalled() {
        //Arrange

        // Act
        UUID result = _studyPlanDTO.getUUID();

        // Assert
        assertEquals(result, _uuid);
    }
}