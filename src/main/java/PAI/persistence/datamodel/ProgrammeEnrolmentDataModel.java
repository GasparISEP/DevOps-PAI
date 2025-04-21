package PAI.persistence.datamodel;

import PAI.domain.ProgrammeEnrolment;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "programme_enrolment")
public class ProgrammeEnrolmentDataModel {

    @EmbeddedId
    @Column(name = "programme_enrolment_id")
    private ProgrammeEnrolmentIDDataModel _programmeEnrolmentID;

    @Embedded
    private ProgrammeIDDataModel _programmeID;

    @Embedded
    private StudentIDDataModel _studentID;

    @Embedded
    private AccessMethodIDDataModel _accessMethodID;

    @Column(name = "enrolment_date")
    private LocalDate _date;

    public ProgrammeEnrolmentDataModel() {
    }

    public ProgrammeEnrolmentDataModel(ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDM, ProgrammeIDDataModel programmeIDDM,
                                       StudentIDDataModel studentIDDM, AccessMethodIDDataModel accessMethodIDDM, LocalDate date) {

        if (programmeEnrolmentIDDM == null || studentIDDM == null || programmeIDDM == null || accessMethodIDDM == null || date == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }

        this._programmeEnrolmentID = programmeEnrolmentIDDM;
        this._programmeID = programmeIDDM;
        this._studentID = studentIDDM;
        this._accessMethodID = accessMethodIDDM;
        this._date = date;
    }

    public ProgrammeEnrolmentIDDataModel getProgrammeEnrolmentID() {
        return _programmeEnrolmentID;
    }

    public ProgrammeIDDataModel getProgrammeID() {
        return _programmeID;
    }

    public StudentIDDataModel getStudentID() {
        return _studentID;
    }

    public AccessMethodIDDataModel getAccessMethodID() {
        return _accessMethodID;
    }

    public LocalDate getDate() {
        return _date;
    }
}