package PAI.controllerRest.accessMethodRestControllerTest;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodControllerAssembler;
import PAI.assembler.accessMethod.IAccessMethodHateoasAssembler;
import PAI.controllerRest.AccessMethodRestController;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.service.accessMethod.IAccessMethodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Collections;
import java.util.List;


@WebMvcTest(AccessMethodRestController.class)
public class AccessMethodRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IAccessMethodService service;

    @MockitoBean
    private IAccessMethodControllerAssembler assembler;

    @MockitoBean
    private IAccessMethodHateoasAssembler hateoasAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturn201WhenCreatingAccessMethod() throws Exception {
        // Arrange
        String name = "Test Name";
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("abc123", name);
        AccessMethodResponseDTO responseDTO = new AccessMethodResponseDTO("abc123", name);

        when(assembler.toCommand(any())).thenReturn(command);
        when(service.configureAccessMethod(any())).thenReturn(serviceDTO);
        when(assembler.toResponseDto(any())).thenReturn(responseDTO);
        when(hateoasAssembler.toModel(any())).thenReturn(EntityModel.of(responseDTO));

        // Act & Assert
        mockMvc.perform(post("/access-methods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("abc123"))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    void shouldReturn404WhenAccessMethodNotFound() throws Exception {
        // Arrange
        when(service.getAccessMethodById("notfound")).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/access-methods/notfound"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn200WhenGettingAccessMethodById() throws Exception {
        // Arrange
        String id = "abc123";
        String name = "Test Name";
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO(id, name);
        AccessMethodResponseDTO responseDTO = new AccessMethodResponseDTO(id, name);

        when(service.getAccessMethodById(id)).thenReturn(serviceDTO);
        when(assembler.toResponseDto(serviceDTO)).thenReturn(responseDTO);
        when(hateoasAssembler.toModel(responseDTO)).thenReturn(EntityModel.of(responseDTO));

        // Act & Assert
        mockMvc.perform(get("/access-methods/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    void shouldReturn400WhenAssemblerReturnsNull() throws Exception {
        // Arrange
        String name = "Test Name";
        AccessMethodRequestDTO requestDTO = new AccessMethodRequestDTO(name);
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(name);
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);
        AccessMethodServiceDTO serviceDTO = new AccessMethodServiceDTO("abc123", name);

        when(assembler.toCommand(any())).thenReturn(command);
        when(service.configureAccessMethod(any())).thenReturn(serviceDTO);
        when(assembler.toResponseDto(any())).thenReturn(null);

        // Act & Assert
        mockMvc.perform(post("/access-methods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenPostDataIsInvalid() throws Exception {
        // Arrange
        AccessMethodRequestDTO invalidRequest = new AccessMethodRequestDTO(""); // Nome inválido

        // Act & Assert
        mockMvc.perform(post("/access-methods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllAccessMethods_shouldReturn200WithCollection() throws Exception {
        // Arrange
        List<AccessMethodServiceDTO> serviceDTOs = List.of(
                new AccessMethodServiceDTO("id1", "Name1"),
                new AccessMethodServiceDTO("id2", "Name2")
        );

        List<AccessMethodResponseDTO> responseDTOs = List.of(
                new AccessMethodResponseDTO("id1", "Name1"),
                new AccessMethodResponseDTO("id2", "Name2")
        );

        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel =
                CollectionModel.of(
                        responseDTOs.stream()
                                .map(dto -> EntityModel.of(dto))
                                .toList(),
                        linkTo(methodOn(AccessMethodRestController.class).getAllAccessMethods()).withSelfRel()
                );

        when(service.getAllAccessMethods()).thenReturn(serviceDTOs);
        when(assembler.toResponseDtoList(serviceDTOs)).thenReturn(responseDTOs);
        when(hateoasAssembler.toCollectionModel(responseDTOs)).thenReturn(collectionModel);

        // Act & Assert
        mockMvc.perform(get("/access-methods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.accessMethodResponseDTOList").exists())
                .andExpect(jsonPath("$._links.self.href").exists());
    }

    @Test
    void getAllAccessMethods_shouldReturn204WhenNoData() throws Exception {
        // Arrange
        when(service.getAllAccessMethods()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/access-methods"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn204WhenNoAccessMethods() throws Exception {

        // Arrange
        when(service.getAllAccessMethods()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/access-methods"))
                .andExpect(status().isNoContent());
    }
}