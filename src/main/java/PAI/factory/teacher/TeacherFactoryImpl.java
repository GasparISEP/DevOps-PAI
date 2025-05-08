package PAI.factory.teacher;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import org.springframework.stereotype.Component;


@Component
public class TeacherFactoryImpl implements ITeacherFactory {

    public Teacher createTeacher(TeacherID teacherID, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                 Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        Address address = createAddress(street, postalCode, location, country);

        return new Teacher(teacherID, name, email, nif, phoneNumber, academicBackground, address, departmentID);
    }

    private Address createAddress(Street street, PostalCode postalCode, Location location, Country country){
        return new Address(street, postalCode, location, country);
    }
}
