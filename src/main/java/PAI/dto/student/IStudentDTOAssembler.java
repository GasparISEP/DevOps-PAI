package PAI.dto.student;

import PAI.VOs.*;
import PAI.domain.student.Student;

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