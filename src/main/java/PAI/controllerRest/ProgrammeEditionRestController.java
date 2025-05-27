package PAI.controllerRest;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import PAI.service.programmeEdition.IProgrammeEditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/programmeeditions")
public class ProgrammeEditionRestController {

    private final IProgrammeEditionService programmeEditionService;
    private final IProgrammeEditionAssembler programmeEditionAssembler;

    public ProgrammeEditionRestController(IProgrammeEditionService programmeEditionService, IProgrammeEditionAssembler programmeEditionAssembler) {
       if (programmeEditionService == null) {
           throw new IllegalArgumentException("ProgrammeEdition service cannot be null");
       }
       if (programmeEditionAssembler == null) {
           throw new IllegalArgumentException("ProgrammeEdition assembler cannot be null");
       }
        this.programmeEditionService = programmeEditionService;
       this.programmeEditionAssembler = programmeEditionAssembler;
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

        List<ProgrammeEdition> programmeEditions = programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID);

        List<ProgrammeEditionDTO> dtos = programmeEditions.stream()
                .map(pe -> programmeEditionAssembler.toDTO(pe.identity().getProgrammeID(), pe.identity().getSchoolYearID()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

}

