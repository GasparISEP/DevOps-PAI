package PAI.controllerRest;

import PAI.dto.programmeEdition.CountStudentsInProgrammeEditionDto;
import PAI.service.programmeEdition.IProgrammeEditionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/programmeeditions")
public class ProgrammeEditionRestController {

    private final IProgrammeEditionService programmeEditionService;

    public ProgrammeEditionRestController(IProgrammeEditionService programmeEditionService) {
       if (programmeEditionService == null) {
           throw new IllegalArgumentException("ProgrammeEdition service cannot be null");
       }
        this.programmeEditionService = programmeEditionService;
    }
    @GetMapping
    public ResponseEntity<Iterable<CountStudentsInProgrammeEditionDto>> getAllProgrammeEditions() {
        Iterable<CountStudentsInProgrammeEditionDto> programmeEditionDTOs =
                programmeEditionService.getAllProgrammeEditions();
        return ResponseEntity.ok(programmeEditionDTOs);
    }

    @GetMapping("/{programmeName}/{programmeAcronym}/{schoolYearID}/students")
    public ResponseEntity<Integer> getNumberOfStudents(
            @PathVariable String programmeName,
            @PathVariable String programmeAcronym,
            @PathVariable UUID schoolYearID) throws Exception {

        CountStudentsInProgrammeEditionDto dto =
                new CountStudentsInProgrammeEditionDto(programmeName, programmeAcronym, schoolYearID);

        int totalStudents = programmeEditionService.countTotalNumberOfStudentsInAProgrammeEdition(dto);

        return ResponseEntity.ok(totalStudents);
    }
}

