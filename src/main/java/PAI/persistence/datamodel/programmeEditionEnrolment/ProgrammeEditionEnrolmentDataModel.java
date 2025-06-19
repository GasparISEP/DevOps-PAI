package PAI.persistence.datamodel.programmeEditionEnrolment;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "PROGRAMME_EDITION_ENROLMENTS")
public class ProgrammeEditionEnrolmentDataModel implements Serializable {

    @EmbeddedId
    private ProgrammeEditionEnrolmentIDDataModel id;

    @Column(name = "enrolment_date", nullable = false)
    private LocalDate _enrolmentDate;

    @Column (name= "status", nullable = false)
    private boolean isActive;

    @Version
    private Long version;

    @Column (name= "generated_ID", nullable = false)
    private UUID generatedID;

    public ProgrammeEditionEnrolmentDataModel() {
    }

    public ProgrammeEditionEnrolmentDataModel(ProgrammeEditionEnrolmentIDDataModel id,
                                              LocalDate enrolmentDate, boolean isActive, UUID generatedID) {
        this.id = id;
        this._enrolmentDate = enrolmentDate;
        this.isActive = isActive;
        this.generatedID = generatedID;
    }

    public ProgrammeEditionEnrolmentIDDataModel getProgrammeEditionEnrolmentIDDataModel() {
        return id;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UUID getGeneratedID() {return generatedID;}
}