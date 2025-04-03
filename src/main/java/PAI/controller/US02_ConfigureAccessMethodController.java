package PAI.controller;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.repository.accessMethodRepository.AccessMethodRepositoryImpl;
import PAI.repository.accessMethodRepository.IRepositoryAccessMethod;

public class US02_ConfigureAccessMethodController {

    private final IRepositoryAccessMethod _accessMethodRepository;

    public US02_ConfigureAccessMethodController(IRepositoryAccessMethod accessMethodRepository) {

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