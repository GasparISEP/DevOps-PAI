package PAI.controller;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.repository.DegreeTypeRepoDDD.DegreeTypeRepository_2;

public class US10_IWantToConfigureDegreeTypesLevelsController {

    private final DegreeTypeRepository_2 degreeTypeRepository;

    public US10_IWantToConfigureDegreeTypesLevelsController(DegreeTypeRepository_2 degreeTypeRepository) {
        this.degreeTypeRepository = degreeTypeRepository;
    }

    public boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        return degreeTypeRepository.registerDegreeType(degreeTypeID, name, maxEcts);
    }
}
