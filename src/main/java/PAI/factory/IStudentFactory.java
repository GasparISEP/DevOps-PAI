package PAI.factory;

import PAI.VOs.StudentID;
import PAI.domain.Address;
import PAI.domain.Student;

public interface IStudentFactory {

    Student newStudent(StudentID studentID, String name, String NIF, String phone, String email, Address address);

}
