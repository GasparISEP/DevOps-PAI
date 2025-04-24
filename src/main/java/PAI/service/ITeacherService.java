package PAI.service;

import PAI.VOs.*;

import java.util.Optional;

public interface ITeacherService {

    Optional<TeacherID> registerTeacher (TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                              Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID);
}
