package PAI.assembler.student;

import PAI.VOs.*;
import PAI.domain.student.Student;
import PAI.dto.student.StudentDTO;
import PAI.dto.student.StudentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOAssemblerImpl implements IStudentDTOAssembler{

    public Name toName(StudentDTO studentDTO) {
        return new Name(studentDTO.getName());
    }

    public NIF toNIF(StudentDTO studentDTO) {
        return new NIF(studentDTO.getNIF(), new Country(studentDTO.getNIFCountry()));
    }

    public PhoneNumber toPhoneNumber(StudentDTO studentDTO) {
        return new PhoneNumber(studentDTO.getPhoneCountryCode(), studentDTO.getPhoneNumber());
    }

    public Email toEmail(StudentDTO studentDTO) {
        return new Email(studentDTO.getEmail());
    }

    public Address toAddress(StudentDTO studentDTO) {
        return new Address(new Street(studentDTO.getStreet()), new PostalCode(studentDTO.getPostalCode()), new Location(studentDTO.getLocation()), new Country(studentDTO.getAddressCountry()));
    }

    public StudentResponseDTO toStudentResponseDTO(Student student) {

        int studentID = student.getStudentID().getUniqueNumber();
        String name = student.getStudentName().getName();
        String nif = student.getStudentNIF().getNIF();
        String nifCountry = student.getStudentNIF().getCountry().toString();
        String street = student.getStudentAddress().getStreet().getStreet();
        String postalCode = student.getStudentAddress().getPostalCode().getPostalCode();
        String location = student.getStudentAddress().getLocation().getLocation();
        String addressCountry = student.getStudentAddress().getCountry().getCountryName();
        String phoneCountryCode = student.getStudentPhoneNumber().getCountryCode();
        String phoneNumber = student.getStudentPhoneNumber().getNumber();
        String email = student.getStudentEmail().getEmail();
        String academicEmail = student.getStudentAcademicEmail().toString();

        return new StudentResponseDTO(studentID, name, nif, nifCountry, street, postalCode, location, addressCountry, phoneCountryCode, phoneNumber, email, academicEmail);

    }
}