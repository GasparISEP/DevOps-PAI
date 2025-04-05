package PAI.controller;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.repository.DegreeTypeRepository.DegreeTypeRepository;

public class US10_IWantToConfigureDegreeTypesLevelsController {

    private final DegreeTypeRepository degreeTypeRepository;

    public US10_IWantToConfigureDegreeTypesLevelsController(DegreeTypeRepository degreeTypeRepository) {
        this.degreeTypeRepository = degreeTypeRepository;
    }

    public boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        return degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts);
    }
}
