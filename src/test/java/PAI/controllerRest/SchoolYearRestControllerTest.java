package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrown() {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        String errorMessage = "Invalid input data";

        when(iSYService.getAllSchoolYears()).thenThrow(new IllegalArgumentException(errorMessage));

        // Act
        ResponseEntity<?> response = syRestController.getAllSchoolYears();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        assertEquals(errorMessage, response.getBody());
    }

    @Test
    void shouldReturnInternalServerErrorWhenUnexpectedExceptionIsThrown() {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        when(iSYService.getAllSchoolYears()).thenThrow(new RuntimeException("Database is down"));

        // Act
        ResponseEntity<?> response = syRestController.getAllSchoolYears();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCodeValue());
        assertEquals("Unexpected error occurred", response.getBody());
    }

    @Test
    void shouldReturnCurrentSchoolYearDTO() {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);
        CurrentSchoolYearDTO currentSchoolYearDTO = mock(CurrentSchoolYearDTO.class);
        CurrentSchoolYearResponseDTO currentSchoolYearResponseDTO = mock(CurrentSchoolYearResponseDTO.class);
        when(iSYService.getCurrentSchoolYear()).thenReturn(Optional.of(currentSchoolYearDTO));
        when(iSYMapperDTO.toResponseDTO(currentSchoolYearDTO)).thenReturn(currentSchoolYearResponseDTO);

        // Act
        ResponseEntity<?> response = syRestController.getCurrentSchoolYear();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldReturnNotFoundIfNoCurrentSchoolYearDTO() {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService);

        when(iSYService.getCurrentSchoolYear()).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = syRestController.getCurrentSchoolYear();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No current School Year", response.getBody());
    }

    @Test
    void shouldReturnSchoolYearByIDWithDTO() {
        //arrange
        ISchoolYearAssembler schoolYearAssembler = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService);

        String id = "550e8400-e29b-41d4-a716-446655440000";
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        SchoolYearDTO schoolYearDTO = mock(SchoolYearDTO.class);

        when(schoolYearAssembler.fromStringToSchoolYearID(id)).thenReturn(schoolYearID);
        when(iSYService.getSchoolYearByID(schoolYearID)).thenReturn(Optional.of(schoolYear));
        when(schoolYearAssembler.toDTO(schoolYear)).thenReturn(schoolYearDTO);

        //act
        ResponseEntity<?> response = syRestController.getSchoolYearByID(id);

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturn404WhenSchoolYearNotFound() {
        // Arrange
        ISchoolYearAssembler schoolYearAssembler = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService);

        String id = "550e8400-e29b-41d4-a716-446655440000";
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(schoolYearAssembler.fromStringToSchoolYearID(id)).thenReturn(schoolYearID);
        when(iSYService.getSchoolYearByID(schoolYearID)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = syRestController.getSchoolYearByID(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenIllegalArgumentExceptionIsThrownGetSchoolYearByID() {
        // Arrange
        ISchoolYearAssembler schoolYearAssembler = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService);

        String invalidId = "550e8400-e29b-41d4-a716-446655440000";

        when(schoolYearAssembler.fromStringToSchoolYearID(invalidId))
                .thenThrow(new IllegalArgumentException("Invalid SchoolYear ID"));

        // Act
        ResponseEntity<?> response = syRestController.getSchoolYearByID(invalidId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn500WhenUnexpectedExceptionOccurs() {
        // Arrange
        String id = "550e8400-e29b-41d4-a716-446655440000";
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ISchoolYearAssembler schoolYearAssembler = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService);

        when(schoolYearAssembler.fromStringToSchoolYearID(id)).thenReturn(schoolYearID);
        when(iSYService.getSchoolYearByID(schoolYearID))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<?> response = syRestController.getSchoolYearByID(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}