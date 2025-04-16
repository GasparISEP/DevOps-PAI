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

    @Test
    public void testConstructor() {
        //arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Ola");
        Acronym acronym = new Acronym("OLA");
        ProgrammeID progID = new ProgrammeID(name, acronym);

        //act
        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel(progID);

        //assert
        assertNotNull(dataModel);
    }

    @Test
    public void testGetName() {
        //arrange

        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();

        //act
        String res = dataModel.getName();

        //assert
        assertEquals(res,dataModel.getName());
    }

    @Test
    public void testGetAcronym() {
        //arrange

        ProgrammeIDDataModel dataModel = new ProgrammeIDDataModel();

        //act
        String res = dataModel.getAcronym();

        //assert
        assertEquals(res,dataModel.getAcronym());
    }
}