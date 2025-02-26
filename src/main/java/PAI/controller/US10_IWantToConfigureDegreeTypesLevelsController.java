package PAI.controller;

import PAI.domain.DegreeTypeRepository;


public class US10_IWantToConfigureDegreeTypesLevelsController {

    private DegreeTypeRepository _degreeTypeRepository;

    public US10_IWantToConfigureDegreeTypesLevelsController(DegreeTypeRepository degreeTypeRepository) throws Exception {

        if (degreeTypeRepository == null) {
            throw new Exception("DegreeType Repository cannot be null");
        }
        _degreeTypeRepository = degreeTypeRepository;
    }

    public boolean registerDegreeType(String name, int maxEcts) throws Exception {

        return _degreeTypeRepository.registerDegreeType(name, maxEcts);
    }
}
