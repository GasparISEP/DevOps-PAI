package PAI.controller;

import PAI.VOs.*;
import PAI.repository.IStudentRepository;

public class US08_IWantToRegisterAStudentInTheSystemController {

    private IStudentRepository _IStudentRepository;

    public US08_IWantToRegisterAStudentInTheSystemController(
            IStudentRepository iStudentRepository) throws Exception {

        if (iStudentRepository == null)
            throw new IllegalArgumentException ("Student repository cannot be null!");

         _IStudentRepository = iStudentRepository;
    }

}