package PAI.factory;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeTypeDDD.DegreeType_2;

public class DegreeTypeFactoryImpl_2 implements IDegreeTypeFactoryInterface_2 {

    public DegreeType_2 addNewDegreeType_2(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        return new DegreeType_2(degreeTypeID, name, maxEcts);
    }
}

