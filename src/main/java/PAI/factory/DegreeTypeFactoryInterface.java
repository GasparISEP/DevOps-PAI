package PAI.factory;

import PAI.domain.DegreeType;

public interface DegreeTypeFactoryInterface {
    DegreeType addNewDegreeType (String name, int maxEcts) throws Exception;
}
