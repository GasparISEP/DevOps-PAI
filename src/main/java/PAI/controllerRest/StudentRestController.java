package PAI.controllerRest;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.student.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final IStudentService service;
    private final IStudentDTOAssembler mapper;

    public StudentRestController(IStudentService service, IStudentDTOAssembler mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping()
    public ResponseEntity<StudentResponseDTO> registerAStudent (@RequestBody StudentDTO studentDTO){
        if (studentDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        //StudentID studentID = mapper.toStudentID(studentDTO);
        Name name = mapper.toName(studentDTO);
        NIF nif = mapper.toNIF(studentDTO);
        PhoneNumber phoneNumber = mapper.toPhoneNumber(studentDTO);
        Email email = mapper.toEmail(studentDTO);
        Address address = mapper.toAddress(studentDTO);
//        StudentAcademicEmail studentAcademicEmail = mapper.toAcademicEmail(studentDTO);


        Student student = service.registerStudent( name, nif, phoneNumber, email, address.getStreet(), address.getPostalCode(), address.getLocation(), address.getCountry());


        StudentResponseDTO studentResponseDTO = mapper.toStudentResponseDTO(student);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<Map<String, Integer>> getLastStudentID() {
        int value = service.getLastStudentID();
        Map<String, Integer> response = Collections.singletonMap("lastStudentID", value);
        return ResponseEntity.ok(response);
    }

}