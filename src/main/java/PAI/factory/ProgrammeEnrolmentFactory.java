package PAI.factory;

import PAI.domain.AccessMethod;
import PAI.domain.Programme;
import PAI.domain.ProgrammeEnrolment;
import PAI.domain.Student;

public interface ProgrammeEnrolmentFactory {

    ProgrammeEnrolment createProgrammeEnrolment (Student student, AccessMethod accessMethod, Programme programme, String date);
}
