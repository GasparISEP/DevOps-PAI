package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class DegreeTypeList {
    private List<DegreeType> degreeTypes;

    public DegreeTypeList () {
        degreeTypes = new ArrayList<>();
    }

    public void registerDegreeType (String name, int maxEcts) throws Exception{
        DegreeType degreeType = new DegreeType(name,maxEcts);
        degreeTypes.add(degreeType);

    }



}
