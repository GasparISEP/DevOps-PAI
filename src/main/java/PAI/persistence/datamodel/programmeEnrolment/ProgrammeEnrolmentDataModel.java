package PAI.persistence.datamodel.programmeEnrolment;

import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

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

    @Column(name = "programme_enrolment_gid", nullable = false, unique = true)
    private UUID programmeEnrolmentGID;

    public ProgrammeEnrolmentDataModel() {
    }

    public ProgrammeEnrolmentDataModel(ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDM, ProgrammeIDDataModel programmeIDDM,
                                       StudentIDDataModel studentIDDM, AccessMethodIDDataModel accessMethodIDDM, LocalDate date, UUID programmeEnrolmentGID) {

        if (programmeEnrolmentIDDM == null || studentIDDM == null || programmeIDDM == null || accessMethodIDDM == null || date == null || programmeEnrolmentGID == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }

        this.programmeEnrolmentID = programmeEnrolmentIDDM;
        this.accessMethodID = accessMethodIDDM;
        this.date = date;
        this.programmeEnrolmentGID = programmeEnrolmentGID;
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

    public UUID getProgrammeEnrolmentGID() {
        return programmeEnrolmentGID;
    }
}