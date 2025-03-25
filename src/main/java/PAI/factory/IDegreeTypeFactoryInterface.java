package PAI.factory;

import PAI.domain.DegreeType;

public interface IDegreeTypeFactoryInterface {
    DegreeType addNewDegreeType (String name, int maxEcts) throws Exception;
}
