package PAI.factory;

import PAI.domain.*;

public class TeacherFactory {

    public Teacher createTeacher(String acronym, String name, String email, String nif, String phoneNumber,
                                 String academicBackground, String street, String postalCode, String location,
                                 String country, AddressFactory addressFactory, String date, TeacherCategory category, int workingPercentage,
                                 Department department, TeacherCareerProgressionFactory CareerProgressionfactory) throws IllegalArgumentException {

        return new Teacher(acronym, name, email, nif, phoneNumber, academicBackground, street, postalCode,
                location, country, addressFactory, date, category, workingPercentage, department, CareerProgressionfactory);
    }
}
