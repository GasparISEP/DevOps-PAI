package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeRepositoryTest {

    // Test to add a valid object
    @Test
    void createDegreeTypeRepository () throws Exception {

        //arrange

        //act + assert
        DegreeTypeRepository degreeType1 = new DegreeTypeRepository();
    }

    // Add DegreeType
    @Test
    void addDegreeTypeToRepository() throws Exception {


        DegreeTypeRepository repository = new DegreeTypeRepository();


        DegreeType Bachelor = new DegreeType("Bachelor",20);


        repository.addDegreeType(Bachelor);


        List<DegreeType> degreeTypeList = repository.getDegreeTypeList();
        assertTrue(degreeTypeList.contains(Bachelor));

    }

}