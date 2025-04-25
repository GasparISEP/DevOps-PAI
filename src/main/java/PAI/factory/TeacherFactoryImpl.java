package PAI.factory;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.repository.ITeacherRepository;
import PAI.service.ITeacherService;
import PAI.utils.TeacherAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeacherFactoryImpl implements ITeacherFactory {

    private ITeacherRepository teacherRepository;

    public TeacherFactoryImpl (ITeacherRepository teacherRepository) {

        if (teacherRepository == null)
            throw new IllegalArgumentException("Teacher Repository cannot be null.");

        this.teacherRepository = Objects.requireNonNull(teacherRepository, "Teacher Repository cannot be null.");
    }

    public Teacher createTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground,
                                 Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {

        if (teacherRepository.existsByIDorNIF(new TeacherID(acronym), nif))
            throw new TeacherAlreadyExistsException(acronym, nif);

        Address address = createAddress(street, postalCode, location, country);

        return new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, address, departmentID);
    }

    private Address createAddress(Street street, PostalCode postalCode, Location location, Country country){
        return new Address(street, postalCode, location, country);
    }
}
