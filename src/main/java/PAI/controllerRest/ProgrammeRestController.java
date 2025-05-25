package PAI.controllerRest;

import PAI.assembler.programme.IProgrammeAssembler;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeRequestDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.service.programme.IProgrammeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmes")
public class ProgrammeRestController {

    private IProgrammeService _programmeService;
    private IProgrammeAssembler _programmeAssembler;

    public ProgrammeRestController (IProgrammeService programmeService, IProgrammeAssembler programmeAssembler){
        if (programmeService == null) {
            throw new IllegalArgumentException("ProgrammeService cannot be null.");
        }
        if (programmeAssembler == null) {
            throw new IllegalArgumentException("ProgrammeAssembler cannot be null.");
        }

        this._programmeService = programmeService;
        this._programmeAssembler = programmeAssembler;
    }

    @PostMapping()
    public ResponseEntity<ProgrammeResponseDTO> registerProgramme (@RequestBody ProgrammeRequestDTO programmeRequestDTO){
        if (programmeRequestDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            ProgrammeVOsDTO programmeVOsDto = _programmeAssembler.fromDTOToDomain(programmeRequestDTO);

            ProgrammeResponseDTO programmeResponseDTO = _programmeService.registerProgramme(programmeVOsDto);

            if(programmeResponseDTO!=null){
                return new ResponseEntity<>(programmeResponseDTO, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("ids")
    public ResponseEntity<List<ProgrammeIDDTO>> getAllProgrammeIDDTOs (){
        List<ProgrammeIDDTO> programmeIDDTOS = _programmeService.getAllProgrammeIDDTOs();
        if(!programmeIDDTOS.isEmpty()) {
            return ResponseEntity.ok(programmeIDDTOS);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
