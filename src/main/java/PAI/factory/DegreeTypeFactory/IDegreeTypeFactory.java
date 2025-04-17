package PAI.factory.DegreeTypeFactory;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;

public interface IDegreeTypeFactory {
    DegreeType create(Name name, MaxEcts maxEcts) throws Exception;
}