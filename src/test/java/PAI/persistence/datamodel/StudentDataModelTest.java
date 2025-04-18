package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentDataModelTest {

    @Test
    void emptyConstructorShouldCreateObject() {
        //arrange

        //act
        StudentDataModel studentDataModel = new StudentDataModel();

        //assert
        assertNotNull(studentDataModel);
    }

    @Test
    void constructorWithParametersShouldCreateObject() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        //act
        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //assert
        assertNotNull(studentDataModel);
    }

    @Test
    void getStudentIDShouldReturnStudentID() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        StudentIDDataModel result = studentDataModel.getStudentID();

        //assert
        assertEquals(studentIDDataModelDouble, result);
    }

    @Test
    void getNameShouldReturnName() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        String result = studentDataModel.getName();

        //assert
        assertEquals("Harry Potter", result);
    }

    @Test
    void getNIFShouldReturnNIF() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        NIFDataModel result = studentDataModel.getNIF();

        //assert
        assertEquals(nifDataModelDouble, result);
    }

    @Test
    void getPhoneNumberShouldReturnPhoneNumber() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        PhoneNumberDataModel result = studentDataModel.getPhoneNumber();

        //assert
        assertEquals(phoneNumberDataModelDouble, result);

    }

    @Test
    void getEmailShouldReturnEmail() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        String result = studentDataModel.getEmail();

        //assert
        assertEquals("harrypotter@gmail.com", result);
    }

    @Test
    void getAddressShouldReturnAddress() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        AddressDataModel result = studentDataModel.getAddress();

        //assert
        assertEquals(addressDataModelDouble, result);
    }

    @Test
    void getAcademicEmailShouldReturnAcademicEmail() {
        //arrange
        StudentIDDataModel studentIDDataModelDouble = mock(StudentIDDataModel.class);
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneNumberDataModelDouble = mock(PhoneNumberDataModel.class);
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        StudentAcademicEmailDataModel studentAcademicEmailDataModelDouble = mock(StudentAcademicEmailDataModel.class);

        StudentDataModel studentDataModel = new StudentDataModel(studentIDDataModelDouble, "Harry Potter", nifDataModelDouble,
                phoneNumberDataModelDouble, "harrypotter@gmail.com", addressDataModelDouble, studentAcademicEmailDataModelDouble);

        //act
        StudentAcademicEmailDataModel result = studentDataModel.getAcademicEmail();

        //assert
        assertEquals(studentAcademicEmailDataModelDouble, result);
    }
}