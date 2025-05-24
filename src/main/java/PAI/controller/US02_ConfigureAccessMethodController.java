package PAI.controller;
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
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(accessMethodName);

        try {
            return accessMethodService.configureAccessMethod(command).isPresent();
        } catch (Exception e) {

            return false;
        }
    }
}