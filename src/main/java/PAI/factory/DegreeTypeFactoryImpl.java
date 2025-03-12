package PAI.factory;

import PAI.domain.DegreeType;

public class DegreeTypeFactoryImpl implements DegreeTypeFactoryInterface {

    public DegreeType addNewDegreeType (String name, int maxEcts) throws Exception{
        return new DegreeType (name, maxEcts);
                }
}


