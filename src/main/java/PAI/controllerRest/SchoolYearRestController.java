package PAI.controllerRest;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/schoolyears")
public class SchoolYearRestController {

    private final ISchoolYearAssembler schoolYearAssembler;
    private final ISchoolYearService schoolYearService;

    public SchoolYearRestController(ISchoolYearAssembler iSYMapperDTO, ISchoolYearService iSYService) {
        schoolYearAssembler = iSYMapperDTO;
        schoolYearService = iSYService;
    }

    @PostMapping()
    public ResponseEntity<SchoolYearDTO> createASchoolYear(@RequestBody SchoolYearDTO schoolYearDTO) {
        if (schoolYearDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Description description = schoolYearAssembler.toDescription(schoolYearDTO);
            Date startDate = schoolYearAssembler.toStartDate(schoolYearDTO);
            Date endDate = schoolYearAssembler.toEndDate(schoolYearDTO);

            SchoolYear schoolYear = schoolYearService.addSchoolYear(description, startDate, endDate);

            if (schoolYear != null) {
                SchoolYearDTO schoolYearDTO1 = schoolYearAssembler.toDTO(schoolYear);
                return new ResponseEntity<>(schoolYearDTO1, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSchoolYears() {
        try {
            Iterable<SchoolYearDTO> schoolYearDTOS = schoolYearService.getAllSchoolYears();
            return (ResponseEntity.ok(schoolYearDTOS));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("current")
    public ResponseEntity<?> getCurrentSchoolYear() {
        Optional<CurrentSchoolYearResponseDTO> currentSchoolYearResponseDTO = schoolYearService.getCurrentSchoolYear();
        if (currentSchoolYearResponseDTO.isPresent()) {
            return new ResponseEntity<>(currentSchoolYearResponseDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No current School Year", HttpStatus.NOT_FOUND);
        }
    }
}
