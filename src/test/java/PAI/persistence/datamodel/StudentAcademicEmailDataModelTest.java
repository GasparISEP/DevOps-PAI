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
            String expectedFullEmail = "1234567@isep.ipp.pt";

            //Arrange
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel(expectedFullEmail);

            //Assert
            assertNotNull(dataModel);
        }

        @Test
        void getFullEmail() {
            //Arrange
            String expectedFullEmail = "1234567@isep.ipp.pt";

            //Act
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel("1234567@isep.ipp.pt");

            //Arrange
            assertEquals(expectedFullEmail, dataModel.getFullEmail());
        }

        @Test
        void getFullEmailError() {
            //Arrange
            String expectedFullEmail = "1234568@isep.ipp.pt";

            //Act
            StudentAcademicEmailDataModel dataModel = new StudentAcademicEmailDataModel("1234567@isep.ipp.pt");

            //Arrange
            assertNotEquals(expectedFullEmail, dataModel.getFullEmail());
        }

    }

