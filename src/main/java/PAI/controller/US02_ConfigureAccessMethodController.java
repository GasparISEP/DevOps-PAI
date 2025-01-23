package PAI.controller;

import PAI.domain.AccessMethod;
import PAI.domain.AccessMethodRepository;

import java.util.Optional;

public class US02_ConfigureAccessMethodController {

    //Argumentos

    private AccessMethodRepository _accessMethodRepository;

    //Construtor, injecta dependêcias neste caso é o repositório.


    public US02_ConfigureAccessMethodController(AccessMethodRepository accessMethodRepository) {

        _accessMethodRepository = accessMethodRepository;

    }
    //Configure Access Method

    public boolean configureAccessMethod(String accessMethodName) {
        if (this._accessMethodRepository == null) {
            return false;
        }
        Optional<AccessMethod> opt1 = _accessMethodRepository.createAccessMethod(accessMethodName);
        if (opt1.isPresent()) {
            return true;
        }
        else {
            return false;
        }
    }





}