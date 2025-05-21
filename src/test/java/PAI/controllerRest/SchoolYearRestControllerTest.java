package PAI.controllerRest;

import PAI.dto.SchoolYear.ISchoolYearMapperDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SchoolYearRestControllerTest {

    @Test
    void shouldCreateASYRestController() {
        //Arrange
        ISchoolYearMapperDTO iSYMapperDTO = mock(ISchoolYearMapperDTO.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);

        //Act
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        //Assert
        assertNotNull(syRestController);

    }

}