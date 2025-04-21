package PAI.persistence.datamodel.accessMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AccessMethodIDDataModel implements Serializable {

    @Column(name = "access_method_id")
    private UUID id;

    protected AccessMethodIDDataModel() {}

    public AccessMethodIDDataModel(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessMethodIDDataModel that = (AccessMethodIDDataModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}