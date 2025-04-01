package PAI.factory;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;

public class TeacherFactoryImpl implements ITeacherFactory {

    public Teacher createTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                 Street street, PostalCode postalCode, Location location, Country country, Department department) {

        AddressVO address = createAddress(street, postalCode, location, country);

        return new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, address, department);
    }

    private AddressVO createAddress(Street street, PostalCode postalCode, Location location, Country country){
        return new AddressVO(street, postalCode, location, country);
    }
}
