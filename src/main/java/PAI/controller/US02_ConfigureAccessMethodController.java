package PAI.controller;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
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

        try {
            NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(accessMethodName);
            RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);
            AccessMethodServiceDTO dto = accessMethodService.configureAccessMethod(command);
            return dto != null;

        } catch (Exception e) {

            return false;
        }
    }
}