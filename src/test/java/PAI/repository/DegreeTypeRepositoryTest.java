package PAI.repository;

import PAI.factory.DegreeTypeFactory;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;


class DegreeTypeRepositoryTest {
    @Test
    public void createList() throws Exception {

        //arrange

        DegreeTypeFactory degreeTypeFactory = mock (DegreeTypeFactory.class);
        DegreeTypeRepository degreeTypeRepository = new DegreeTypeRepository(degreeTypeFactory);

        //act
        degreeTypeRepository.registerDegreeType("Master",300);


    }
}