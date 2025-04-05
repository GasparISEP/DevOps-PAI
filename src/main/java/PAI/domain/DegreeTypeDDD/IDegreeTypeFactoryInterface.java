package PAI.domain.DegreeTypeDDD;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;

public interface IDegreeTypeFactoryInterface {
    DegreeType addNewDegreeType_2(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception;
}
