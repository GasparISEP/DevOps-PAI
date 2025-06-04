package PAI.assembler.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;

public interface IStudentDTOAssembler {

    StudentID toStudentID(StudentDTO studentDTO);
    Name toName(StudentDTO studentDTO);
    NIF toNIF(StudentDTO studentDTO);
    PhoneNumber toPhoneNumber(StudentDTO studentDTO);
    Email toEmail(StudentDTO studentDTO);
    Address toAddress(StudentDTO studentDTO);
    StudentAcademicEmail toAcademicEmail(StudentDTO studentDTO);
    StudentResponseDTO toStudentResponseDTO(Student student);
}