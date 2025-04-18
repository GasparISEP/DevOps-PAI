package PAI.controller;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.repository.accessMethodRepository.IRepositoryAccessMethod;
import PAI.service.accessMethod.IAccessMethodService;

public class US02_ConfigureAccessMethodController {

    private final IAccessMethodService accessMethodService;

    public US02_ConfigureAccessMethodController(IAccessMethodService accessMethodService) {
        if(accessMethodService == null) {
            throw new IllegalArgumentException("Access method service cannot be null");
        }
        this.accessMethodService = accessMethodService;
    }

    //Configure Access Method
    public boolean configureAccessMethod (NameWithNumbersAndSpecialChars accessMethodName) {
        if (accessMethodName== null) {
            return false;
        }
        return accessMethodService.registerAccessMethod(accessMethodName).isPresent();
    }
}