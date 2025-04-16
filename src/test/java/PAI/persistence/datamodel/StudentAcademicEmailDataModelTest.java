package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentAcademicEmailDataModelTest {

        @Test
        void emptyConstructor() {

            //Act
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel();

            //Assert
            assertNotNull(dataModel);
        }

        @Test
        void constructorWithArguments() {
            //Arrange
            String studentEmail = "1234567";
            String emailDomain = "isep.ipp.pt";

            //Arrange
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel(studentEmail, emailDomain);

            //Assert
            assertNotNull(dataModel);
        }

        @Test
        void getStudentEmail() {
            //Arrange
            String studentEmail = "1234567";
            String emailDomain = "isep.ipp.pt";

            //Act
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel(studentEmail, emailDomain);

            //Arrange
            assertEquals(studentEmail, dataModel.getStudentMail());
        }

        @Test
        void getEmailDomain() {
            //Arrange
            String studentEmail = "1234567";
            String emailDomain = "isep.ipp.pt";

            //Act
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel(studentEmail, emailDomain);

            //Arrange
            assertEquals(emailDomain, dataModel.getEmailDomain());
        }

        @Test
        void getFullEmail() {
            //Arrange
            String studentEmail = "1234567";
            String emailDomain = "isep.ipp.pt";
            String expectedFullEmail = "1234567@isep.ipp.pt";

            //Act
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel(studentEmail, emailDomain);

            //Arrange
            assertEquals(expectedFullEmail, dataModel.getFullEmail());
        }

        @Test
        void getFullEmailError() {
            //Arrange
            String studentEmail = "1234567";
            String emailDomain = "isep.ipp.pt";
            String expectedFullEmail = "1234568@isep.ipp.pt";

            //Act
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel(studentEmail, emailDomain);

            //Arrange
            assertNotEquals(expectedFullEmail, dataModel.getFullEmail());
        }

    }

