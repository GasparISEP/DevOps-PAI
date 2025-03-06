package PAI.factory;

import PAI.domain.DegreeType;

public class DegreeTypeFactory implements DegreeTypeFactoryImpl {

    public DegreeType addNewDegreeType (String name, int maxEcts) throws Exception{
        return new DegreeType (name, maxEcts);
                }
}


