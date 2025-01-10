package PAI.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnrolmentTest {

    @Test
    void constructorAlwaysCreatesAnObject() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("M1");

        //act
        Enrolment enrolment = new Enrolment(student1, am1);
    }

    @Test
    void shouldReturnTrueIfTheStudentIsTheSame() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("M1");
        Enrolment enrolment = new Enrolment(student1, am1);
        Address address2 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student2 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address2);

        //act
        boolean result = enrolment.isSameStudent(student2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheStudentIsNotTheSame() throws Exception {
        //arrange
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        AccessMethod am1 = new AccessMethod("M1");
        Enrolment enrolment = new Enrolment(student1, am1);
        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
        Student student2 = new Student(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

        //act
        boolean result = enrolment.isSameStudent(student2);

        //assert
        assertFalse(result);
    }

}