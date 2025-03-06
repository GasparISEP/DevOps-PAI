package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.DegreeTypeFactory;

import java.util.ArrayList;
import java.util.List;

public class DegreeTypeRepository {
    private List<DegreeType> degreeTypes = new ArrayList<>();
    private DegreeTypeFactory degreeTypeFactory;

    public DegreeTypeRepository(DegreeTypeFactory degreeTypeFactory) {
        this.degreeTypeFactory = degreeTypeFactory;
    }

    public boolean registerDegreeType (String name, int maxEcts) throws Exception{
        DegreeType degreeType = degreeTypeFactory.addNewDegreeType(name, maxEcts);
        if(degreeTypes.equals(degreeType)) {
            return false;
        }
        degreeTypes.add(degreeType);
        return true;

    }



}
