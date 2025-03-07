package PAI.factory;

import PAI.domain.DegreeType;

public interface DegreeTypeFactoryImpl {
    DegreeType addNewDegreeType (String name, int maxEcts) throws Exception;
}
