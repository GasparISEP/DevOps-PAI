package PAI.controller;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.repository.accessMethodRepository.AccessMethodRepositoryImpl;

public class US02_ConfigureAccessMethodController {

    private final AccessMethodRepositoryImpl _accessMethodRepository;

    public US02_ConfigureAccessMethodController(AccessMethodRepositoryImpl accessMethodRepository) {

        _accessMethodRepository = accessMethodRepository;

    }

    //Configure Access Method
    public boolean configureAccessMethod (NameWithNumbersAndSpecialChars accessMethodName) {
        if (this._accessMethodRepository == null) {
            return false;
        }
        return _accessMethodRepository.registerAccessMethod(accessMethodName);
    }





}