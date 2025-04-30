package PAI.persistence.datamodel;

import PAI.VOs.EnrolmentStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Programme_edition_enrolments")
public class ProgrammeEditionEnrolmentDataModel implements Serializable {

    @EmbeddedId
    private ProgrammeEditionEnrolmentIDDataModel _id;

    @Column(name = "enrolment_date", nullable = false)
    private LocalDate _enrolmentDate;

    @Column (name= "status", nullable = false)
    private boolean isActive;

    @Version
    private Long version;

    public ProgrammeEditionEnrolmentDataModel() {
    }

    public ProgrammeEditionEnrolmentDataModel(ProgrammeEditionEnrolmentIDDataModel id,
                                              LocalDate enrolmentDate, boolean isActive) {
        this._id = id;
        this._enrolmentDate = enrolmentDate;
        this.isActive = isActive;
    }

    public ProgrammeEditionEnrolmentIDDataModel getProgrammeEditionEnrolmentIDDataModel() {
        return _id;
    }

    public LocalDate getEnrolmentDate() {
        return _enrolmentDate;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolmentDataModel that = (ProgrammeEditionEnrolmentDataModel) o;
        return Objects.equals(_id, that._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

}
