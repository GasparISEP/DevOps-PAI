package PAI.domain;

public class ProgrammeEnrolmentFactory {
    public ProgrammeEnrolment createProgrammeEnrolment (Student student, AccessMethod accessMethod, Programme programme, String date) throws IllegalArgumentException {
        return new ProgrammeEnrolment(student, accessMethod, programme, date);
    }
}
