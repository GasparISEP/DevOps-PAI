package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.service.IStudentService;
import org.springframework.stereotype.Controller;

@Controller
public class US08_IWantToRegisterAStudentInTheSystemController {

    private IStudentService _studentService;

    public US08_IWantToRegisterAStudentInTheSystemController(IStudentService studentService) {

        if (studentService == null)
            throw new IllegalArgumentException ("Student service cannot be null!");

         _studentService = studentService;
    }

    public Student registerStudent (int uniqueNumber, String name, String nif, String countryNIF,
                                    String countryCode, String phoneNumber, String email, String street,
                                    String postalCode, String location, String country) throws Exception {

        StudentID studentID = new StudentID(uniqueNumber);
        Name nameVO = new Name(name);
        Country nifCountry = new Country(countryNIF);
        NIF nifVO = new NIF (nif, nifCountry);
        PhoneNumber phone = new PhoneNumber(countryCode, phoneNumber);
        Email emailVO = new Email(email);
        Street streetVO = new Street(street);
        PostalCode postalCodeVO = new PostalCode(postalCode);
        Location locationVO = new Location(location);
        Country countryVO = new Country(country);
        StudentAcademicEmail academicEmailVO = new StudentAcademicEmail(uniqueNumber);

        return _studentService.registerStudent(studentID, nameVO, nifVO, phone, emailVO, streetVO, postalCodeVO, locationVO, countryVO, academicEmailVO);

    }

}