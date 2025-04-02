package PAI.factory;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;

public interface ITeacherFactory {
    Teacher createTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                          Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID);
}
