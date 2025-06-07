package PAI.controllerRest;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.student.IStudentHateoasAssembler;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.student.IStudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/students")
public class StudentRestController {

    private final IStudentService service;
    private final IStudentDTOAssembler mapper;
    private final IStudentHateoasAssembler hateoasAssembler;

    public StudentRestController(IStudentService service,
                                 IStudentDTOAssembler mapper,
                                 IStudentHateoasAssembler hateoasAssembler) {
        this.service = service;
        this.mapper = mapper;
        this.hateoasAssembler = hateoasAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<StudentResponseDTO>> registerAStudent(@RequestBody StudentDTO studentDTO) {
        if (studentDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Deixa de usar o ID vindo do DTO — é gerado no service
        Name name = mapper.toName(studentDTO);
        NIF nif = mapper.toNIF(studentDTO);
        PhoneNumber phoneNumber = mapper.toPhoneNumber(studentDTO);
        Email email = mapper.toEmail(studentDTO);
        Address address = mapper.toAddress(studentDTO);

        Student student = service.registerStudent(
                name, nif, phoneNumber, email,
                address.getStreet(), address.getPostalCode(),
                address.getLocation(), address.getCountry()
        );

        StudentResponseDTO responseDTO = mapper.toStudentResponseDTO(student);
        EntityModel<StudentResponseDTO> studentModel = hateoasAssembler.toModel(responseDTO);

        return new ResponseEntity<>(studentModel, HttpStatus.CREATED);
    }

    @GetMapping("/last-id")
    public ResponseEntity<Map<String, Integer>> getLastStudentID() {
        int value = service.getLastStudentID();
        Map<String, Integer> response = Collections.singletonMap("lastStudentID", value);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentResponseDTO>>> getAllStudents() {
        List<Student> students = service.getAllStudents();

        List<EntityModel<StudentResponseDTO>> studentModels = students.stream()
                .map(mapper::toStudentResponseDTO)
                .map(hateoasAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(studentModels,
                linkTo(methodOn(StudentRestController.class).getAllStudents()).withSelfRel()
        ));
    }
}