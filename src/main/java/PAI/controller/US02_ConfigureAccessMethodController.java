package PAI.controller;

import PAI.domain.AccessMethod;
import PAI.repository.AccessMethodRepository;

import java.util.Optional;

public class US02_ConfigureAccessMethodController {

    private final AccessMethodRepository _accessMethodRepository;

    public US02_ConfigureAccessMethodController(AccessMethodRepository accessMethodRepository) {

        _accessMethodRepository = accessMethodRepository;

    }

    //Configure Access Method
    public boolean configureAccessMethod(String accessMethodName) throws Exception {
        if (this._accessMethodRepository == null) {
            return false;
        }
        return _accessMethodRepository.registerAccessMethod(accessMethodName);
    }





}