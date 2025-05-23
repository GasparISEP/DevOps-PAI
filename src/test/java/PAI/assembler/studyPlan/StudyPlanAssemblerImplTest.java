package PAI.assembler.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.MaxEcts;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudyPlanAssemblerImplTest {

    private StudyPlan mockStudyPlan(LocalDate date, int ectsValue, int durationValue) {
        StudyPlan studyPlan = mock(StudyPlan.class);
        Date startDate = mock(Date.class);
        MaxEcts ects = mock(MaxEcts.class);
        DurationInYears duration = mock(DurationInYears.class);

        when(studyPlan.getStartDate()).thenReturn(startDate);
        when(studyPlan.getQuantityOfEcts()).thenReturn(ects);
        when(studyPlan.getDurationInYears()).thenReturn(duration);
        when(startDate.getLocalDate()).thenReturn(date);
        when(ects.getMaxEcts()).thenReturn(ectsValue);
        when(duration.getDurationInYears()).thenReturn(durationValue);

        return studyPlan;
    }

    @Test
    void shouldCreateStudyPlanResponseDTOWhenToDTOIsCalled(){
        // Arrange
        IStudyPlanAssembler assembler = new StudyPlanAssemblerImpl();
        LocalDate startDate = LocalDate.of(2025, 9, 1);
        int quantityOfEtcs = 30;
        int durationInYears = 3;
        StudyPlan studyPlanDouble = mockStudyPlan(startDate, quantityOfEtcs, durationInYears);

        try(MockedConstruction<StudyPlanResponseDTO> constructerDouble = mockConstruction(StudyPlanResponseDTO.class)){
            // Act
            StudyPlanResponseDTO result = assembler.toDTO(studyPlanDouble);

            // Assert
            assertEquals(1, constructerDouble.constructed().size());
            assertSame(constructerDouble.constructed().get(0), result);
        }
    }
}