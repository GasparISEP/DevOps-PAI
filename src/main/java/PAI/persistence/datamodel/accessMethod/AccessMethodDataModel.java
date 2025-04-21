package PAI.persistence.datamodel.accessMethod;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "access_method")
public class AccessMethodDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "access_method_id")
    private UUID accessMethodID;

    @Column(name = "name")
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
