package PAI.controller;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.repository.degreeTypeRepository.DegreeTypeRepositoryImpl;

public class US10_IWantToConfigureDegreeTypesLevelsController {

    private final DegreeTypeRepositoryImpl degreeTypeRepositoryImpl;

    public US10_IWantToConfigureDegreeTypesLevelsController(DegreeTypeRepositoryImpl degreeTypeRepositoryImpl) {
        this.degreeTypeRepositoryImpl = degreeTypeRepositoryImpl;
    }

    public boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        return degreeTypeRepositoryImpl.registerDegreeType(degreeTypeID, name, maxEcts);
    }
}
