package PAI.factory.DegreeTypeFactory;

import PAI.domain.degreeType.DegreeType;
import java.util.List;

public interface IDegreeTypeListFactory {
    List<DegreeType> createEmptyList();
    List<DegreeType> createFromExisting(List<DegreeType> existing);
}