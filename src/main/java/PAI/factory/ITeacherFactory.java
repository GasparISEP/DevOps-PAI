package PAI.factory;

import PAI.domain.*;

public interface ITeacherFactory {
    Teacher createTeacher(String acronym, String name, String email, String nif, String phoneNumber, String academicBackground, String street, String postalCode,
                          String location, String country, IAddressFactory addressFactory, String date, TeacherCategory category, int workingPercentage,
                          Department department);
}
