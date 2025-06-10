package PAI.dto.studyPlan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanResponseDTOTest {

    private String _programmeName;
    private String _programmeAcronym;
    private LocalDate _dateDouble;
    private StudyPlanResponseDTO _studyPlanResponseDTO;
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

        _studyPlanResponseDTO = new StudyPlanResponseDTO(_programmeAcronym,
                _dateDouble, _durationInYearsDouble, _MaxEtcsDouble, _uuid);
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

    @Test
    void shouldReturnUUIDWhenGetUUIDIsCalled() {
        //Arrange

        // Act
        UUID result = _studyPlanResponseDTO.getUUID();

        // Assert
        assertEquals(result, _uuid);
    }
}