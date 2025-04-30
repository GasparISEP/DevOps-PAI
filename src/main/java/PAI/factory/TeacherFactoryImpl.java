package PAI.factory;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.exception.TeacherAlreadyExistsException;
import org.springframework.stereotype.Component;


@Component
public class TeacherFactoryImpl implements ITeacherFactory {

    public Teacher createTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                 Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        Address address = createAddress(street, postalCode, location, country);

        return new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, address, departmentID);
    }

    private Address createAddress(Street street, PostalCode postalCode, Location location, Country country){
        return new Address(street, postalCode, location, country);
    }
}
