package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeListTest {
    @Test
    public void createList() throws Exception {

        //arrange
        DegreeTypeList degreeTypeList1 = new DegreeTypeList();
        //act
        degreeTypeList1.registerDegreeType("Master",300);


    }
}