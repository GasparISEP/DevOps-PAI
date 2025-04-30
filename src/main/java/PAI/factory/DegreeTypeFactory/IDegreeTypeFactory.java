package PAI.factory.DegreeTypeFactory;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;

public interface IDegreeTypeFactory {

    DegreeType create(Name name, MaxEcts maxEcts) throws Exception;

    DegreeType recreate(DegreeTypeID id, Name name, MaxEcts maxEcts);
}