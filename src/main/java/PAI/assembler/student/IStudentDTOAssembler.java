package PAI.assembler.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentIDDTO;
import PAI.dto.student.StudentResponseDTO;

public interface IStudentDTOAssembler {

    Name toName(StudentDTO studentDTO);
    NIF toNIF(StudentDTO studentDTO);
    PhoneNumber toPhoneNumber(StudentDTO studentDTO);
    Email toEmail(StudentDTO studentDTO);
    Address toAddress(StudentDTO studentDTO);
    StudentResponseDTO toStudentResponseDTO(Student student);
    StudentID toIdDTO(StudentIDDTO dto);
}