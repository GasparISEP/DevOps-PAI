package PAI.domain.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;

public interface IDegreeTypeFactory {
    DegreeType addNewDegreeType_2(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception;
}