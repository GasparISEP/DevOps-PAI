package PAI.factory;

import PAI.domain.DegreeType;
import PAI.domain.DegreeTypeFactory;

public interface DegreeTypeFactoryImpl {
    DegreeType addNewDegreeType (String name, int maxEcts) throws Exception;
}
