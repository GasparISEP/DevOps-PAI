package PAI.factory;

import PAI.domain.AccessMethod;
import PAI.domain.Programme;
import PAI.domain.ProgrammeEnrolment;
import PAI.domain.Student;

public class ProgrammeEnrolmentFactory implements ProgrammeEnrolmentFactoryInterface {
    public ProgrammeEnrolment createProgrammeEnrolment (Student student, AccessMethod accessMethod, Programme programme, String date) {
        if (!isFactoryArgumentValid(student) || !isFactoryArgumentValid(accessMethod) || !isFactoryArgumentValid(programme) || !isFactoryArgumentValid(date)) {
            throw new IllegalArgumentException("Argument cannot be null or blank");
        }
        return new ProgrammeEnrolment(student, accessMethod, programme, date);
    }

    private boolean isFactoryArgumentValid(Object argument) {
        if (argument == null) {
            return false;
        }
        if (argument instanceof String) {
            if(((String) argument).isBlank()) {
                return false;
            }
        }
        return true;
    }
}
