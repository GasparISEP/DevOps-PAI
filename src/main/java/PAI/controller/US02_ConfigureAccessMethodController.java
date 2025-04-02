package PAI.controller;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.repository.accessMethodRepositoryDDD.AccessMethodDDDRepositoryImpl;

public class US02_ConfigureAccessMethodController {

    private final AccessMethodDDDRepositoryImpl _accessMethodRepository;

    public US02_ConfigureAccessMethodController(AccessMethodDDDRepositoryImpl accessMethodRepository) {

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