package PAI.controller;

import PAI.domain.AccessMethod;
import PAI.repository.AccessMethodRepository;

import java.util.Optional;

public class US02_ConfigureAccessMethodController {

    //Argumentos

    private final AccessMethodRepository _accessMethodRepository;

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
        return opt1.isPresent();
    }





}