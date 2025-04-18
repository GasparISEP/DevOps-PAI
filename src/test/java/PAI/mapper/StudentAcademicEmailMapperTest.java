package PAI.mapper;

import PAI.VOs.StudentAcademicEmail;
import PAI.VOs.StudentID;
import PAI.persistence.datamodel.PhoneNumberDataModel;
import PAI.persistence.datamodel.StudentAcademicEmailDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentAcademicEmailMapperTest {


    @Test
    void testDomainToDataModel() {
        StudentAcademicEmail studentAcademicEmail = mock(StudentAcademicEmail.class);
        StudentAcademicEmailMapper mapper = new StudentAcademicEmailMapper();

        when(studentAcademicEmail.getStudentEmail()).thenReturn("1234567@isep.ipp.pt");


        StudentAcademicEmailDataModel studentAcademicEmailDataModel = mapper.domainToDataModel(studentAcademicEmail);

        assertEquals("1234567@isep.ipp.pt", studentAcademicEmailDataModel.getFullEmail());
        assertEquals("1234567", studentAcademicEmailDataModel.getStudentEmail());
        assertEquals("isep.ipp.pt", studentAcademicEmailDataModel.getEmailDomain());

    }

    @Test
    void testDataModelToDomain() {
        StudentAcademicEmailDataModel studentAcademicEmailDataModel = mock(StudentAcademicEmailDataModel.class);
        StudentAcademicEmailMapper mapper = new StudentAcademicEmailMapper();

        when(studentAcademicEmailDataModel.getStudentEmail()).thenReturn("1234567");
        when(studentAcademicEmailDataModel.getEmailDomain()).thenReturn("isep.ipp.pt");
        when(studentAcademicEmailDataModel.getFullEmail()).thenReturn("1234567@isep.ipp.pt");

        StudentAcademicEmail studentAcademicEmail = mapper.dataModelToDomain(studentAcademicEmailDataModel);

        assertEquals("1234567@isep.ipp.pt", studentAcademicEmail.getStudentEmail());



    }
}