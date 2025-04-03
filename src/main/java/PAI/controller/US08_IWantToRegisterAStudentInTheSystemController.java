package PAI.controller;

import PAI.VOs.*;
import PAI.repository.IStudentRepository;

public class US08_IWantToRegisterAStudentInTheSystemController {

    private IStudentRepository _IStudentRepository;

    public US08_IWantToRegisterAStudentInTheSystemController(
            IStudentRepository iStudentRepository) throws Exception {

        if (iStudentRepository == null)
            throw new IllegalArgumentException ("Student repository cannot be null!");

         _IStudentRepository = iStudentRepository;
    }

    public boolean registerStudent (int uniqueNumber, String name, String nif, Country nifCountry,
                                    String countryCode, String phoneNumber, String email, String street,
                                    String postalCode, String location, String country) throws Exception {

        StudentID studentID = new StudentID(uniqueNumber);
        Name nameVO = new Name(name);
        NIF nifVO = new NIF(nif, nifCountry);
        PhoneNumber phone = new PhoneNumber(countryCode, phoneNumber);
        Email emailVO = new Email(email);
        Street streetVO = new Street(street);
        PostalCode postalCodeVO = new PostalCode(postalCode);
        Location locationVO = new Location(location);
        Country countryVO = new Country(country);
        StudentAcademicEmail academicEmailVO = new StudentAcademicEmail(studentID);

        _IStudentRepository.registerStudent(studentID, nameVO, nifVO, phone, emailVO, streetVO, postalCodeVO, locationVO, countryVO, academicEmailVO);

        return true;
    }
}