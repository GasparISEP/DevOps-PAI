package PAI.persistence.datamodel;

import PAI.persistence.datamodel.Student.StudentIDDataModel;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "programme_enrolment")
public class ProgrammeEnrolmentDataModel {

    @EmbeddedId
    @Column(name = "programme_enrolment_id")
    private ProgrammeEnrolmentIDDataModel programmeEnrolmentID;

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
        this.accessMethodID = accessMethodIDDM;
        this.date = date;
    }

    public ProgrammeEnrolmentIDDataModel getProgrammeEnrolmentID() {
        return programmeEnrolmentID;
    }

    public AccessMethodIDDataModel getAccessMethodID() {
        return accessMethodID;
    }

    public LocalDate getDate() {
        return date;
    }
}