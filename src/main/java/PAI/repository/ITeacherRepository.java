package PAI.repository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.Department;
import PAI.domain.Teacher;

import java.util.Optional;

public interface ITeacherRepository extends IRepository<TeacherID, Teacher> {

    Optional<TeacherID> registerTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                               Street street, PostalCode postalCode, Location location, Country country, Department department);
}
