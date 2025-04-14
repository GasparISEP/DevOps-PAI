package PAI.persistence.datamodel;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgrammeIDDataModelTest {

    @Test
    public void testDefaultConstructor() {
        //Arrange+Act
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();

        //Assert
        assertNotNull(dataModel);
    }
}