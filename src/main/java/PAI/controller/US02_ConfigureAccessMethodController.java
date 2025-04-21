package PAI.controller;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.service.accessMethod.IAccessMethodService;
import org.springframework.stereotype.Component;

@Component
public class US02_ConfigureAccessMethodController {

    private final IAccessMethodService accessMethodService;

    public US02_ConfigureAccessMethodController(IAccessMethodService accessMethodService) {
        if(accessMethodService == null) {
            throw new IllegalArgumentException("Access method service cannot be null");
        }
        this.accessMethodService = accessMethodService;
    }

    //Configure Access Method
    public boolean configureAccessMethod (String accessMethodName) {
        if (accessMethodName== null) {
            return false;
        }
        NameWithNumbersAndSpecialChars accessMethodNameVO = new NameWithNumbersAndSpecialChars(accessMethodName);
        return accessMethodService.registerAccessMethod(accessMethodNameVO).isPresent();
    }
}