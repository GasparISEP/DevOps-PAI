package PAI.persistence.mem.degreeType;

import PAI.domain.degreeType.DegreeType;
import java.util.List;

public interface IDegreeTypeListFactory {
    List<DegreeType> createEmptyList();
    List<DegreeType> createFromExisting(List<DegreeType> existing);
}