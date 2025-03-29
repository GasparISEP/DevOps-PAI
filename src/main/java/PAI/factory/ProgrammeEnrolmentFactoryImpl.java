package PAI.factory;

import PAI.VOs.Date;
import PAI.domain.AccessMethod;
import PAI.domain.Programme;
import PAI.domain.ProgrammeEnrolment;
import PAI.domain.Student;

public class ProgrammeEnrolmentFactoryImpl implements IProgrammeEnrolmentFactory {

    public ProgrammeEnrolment createProgrammeEnrolment (Student student, AccessMethod accessMethod, Programme programme, Date date) throws IllegalArgumentException {

        return new ProgrammeEnrolment(student, accessMethod, programme, date);
    }
}
