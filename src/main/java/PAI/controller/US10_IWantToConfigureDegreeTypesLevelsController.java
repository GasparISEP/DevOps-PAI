package PAI.controller;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.service.degreeType.DegreeTypeService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Component
public class US10_IWantToConfigureDegreeTypesLevelsController {

    private final DegreeTypeService service;

    public US10_IWantToConfigureDegreeTypesLevelsController(DegreeTypeService service) {
        this.service = service;
    }

    public boolean registerDegreeType(Name name, MaxEcts maxEcts) throws Exception {
        return service.registerDegreeType(name, maxEcts);
    }

    public boolean registerDegreeTypeWithUUID(DegreeTypeID id, Name name, MaxEcts maxEcts) throws Exception {
        return service.registerDegreeTypeWithUUID(id,name,maxEcts);
    }


}
