package PAI.factory;

import PAI.domain.*;

public interface TeacherFactory {
    Teacher createTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode,
                          String location, String country, AddressFactoryImpl addressFactory, String date, TeacherCategory category, int workingPercentage,
                          Department department);
}
