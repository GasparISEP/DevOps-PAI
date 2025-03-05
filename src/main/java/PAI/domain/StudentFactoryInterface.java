package PAI.domain;

public interface StudentFactoryInterface {

    Student newStudent(int uniqueNumber, String name, String NIF, String phone, String email, Address address);

}
