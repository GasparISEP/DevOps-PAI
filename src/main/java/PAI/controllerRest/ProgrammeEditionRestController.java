package PAI.controllerRest;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.service.programmeEdition.IProgrammeEditionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/programmeeditions")
public class ProgrammeEditionRestController {

    private final IProgrammeEditionService programmeEditionService;
    private final IProgrammeEditionControllerAssembler programmeEditionControllerAssembler;

    public ProgrammeEditionRestController(IProgrammeEditionService programmeEditionService, IProgrammeEditionControllerAssembler programmeEditionControllerAssembler) {
        if (programmeEditionService == null) {
            throw new IllegalArgumentException("ProgrammeEdition service cannot be null");
        }
        if (programmeEditionControllerAssembler == null) {
            throw new IllegalArgumentException("ProgrammeEdition Controller Assembler cannot be null");
        }

        this.programmeEditionService = programmeEditionService;
        this.programmeEditionControllerAssembler = programmeEditionControllerAssembler;
    }

    @GetMapping
    public ResponseEntity<Iterable<CountStudentsDto>> getAllProgrammeEditions() {
        Iterable<CountStudentsDto> programmeEditionDTOs =
                programmeEditionService.getAllProgrammeEditions();
        return ResponseEntity.ok(programmeEditionDTOs);
    }

    @GetMapping("/{programmeName}/{programmeAcronym}/{schoolYearID}/students")
    public ResponseEntity<Integer> getNumberOfStudents(
            @PathVariable("programmeName") String programmeName,
            @PathVariable("programmeAcronym") String programmeAcronym,
            @PathVariable("schoolYearID") UUID schoolYearID) throws Exception {

        CountStudentsDto dto =
                new CountStudentsDto(programmeName, programmeAcronym, schoolYearID);

        int totalStudents = programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        return ResponseEntity.ok(totalStudents);
    }

    @GetMapping("/programme/{programmeName}/{programmeAcronym}")
    public ResponseEntity<List<ProgrammeEditionDTO>> getProgrammeEditionsByProgrammeID(
            @PathVariable String programmeName,
            @PathVariable String programmeAcronym) throws Exception {

        ProgrammeID programmeID = new ProgrammeID(
                new NameWithNumbersAndSpecialChars(programmeName),
                new Acronym(programmeAcronym)
        );

        List<ProgrammeEditionDTO> dtos = programmeEditionService
                .getProgrammeEditionIDsByProgrammeID(programmeID)
                .stream()
                .map(id -> programmeEditionControllerAssembler.toDTOFromIDs(id.getProgrammeID(), id.getSchoolYearID()))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping()
    public ResponseEntity<?> createAProgrammeEditionForTheCurrentSchoolYear(@Valid @RequestBody ProgrammeEditionRequestDTO requestDto) {
        try {
            ProgrammeEditionDTO programmeEditionDTO = programmeEditionControllerAssembler.toDTO(requestDto);
            ProgrammeEditionDTO serviceResult = programmeEditionService.createProgrammeEditionAndSave(programmeEditionDTO);
            ProgrammeEditionResponseDTO response = programmeEditionControllerAssembler.toResponseDTO(serviceResult);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

