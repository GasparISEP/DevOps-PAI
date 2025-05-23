package PAI.controllerRest;

import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.DegreeTypeDTO;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.dto.degreeType.RegisterDegreeTypeRequest;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import PAI.assembler.degreeType.IDegreeTypeAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/degreetypes")
public class DegreeTypeRestController {

    private final IDegreeTypeRegistrationService service;
    private final IDegreeTypeAssembler assembler;

    public DegreeTypeRestController(IDegreeTypeRegistrationService service, IDegreeTypeAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<?> registerDegreeType(@RequestBody RegisterDegreeTypeRequest request) {
        try {
            RegisterDegreeTypeCommand command = assembler.toRegisterDegreeTypeCommand(request);
            DegreeType created = service.createAndSaveDegreeType(command);
            DegreeTypeDTO dto = assembler.toDTO(created);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (BusinessRuleViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllDegreeTypes() {
        try {
            Iterable<DegreeType> resultIterable = service.getAllDegreeTypes();
            List<DegreeType> result = StreamSupport.stream(resultIterable.spliterator(), false)
                    .collect(Collectors.toList());
            List<DegreeTypeDTO> dtos = assembler.toDTOs(result);
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }
}

