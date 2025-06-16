package PAI.controllerRest;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.schoolYear.ISchoolYearHateoasAssembler;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.CurrentSchoolYearDTO;
import PAI.dto.schoolYear.CurrentSchoolYearResponseDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearIDDescriptionResponseDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school-years")
public class SchoolYearRestController {

    private final ISchoolYearAssembler schoolYearAssembler;
    private final ISchoolYearService schoolYearService;
    private final ISchoolYearHateoasAssembler schoolYearHateoasAssembler;

    public SchoolYearRestController(ISchoolYearAssembler iSYMapperDTO, ISchoolYearService iSYService, ISchoolYearHateoasAssembler iSYAssembler) {
        schoolYearAssembler = iSYMapperDTO;
        schoolYearService = iSYService;
        schoolYearHateoasAssembler = iSYAssembler;
    }

    @PostMapping()
    public ResponseEntity<?> createASchoolYear(@RequestBody SchoolYearDTO schoolYearDTO) {
        if (schoolYearDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Description description = schoolYearAssembler.toDescription(schoolYearDTO);
            Date startDate = schoolYearAssembler.toStartDate(schoolYearDTO);
            Date endDate = schoolYearAssembler.toEndDate(schoolYearDTO);

            SchoolYear schoolYear = schoolYearService.addSchoolYear(description, startDate, endDate);

            if (schoolYear != null) {
                CurrentSchoolYearDTO schoolYearDTO1 = schoolYearAssembler.toCurrentSchoolYearDTO(schoolYear);
                return ResponseEntity.status(HttpStatus.CREATED).body(schoolYearHateoasAssembler.toModel(schoolYearDTO1));
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
            Iterable<CurrentSchoolYearDTO> schoolYearDTOS = schoolYearService.getAllSchoolYears();
            return (ResponseEntity.ok(schoolYearHateoasAssembler.CollectionModel(schoolYearDTOS)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    @GetMapping("current")
    public ResponseEntity<?> getCurrentSchoolYear() {
        Optional<CurrentSchoolYearDTO> serviceResult =  schoolYearService.getCurrentSchoolYear();
        if (serviceResult.isPresent()) {
            CurrentSchoolYearResponseDTO currentSchoolYearResponseDTO = schoolYearAssembler.toResponseDTO(serviceResult.get());
            return new ResponseEntity<>(currentSchoolYearResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No current School Year", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/descriptions")
    public ResponseEntity<List<SchoolYearIDDescriptionResponseDTO>> getAllSchoolYearsIDDescriptions() {
        List<SchoolYearIDDescriptionResponseDTO> dtos = schoolYearService.getAllSchoolYearsIDDescriptions();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSchoolYearByID(@PathVariable("id") String id) {
        try {
            SchoolYearID schoolYearID = schoolYearAssembler.fromStringToSchoolYearID(id);

            Optional<SchoolYear> schoolYear = schoolYearService.getSchoolYearByID(schoolYearID);
            if (schoolYear.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SchoolYear not found");
            }

            SchoolYearDTO schoolYearDTO = schoolYearAssembler.toDTO(schoolYear.get());
            return ResponseEntity.ok(schoolYearDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }
}
