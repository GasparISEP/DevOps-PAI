package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.schoolYear.ISchoolYearHateoasAssembler;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.department.DepartmentDTO;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearIDDescriptionResponseDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);

        //Act
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

        //Assert
        assertNotNull(syRestController);

    }

    @Test
    void ReturnsBadRequestWhenDTOisNull() {
        //Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController schoolYearRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

        //Act
        ResponseEntity<?> resp = schoolYearRestController.createASchoolYear(null);

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void ReturnsCreatedWithBody1whenServiceReturnsSchoolYear() throws Exception {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

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
        ResponseEntity<?> resp = syRestController.createASchoolYear(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());

    }

    @Test
    void ReturnsBadRequestWhenServiceReturnsNull() throws Exception {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

        when(iSYService.addSchoolYear(any(), any(), any())).thenReturn(null);
        SchoolYearDTO dto = mock(SchoolYearDTO.class);

        // Act
        ResponseEntity<?> resp = syRestController.createASchoolYear(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void ReturnsBadRequestwhenAnyExceptionThrown() {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

        SchoolYearDTO dto = mock(SchoolYearDTO.class);
        when(iSYMapperDTO.toDescription(dto)).thenThrow(new RuntimeException("Invalid"));

        // Act
        ResponseEntity<?> resp = syRestController.createASchoolYear(dto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
    }

    @Test
    void shouldReturnAListOfSchoolYears () {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

        CurrentSchoolYearDTO dto1 = mock(CurrentSchoolYearDTO.class);
        CurrentSchoolYearDTO dto2 = mock(CurrentSchoolYearDTO.class);
        CurrentSchoolYearDTO dto3 = mock(CurrentSchoolYearDTO.class);

        List<CurrentSchoolYearDTO> schoolYearDTOS = List.of(dto1, dto2, dto3);

        when(iSYService.getAllSchoolYears()).thenReturn(schoolYearDTOS);

        EntityModel<CurrentSchoolYearDTO> em1 = mock(EntityModel.class);
        EntityModel<CurrentSchoolYearDTO> em2 = mock(EntityModel.class);
        EntityModel<CurrentSchoolYearDTO> em3 = mock(EntityModel.class);

        List<EntityModel<CurrentSchoolYearDTO>> entityModels = List.of(em1, em2, em3);

        CollectionModel<EntityModel<CurrentSchoolYearDTO>> collectionModel = mock(CollectionModel.class);
        when(collectionModel.getContent()).thenReturn(entityModels);
        when(collectionModel.iterator()).thenReturn(entityModels.iterator());

        when(iSYHateoas.CollectionModel(schoolYearDTOS)).thenReturn(collectionModel);

        // Act
        ResponseEntity<?> response = syRestController.getAllSchoolYears();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(collectionModel, response.getBody());
        CollectionModel<EntityModel<CurrentSchoolYearDTO>> body = (CollectionModel<EntityModel<CurrentSchoolYearDTO>>) response.getBody();
        assertTrue(body.iterator().hasNext());
        assertEquals(3, ((Collection<?>) body.getContent()).size());
        verify(iSYService).getAllSchoolYears();
        verify(iSYHateoas).CollectionModel(schoolYearDTOS);
    }

    @Test
    void shouldReturnAnEmptyListOfSchoolYearsIfThereAreNoSchoolYearsInTheSystem () {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

        List<CurrentSchoolYearDTO> schoolYearDTOS = List.of();
        when(iSYService.getAllSchoolYears()).thenReturn(schoolYearDTOS);

        CollectionModel<EntityModel<CurrentSchoolYearDTO>> collectionModel = mock(CollectionModel.class);
        when(iSYHateoas.CollectionModel(schoolYearDTOS)).thenReturn(collectionModel);

        // Act
        ResponseEntity<?> response = syRestController.getAllSchoolYears();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(collectionModel, response.getBody());
        Collection<?> content = ((CollectionModel<?>) response.getBody()).getContent();
        assertTrue(content.isEmpty());
        verify(iSYService).getAllSchoolYears();
        verify(iSYHateoas).CollectionModel(schoolYearDTOS);
    }

    @Test
    void shouldReturnBadRequestWhenIllegalArgumentExceptionIsThrown() {
        // Arrange
        ISchoolYearAssembler iSYMapperDTO = mock(ISchoolYearAssembler.class);
        ISchoolYearService iSYService = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);
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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(iSYMapperDTO,iSYService, iSYHateoas);

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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService, iSYHateoas);

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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService, iSYHateoas);

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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService, iSYHateoas);

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
        ISchoolYearHateoasAssembler iSYHateoas = mock(ISchoolYearHateoasAssembler.class);
        SchoolYearRestController syRestController = new SchoolYearRestController(schoolYearAssembler,iSYService, iSYHateoas);

        when(schoolYearAssembler.fromStringToSchoolYearID(id)).thenReturn(schoolYearID);
        when(iSYService.getSchoolYearByID(schoolYearID))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act
        ResponseEntity<?> response = syRestController.getSchoolYearByID(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getAllSchoolYearsIDDescriptions_returnsListAnd200() {
        ISchoolYearService service = mock(ISchoolYearService.class);
        ISchoolYearHateoasAssembler hateoasAssembler = mock(ISchoolYearHateoasAssembler.class);
        ISchoolYearAssembler schoolYearAssembler = mock(ISchoolYearAssembler.class);
        SchoolYearRestController controller = new SchoolYearRestController(schoolYearAssembler,service,hateoasAssembler);

        SchoolYearIDDescriptionResponseDTO dto = new SchoolYearIDDescriptionResponseDTO("id1", "2015");
        when(service.getAllSchoolYearsIDDescriptions()).thenReturn(List.of(dto));

        ResponseEntity<List<SchoolYearIDDescriptionResponseDTO>> response = controller.getAllSchoolYearsIDDescriptions();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("2015", response.getBody().get(0).description());
    }
}