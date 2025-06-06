package PAI.controllerRest;

import PAI.assembler.accessMethod.IAccessMethodControllerAssembler;
import PAI.dto.accessMethod.*;
import PAI.assembler.accessMethod.IAccessMethodHateoasAssembler;
import PAI.service.accessMethod.IAccessMethodService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static PAI.utils.ValidationUtils.validateNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/access-methods")
public class AccessMethodRestController {

    private final IAccessMethodService accessMethodService;
    private final IAccessMethodControllerAssembler assembler;
    private final IAccessMethodHateoasAssembler hateoasAssembler;

    public AccessMethodRestController(IAccessMethodService accessMethodService,
                                      IAccessMethodControllerAssembler assembler,
                                      IAccessMethodHateoasAssembler hateoasAssembler) {
        this.accessMethodService = validateNotNull(accessMethodService, "AccessMethodService");
        this.assembler = validateNotNull(assembler, "AccessMethodAssembler");
        this.hateoasAssembler = validateNotNull(hateoasAssembler, "AccessMethodHateoasAssembler");
    }

    @PostMapping
    public ResponseEntity<EntityModel<AccessMethodResponseDTO>> configureAccessMethod(@Valid @RequestBody AccessMethodRequestDTO requestDTO) {
        RegisterAccessMethodCommand command = assembler.toCommand(requestDTO);
        AccessMethodServiceDTO serviceDTO = accessMethodService.configureAccessMethod(command);
        AccessMethodResponseDTO responseDTO = assembler.toResponseDto(serviceDTO);

        if (responseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        EntityModel<AccessMethodResponseDTO> resource = hateoasAssembler.toModel(responseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AccessMethodResponseDTO>> getAccessMethodById(@PathVariable(name = "id")  String id) {
        AccessMethodServiceDTO serviceDTO = accessMethodService.getAccessMethodById(id);
        if (serviceDTO == null) {
            return ResponseEntity.notFound().build();
        }

        AccessMethodResponseDTO responseDTO = assembler.toResponseDto(serviceDTO);
        EntityModel<AccessMethodResponseDTO> resource = hateoasAssembler.toModel(responseDTO);
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<AccessMethodResponseDTO>>> getAllAccessMethods() {
        List<AccessMethodServiceDTO> serviceDTOs = accessMethodService.getAllAccessMethods();
        if (serviceDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AccessMethodResponseDTO> responseDTOs = assembler.toResponseDtoList(serviceDTOs);
        CollectionModel<EntityModel<AccessMethodResponseDTO>> collectionModel = hateoasAssembler.toCollectionModel(responseDTOs);

        return ResponseEntity.ok(collectionModel);
    }
}

