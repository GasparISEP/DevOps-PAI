package PAI.controllerRest;

import PAI.VOs.Acronym;
import PAI.VOs.ProgrammeID;
import PAI.assembler.programmeEdition.IProgrammeEditionControllerAssembler;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionRequestDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseDTO;
import PAI.service.programmeEdition.IProgrammeEditionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<CountStudentsDto>> getAllProgrammeEditions() throws Exception {
        List<CountStudentsDto> programmeEditionDtos = programmeEditionService
                .findAllProgrammeEditions()
                .stream()
                .map(programmeEditionControllerAssembler::toCountDTO)
                .toList();
        return ResponseEntity.ok(programmeEditionDtos);
    }

    @GetMapping("/{id}/{schoolYearID}/students")
    public ResponseEntity<Integer> getNumberOfStudents(
            @PathVariable("id") String programmeAcronym,
            @PathVariable("schoolYearID") String schoolYearID) throws Exception {

        CountStudentsDto dto =
                new CountStudentsDto(programmeAcronym, schoolYearID);

        int totalStudents = programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        return ResponseEntity.ok(totalStudents);
    }

    @GetMapping("/programme/{programmeid}")
    public ResponseEntity<List<ProgrammeEditionServiceDTO>> getProgrammeEditionsByProgrammeID(
            @PathVariable("programmeid") String programmeAcronym) throws Exception {

        ProgrammeID programmeID = new ProgrammeID(
                new Acronym(programmeAcronym)
        );

        List<ProgrammeEditionServiceDTO> dtos = programmeEditionService
                .getProgrammeEditionIDsByProgrammeID(programmeID)
                .stream()
                .map(id -> programmeEditionControllerAssembler.toDTOFromIDs(id.getProgrammeID(), id.getSchoolYearID()))
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping()
    public ResponseEntity<?> createAProgrammeEditionForTheCurrentSchoolYear(@Valid @RequestBody ProgrammeEditionRequestDTO requestDto) {
        try {
            ProgrammeEditionServiceDTO programmeEditionServiceDTO = programmeEditionControllerAssembler.toDTO(requestDto);
            ProgrammeEditionServiceDTO serviceResult = programmeEditionService.createProgrammeEditionAndSave(programmeEditionServiceDTO);
            ProgrammeEditionResponseDTO response = programmeEditionControllerAssembler.toResponseDTO(serviceResult);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

