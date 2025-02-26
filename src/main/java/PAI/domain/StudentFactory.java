package PAI.domain;

public class StudentFactory {

    public Student newStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address) throws Exception {
        return new Student(uniqueNumber, name, NIF, phone, email, address);
    }
}
