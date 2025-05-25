package PAI.dto.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RegisterStudyPlanCommandTest {

    private ProgrammeID _programmeId;
    private Date _startDate;
    private RegisterStudyPlanCommand _studyPlanCommand;

    // Arrange
    private void createDoubles(){
        _programmeId = mock(ProgrammeID.class);
        _startDate = mock(Date.class);
        _studyPlanCommand = new RegisterStudyPlanCommand(_programmeId, _startDate);
    }

    @Test
    void shouldReturnProgrammeIDWhenGetProgrammeIdIsCalled(){
        // Arrange
        createDoubles();
        // Act
        ProgrammeID result = _studyPlanCommand.getProgrammeId();

        // Assert
        assertSame(result, _programmeId);
    }

    @Test
    void shouldReturnStartDateWhenGetStartDateIsCalled(){
        // Arrange
        createDoubles();
        // Act
        Date result = _studyPlanCommand.getStartDate();

        // Assert
        assertSame(result, _startDate);
    }
}