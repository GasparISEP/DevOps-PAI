package PAI.factory.DegreeTypeFactory;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;

public interface IDegreeTypeFactory {
    DegreeType addNewDegreeType_2(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception;
}