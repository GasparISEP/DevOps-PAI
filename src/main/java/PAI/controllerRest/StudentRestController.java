package PAI.controllerRest;

import PAI.VOs.*;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentHATEOASAssembler;
import PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsAssembler;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.student.Student;
import PAI.assembler.student.IStudentDTOAssembler;
import PAI.assembler.student.IStudentHateoasAssembler;
import PAI.assembler.programmeEnrolment.IProgrammeEnrolmentAssembler;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import PAI.dto.programmeEnrolment.ProgrammeEnrolmentResponseDTO;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
import PAI.service.student.IStudentService;
import PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear.ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService;
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
    private final IProgrammeEnrolmentService programmeEnrolmentService;
    private final IProgrammeEnrolmentAssembler programmeEnrolmentMapper;
    private final ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler;
    private final ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService;
    private final IProgrammeEnrolmentHATEOASAssembler enrolmentHateoasAssembler;

    public StudentRestController(IStudentService service, IStudentDTOAssembler mapper, IStudentHateoasAssembler hateoasAssembler, IProgrammeEnrolmentService programmeEnrolmentService, IProgrammeEnrolmentAssembler programmeEnrolmentMapper, ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler, ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService totalEnrolledStudentsService, IProgrammeEnrolmentHATEOASAssembler enrolmentHateoasAssembler) {
        this.service = service;
        this.mapper = mapper;
        this.hateoasAssembler = hateoasAssembler;
        this.programmeEnrolmentService = programmeEnrolmentService;
        this.programmeEnrolmentMapper = programmeEnrolmentMapper;
        this.totalEnrolledStudentsAssembler = totalEnrolledStudentsAssembler;
        this.totalEnrolledStudentsService = totalEnrolledStudentsService;
        this.enrolmentHateoasAssembler = enrolmentHateoasAssembler;
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

    @PostMapping("/enrollStudent")
    public ResponseEntity<EntityModel<ProgrammeEnrolmentResponseDTO>> enrolStudentInProgramme (
            @RequestBody ProgrammeEnrolmentDTO programmeEnrolmentDTO) {

        if (programmeEnrolmentDTO == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            ProgrammeEnrolment pe = programmeEnrolmentService.enrolStudentInProgramme(
                    programmeEnrolmentMapper.toStudentID(programmeEnrolmentDTO),
                    programmeEnrolmentMapper.toAccessMethodID(programmeEnrolmentDTO),
                    programmeEnrolmentMapper.toProgrammeID(programmeEnrolmentDTO),
                    programmeEnrolmentMapper.toDateVO(programmeEnrolmentDTO)
            );

            if (pe != null) {
                ProgrammeEnrolmentResponseDTO dto = programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe);

                EntityModel<ProgrammeEnrolmentResponseDTO> model = enrolmentHateoasAssembler.toModel(dto);

                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(model);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/enrollStudent/{studentId}/{programmeID}")
    public ResponseEntity<ProgrammeEnrolmentResponseDTO> getEnrolmentByStudentAndProgramme(
            @PathVariable("studentId")   int    studentId,
            @PathVariable("programmeID") String programmeID
    ) {
        StudentID sid = new StudentID(studentId);

        ProgrammeID pid = new ProgrammeID(new Acronym(programmeID));


        ProgrammeEnrolment pe = programmeEnrolmentService
                .findEnrolmentByStudentAndProgramme(sid, pid);

        if (pe == null) {
            return ResponseEntity.notFound().build();
        }


        ProgrammeEnrolmentResponseDTO dto =
                programmeEnrolmentMapper.toProgrammeEnrolmentDTO(pe);
        return ResponseEntity.ok(dto);
    }





}