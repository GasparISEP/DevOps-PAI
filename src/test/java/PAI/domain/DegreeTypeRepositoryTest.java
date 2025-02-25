package PAI.domain;

import org.junit.jupiter.api.Test;

class DegreeTypeRepositoryTest {
    @Test
    public void createList() throws Exception {

        //arrange
        DegreeTypeRepository degreeTypeRepository1 = new DegreeTypeRepository();
        //act
        degreeTypeRepository1.registerDegreeType("Master",300);


    }
}