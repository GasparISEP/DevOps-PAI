package PAI.controllerRest;

import PAI.VOs.DegreeTypeID;
import PAI.assembler.degreeType.DegreeTypeHATEOASAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.dto.degreeType.DegreeTypeDTO;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.dto.degreeType.RegisterDegreeTypeRequest;
import PAI.exception.BusinessRuleViolationException;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import PAI.assembler.degreeType.IDegreeTypeAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/degreetypes")
public class DegreeTypeRestController {

    private final IDegreeTypeRegistrationService _service;
    private final IDegreeTypeAssembler _assembler;
    private final DegreeTypeHATEOASAssembler _hateoasAssembler;

    public DegreeTypeRestController(IDegreeTypeRegistrationService service, IDegreeTypeAssembler assembler, DegreeTypeHATEOASAssembler hateoasAssembler) {
        this._service = service;
        this._assembler = assembler;
        this._hateoasAssembler = hateoasAssembler;
    }

    @PostMapping
    public ResponseEntity<?> registerDegreeType(@RequestBody RegisterDegreeTypeRequest request) {
        try {
            RegisterDegreeTypeCommand command = _assembler.toRegisterDegreeTypeCommand(request);
            DegreeType created = _service.createAndSaveDegreeType(command);
            DegreeTypeDTO dto = _assembler.toDTO(created);

            EntityModel<DegreeTypeDTO> model = _hateoasAssembler.toModel(dto);

            return ResponseEntity
                    .created(model.getRequiredLink("self").toUri()) // Location header
                    .body(model);
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
            Iterable<DegreeType> resultIterable = _service.getAllDegreeTypes();

            List<EntityModel<DegreeTypeDTO>> entityModels = StreamSupport.stream(resultIterable.spliterator(), false)
                    .map(_assembler::toDTO)
                    .map(_hateoasAssembler::toModel)
                    .collect(Collectors.toList());

            CollectionModel<EntityModel<DegreeTypeDTO>> collectionModel = CollectionModel.of(
                    entityModels,
                    linkTo(methodOn(DegreeTypeRestController.class).getAllDegreeTypes()).withSelfRel()
            );

            return ResponseEntity.ok(collectionModel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDegreeTypeById(@PathVariable("id") String id) {
        try {
            DegreeTypeID degreeTypeID = new DegreeTypeID(id);
            Optional<DegreeType> optional = _service.getDegreeTypeById(degreeTypeID);

            if (optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DegreeType not found");
            }

            DegreeTypeDTO dto = _assembler.toDTO(optional.get());
            return ResponseEntity.ok(_hateoasAssembler.toModel(dto));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

}

