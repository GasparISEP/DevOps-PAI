package PAI.persistence.datamodel;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "access_method")
public class AccessMethodDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID accessMethodID;
    private String name;

    public AccessMethodDataModel(UUID accessMethodID, String name) {
        if(accessMethodID == null) {
            throw new IllegalArgumentException("id can't be null");
        }
        if(name == null) {
            throw new IllegalArgumentException("name can't be null");
        }
        this.accessMethodID = accessMethodID;
        this.name = name;
    }

    protected AccessMethodDataModel() {
    }

    public UUID getAccessMethodID() {
        return accessMethodID;
    }

    public String getName() {
        return name;
    }
}
