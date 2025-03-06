package PAI.domain;

import PAI.factory.DegreeTypeFactoryImpl;

public class DegreeTypeFactory implements DegreeTypeFactoryImpl {

    public DegreeType addNewDegreeType (String name, int maxEcts) throws Exception{
        return new DegreeType (name, maxEcts);
                }
}


