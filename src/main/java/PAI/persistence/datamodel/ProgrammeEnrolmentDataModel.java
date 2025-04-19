package PAI.persistence.datamodel;

import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "programme_enrolment")
public class ProgrammeEnrolmentDataModel {

    @EmbeddedId
    @Column(name = "programme_enrolment_id")
    private ProgrammeEnrolmentIDDataModel programmeEnrolmentID;

    @Embedded
    private ProgrammeIDDataModel programmeID;

    @Embedded
    private StudentIDDataModel studentID;

    @Embedded
    private AccessMethodIDDataModel accessMethodID;

    @Column(name = "enrolment_date")
    private LocalDate date;

    public ProgrammeEnrolmentDataModel() {
    }

    public ProgrammeEnrolmentDataModel(ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDM, ProgrammeIDDataModel programmeIDDM,
                                       StudentIDDataModel studentIDDM, AccessMethodIDDataModel accessMethodIDDM, LocalDate date) {

        if (programmeEnrolmentIDDM == null || studentIDDM == null || programmeIDDM == null || accessMethodIDDM == null || date == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }

        this.programmeEnrolmentID = programmeEnrolmentIDDM;
        this.programmeID = programmeIDDM;
        this.studentID = studentIDDM;
        this.accessMethodID = accessMethodIDDM;
        this.date = date;
    }

    public ProgrammeEnrolmentIDDataModel getProgrammeEnrolmentID() {
        return programmeEnrolmentID;
    }

    public ProgrammeIDDataModel getProgrammeID() {
        return programmeID;
    }

    public StudentIDDataModel getStudentID() {
        return studentID;
    }

    public AccessMethodIDDataModel getAccessMethodID() {
        return accessMethodID;
    }

    public LocalDate getDate() {
        return date;
    }
}