package PAI.domain.teacher;

import PAI.VOs.*;
import PAI.VOs.Location;

public interface ITeacherFactory {
    Teacher createTeacher(TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                          Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID);
}
