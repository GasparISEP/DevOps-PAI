package PAI.assembler.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.dto.studyPlan.StudyPlanResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudyPlanAssemblerImplTest {

    @Test
    void shouldCreateRegisterStudyPlanCommandWhenToCommandIsCalled() {
        // Arrange
        IStudyPlanAssembler assembler = new StudyPlanAssemblerImpl();
        String programmeName = "Biology";
        String programmeAcronym = "BIO1";
        LocalDate startDate = LocalDate.of(2025, 9, 1);

        try (MockedConstruction<NameWithNumbersAndSpecialChars> nameWithSpecialCharsConstructorDouble = mockConstruction(NameWithNumbersAndSpecialChars.class);
             MockedConstruction<Acronym> acronymConstructorDouble = mockConstruction(Acronym.class);
             MockedConstruction<ProgrammeID> programmeIDConstructorDouble = mockConstruction(ProgrammeID.class);
             MockedConstruction<Date> dateConstructorDouble = mockConstruction(Date.class);
             MockedConstruction<RegisterStudyPlanCommand> studyPlanCommandConstructorDouble = mockConstruction(RegisterStudyPlanCommand.class)){

            // Act
            RegisterStudyPlanCommand result = assembler.toCommand(programmeName, programmeAcronym, startDate);

            // Assert
            assertEquals(1, studyPlanCommandConstructorDouble.constructed().size());
            assertEquals(studyPlanCommandConstructorDouble.constructed().get(0), result);
        }
    }

    @Test
    void shouldCreateStudyPlanDTOWhenToDTOIsCalled() {
        // Arrange
        IStudyPlanAssembler assembler = new StudyPlanAssemblerImpl();
        StudyPlan studyPlanDouble = createDoublesDTO();

        try (MockedConstruction<StudyPlanDTO> constructorDouble = mockConstruction(StudyPlanDTO.class)) {
            // Act
            StudyPlanDTO result = assembler.toDTO(studyPlanDouble);

            // Assert
            assertEquals(1, constructorDouble.constructed().size());
            assertSame(constructorDouble.constructed().get(0), result);
        }
    }

    private StudyPlan createDoublesDTO() {
        // Arrange
        StudyPlan studyPlan = mock(StudyPlan.class);
        String programmeName = "Biology";
        String programmeAcronym = "BIO1";
        LocalDate date = LocalDate.of(2025, 9, 1);
        int maxEcts = 30;
        int durationInYears = 3;
        ProgrammeID programmeId = mock(ProgrammeID.class);
        Date startDate = mock(Date.class);
        MaxEcts ects = mock(MaxEcts.class);
        DurationInYears duration = mock(DurationInYears.class);

        when(studyPlan.getProgrammeID()).thenReturn(programmeId);
        when(studyPlan.getStartDate()).thenReturn(startDate);
        when(studyPlan.getMaxEcts()).thenReturn(ects);
        when(studyPlan.getDurationInYears()).thenReturn(duration);
        when(programmeId.getProgrammeName()).thenReturn(programmeName);
        when(programmeId.getProgrammeAcronym()).thenReturn(programmeAcronym);
        when(startDate.getLocalDate()).thenReturn(date);
        when(ects.getMaxEcts()).thenReturn(maxEcts);
        when(duration.getDurationInYears()).thenReturn(durationInYears);

        return studyPlan;
    }

    @Test
    void shouldCreateStudyPlanResponseDTOWhenToResponseDTOIsCalled() {
        // Arrange
        IStudyPlanAssembler assembler = new StudyPlanAssemblerImpl();
        StudyPlanDTO studyPlanDouble = createDoublesResponseDTO();

        try (MockedConstruction<StudyPlanResponseDTO> constructorDouble = mockConstruction(StudyPlanResponseDTO.class)) {
            // Act
            StudyPlanResponseDTO result = assembler.toResponseDTO(studyPlanDouble);

            // Assert
            assertEquals(1, constructorDouble.constructed().size());
            assertSame(constructorDouble.constructed().get(0), result);
        }
    }

    private StudyPlanDTO createDoublesResponseDTO() {
        // Assert
        StudyPlanDTO studyPlanDto = mock(StudyPlanDTO.class);
        String programmeName = "Biology";
        String programmeAcronym = "BIO1";
        LocalDate startDate = LocalDate.of(2025, 9, 1);
        int maxEcts = 30;
        int durationInYears = 3;

        when(studyPlanDto.getProgrammeName()).thenReturn(programmeName);
        when(studyPlanDto.getProgrammeAcronym()).thenReturn(programmeAcronym);
        when(studyPlanDto.getStartDate()).thenReturn(startDate);
        when(studyPlanDto.getDurationInYears()).thenReturn(durationInYears);
        when(studyPlanDto.getMaxEcts()).thenReturn(maxEcts);

        return studyPlanDto;
    }
}