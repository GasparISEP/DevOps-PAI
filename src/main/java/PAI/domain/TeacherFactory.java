package PAI.domain;

public class TeacherFactory {

    public Teacher createTeacher(String acronym, String name, String email, String nif,
                                        String phoneNumber, String academicBackground, String street,
                                        String postalCode, String location, String country,
                                        String date, TeacherCategory category, int workingPercentage,
                                        Department department) {

        return new Teacher(acronym, name, email, nif,
                phoneNumber, academicBackground, street, postalCode, location, country, date,
                category, workingPercentage, department);
    }
}
