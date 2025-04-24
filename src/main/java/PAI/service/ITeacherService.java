package PAI.service;

import PAI.VOs.*;

public interface ITeacherService {

    boolean registerTeacher (TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                             Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID);
}
