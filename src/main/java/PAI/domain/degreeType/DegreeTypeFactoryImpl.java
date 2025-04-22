package PAI.domain.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;

public class DegreeTypeFactoryImpl implements IDegreeTypeFactory {

    public DegreeType addNewDegreeType_2(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        return new DegreeType(degreeTypeID, name, maxEcts);
    }
}