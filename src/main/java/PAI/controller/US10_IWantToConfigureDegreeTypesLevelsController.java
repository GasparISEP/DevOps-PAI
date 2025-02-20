package PAI.controller;

import PAI.domain.DegreeTypeList;


public class US10_IWantToConfigureDegreeTypesLevelsController {

    private DegreeTypeList _degreeTypeList;

    public US10_IWantToConfigureDegreeTypesLevelsController(DegreeTypeList degreeTypeList) {
        _degreeTypeList = degreeTypeList;
    }

    public boolean registerDegreeType(String name, int maxEcts) throws Exception {

        return _degreeTypeList.registerDegreeType(name, maxEcts);
    }
}
