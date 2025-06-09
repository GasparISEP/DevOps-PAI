package PAI.persistence.datamodel.programmeEdition;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ProgrammeEditionGeneratedIDDataModel implements Serializable {
    @Column(name = "generated_id", nullable = false, unique = true)
    private UUID generatedId;

    public ProgrammeEditionGeneratedIDDataModel() {
    }

    public ProgrammeEditionGeneratedIDDataModel(UUID generatedId) {
        if (generatedId == null) {
            throw new IllegalArgumentException("Generated ID cannot be null");
        }
        this.generatedId = generatedId;
    }

    public UUID getGeneratedId() {
        return generatedId;
    }

    public void setGeneratedId(UUID generatedId) {
        this.generatedId = generatedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammeEditionGeneratedIDDataModel)) return false;
        ProgrammeEditionGeneratedIDDataModel that = (ProgrammeEditionGeneratedIDDataModel) o;
        return generatedId.equals(that.generatedId);
    }

    @Override
    public int hashCode() {
        return generatedId.hashCode();
    }
}
