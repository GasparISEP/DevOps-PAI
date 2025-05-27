package PAI.controllerRest;

import PAI.assembler.accessMethod.IAccessMethodControllerAssembler;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodRequestDTO;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.service.accessMethod.IAccessMethodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static PAI.utils.ValidationUtils.validateNotNull;

@RestController
@RequestMapping("/access-methods")
public class AccessMethodRestController {

    private final IAccessMethodService accessMethodService;
    private final IAccessMethodControllerAssembler assembler;

    public AccessMethodRestController(IAccessMethodService accessMethodService, IAccessMethodControllerAssembler assembler) {
        this.accessMethodService = validateNotNull(accessMethodService, "AccessMethodService");
        this.assembler = validateNotNull(assembler, "AccessMethodAssembler");
    }

    @PostMapping
    public ResponseEntity<AccessMethodResponseDTO> configureAccessMethod(@Valid @RequestBody AccessMethodRequestDTO requestDTO) {
        RegisterAccessMethodCommand command = assembler.toCommand(requestDTO);
        AccessMethodServiceDTO serviceDTO = accessMethodService.configureAccessMethod(command);
        AccessMethodResponseDTO responseDTO = assembler.toResponseDto(serviceDTO);

        if (responseDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
