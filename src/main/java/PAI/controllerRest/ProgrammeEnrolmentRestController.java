package PAI.controllerRest;


import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/programmeEnrolment")
public class ProgrammeEnrolmentRestController {

    private final IProgrammeEnrolmentService programmeEnrolmentService;
    private final IProgrammeEnrolmentAssembler programmeEnrolmentMapper;
    private final ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;



    public ProgrammeEnrolmentRestController(IProgrammeEnrolmentService programmeEnrolmentService, IProgrammeEnrolmentAssembler programmeEnrolmentMapper, ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler) {
        this.programmeEnrolmentService = programmeEnrolmentService;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
        this.totalEnrolledStudentsAssembler = totalEnrolledStudentsAssembler;
    }

    @PostMapping()
    public ResponseEntity<ProgrammeEnrolmentResponseDTO> enrolStudentInProgramme (@RequestBody ProgrammeEnrolmentDTO programmeEnrolmentDTO){
        if (programmeEnrolmentDTO == null){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            StudentID studentID = programmeEnrolmentMapper.toStudentID(programmeEnrolmentDTO);
            AccessMethodID accessMethodID = programmeEnrolmentMapper.toAccessMethodID(programmeEnrolmentDTO);
            ProgrammeID programmeID = programmeEnrolmentMapper.toProgrammeID(programmeEnrolmentDTO);
            Date date = programmeEnrolmentMapper.toDateVO(programmeEnrolmentDTO);

            ProgrammeEnrolment programmeEnrolment = programmeEnrolmentService.enrolStudentInProgramme(studentID,accessMethodID,programmeID,date);

            if(programmeEnrolment!=null){
                ProgrammeEnrolmentResponseDTO programmeEnrolmentResponseDTO = programmeEnrolmentMapper.toProgrammeEnrolmentDTO(programmeEnrolment);
                return new ResponseEntity<>(programmeEnrolmentResponseDTO, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
