package PAI.controllerRest;

import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.IProgrammeDirectorAssembler;
import PAI.domain.programme.Programme;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.*;
import PAI.service.programme.IProgrammeService;
import PAI.service.teacher.ITeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programmes")
public class ProgrammeRestController {

    private final IProgrammeService _programmeService;
    private final IProgrammeAssembler _programmeAssembler;
    private final IProgrammeDirectorAssembler _programmeDirectorAssembler;
    private final ITeacherService _teacherService;

    public ProgrammeRestController(IProgrammeService programmeService,
                                   IProgrammeAssembler programmeAssembler,
                                   IProgrammeDirectorAssembler programmeDirectorAssembler,
                                   ITeacherService teacherService) {
        if (programmeService == null) throw new IllegalArgumentException("ProgrammeService cannot be null.");
        if (programmeAssembler == null) throw new IllegalArgumentException("ProgrammeAssembler cannot be null.");
        if (programmeDirectorAssembler == null) throw new IllegalArgumentException("ProgrammeDirectorAssembler cannot be null.");
        if (teacherService == null) throw new IllegalArgumentException("TeacherService cannot be null.");

        this._programmeService = programmeService;
        this._programmeAssembler = programmeAssembler;
        this._programmeDirectorAssembler = programmeDirectorAssembler;
        this._teacherService = teacherService;
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

    @GetMapping("/assign-director")
    public ResponseEntity<ProgrammeDirectorResponseDTO> getProgrammeDirectorInfo() {
        try {
            List<Programme> programmes = (List<Programme>) _programmeService.findAll();
            List<Teacher> teachers = (List<Teacher>) _teacherService.getAllTeachers();
            ProgrammeDirectorResponseDTO response = _programmeDirectorAssembler.fromDomainToDTO(programmes, teachers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/assign-director")
    public ResponseEntity<Void> assignProgrammeDirector(@RequestBody ProgrammeDirectorRequestDTO dto) {
        if (dto == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            ProgrammeDirectorVOsDTO vosDTO = _programmeDirectorAssembler.fromDTOToDomain(dto);
            ProgrammeID programmeID = new ProgrammeID(vosDTO.getProgrammeName(), vosDTO.getProgrammeAcronym());
            TeacherID teacherID = new TeacherID(vosDTO.getTeacherAcronym());
            _programmeService.changeProgrammeDirector(programmeID, teacherID);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
