package PAI.controllerRest;

import PAI.VOs.*;
import PAI.VOs.Date;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class SchoolYearRestControllerTest {

    @Test
    void shouldCreateASYRestController() {
        //Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);

        //Act
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        //Assert
        assertNotNull(syRestController);

    }

    @Test
    void ReturnsBadRequestWhenDTOisNull() {
        //Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController schoolYearRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        //Act
        ResponseEntity<SchoolYearDTO> resp = schoolYearRestController.createASchoolYear(null);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void ReturnsCreatedWithBody1whenServiceReturnsSchoolYear() throws Exception {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        SchoolYearDTO dto = mock(SchoolYearDTO.class);

        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        when(iSYMapperDTO.toDescription(dto)).thenReturn(description);
        when(iSYMapperDTO.toEndDate(dto)).thenReturn(endDate);
        when(iSYMapperDTO.toStartDate(dto)).thenReturn(startDate);

        SchoolYear schoolYear = mock(SchoolYear.class);
        when(iSYService.addSchoolYear(description,startDate,endDate)).thenReturn(schoolYear);

        when(iSYMapperDTO.toDTO(schoolYear)).thenReturn(dto);

        // Act
        ResponseEntity<SchoolYearDTO> resp = syRestController.createASchoolYear(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());

    }

    @Test
    void ReturnsBadRequestWhenServiceReturnsNull() throws Exception {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        when(iSYService.addSchoolYear(any(), any(), any())).thenReturn(null);
        SchoolYearDTO dto = mock(SchoolYearDTO.class);

        // Act
        ResponseEntity<SchoolYearDTO> resp = syRestController.createASchoolYear(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void ReturnsBadRequestwhenAnyExceptionThrown() {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        SchoolYearDTO dto = mock(SchoolYearDTO.class);
        when(iSYMapperDTO.toDescription(dto)).thenThrow(new RuntimeException("Invalid"));

        // Act
        ResponseEntity<SchoolYearDTO> resp = syRestController.createASchoolYear(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void shouldReturnAListOfSchoolYears () {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        SchoolYearDTO dto1 = mock(SchoolYearDTO.class);
        SchoolYearDTO dto2 = mock(SchoolYearDTO.class);
        SchoolYearDTO dto3 = mock(SchoolYearDTO.class);
        List<SchoolYearDTO> schoolYearDTOS = List.of(dto1, dto2, dto3);
        when(iSYService.getAllSchoolYears()).thenReturn(schoolYearDTOS);

        // Act
        ResponseEntity<?> response = syRestController.getAllSchoolYears();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(schoolYearDTOS, response.getBody());
        assertTrue(((Iterable<?>) response.getBody()).iterator().hasNext());
        verify(iSYService).getAllSchoolYears();
        assertTrue(((Collection<?>) response.getBody()).contains(dto2));
    }

    @Test
    void shouldReturnAnEmptyListOfSchoolYearsIfThereAreNoSchoolYearsInTheSystem () {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        List<SchoolYearDTO> schoolYearDTOS = List.of();
        when(iSYService.getAllSchoolYears()).thenReturn(schoolYearDTOS);

        // Act
        ResponseEntity<?> response = syRestController.getAllSchoolYears();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(schoolYearDTOS, response.getBody());
        assertFalse(((Iterable<?>) response.getBody()).iterator().hasNext());
        verify(iSYService).getAllSchoolYears();
    }
}