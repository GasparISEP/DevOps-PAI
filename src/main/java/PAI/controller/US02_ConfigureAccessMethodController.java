package PAI.controller;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.repository.accessMethodRepositoryDDD.AccessMethodDDDRepository;

import java.util.Optional;

public class US02_ConfigureAccessMethodController {

    private final AccessMethodDDDRepository _accessMethodRepository;

    public US02_ConfigureAccessMethodController(AccessMethodDDDRepository accessMethodRepository) {

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