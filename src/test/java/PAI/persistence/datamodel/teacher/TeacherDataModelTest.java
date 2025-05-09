package PAI.persistence.datamodel.teacher;

import PAI.persistence.datamodel.AddressDataModel;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.PhoneNumberDataModel;
import PAI.persistence.datamodel.TeacherAcademicEmailDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherDataModelTest {

    @Test
    void testEmptyConstructor () {
        // Arrange + Act
        TeacherDataModel teacherDM = new TeacherDataModel();

        // Assert
        assertNotNull(teacherDM);
    }

    @Test
    void testConstructor () {
        // Arrange
        TeacherIDDataModel teacherIDdataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        // Act
        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDdataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        assertNotNull(teacherDataModel);
    }

    @Test
    void getTeacherIDShouldReturnTeacherID () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        TeacherIDDataModel result = teacherDataModel.getTeacherIDDataModel();

        // Assert
        assertEquals(teacherIDDataModelDouble, result);
    }

    @Test
    void getNameShouldReturnName () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        String result = teacherDataModel.getName();

        // Assert
        assertEquals(name, result);
    }

    @Test
    void getEmailShouldReturnEmail () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        String result = teacherDataModel.getEmail();

        // Assert
        assertEquals(email, result);
    }

    @Test
    void getNIFShouldReturnNIF () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        NIFDataModel result = teacherDataModel.getNif();

        // Assert
        assertEquals(nifDataModelDouble, result);
    }

    @Test
    void getPhoneNumberShouldReturnPhoneNumber () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        PhoneNumberDataModel result = teacherDataModel.getPhoneNumber();

        // Assert
        assertEquals(phoneDataModelDouble, result);
    }

    @Test
    void getAcademicBackgroundShouldReturnAcademicBackground () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        String result = teacherDataModel.getAcademicBackground();

        // Assert
        assertEquals(academicBackground, result);
    }

    @Test
    void getAddressShouldReturnAddress () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        AddressDataModel result = teacherDataModel.getAddress();

        // Assert
        assertEquals(addressDataModelDouble, result);
    }

    @Test
    void getTeacherAcademicEmailShouldReturnAcademicEmail () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        TeacherAcademicEmailDataModel result = teacherDataModel.getTeacherAcademicEmail();

        // Assert
        assertEquals(academicEmailDataModelDouble, result);
    }

    @Test
    void getDepartmentIDShouldReturnDepartmentID () {
        // Arrange
        TeacherIDDataModel teacherIDDataModelDouble = mock(TeacherIDDataModel.class);
        String name = "Migas";
        String email = "migas@outlook.com";
        NIFDataModel nifDataModelDouble = mock(NIFDataModel.class);
        PhoneNumberDataModel phoneDataModelDouble = mock(PhoneNumberDataModel.class);
        String academicBackground = "Mestrado em Eng. Informática";
        AddressDataModel addressDataModelDouble = mock(AddressDataModel.class);
        TeacherAcademicEmailDataModel academicEmailDataModelDouble = mock(TeacherAcademicEmailDataModel.class);
        DepartmentIDDataModel departmentIDDataModel = mock(DepartmentIDDataModel.class);

        TeacherDataModel teacherDataModel = new TeacherDataModel(teacherIDDataModelDouble, name, email, nifDataModelDouble, phoneDataModelDouble, academicBackground, addressDataModelDouble, academicEmailDataModelDouble, departmentIDDataModel);

        // Act
        DepartmentIDDataModel result = teacherDataModel.getDepartmentID();

        // Assert
        assertEquals(departmentIDDataModel, result);
    }
}