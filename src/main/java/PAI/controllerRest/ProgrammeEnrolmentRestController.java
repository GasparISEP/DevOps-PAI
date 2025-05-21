package PAI.controllerRest;


import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.dto.programmeEnrolment.IProgrammeEnrolmentMapper;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/programmeEnrolment")
public class ProgrammeEnrolmentRestController {

    private final IProgrammeEnrolmentService programmeEnrolmentService;
    private final IProgrammeEnrolmentMapper programmeEnrolmentMapper;



    public ProgrammeEnrolmentRestController(IProgrammeEnrolmentService programmeEnrolmentService, IProgrammeEnrolmentMapper programmeEnrolmentMapper) {
        this.programmeEnrolmentService = programmeEnrolmentService;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
    }

//    @PostMapping()
//    public ResponseEntity<ProgrammeEnrolmentResponseDTO> enrolStudentInProgramme (@RequestBody ProgrammeEnrolmentDTO programmeEnrolmentDTO){
//        if (programmeEnrolmentDTO == null){
//           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            StudentID studentID = programmeEnrolmentMapper.toStudentID(programmeEnrolmentDTO);
//            AccessMethodID accessMethodID = programmeEnrolmentMapper.toAccessMethodID(programmeEnrolmentDTO);
//            ProgrammeID programmeID = programmeEnrolmentMapper.toProgrammeID(programmeEnrolmentDTO);
//            Date date = programmeEnrolmentMapper.toDateVO(programmeEnrolmentDTO);
//            boolean programmeEnrolment = programmeEnrolmentService.enrolStudentInProgramme(studentID,accessMethodID,programmeID,date);
//
//            ProgrammeEnrolmentResponseDTO programmeEnrolmentResponseDTO = programmeEnrolmentMapper.toProgrammeEnrolmentDTO(programmeEnrolmentDTO)
//
//
//
//
//
//        }
//    }
}
