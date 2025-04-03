package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;

import java.util.Objects;

public class ProgrammeEnrolment implements AggregateRoot<ProgrammeEnrolmentID> {

    private ProgrammeEnrolmentID _peID;
    private StudentID _studentID;
    private AccessMethodID _accessMethodID;
    private ProgrammeID _programmeID;
    private Date _date;

    public ProgrammeEnrolment(StudentID studentID, AccessMethodID accessMethodID, ProgrammeID programmeID, Date date) throws IllegalArgumentException {

        if (studentID == null || accessMethodID == null || programmeID == null){
            throw new IllegalArgumentException ("Argument cannot be null.");
        }

        if(date == null)
            throw new IllegalArgumentException("Date cannot be null!");

        _studentID = studentID;
        _accessMethodID = accessMethodID;
        _programmeID = programmeID;
        _date = date;
        _peID = new ProgrammeEnrolmentID();
    }

    public boolean isDateAfter(Date date) {
        return _date.getLocalDate().isAfter(date.getLocalDate());
    }

    public boolean hasSameStudent(StudentID studentID){return this._studentID.equals(studentID);}

    public boolean hasSameEnrolment(ProgrammeEnrolment programmeEnrolment){
        return this._studentID.equals(programmeEnrolment._studentID) &&
                this._programmeID.equals(programmeEnrolment._programmeID);
    }

    public boolean hasSameProgramme(ProgrammeID programmeID){return programmeID.equals(_programmeID);}

    public StudentID getStudentID() {return _studentID;}

    public AccessMethodID getAccessMethodID() {return _accessMethodID;}

    public ProgrammeID getProgrammeID() {return _programmeID;}

    public Date getDate() {return _date;}

    @Override
    public ProgrammeEnrolmentID identity() {return _peID;}

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;

        if (!(other instanceof ProgrammeEnrolment)) return false;

        return _peID.equals(((ProgrammeEnrolment) other)._peID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_peID);
    }
}


