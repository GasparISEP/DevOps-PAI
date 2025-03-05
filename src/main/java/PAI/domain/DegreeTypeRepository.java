package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class DegreeTypeRepository {
    private List<DegreeType> degreeTypes;

    public DegreeTypeRepository() {
        degreeTypes = new ArrayList<>();
    }

    public boolean registerDegreeType (String name, int maxEcts) throws Exception{
        DegreeType degreeType = new DegreeType(name, maxEcts);
        if(degreeTypes.equals(degreeType)) {
            return false;
        }
        degreeTypes.add(degreeType);
        return true;

    }



}
