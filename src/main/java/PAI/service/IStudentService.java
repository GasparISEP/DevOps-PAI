package PAI.service;

import PAI.VOs.*;
import PAI.domain.Student;

public interface IStudentService {

    Student registerStudent (StudentID studentID, Name name, NIF nif, PhoneNumber phoneNumber, Email email, Street street,
                             PostalCode postalCode, Location location, Country country, StudentAcademicEmail academicEmail) throws Exception;
}
